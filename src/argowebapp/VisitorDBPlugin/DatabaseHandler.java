package argowebapp.VisitorDBPlugin;

import argowebapp.VisitorDBPlugin.DataBaseHandlers.Database;
import com.google.inject.Inject;
import http.server.filehandling.KnownFileTypes;
import http.server.response.HttpResponse;
import http.server.response.StandardResponseHeader;

import java.util.List;
import java.util.Map;


public class DatabaseHandler {

    Database database;

    @Inject
    public DatabaseHandler(Database database){
        this.database=database;
    }

    public HttpResponse execute(Map<String, List<String>> params){
        database.connect();
        if(params.containsKey("insert")){
            database.insertVisitor(params.get("name").get(0),params.get("comment").get(0));
        }
        if(params.containsKey("getAll")){
            byte[] visitors = database.getAllVisitors();
            database.disconnect();
            return new HttpResponse(visitors, KnownFileTypes.JSON, StandardResponseHeader.OK_200);
        }

        database.disconnect();
        return new HttpResponse("HTTP/1.1 301 See Other","Location: ../Visitors.html");
    }
}
