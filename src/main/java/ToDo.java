//@@author junrong98-reused
//Reused from https://nus-cs2103-ay2122s2.github.io/website/admin/ip-w2.html
// with minor modifications

/**
 * The ToDo class which inherits from Task.
 */
public class ToDo extends Task{

    /**
     * The constructor for ToDo class
     * @param description The task sent by the users
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
