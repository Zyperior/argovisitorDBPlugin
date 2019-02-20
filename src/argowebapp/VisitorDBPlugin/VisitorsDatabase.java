package argowebapp.VisitorDBPlugin;

import argowebapp.VisitorDBPlugin.DataBaseHandlers.Database;
import argowebapp.VisitorDBPlugin.DataBaseHandlers.SQLiteHandler;
import http.server.filehandling.KnownFileTypes;
import http.server.response.HttpResponse;
import http.server.serviceloader.ARGOPlugin;
import http.server.serviceloader.PluginType;

import java.util.List;
import java.util.Map;

@PluginType("/v1/VisitorDB")
public class VisitorsDatabase implements ARGOPlugin {

    Database myDataBase = new SQLiteHandler();

    public HttpResponse doSomething(String doThis, Map<String, List<String>> params) {
        System.out.println("DB-plugin loaded");
        System.out.println("Params:");

        myDataBase.connect();
        if(params.containsKey("insert")){
            myDataBase.insertVisitor(params.get("name").get(0),params.get("comment").get(0));
        }
        if(params.containsKey("getAll")){
            return new HttpResponse(null,myDataBase.getAllVisitors(),false, KnownFileTypes.JSON,"HTTP/1.1 200 OK");
        }
        return new HttpResponse(null,null,true,null,"HTTP/1.1 303 See Other","Location: ../index.html");
        //createNewTable();
    }
}
