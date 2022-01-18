/* A type of task that accepts taskName
*/
public class ToDo extends Task {

    public ToDo(String n, boolean d) {
        super(n, d);
        super.type = 'T';
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(getTaskIcon()).append(this.getDoneIcon());
        res.append(this.name).append("\n");;
        return res.toString();
    }
}
