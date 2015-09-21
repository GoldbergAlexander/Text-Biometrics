/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/20/15.
 */
//MongoDB Imports
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

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

public class FingerprintController {
    private static String currentUser = "User";
    private static String collectionName = "fingerprints";
    private static DBCollection collection;

    public static void Save(KeyEvent keyEvent, double time){
        if(collection == null){
            init();
        }
        //Check for user Document
        BasicDBObject query = new BasicDBObject("User",currentUser).append("Key",keyEvent.getCode().toString());
        if(collection.find(query).limit(1).hasNext()){
            update(keyEvent, time, query);
        }else{
            insert(keyEvent,time);
        }

    }
    private static void update(KeyEvent keyEvent, double time,BasicDBObject query){

        BasicDBObject updateObject = new BasicDBObject("$push",new BasicDBObject("Data",time));
        collection.update(query,updateObject);
    }
    private static void insert(KeyEvent keyEvent, double time){
        BasicDBObject document = new BasicDBObject();
        document.append("User",currentUser);
        document.append("Key", keyEvent.getCode().toString());
        document.append("Data", new double[]{time});
        collection.insert(document);
    }
    private static void init(){
        collection = DataBaseController.getCollection(collectionName);
    }


}
