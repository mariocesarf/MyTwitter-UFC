package Module.GUI;

import Module.Exceptions.MFPException;
import Module.Exceptions.PEException;
import Module.Exceptions.PIException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws PEException, PIException, MFPException {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("My Twitter");
        primaryStage.setScene(new Scene(root, 240, 570));
        primaryStage.show();
    }
}
