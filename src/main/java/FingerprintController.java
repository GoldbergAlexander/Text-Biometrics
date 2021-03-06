/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/20/15.
 */
//MongoDB Imports

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

//JavaFX Imports

public class FingerprintController {
    private static String currentUser = "User";
    private static String collectionName = "fingerprints";
    private static DBCollection collection;

    public static void SaveKeyDown(String keyEvent, double time) {
        if(collection == null){
            init();
        }
        //Check for user,key Document
        BasicDBObject query = new BasicDBObject("User", currentUser).append("Key", keyEvent);
        if(collection.find(query).limit(1).hasNext()){
            //Append
            updateKeyData(keyEvent, time, query);
        }else{
            //Insert
            insertUser(keyEvent, time);
        }
    }

    private static void updateKeyData(String keyEvent, double time, BasicDBObject query) {
        //$push append to array
        BasicDBObject update = new BasicDBObject("$push", new BasicDBObject("Data", time));
        collection.update(query,update);
    }

    private static void insertUser(String keyEvent, double time) {
        BasicDBObject document = new BasicDBObject();
        document.append("User",currentUser);
        document.append("Key", keyEvent);
        document.append("Data", new double[]{time});
        collection.insert(document);
    }

    public static void setCurrentUser(String string){
        if(string.equalsIgnoreCase("")){
            string = "Not Provided";
        }
        currentUser = string;
    }

    private static void init(){
        collection = DataBaseModel.getCollection(collectionName);
    }


}
