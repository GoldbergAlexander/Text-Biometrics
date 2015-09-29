import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/27/15.
 */
public class DataBaseModel {
    private static MongoClient mongoClient;
    private static DB db;
    private static String dbName = "local";

    //Connect to the DB
    protected static void connectToDB() {
        try {
            mongoClient = new MongoClient("localhost");
        } catch (Exception e) {
            e.printStackTrace();
        }

        db = mongoClient.getDB(dbName);
    }

    //Which DB
    public static DB getDb() {
        return db;
    }

    public static String getDBName() {
        return dbName;
    }

    public static void setDBName(String string) {
        dbName = string;
    }

    //Expose Collection
    public static DBCollection getCollection(String string) {
        if (db == null) {
            connectToDB();
        }
        return db.getCollection(string);
    }
}
