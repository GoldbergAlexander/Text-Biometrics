/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/27/15.
 */

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import java.util.ArrayList;
import java.util.List;

public class FingerprintModel {
    private static String collectionName = "fingerprints";
    private static DBCollection collection;

    //Access Collections
    private static void init() {
        collection = DataBaseModel.getCollection(collectionName);
    }

    public static double[] getKeyData(String user, String key) {

        //Check for Init()
        if(collection == null){
            init();
        }

        //Initialize array to return
        double[] array = new double[0];
        //Create Query objects
        BasicDBObject query = new BasicDBObject();
        //Search By User
        query.append("User",user);
        //Search by Key
        query.append("Key",key);
        //Execute find with query and pass to cursor
        DBCursor cursor = collection.find(query);
        //Iterate though cursor
        while (cursor.hasNext()){
            //Convert "Data" segement to List
            BasicDBList list = (BasicDBList)cursor.next().get("Data");
            //Convert list to Double array
            Double[] DoubleArray = list.toArray(new Double[list.size()]);
            //Array cursor.next()
            array = new double[DoubleArray.length];
            //Unbox Double array to double array
            for (int i = 0; i < DoubleArray.length; i++) {
                array[i] = DoubleArray[i];
            }
        }
        return array;

    }




    public static double[] getKeyData(String key) {
        String[] users = getUserList();
        double[][] array = new double[users.length][];
        for (int i = 0; i < users.length;i++){
            array[i] = getKeyData(users[i],key);
        }
         return StatsPackage.arrayFlatten(array);
    }

    public static String[] getKeyList(String user) {
        //TODO Alphabetize returned chars and strings
        //Check for Init()
        if (collection == null) {
            init();
        }
        String[] string = new String[0];
        BasicDBObject query = new BasicDBObject();
        query.append("User",user);
        DBCursor cursor = collection.find(query);
        List<String> list = new ArrayList<String>();
        while (cursor.hasNext()) {
            list.add(cursor.next().get("Key").toString());
        }
        return list.toArray(new String[]{});
    }

    public static String[] getKeyList() {
        //TODO Alphabetize returned chars and strings
        //Check for Init()
        if (collection == null) {
            init();
        }
        //Get distinct list by User
        List list = collection.distinct("Key");
        //Return list as an array of String[]
        return (String[])list.toArray(new String[]{});
    }
    /*
    public static String[] getUserList(String key) {

    }
    */
    public static String[] getUserList() {
        //TODO Alphabetize returned Users
        //Check for Init()
        if (collection == null) {
            init();
        }
        //Get distinct list by User
        List list = collection.distinct("User");
        //Return list as an array of String[]
        return (String[])list.toArray(new String[]{});

    }


}
