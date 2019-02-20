package argowebapp.VisitorDBPlugin.DataBaseHandlers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

class SQLiteJsonResponseBody {

    private List<Visitor> visitorList;

    SQLiteJsonResponseBody(List<Visitor> visitorList){
        this.visitorList = visitorList;
    }


    byte[] getBodyData() {

        byte[] bytes;
        Gson gson = new Gson();
        String jsonString = gson.toJson(visitorList);

        bytes = jsonString.getBytes();
        return bytes;
    }
}
