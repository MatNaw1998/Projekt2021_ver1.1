package databaseStuff;

import sourc.Strings;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDAO {

    private DBUtil dbUtil;

    public UserInfoDAO(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    public void insertUserInfo(String uname, String upassword, String utype) throws SQLException, ClassNotFoundException {

        StringBuilder sb = new StringBuilder("Insert INtO UserInfo (user_name, user_password, user_type) values('");
        sb.append(uname);
        sb.append("', '");
        sb.append(upassword);
        sb.append("', '");
        sb.append(utype);
        sb.append("');");
        String insertStmt = sb.toString();
        System.out.println(insertStmt);

        try {

            dbUtil.dbExecuteUpdate(insertStmt);
           // consoleTextArea.appendText(insertStmt + "\n");

        } catch (SQLException e) {
            //consoleTextArea.appendText("Error occurred while INSERT Operation.");
            throw e;
        }

    }

    public String getUserId(String user_name) throws SQLException, ClassNotFoundException {

        String selectStmt = "select User_ID from userinfo where user_name = '" + user_name + "';";
        String uID = null;
        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            while (resultSet.next()) {
                uID = (resultSet.getString("User_ID"));
            }
            System.out.println("user id " + uID);
            // consoleTextArea.appendText(selectStmt + "\n");
        } catch (SQLException e) {
            // consoleTextArea.appendText("While searching a racket from name, an error occurred. \n");
            throw e;
        }
        return uID;
    }

    public String getUserType(String user_name) throws SQLException, ClassNotFoundException {

        String selectStmt = "select user_type from userinfo where user_name = '" + user_name + "';";
        String utype = null;
        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            while (resultSet.next()) {
                utype = (resultSet.getString("user_type"));
            }
            System.out.println("user type " + utype);
            // consoleTextArea.appendText(selectStmt + "\n");
        } catch (SQLException e) {
            // consoleTextArea.appendText("While searching a racket from name, an error occurred. \n");
            throw e;
        }

        return utype;
    }

    public boolean verifyPassword(String user_name,String pass1) throws SQLException, ClassNotFoundException {

        String selectStmt = "select user_password from userinfo where user_name = '" + user_name + "';";
        String pass2 = null;
        boolean doesMatch = false;

        try {
            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            while (resultSet.next()) {
                pass2 = (resultSet.getString("user_password"));
                if (pass2.equals(pass1)) doesMatch = true;
            }
            System.out.println("czy haslo sie zgadza " + doesMatch);
            // consoleTextArea.appendText(selectStmt + "\n");
        } catch (SQLException e) {
            // consoleTextArea.appendText("While searching a racket from name, an error occurred. \n");
            throw e;
        }

        return doesMatch;
    }

    public boolean doesUserExists(String user_name) throws SQLException, ClassNotFoundException {

        String selectStmt = "select user_id from userinfo where user_name = '" + user_name + "';";
        //String pass = null;
        boolean doesExist = false;

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            while (resultSet.next()) {
                doesExist = true;
                //pass = (resultSet.getString("user_password"));
            }
            //System.out.println("czy user istnieje " + doesExist);

            // consoleTextArea.appendText(selectStmt + "\n");


        } catch (SQLException e) {
            // consoleTextArea.appendText("While searching a racket from name, an error occurred. \n");

            throw e;
        }

        return doesExist;
    }


    public boolean isMailOccupied(String user_mail) throws SQLException, ClassNotFoundException {

        String selectStmt = "select email from klienci where email = '" + user_mail+ "';";
        //String pass = null;
        boolean doesExist = false;
        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
            while (resultSet.next()) {
                doesExist = true;
            }

        } catch (SQLException e) {
            // consoleTextArea.appendText("While searching a racket from name, an error occurred. \n");

            throw e;
        }

        return doesExist;
    }


}
/*
    public ObservableList<Racket> showAllRackets() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM rackets;";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Racket> racketList = getRacketList(resultSet);
            consoleTextArea.appendText(selectStmt);

            return racketList;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching rackets, an error occurred. \n");
            throw e;
        }

    }

    public void insertRacket(String name) throws SQLException, ClassNotFoundException {

        StringBuilder sb = new StringBuilder("INSERT INTO rackets(model) VALUES('");
        sb.append(name);
        sb.append("');");
        String insertStmt = sb.toString();

        try {

            dbUtil.dbExecuteUpdate(insertStmt);
            consoleTextArea.appendText(insertStmt + "\n");

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while INSERT Operation.");
            throw e;
        }

    }
*/


