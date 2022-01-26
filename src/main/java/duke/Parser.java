package duke;

public class Parser {
    private String command;
    private String desc = null;
    private String date = null;

    public Parser(String str) {
        String[] temp = str.split(" ");
        command = temp[0];
        if (temp.length > 1) {
            int index = 1;
            while (index < temp.length) {
                if (!temp[index].equals("/by") && !temp[index].equals("/at")) {
                    if (desc == null) {
                        desc = temp[index];
                    } else {
                        desc = desc + " " + temp[index];
                    }
                    index++;
                } else {
                    break;
                }
            }
            index++;
            while (index < temp.length) {
                if (date == null) {
                    date = temp[index];
                } else {
                    date = date + " " + temp[index];
                }
                index++;
            }
        }
    }

    public String getCmd() {
        return command;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }
}
