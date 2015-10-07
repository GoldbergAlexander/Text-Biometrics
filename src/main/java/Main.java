/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/20/15.
 */
//MongoDB Imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Javafx

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("TextBiometrics");
        primaryStage.setScene(new Scene(root,595.0 , 300.0));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}