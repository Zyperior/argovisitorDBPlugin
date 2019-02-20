package argowebapp.VisitorDBPlugin;

import com.google.inject.Guice;
import com.google.inject.Injector;
import http.server.response.HttpResponse;
import http.server.serviceloader.ARGOPlugin;
import http.server.serviceloader.PluginType;

import java.util.List;
import java.util.Map;

@PluginType("/v1/VisitorDB")
public class DatabaseMain implements ARGOPlugin {

    public HttpResponse doSomething(String doThis, Map<String, List<String>> params) {
        Injector injector = Guice.createInjector(new VisitorsDatabaseModule());
        DatabaseHandler databaseHandler = injector.getInstance(DatabaseHandler.class);

        return databaseHandler.execute(params);

    }
}
