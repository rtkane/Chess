package handlers;

import com.google.gson.Gson;
import dataaccess.AuthDAOIM;
import dataaccess.DataAccessException;
import dataaccess.UserDAOIM;
import requests.RegisterRequest;
import results.RegisterResult;
import service.RegisterService;
import spark.Request;
import spark.Response;
import spark.Route;

public class RegisterHandler implements Route {

    private RegisterService registerService ;
    private Gson gson = new Gson();

    public RegisterHandler(){
        this.registerService = new RegisterService(UserDAOIM.getInstance(), AuthDAOIM.getInstance());
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
            } else if ( registerResult.getMessage() == "Fill in all fields"){

                response.status(400);
                return gson.toJson(new ErrorResponse("Error: bad request"));
            }
            else {
                response.status(403);
                return gson.toJson(new ErrorResponse("Error: already taken"));

            }
        } catch (DataAccessException e) {
            response.status(500);
            return gson.toJson(new ErrorResponse("Error: " + e.getMessage()));
        }

        return gson.toJson(registerResult);
    }



    // Helper class for error response
    private static class ErrorResponse {
        String message;

        ErrorResponse(String message) {
            this.message = message;
        }
    }
}
