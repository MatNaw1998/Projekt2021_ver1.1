package databaseStuff;

import javafx.scene.control.TextArea;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.util.TimeZone;


public class DBUtil {

    private String userName;
    private String userPassword;
    //private TextArea consoleTextArea;

    private Connection conn = null;

    public DBUtil(String userName, String userPassword){ /*, TextArea consoleTextArea) { */
        this.userName = userName;
        this.userPassword = userPassword;
       // this.consoleTextArea = consoleTextArea;
    }

    public void dbConnect() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
           // consoleTextArea.appendText("No MySQL JDBC Driver found." + "\n");
            e.printStackTrace();
            throw e;
        }

        try {
            conn = DriverManager.getConnection(createURL());//("jdbc:mysql://localhost/?user=root&password=root");//
        } catch (SQLException e) {
           // consoleTextArea.appendText("Connection Failed! Try again." + "\n");
            e.printStackTrace();
            throw e;
        }

    }

    public void dbDisconnect() throws SQLException {

        try {

            if (conn != null && !conn.isClosed()) {

                conn.close();
              //  consoleTextArea.appendText("Connection closed. Bye!" + "\n");

            }
        } catch (Exception e) {
            throw e;
        }
    }

    private String createURL() {

        StringBuilder urlSB = new StringBuilder("jdbc:mysql://");
        urlSB.append("localhost:3306/");
        urlSB.append("dbProjetk?");
        urlSB.append("useUnicode=true&characterEncoding=utf-8");
        urlSB.append("&user=");
        urlSB.append(userName);
        urlSB.append("&password=");
        urlSB.append(userPassword);
        urlSB.append("&serverTimezone=CET"); //+ TimeZone.getDefault().getID());

        System.out.println(urlSB);
        return urlSB.toString();
    }

    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs;

        try {

            dbConnect();

            stmt = conn.prepareStatement(queryStmt);

            resultSet = stmt.executeQuery(queryStmt);

            crs = new CachedRowSetWrapper();

            crs.populate(resultSet);
        } catch (SQLException e) {
         //   consoleTextArea.appendText("Problem occurred at executeQuery operation. \n");
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }

        return crs;
    }

    public  void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);

        } catch (SQLException e) {
          //  consoleTextArea.appendText("Problem occurred at executeUpdate operation. \n");
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }

}
