package chatbot.task;

import chatbot.datetime.Timestamp;

public class Task {

    private final String title;
    private final String type;
    private final Timestamp datetime;
    private String done;

    public Task(String title, String type, Timestamp datetime) {
        this.title = title;
        this.type = type;
        this.datetime = datetime;
        this.done = " ";
    }

    public Task(String title, String type, String done, Timestamp datetime) {
        this.title = title;
        this.type = type;
        this.done = done;
        this.datetime = datetime;
    }

    public String isCompleted() {
        return done;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getDateTime() {
        return datetime;
    }

    public void mark() {
        done = "X";
    }

    public void unmark() {
        done = " ";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, done, title);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Task) {
            Task t = (Task) other;
            return (
                    t.getTitle() == title
                            && t.getType() == type
                            && t.getDateTime() == datetime
                            && t.isCompleted() == done
            );
        } else {
            return false;
        }
    }
}
