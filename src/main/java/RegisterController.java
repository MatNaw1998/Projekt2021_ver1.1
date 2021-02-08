import databaseStuff.DBUtil;
import databaseStuff.KlienciDAO;
import databaseStuff.UserInfoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sourc.DBAdmin;
import sourc.Strings;

import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField textFieldLogin;
    @FXML
    private PasswordField passField1;
    @FXML
    private PasswordField passField2;
    @FXML
    private TextField textFieldMail1;
    @FXML
    private TextField textFieldMail2;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldSurname;
    @FXML
    private TextField textFieldAdress;
    @FXML
    private TextField textFieldTypeCode;
    @FXML
    private Button buttonRegister;
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelPass1;
    @FXML
    private Label labelPass2;
    @FXML
    private Label labelMail1;
    @FXML
    private Label labelMail2;
    @FXML
    private Label labelName;
    @FXML
    private Label labelSurname;
    @FXML
    private Label labelAdress;
    @FXML
    private Label labelPhone;
    @FXML
    private TextField textFieldPhone;

    private UserInfoDAO userInfoDAO;
    private KlienciDAO kliencioDAO;
    private DBUtil dbUtil;
    private boolean doesExist;
    private boolean isReady;

    public void onActionbuttonRegister(ActionEvent actionEvent) {
        clearBooleans();
        dbUtil = new DBUtil("root", DBAdmin.mateusz);
        try {
            dbUtil.dbConnect();
            userInfoDAO = new UserInfoDAO(dbUtil);
            kliencioDAO = new KlienciDAO(dbUtil);
            userInfoDAO.doesUserExists(textFieldLogin.getText());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (textFieldLogin.getText() != "") {
            System.out.println("login is not empty");
            setDoesExist();

            if (!doesExist) { //nie jest "" i nie jest zajety
                isReady = true;
                clearLabels();
                System.out.println(doesExist);
                if (passField1.getText() == "") {
                    labelPass1.setText(Strings.empty);
                    isReady = false;
                }
                if (passField2.getText() == "") {
                    labelPass2.setText(Strings.empty);
                    isReady = false;
                }
                if (textFieldMail1.getText() == "") {
                    labelMail1.setText(Strings.empty);
                    isReady = false;
                }
                if (textFieldMail2.getText() == "") {
                    labelMail2.setText(Strings.empty);
                    isReady = false;
                }
                if (textFieldName.getText() == "") {
                    labelName.setText(Strings.empty);
                    isReady = false;
                }
                if (textFieldSurname.getText() == "") {
                    labelSurname.setText(Strings.empty);
                    isReady = false;
                }
                if (textFieldAdress.getText() == "") {
                    labelAdress.setText(Strings.empty);
                    isReady = false;
                }
                if (textFieldPhone.getText() == "") {
                    labelPhone.setText(Strings.empty);
                    isReady = false;
                }

            } else {
                labelLogin.setText(Strings.occupiedUser);
                isReady = false;
            } //nie jest "" i jest zajety"pole nie moze byc puste"
        } else {
            labelLogin.setText(Strings.empty);
            isReady = false;
        } //jest ""
        System.out.println(isReady);


        try {
            //sprawdzenie maila, hasla, czy sie nie powtarza/nie jest zajety mail
            if (userInfoDAO.isMailOccupied(textFieldMail1.getText())) {
                labelMail1.setText(Strings.occupiedUser);
                isReady = false;
            }

            if (!textFieldMail1.getText().equals(textFieldMail2.getText())) {
                isReady = false;
                labelMail2.setText(Strings.notMachingMail);

            }
            if (!passField1.getText().equals(passField2.getText())) {
                System.out.println(passField1.getText() + "   " + passField2.getText());
                isReady = false;
                labelPass2.setText(Strings.notMachingPsswrd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (isReady) {
            createNewUser(textFieldLogin.getText(), passField1.getText(), textFieldMail1.getText(), textFieldPhone.getText(),
                textFieldPhone.getText(), textFieldName.getText(), textFieldSurname.getText());
        }
    }

    private void setDoesExist() {
        try {
            doesExist = userInfoDAO.doesUserExists(textFieldLogin.getText());
        } catch (SQLException throwables) {
            doesExist = true;
        } catch (ClassNotFoundException e) {
            doesExist = true;
        }
    }

    private void createNewUser(String ulogin, String upass, String umail, String uphone, String uadress, String uname, String usurname) {
        String usertype = "customer";
        if (textFieldTypeCode.equals("2137")) {
            usertype = "company";
        }
        try {
            userInfoDAO.insertUserInfo(ulogin, upass, usertype);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            labelLogin.setText(Strings.unableToAddUser);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            labelLogin.setText(Strings.unableToAddUser);
        }
        try {
            kliencioDAO.uptadeKlienci(umail, uadress, uphone, uname, usurname, userInfoDAO.getUserId(ulogin));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    private void clearBooleans() {
        doesExist = false;
        isReady = false;
    }

    private void setLabelTags() {
        labelLogin.setText(Strings.login);
        labelPass1.setText(Strings.password);
        labelPass2.setText(Strings.rePassword);
        labelMail1.setText(Strings.email);
        labelMail2.setText(Strings.reEmail);
        labelName.setText(Strings.name);
        labelSurname.setText(Strings.surname);
        labelAdress.setText(Strings.adress);
    }

        private void clearLabels() {
        labelLogin.setText(null);
        labelPass1.setText(null);
        labelPass2.setText(null);
        labelMail1.setText(null);
        labelMail2.setText(null);
        labelName.setText(null);
        labelSurname.setText(null);
        labelAdress.setText(null);
    }



}
