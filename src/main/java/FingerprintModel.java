/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/27/15.
 */

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        for (int i = 0; i < users.length; i++) {
            array[i] = getKeyData(users[i], key);
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
        query.append("User", user);
        DBCursor cursor = collection.find(query);
        List<String> list = new ArrayList<String>();
        while (cursor.hasNext()) {
            list.add(cursor.next().get("Key").toString());
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int length1 = s1.length();
                int length2 = s2.length();
                boolean s1Col = s1.contains(":");
                boolean s2Col = s2.contains(":");

                //If it has collon make it come after
                if (s1Col == true && s2Col == false) {
                    length1 = length2 + 1;
                } else if (s1Col == false && s2Col == true) {
                    length2 = length1 + 1;
                }
                if (length1 < length2) {
                    return -1;
                } else if (length1 > length2) {
                    return 1;
                } else {
                    return s1.compareTo(s2);
                }
            }
        });
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
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int length1 = s1.length();
                int length2 = s2.length();
                boolean s1Col = s1.contains(":");
                boolean s2Col = s2.contains(":");

                //If it has collon make it come after
                if (s1Col == true && s2Col == false) {
                    length1 = length2 + 1;
                } else if (s1Col == false && s2Col == true) {
                    length2 = length1 + 1;
                }
                if (length1 < length2) {
                    return -1;
                } else if (length1 > length2) {
                    return 1;
                } else {
                    return s1.compareTo(s2);
                }
            }
        });
        return (String[]) list.toArray(new String[]{});
    }

    /*
    public static String[] getUserList(String key) {

    }
    */
    public static String[] getUserList() {
        //Check for Init()
        if (collection == null) {
            init();
        }
        //Get distinct list by User
        List list = collection.distinct("User");
        //Return list as an array of String[]
        Collections.sort(list);
        return (String[]) list.toArray(new String[]{});

    }

    public static void debugDataDump() {

        System.out.println("User List");
        String[] userlist = FingerprintModel.getUserList();
        for (int i = 0; i < userlist.length; i++) {
            System.out.println(userlist[i]);
        }

        System.out.println("Key Data");
        String[] allKeyList = FingerprintModel.getKeyList();
        for (int i = 0; i < allKeyList.length; i++) {
            System.out.println(allKeyList[i]);
        }

        System.out.println("Key Data");

        String[] keylist;
        for (int i = 0; i < userlist.length; i++) {
            System.out.println("------------------------------------------------");
            System.out.println("User: " + userlist[i]);
            keylist = FingerprintModel.getKeyList(userlist[i]);
            for (int j = 0; j < keylist.length; j++) {
                System.out.print("Key: " + keylist[j] + ":\t");
                double[] data = FingerprintModel.getKeyData(userlist[i], keylist[j]);
                for (int l = 0; l < data.length; l++) {
                    System.out.print(data[l] + " ");
                }
                System.out.println();
            }
        }
    }


}
