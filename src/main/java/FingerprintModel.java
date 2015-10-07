/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/27/15.
 */

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

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


    /*

    public static double[] getKeyData(String key) {


    }

    public static String[] getKeyList(String user) {

    }

    public static String[] getKeyList() {

    }

    public static String[] getUserList(String key) {

    }
    */
    public static String[] getUserList() {

    }



}
