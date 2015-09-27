/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/20/15.
 */
//Database Imports
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;

public class DataBaseController {
    private static MongoClient mongoClient;
    private static DB db;
    private static String dbName = "local";

    //Connect to the DB
    protected static void connectToDB(){
        try {
             mongoClient = new MongoClient("localhost");
        }catch (Exception e){
            e.printStackTrace();
        }

        db = mongoClient.getDB(dbName);
    }

    //Which DB
    public static DB getDb(){
        return db;
    }
    public static void setDBName(String string){
        dbName = string;
    }
    public static String getDBName(){
        return dbName;
    }


    //Expose Collection
    public static DBCollection getCollection(String string){
        if(db == null){
            connectToDB();
        }
        return db.getCollection(string);
    }


}
