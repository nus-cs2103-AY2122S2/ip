<<<<<<< HEAD
public abstract class Task {
=======
import java.time.LocalDate;

>>>>>>> branch-Level-8
    public String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    public String getDoneIcon () {
        return (this.done) ? "X" : " ";
    }

<<<<<<< HEAD
    public abstract String toSaveData();
=======
    /**
     * Accepts string in yyyy-mm-dd format
     * @param dateString
     * @return
     */
    public LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString);
    }
>>>>>>> branch-Level-8

    @Override
    public String toString() {
        String status = this.getDoneIcon();
        return String.format("[%s] %s", status, this.description);
    }
}
