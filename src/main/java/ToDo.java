/**
 * This is a child class of Task, Todo.
 * Todo class accepts only a title and if it is complete as parameter
 *
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class ToDo extends Task {

    public ToDo(String n, int d) {
        super(n, d);
        super.type = 'T';
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(getTaskIcon()).append(" - ");
        res.append(this.getDoneIcon()).append(" - ");
        res.append(this.name).append("\n");;
        return res.toString();
    }
}
