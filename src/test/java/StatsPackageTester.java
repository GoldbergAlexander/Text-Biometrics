/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 10/1/15.
 */
public class StatsPackageTester {
    public static void main(String[] args){
        double[] alex, jessi;
        System.out.println("Alexander A");
        alex = FingerprintModel.getKeyData("Alexander Goldberg", "A");
        print(alex);
        System.out.println("Jessi A");
        jessi = FingerprintModel.getKeyData("Jessi Austin", "A");
        print(jessi);
        System.out.println("Stat: ");
        System.out.println(StatsPackage.tTest(jessi, alex));
        StatsPackage.summaryStatistics(alex);

    }

    private static void print(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
