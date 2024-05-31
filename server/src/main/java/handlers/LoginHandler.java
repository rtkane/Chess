package handlers;

import com.google.gson.Gson;
import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.UserDAOIM;
import requests.LoginRequest;
import results.LoginResult;
import service.LoginService;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginHandler implements Route {
    private  LoginService loginService = new LoginService(UserDAOIM.getInstance(), AuthDAOIM.getInstance());


    private final Gson gson = new Gson();

    @Override
    public Object handle(Request request, Response response) throws Exception {
        // Parse request body
        LoginRequest loginRequest;
        try {
            String requestBody = request.body();
            if (requestBody == null || requestBody.isEmpty()) {
                response.status(400);
                return gson.toJson(new ErrorResponse("Error: Request body is empty"));
            }
            loginRequest = gson.fromJson(requestBody, LoginRequest.class);
        } catch (Exception e) {
            response.status(400);
            return gson.toJson(new ErrorResponse("Error: Invalid request body"));
        }

        // Perform login
        try {
            LoginResult loginResult = loginService.login(loginRequest);
            if (loginResult.getSuccess()) {
                response.status(200);
            } else {
                response.status(401);
                return gson.toJson(new ErrorResponse("Error: unauthorized"));
            }
            return gson.toJson(loginResult);
        } catch (DataAccessException e) {
            response.status(500);
            return gson.toJson(new ErrorResponse("Error: " + e.getMessage()));
        }
    }

    private static class ErrorResponse {
        String message;

        ErrorResponse(String message) {
            this.message = message;
        }
    }
}
