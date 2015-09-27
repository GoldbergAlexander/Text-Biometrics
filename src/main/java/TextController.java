import java.io.FileInputStream;
import java.lang.Exception;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/22/15.
 */
public class TextController {
    
    public static void textTyped(KeyEvent event, Label label){
        char charTyped = event.getCharacter().charAt(0);
        char charLabel = label.getText().charAt(label.getText().length()/2);
        if (charTyped == charLabel) {
            label.setUnderline(false);
            String oldLabelText = label.getText();
            String newLabelText = oldLabelText.substring(1,label.getText().length()) + TextModel.getNextChar();
            label.setText(newLabelText);
        }else{
            label.setUnderline(true);
        }
    }

}
