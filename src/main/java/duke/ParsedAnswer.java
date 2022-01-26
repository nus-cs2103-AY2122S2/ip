package duke;

public class ParsedAnswer {
    private String command;
    private Integer index;
    private String date;
    private String desc;

    ParsedAnswer(String command, Integer index) {
        this.command = command;
        this.index = index;
    }

    public String getCommand() {
        return this.command;
    }

    public Integer getIndex() {
        return this.index;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
