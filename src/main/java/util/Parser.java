package util;

public class Parser {
    String task, item;

    public Parser() {
        this.task = "";
        this.item = "";
    }


    public void parse(String inp) {
        String[] strarr = inp.split(" ");
        this.task = strarr[0];
        this.item = "";

        for (int i = 1; i < strarr.length; i++) {
            if (i != strarr.length - 1) {
                item += strarr[i] + " ";
            } else {
                item += strarr[i];
            }

        }
    }

    public String getTask() {
        return this.task;
    }

    public String getItem() {
        return this.item;
    }
}