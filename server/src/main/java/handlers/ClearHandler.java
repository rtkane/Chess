package handlers;

import com.google.gson.Gson;
import dataaccess.*;
import results.ClearResult;
import service.ClearService;
import service.RegisterService;
import spark.Request;
import spark.Response;
import spark.Route;

public class ClearHandler implements Route {
    private ClearService clearService;
    private Gson gson = new Gson();

    public ClearHandler(){
        SQLUserDAO userDAO = new SQLUserDAO();
        SQLAuthDAO authDAO = new SQLAuthDAO();
        this.clearService = new ClearService(userDAO, authDAO, GameDataDAOIM.getInstance());    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        ClearResult clearResult = clearService.clear();

        if (clearResult.getSuccess()) {
            response.status(200);
        } else {
            response.status(500);
        }
        return gson.toJson(clearResult);
    }
}
