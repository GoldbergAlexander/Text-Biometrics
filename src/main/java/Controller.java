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
    protected int labelLength;
    @FXML
    protected TextArea textAreaInput;
    @FXML
    protected TextField textUserInput;

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
        FingerprintController.setCurrentUser(textUserInput.getText());
        FingerprintController.Save(workingKeyEvent,keyDownTime);
    }

    @FXML
    protected void textAreaInputKeyTyped(KeyEvent event){
        char charTyped = event.getCharacter().charAt(0);
        char charLabel = labelOutput.getText().charAt(labelLength/2);
        if (charTyped == charLabel) {
            labelOutput.setUnderline(false);
            String oldLabelText = labelOutput.getText();
            String newLabelText = oldLabelText.substring(1,labelLength) + TextModel.getNextChar();
            labelOutput.setText(newLabelText);
        }else{
            labelOutput.setUnderline(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelLength = labelOutput.getText().length();
    }
}
