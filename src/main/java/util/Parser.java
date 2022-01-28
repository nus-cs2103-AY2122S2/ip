package util;

public class Parser {
    String task, item;

    public Parser() {
        this.task = "";
        this.item = "";
    }

    /**
     * Parse the input into command and details of the command
     * @param inp input to be parsed
     */


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

    /**
     *
     * @return the parsed command
     */


    public String getTask() {
        return this.task;
    }

    /**
     * @return the parsed description of the command
     *
     */

    public String getItem() {
        return this.item;
    }
}