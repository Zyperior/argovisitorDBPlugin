package argowebapp.VisitorDBPlugin.DataBaseHandlers;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHandler implements Database {

    private Connection conn = null;
    private String sql = null;

    @Override
    public void connect() {
        try {
            //create directory if not exists
            File directory = new File("files/databases/SQLite");
            if(!directory.exists()){
                directory.mkdirs();
            }

            // db parameters
            String url = "jdbc:sqlite:files/databases/SQLite/visitorsDB.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            createNewTable();
            System.out.println("Connection to SQLite-database has been established.");


        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    @Override
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public byte[] getAllVisitors() {

        try {
            SQLiteJsonResponseBody body = convertSQLiteToJSON("*");
            return body.getBodyData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void getVisitor(String name) {

    }

    @Override
    public void insertVisitor(String name, String comment) {
        int count = 0;
        try{
            Statement countStatement = conn.createStatement();
            ResultSet resultSet = countStatement.executeQuery("SELECT COUNT(*) FROM visitors");

            while(resultSet.next()){
                count = resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO visitors(visitorNr,name,comment) VALUES(?,?,?)";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,count);
            pstmt.setString(2, name);
            pstmt.setString(3, comment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting into visitor-database: " + e.getMessage());
        }

    }

    private void createNewTable() {

        Statement statement = null;

        sql = "CREATE TABLE IF NOT EXISTS visitors (\n"
                + "	visitorNr integer DEFAULT KEY,\n"
                + "	name text,\n"
                + "	comment text\n"
                + ");";

        try{
            statement = conn.createStatement();
            if(statement.execute(sql)){
                System.out.println("Created table \"visitors\"");
            }


        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        } finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing create-statement: " + e.getMessage());
                }
            }
        }
    }

    private SQLiteJsonResponseBody convertSQLiteToJSON(String searchParam) throws SQLException {

        String selectQuery = "SELECT " + searchParam + " FROM visitors";

        Statement database = null;
        ResultSet resultSet;
        List<Visitor> visitors = new ArrayList<>();

        try {
            database = conn.createStatement();
            resultSet = database.executeQuery(selectQuery);
            while(resultSet.next()) {

                visitors.add(new Visitor(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        database.close();

        return new SQLiteJsonResponseBody(visitors);
    }

}
