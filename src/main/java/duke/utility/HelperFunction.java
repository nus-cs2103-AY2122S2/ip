package duke.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class HelperFunction {

    public static void outputLimit(String output) {
        String[] sentences = output.split("\n");
        for (String sentence: sentences) {
            if (sentence.length() < duke.utils.Constants.WIDTH) {
            } else {
                displaySentence(sentence);
            }
        }
    }

    private static void displaySentence(String sentence) {
        String[] words = sentence.split("\\s+");
        int count = 0;
        for (String str: words) {
            count += str.length();
            if (count < duke.utils.Constants.WIDTH) {
                System.out.print(str + " ");
            } else {
                System.out.print('\n' + str + " ");
                count = str.length();
            }
        }
        System.out.print('\n');
    }

    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curr = new Date(System.currentTimeMillis());
        return formatter.format(curr);
    }


}