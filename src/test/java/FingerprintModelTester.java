/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 9/27/15.
 */
public class FingerprintModelTester {
    public static void main(String[] args) {
        System.out.println("Testing getKeyData():1");
        print(FingerprintModel.getKeyData("A"));

        System.out.println("Testing getKeyData():2");
        print(FingerprintModel.getKeyData("Alexander Goldberg", "A"));

        System.out.println("Testing getKeyList():1");
        print(FingerprintModel.getKeyList("Alexander Goldberg"));

        System.out.println("Testing getKeyList():2");
        print(FingerprintModel.getKeyList());

        System.out.println("Testing getUserList():1");
        print(FingerprintModel.getUserList("A"));

        System.out.println("Testing getUserList():2");
        print(FingerprintModel.getUserList());
    }

    private static void print(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private static void print(Double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private static void print(Double[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.println(array[i][j]);
            }
        }
    }


}
