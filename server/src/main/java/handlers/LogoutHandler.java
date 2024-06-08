package handlers;

import com.google.gson.Gson;
import dataaccess.*;
import requests.LogoutRequest;
import results.LogoutResult;
import service.LogoutService;
import spark.Request;
import spark.Response;
import spark.Route;

public class LogoutHandler implements Route {
    private SQLAuthDAO authDAO = new SQLAuthDAO();
    private SQLUserDAO userDAO = new SQLUserDAO();

    private LogoutService logoutService = new LogoutService(userDAO, authDAO);
    private final Gson gson = new Gson();


    @Override
    public Object handle(Request request, Response response) throws Exception {

        String authToken  = request.headers("authorization");

        LogoutRequest logoutRequest = new LogoutRequest(authToken);


        try {
            LogoutResult logoutResult = logoutService.logout(logoutRequest);
            if (logoutResult.getSuccess()) {
                response.status(200);
            } else {
                response.status(401);
                return gson.toJson(new ErrorResponse("Error: unauthorized"));
            }
            return gson.toJson(logoutResult);
        } catch (DataAccessException e) {
            response.status(401);
            return gson.toJson(new ErrorResponse("Error: unauthorized"));
        }
    }

}
