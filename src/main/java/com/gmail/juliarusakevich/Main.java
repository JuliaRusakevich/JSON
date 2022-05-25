package com.gmail.juliarusakevich;

import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {

        double d = 10.3345678;
        var result = getFormattedDouble(d);
        System.out.println("result: " + result);


        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(d);
        System.out.println(formatted); //prints 2.46
    }

    private static Double getFormattedDouble(double nonFormattedDouble) {
        var result = new DecimalFormat("#.##")
                .format(nonFormattedDouble)
                .replace(",", ".");
        return Double.parseDouble(result);


    }
}
