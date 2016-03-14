/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/20/15.
 */

//JavaFX Imports

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    //Views
    @FXML
    protected VBox dataEntry, dataView;
    @FXML
    protected BorderPane view;
    @FXML
    protected Label labelOutput;
    @FXML
    protected TextArea textAreaInput;
    @FXML
    protected TextField textUserInput;

    //Key Tracking Variables;
    private double keyDown = 0, keyUp = 0;
    private KeyEvent workingKeyEvent = null;

    @FXML
    protected void textAreaInputKeyPressed(KeyEvent event){
        //Allow multiple access to event
        KeyEvent keyevent = event;
        //Get Time
        keyDown = System.currentTimeMillis();
        //System.out.println(keyDown - keyUp);
        //Insure not the first char
        if (workingKeyEvent != null) {
            //Time between keyup
            double time = keyDown - keyUp;
            KeyEvent lastKey = workingKeyEvent;
            KeyEvent currentKey = keyevent;
            //Travel Time
            FingerprintController.setCurrentUser(textUserInput.getText());
            FingerprintController.SaveKeyDown(lastKey.getCode().toString() + ":" + currentKey.getCode().toString(), keyDown - keyUp);
        }
        //Key Goes Down -- Set keyDown Time and KeyEvent
        workingKeyEvent = keyevent;
    }
    @FXML
    protected void textAreaInputKeyReleased(KeyEvent event){
        //Key Released -- Set keyUp Time and send KeyEvent
        keyUp = System.currentTimeMillis();
        //Evaluate Key Down Time
        double keyDownTime = keyUp - keyDown;
        FingerprintController.setCurrentUser(textUserInput.getText());
        FingerprintController.SaveKeyDown(workingKeyEvent.getCode().toString(), keyDownTime);
    }

    @FXML
    protected void textAreaInputKeyTyped(KeyEvent event){
        TextController.textTyped(event,labelOutput);
    }

    @FXML
    protected void setDataEntry(){
        view.getChildren().removeAll();
        view.setCenter(dataEntry);
    }
    @FXML void setDataView(){
        view.getChildren().removeAll();
        view.setCenter(dataView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        view.getChildren().removeAll();
        FingerprintModel.debugDataDump();


    }
}
