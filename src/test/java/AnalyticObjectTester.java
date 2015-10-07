/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 10/7/15.
 */
public class AnalyticObjectTester {

    public static void main(String[] args) {
        AnalyticObject agoldberg = new AnalyticObject("Alexander Goldberg");
        AnalyticObject jaustin = new AnalyticObject("Jessi Austin");
        AnalyticObject nprovided = new AnalyticObject("Not Provided");
        AnalyticObject all = new AnalyticObject();


        System.out.println("Using Agoldberg");
        System.out.println("Similerarty to agoldberg");
        System.out.println(agoldberg.averageTTest(agoldberg));
        System.out.println("Similerarty to jaustin");
        System.out.println(agoldberg.averageTTest(jaustin));
        System.out.println("Similerarty to nprovided");
        System.out.println(agoldberg.averageTTest(nprovided));
        System.out.println("Similerarty to all");
        System.out.println(agoldberg.averageTTest(all));

    }
}
