import databaseStuff.DBUtil;
import databaseStuff.UserInfoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sourc.DBAdmin;

import java.sql.SQLException;


//jak mi zaimportujesz  import java.awt.*; zamiast >javafx.scene.control.TextField, javafx.scene.control.Button < itd
//to masz splyw na zapore w szczecinie, pol nocy nad tym zdychalam

public class LoginController {

    //protected boolean isAnyoneLoggedIn = false;
    @FXML
    private Pane pane;
    @FXML
    private  TextField usernameTextField;
    @FXML
    javafx.scene.control.PasswordField passwordField;
    @FXML
    private Button buttonLogin;
    @FXML
    private TextField errorTextField;
    private String viewName;

    DBUtil dbUtil;
    UserInfoDAO userInfoDAO;
    private boolean exist = false,
            passMatches = false,
            isAnCasualUser = false,
            isAnCompanyUser = false;

    public void onActionButtonLogin(ActionEvent actionEvent) {

        String uname = null, pass = null;


        if (!usernameTextField.getText().equals(null)) {
            uname = usernameTextField.getText();
        }
        if (!passwordField.getText().equals(null)) {
            pass = passwordField.getText();
        }
        System.out.println("dane logowania" + uname + pass);

        verifyUser(uname, pass);
        if (passMatches) {
            setUserType(uname);
            if (isAnCasualUser) {
                //isAnyoneLoggedIn = true;
                viewName = "userView.fxml";
            } else if (isAnCompanyUser) {
                // isAnyoneLoggedIn = true;
                viewName = "companyView.fxml";
            }
        } else { //!passmatches
            if (exist);
            {errorTextField.setText("Niepoprawne haslo");}
            if (!exist); {errorTextField.setText("Niepoprawny login lub haslo");}

        }

        if (isAnCompanyUser || isAnCasualUser) {
            try {

                final Node source = (Node) actionEvent.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(viewName));
                Parent root = fxmlLoader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void verifyUser(String uname, String password) {

        dbUtil = new DBUtil("root", DBAdmin.mateusz);
        try {
            dbUtil.dbConnect();
            userInfoDAO = new UserInfoDAO(dbUtil);
            exist = userInfoDAO.doesUserExists(uname);
            passMatches = userInfoDAO.verifyPassword(uname, password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setUserType(String uname) {
        String utype = null;
        dbUtil = new DBUtil("root", "Mateusz12");
        try {
            dbUtil.dbConnect();
            userInfoDAO = new UserInfoDAO(dbUtil);
            utype = userInfoDAO.getUserType(uname);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (utype.equals("customer"))isAnCasualUser = true;
        if (utype.equals("company"))isAnCompanyUser = true;
    }


    public void onActionButtonRegister(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerView.fxml"));
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();

            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
       /* assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert buttonLogin != null : "fx:id=\"buttonL\" was not injected: check your FXML file 'dbFX.fxml'.";
       // assert buttonRegister != null : "fx:id=\"disconnectButton\" was not injected: check your FXML file 'dbFX.fxml'.";
*/

    }

}
