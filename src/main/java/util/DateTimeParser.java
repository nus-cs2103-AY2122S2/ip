
package util;

public class DateTimeParser {

    private String time;
    private String des;

    /**
     *
     * @param task description of task
     * @param type type of task: either deadline or event
     */
    public void parseDateTime(String task, String type) {
        if (type.equals("deadline")) {
            parseDeadline(task);
        } else {
            parseEvent(task);
        }

    }

    private void parseDeadline(String item) {
        this.des = item.split(" /by ")[0];
        this.time = item.split(" /by ")[1];

    }

    private void parseEvent(String item) {
        this.des = item.split(" /at ")[0];
        this.time = item.split(" /at ")[1];

    }

    public String getTime() {
        return this.time;
    }

    public String getDescription() {
        return this.des;
    }



}