/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/27/15.
 */

import com.mongodb.*;

import java.util.ArrayList;

public class FingerprintModel {
    private static String collectionName = "fingerprints";
    private static DBCollection collection;

    //Access Collections
    private static void init() {
        collection = DataBaseModel.getCollection(collectionName);
    }

    public static Double[] getKeyData(String user, String key) {
        Double[] array;
        //Check for init
        if (collection == null) {
            init();
        }
        //Build query object
        BasicDBObject query = new BasicDBObject();
        query.append("User", user);
        query.append("Key", key);

        DBCursor cursor = collection.find().limit(1);
        DBObject dbObject = cursor.next();
        BasicDBList object = (BasicDBList) dbObject.get("Data");
        array = object.toArray(new Double[0]);

        return array;
    }

    public static Double[][] getKeyData(String key) {
        ArrayList<Double[]> array = new ArrayList<Double[]>();
        int i = 0;
        //Check for init
        if (collection == null) {
            init();
        }
        //Build query object
        BasicDBObject query = new BasicDBObject();
        query.append("Key", key);

        DBCursor cursor = collection.find(query);
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            BasicDBList object = (BasicDBList) dbObject.get("Data");
            array.add(object.toArray(new Double[]{}));
            i++;
        }
        return array.toArray(new Double[][]{});
    }

    public static String[] getKeyList(String user) {
        ArrayList<String> strings = new ArrayList<String>();
        int i = 0;
        BasicDBObject query;
        if (user == null) {
            query = new BasicDBObject();
        } else {
            query = new BasicDBObject();
            query.append("User", user);
        }
        DBCursor cursor = collection.find(query);
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            if (!strings.contains(dbObject.get("Key").toString())) {
                strings.add(dbObject.get("Key").toString());
            }
        }
        return strings.toArray(new String[]{});
    }

    public static String[] getKeyList() {
        return getKeyList(null);
    }

    public static String[] getUserList(String key) {
        ArrayList<String> strings = new ArrayList<String>();
        int i = 0;
        BasicDBObject query;
        if (key == null) {
            query = new BasicDBObject();

        } else {
            query = new BasicDBObject();
            query.append("Key", key);
        }
        DBCursor cursor = collection.find(query);

        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            //TODO Convert this to a DB function
            if (!strings.contains(dbObject.get("User").toString())) {
                strings.add(dbObject.get("User").toString());
            }
        }
        return strings.toArray(new String[]{});
    }

    public static String[] getUserList() {
        return getUserList(null);
    }


}
