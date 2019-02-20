package argowebapp.VisitorDBPlugin.DataBaseHandlers;

import java.util.List;

public interface Database {

    void connect();

    void disconnect();

    byte[] getAllVisitors();

    void getVisitor(String name);

    void insertVisitor(String name, String comment);
}
