package pl.edu.wat;

import java.text.DecimalFormat;

public class Utill {

    public static String doubleArrayToString(double[] arr) {
        StringBuilder builder = new StringBuilder();
        DecimalFormat format = new DecimalFormat("#");

        builder.append("[");
        for(int i = 0; i < arr.length - 1; i++) {
            builder.append(format.format(arr[i])).append(", ");
        }
        builder.append(format.format(arr[arr.length - 1])).append("]");

        return builder.toString();
    }

}
