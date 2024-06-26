package handlers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dataaccess.*;
import requests.JoinGameRequest;
import results.JoinGameResult;
import service.JoinGameService;
import spark.Request;
import spark.Response;
import spark.Route;

public class JoinGameHandler implements Route {
    SQLAuthDAO authDAO = new SQLAuthDAO();
    SQLGameDAO gameDAO = new SQLGameDAO();

    private final JoinGameService joinGameService = new JoinGameService(authDAO, gameDAO);
    private final Gson gson = new Gson();

    @Override
    public Object handle(Request request, Response response) throws Exception {
        JoinGameRequest joinGameRequest;
        String authToken = request.headers("authorization");
        System.out.println("Check 1");

        if (authToken == null || authToken.isEmpty()) {
            response.status(401);
            return gson.toJson(new ErrorResponse("Unauthorized: Missing or empty authorization token"));
        }
        System.out.println("Check 2");

        try {
            joinGameRequest = gson.fromJson(request.body(), JoinGameRequest.class);
            joinGameRequest.setAuthToken(authToken);
        } catch (JsonSyntaxException e) {
            response.status(400);
            return gson.toJson(new ErrorResponse("Bad Request: Invalid JSON syntax"));
        }
        System.out.println("Check 3");
        try {
            System.out.println("in try");
            JoinGameResult joinGameResult = joinGameService.joinGame(joinGameRequest);
            System.out.println("Success: " + joinGameResult.getSuccess());
            if (joinGameResult.getSuccess()) {
                response.status(200);
            } else if (joinGameResult.getSuccess() == false && joinGameResult.getMessage() == "Error: unauthorized") {
                response.status(401);

            }
            else if (joinGameResult.getSuccess() == false && joinGameResult.getMessage() == "Error: team color already taken") {
                response.status(403);
            }
            else {
                response.status(400);
            }
            return gson.toJson(joinGameResult);
        } catch (DataAccessException e) {
            response.status(400);
            return gson.toJson(new ErrorResponse("Internal Server Error: " + e.getMessage()));
        }
    }


}
