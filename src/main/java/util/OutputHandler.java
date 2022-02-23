package util;

import java.util.ArrayList;

public class OutputHandler {
    private static final String LINE_BORDER = "-----------------------------------------------";


    public void print(ArrayList<String> texts, boolean hasBorder) {
        if (hasBorder) {
            System.out.println(LINE_BORDER);
        }
        for (String text: texts) {
            System.out.println(text);
        }
        if (hasBorder) {
            System.out.println(LINE_BORDER);
        }
    }

    public void print(ArrayList<String> texts) {
        print(texts, true);
    }

    public void print(String text, boolean hasBorder) {
        ArrayList<String> texts = new ArrayList<>(0);
        texts.add(text);
        print(texts, hasBorder);
    }

    public void print(String text) {
        print(text, true);
    }
}
