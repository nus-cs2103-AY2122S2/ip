import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class Task {
    enum Type {
        E, T, D
    }

    protected String description;
    protected boolean status;

    public Task(String description) {
        this.description = description;
        status = false;
    }

    String getStatus() {
        return status ? "[X]" : "[ ]";
    }

    void markDone() {
        this.status = true;
    }

    void unmarkDone() {
        this.status = false;
    }

    String getDescription(){
        return this.description;
    }

}