package handlers;

import com.google.gson.Gson;
import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.GameDataDAOIM;
import requests.ListGameRequest;
import results.ListGameResult;
import service.ListGameService;
import spark.Request;
import spark.Response;
import spark.Route;

public class ListGameHandler implements Route {
    private final ListGameService listGameService = new ListGameService(AuthDAOIM.getInstance(), GameDataDAOIM.getInstance());

    private final Gson gson = new Gson();
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String authToken = request.headers("authorization");

        if (authToken == null || authToken.isEmpty()) {
            response.status(401);
            return gson.toJson(new ListGameHandler.ErrorResponse("Error: unauthorized"));
        }

        ListGameRequest listGameRequest = new ListGameRequest(authToken);

        try {
            ListGameResult listGameResult = listGameService.listGame(listGameRequest);
            if (listGameResult.getSuccess()){
                response.status(200);
            }
            else {
                response.status(401);
                return gson.toJson(new ListGameHandler.ErrorResponse("Error: unauthorized"));
            }
            return gson.toJson(listGameResult);
        } catch (DataAccessException e) {
            response.status(401);
            return gson.toJson(new ListGameHandler.ErrorResponse("Error: unauthorized"));
        }

    }


    private static class ErrorResponse {
        String message;

        ErrorResponse(String message) {
            this.message = message;
        }
    }
}
