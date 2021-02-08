import databaseStuff.DBUtil;
import databaseStuff.UserInfoDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//VM : --module-path c:/Users/javafx-sdk-11.0.2/lib --add-modules=javafx.controls,javafx.fxml
public class Main extends Application {

    public static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("loginView"), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Paczki");
        stage.show();

     /*   DBUtil dbUtil = new DBUtil("root", "root");
        dbUtil.dbConnect();

        UserInfoDAO userInfoDAO = new UserInfoDAO(dbUtil);
        userInfoDAO.verifyIfUserExists("u1");
        userInfoDAO.verifyPassword("u1","u1");
*/
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }




}