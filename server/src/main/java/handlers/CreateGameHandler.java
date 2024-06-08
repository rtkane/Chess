package handlers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dataaccess.*;
import requests.CreateGameRequest;
import results.CreateGameResult;
import service.CreateGameService;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateGameHandler implements Route {
    SQLAuthDAO authDAO = new SQLAuthDAO();
    SQLGameDAO gameDAO = new SQLGameDAO();

    private final CreateGameService createGameService = new CreateGameService(authDAO, gameDAO);
    private final Gson gson = new Gson();

    @Override
    public Object handle(Request request, Response response) throws Exception {
        CreateGameRequest createGameRequest;
        String authToken = request.headers("authorization");

        if (authToken == null || authToken.isEmpty()) {
            response.status(401);
            return gson.toJson(new ErrorResponse("Error: unauthorized"));
        }

        try {
            createGameRequest = gson.fromJson(request.body(), CreateGameRequest.class);
            createGameRequest.setAuthToken(authToken);
        } catch (JsonSyntaxException e) {
            response.status(400);
            return gson.toJson(new ErrorResponse("Error: bad request"));
        }

        try {
            CreateGameResult createGameResult = createGameService.createGame(createGameRequest);
            if (createGameResult.getSuccess()) {
                response.status(200);
                return gson.toJson(createGameResult);
            } else if (createGameResult.getSuccess() == false && createGameResult.getMessage() == "Error: AuthToken not found") {
                response.status(401);
                return gson.toJson(new ErrorResponse("Error: unauthorized"));
            } else {
                response.status(401);
                return gson.toJson(new ErrorResponse("Error: unauthorized"));
            }
        } catch (DataAccessException e) {
            response.status(401);
            return gson.toJson(new ErrorResponse("Error: " + e.getMessage()));
        }
    }


}
