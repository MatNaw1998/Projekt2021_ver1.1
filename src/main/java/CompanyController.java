import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class CompanyController {

    @FXML
    private Label LabelID;
    @FXML
    private Button ButtonParcelLockers;
    @FXML
    private Button ButtonIncome;
    @FXML
    private Button ButtonMyParcels;

    public void onActionButtonParcelLockers(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("parcelLockersView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            //Main.scene.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void onActionButtonIncome(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("incomeView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            //Main.scene.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void onActionButtonMyParcels(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myParcelsView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            //Main.scene.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {}
}
