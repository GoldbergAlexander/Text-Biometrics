/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/20/15.
 */

//JavaFX Imports
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.animation.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    protected Label labelOutput;
    @FXML
    protected TextArea textAreaInput;

    //Key Tracking Variables;
    private double keyDown = 0, keyUp = 0;
    private KeyEvent workingKeyEvent;

    @FXML
    protected void textAreaInputKeyPressed(KeyEvent event){
        //Key Goes Down -- Set keyDown Time and KeyEvent
        workingKeyEvent = event;
        keyDown = System.currentTimeMillis();
    }
    @FXML
    protected void textAreaInputKeyReleased(KeyEvent event){
        //Key Released -- Set keyUp Time and send KeyEvent
        keyUp = System.currentTimeMillis();
        //Evaluate Key Down Time
        double keyDownTime = keyUp - keyDown;
        FingerprintController.Save(workingKeyEvent,keyDownTime);

    }
    @FXML
    protected void textAreaInputKeyTyped(KeyEvent event){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
