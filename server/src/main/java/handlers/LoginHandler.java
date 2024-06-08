package handlers;

import com.google.gson.Gson;
import dataaccess.*;
import requests.LoginRequest;
import results.LoginResult;
import service.LoginService;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginHandler implements Route {
    private SQLAuthDAO authDAO = new SQLAuthDAO();
    private SQLUserDAO userDAO = new SQLUserDAO();


    private  LoginService loginService = new LoginService(userDAO, authDAO);
    private final Gson gson = new Gson();

    @Override
    public Object handle(Request request, Response response) throws Exception {
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
            response.status(401);
            return gson.toJson(new ErrorResponse("Error: unauthorized"));
        }
    }

}
