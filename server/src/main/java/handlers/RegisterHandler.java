package handlers;

import com.google.gson.Gson;
import dataaccess.SQLAuthDAO;
import dataaccess.SQLUserDAO;
import dataaccess.DataAccessException;
import requests.RegisterRequest;
import results.RegisterResult;
import service.RegisterService;
import spark.Request;
import spark.Response;
import spark.Route;

public class RegisterHandler implements Route {

    private RegisterService registerService;
    private Gson gson = new Gson();

    public RegisterHandler() {
        SQLUserDAO userDAO = new SQLUserDAO();
        SQLAuthDAO authDAO = new SQLAuthDAO();
        this.registerService = new RegisterService(userDAO, authDAO);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        RegisterRequest registerRequest;
        try {
            registerRequest = gson.fromJson(request.body(), RegisterRequest.class);
        } catch (Exception e) {
            response.status(400);
            return gson.toJson(new ErrorResponse("Error: bad request"));
        }

        RegisterResult registerResult;
        try {
            registerResult = registerService.register(registerRequest);
            if (registerResult.getSuccess()) {
                response.status(200);
            } else if (registerResult.getMessage().equals("Fill in all fields")) {
                response.status(400);
                return gson.toJson(new ErrorResponse("Error: bad request"));
            } else {
                response.status(403);
                return gson.toJson(new ErrorResponse("Error: already taken"));
            }
        } catch (DataAccessException e) {
            response.status(500);
            return gson.toJson(new ErrorResponse("Error: " + e.getMessage()));
        }

        return gson.toJson(registerResult);
    }

    private static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
