/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/27/15.
 */
public class FingerprintModelTester {
    public static void main(String[] args) {


        System.out.println("Testing getKeyData():1");
        print(FingerprintModel.getKeyData("Alexander Goldberg", "C"));
        System.out.println("Testing getUserList():1");
        print(FingerprintModel.getUserList());
        System.out.println("Testing getKeyList():1");
        print(FingerprintModel.getKeyList());
        System.out.println("Testing get getKeyData():2");
        print(FingerprintModel.getKeyData("C"));
        System.out.println("Testing getKeyList():3");
        print(FingerprintModel.getKeyList("Alexander Goldberg"));



    }

    private static void print(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private static void print(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private static void print(double[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.println(array[i][j]);
            }
        }
    }


}
