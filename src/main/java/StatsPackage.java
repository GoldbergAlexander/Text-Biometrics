import org.apache.commons.math3.stat.inference.TestUtils;

/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 10/1/15.
 */
public class StatsPackage {
    public static double tTest(Double[] arrayOne, Double[] arrayTwo){
        return TestUtils.t(arrayUnbox(arrayOne),arrayUnbox(arrayTwo));
    }
    
    public static Double tTest(Double[][] arrayOne, Double[] arrayTwo){
        return tTest(arrayFlatten(arrayOne),arrayTwo);
    }
    
    public static Double tTest(Double[][] arrayOne, Double[][] arrayTwo){
        return tTest(arrayFlatten(arrayOne),arrayFlatten(arrayTwo));
    }
    
    
    public static Double[] arrayFlatten(Double[][] array){
        int nElements = 0;
        //get total number of elements
        for(int i = 0; i< array.length;i++){
            for (int j = 0;j < array[i].length;j++){
                nElements++;
            }
        }
        Double[] arrayToReturn = new Double[nElements];

        nElements = 0;
        for(int i = 0; i< array.length;i++){
            for (int j = 0;j < array[i].length;j++){
                arrayToReturn[nElements] = array[i][j];
                nElements++;
            }
        }
        return arrayToReturn;

    }

    public static double[] arrayUnbox(Double[] array){
        double[] returnArray = new double[array.length];
        for(int i = 0; i < array.length;i++){
            returnArray[i] = array[i];
        }
        return returnArray;
    }
}

