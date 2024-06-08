package handlers;

import com.google.gson.Gson;
import dataaccess.*;
import requests.ListGameRequest;
import results.ListGameResult;
import service.ListGameService;
import spark.Request;
import spark.Response;
import spark.Route;

public class ListGameHandler implements Route {
    SQLAuthDAO authDAO = new SQLAuthDAO();
    SQLGameDAO gameDAO = new SQLGameDAO();

    private final ListGameService listGameService = new ListGameService(authDAO, gameDAO);

    private final Gson gson = new Gson();
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String authToken = request.headers("authorization");

        if (authToken == null || authToken.isEmpty()) {
            response.status(401);
            return gson.toJson(new ErrorResponse("Error: unauthorized"));
        }

        ListGameRequest listGameRequest = new ListGameRequest(authToken);

        try {
            ListGameResult listGameResult = listGameService.listGame(listGameRequest);
            if (listGameResult.getSuccess()){
                response.status(200);
            }
            else {
                response.status(401);
                return gson.toJson(new ErrorResponse("Error: unauthorized"));
            }
            return gson.toJson(listGameResult);
        } catch (DataAccessException e) {
            response.status(500);
            return gson.toJson(new ErrorResponse("Error: unauthorized"));
        }

    }

}
