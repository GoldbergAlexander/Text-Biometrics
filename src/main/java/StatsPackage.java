/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 10/1/15.
 */

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.inference.TestUtils;

public class StatsPackage {

    public static void debugDescriptiveStatistics(double[] array) {

        // Get a DescriptiveStatistics instance
        DescriptiveStatistics stats = new DescriptiveStatistics();

        // Add the data from the array
        for (int i = 0; i < array.length; i++) {
            stats.addValue(array[i]);
        }

        //Print the stats
        System.out.println(removeOutliers(stats));
    }

    public static double tTest(double[] array1, double[] array2) {
        DescriptiveStatistics one = new DescriptiveStatistics();
        DescriptiveStatistics two = new DescriptiveStatistics();

        for (int i = 0; i < array1.length; i++) {
            one.addValue(array1[i]);
        }

        for (int i = 0; i < array2.length; i++) {
            two.addValue(array2[i]);
        }
        //TODO Fix outlier problem
        //one = removeOutliers(one);
        //two = removeOutliers(two);

        return TestUtils.tTest(one, two);
    }



    public static DescriptiveStatistics removeOutliers(DescriptiveStatistics stats) {
        //Move values into a sorted array
        double[] values = stats.getSortedValues();
        //Prepare an object to store the new values;
        DescriptiveStatistics newValues = new DescriptiveStatistics();
        //Get mean
        double mean = stats.getMean();
        double IQR;
        double lowerBound;
        double upperBound;

        //Get quartiles

        //Lower Quartile
        //Array Positions
        double upperLower = Math.ceil(values.length * .25);
        double lowerLower = Math.floor(values.length * .25);
        double lowerQuartile = (values[(int) lowerLower] + values[(int) upperLower]) / 2;

        //System.out.println("[DEBUG]upperLower="+upperLower+"\nlowerLower="+lowerLower+"\nlowerQuartile="+lowerQuartile);

        //Upper Quartile
        //Array Positions
        double upperUpper = Math.ceil(values.length * .75);
        double lowerUpper = Math.floor(values.length * .75);
        double upperQuartile = (values[(int) lowerUpper] + values[(int) upperUpper]) / 2;

        //System.out.println("[DEBUG]upperUpper="+upperUpper+"\nlowerUpper="+lowerUpper+"\nupperQuartile="+upperQuartile);

        IQR = upperQuartile - lowerQuartile;

        lowerBound = mean - (1.5 * IQR);
        upperBound = mean + (1.5 * IQR);

        //Check for outliers and move into a new Object
        for (int i = 0; i < values.length; i++) {
            if (values[i] >= lowerBound && values[i] <= upperBound) {
                newValues.addValue(values[i]);
            }
        }

        return newValues;


    }

    public static double[] arrayFlatten(double[][] array) {
        int nElements = 0;
        //get total number of elements
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                nElements++;
            }
        }
        double[] arrayToReturn = new double[nElements];

        nElements = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                arrayToReturn[nElements] = array[i][j];
                nElements++;
            }
        }
        return arrayToReturn;

    }

    public static double[] arrayUnbox(Double[] array) {
        double[] returnArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            returnArray[i] = array[i];
        }
        return returnArray;
    }

}

