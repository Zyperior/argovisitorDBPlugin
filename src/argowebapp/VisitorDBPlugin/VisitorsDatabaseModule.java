package argowebapp.VisitorDBPlugin;
import argowebapp.VisitorDBPlugin.DataBaseHandlers.Database;
import argowebapp.VisitorDBPlugin.DataBaseHandlers.SQLiteHandler;
import com.google.inject.AbstractModule;

public class VisitorsDatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DatabaseHandler.class);
        bind(Database.class).to(SQLiteHandler.class);
    }
}
