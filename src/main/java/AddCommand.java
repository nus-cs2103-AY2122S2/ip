public class AddCommand extends Command{
    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    Type ty;
    String body;

    public AddCommand(String s, String body) {
        if (s.equals("TODO")) {
            this.ty = Type.TODO;
        } else if (s.equals("DEADLINE")) {
            this.ty = Type.DEADLINE;
        } else if (s.equals("EVENT")) {
            this.ty = Type.EVENT;
        }
        this.body = body;
    }

    public void execute(TaskList t, Ui u, Storage s) throws TsundereException{

       String[] splitStr;
        switch (this.ty) {
            case TODO:
                t.addTask(new ToDo(body));
                break;
            case DEADLINE:
                splitStr = body.split("/by");
                if (splitStr.length < 2) {
                    throw new TsundereException("Hmph you baka, gimme a correct format. For example, deadline sleep/by 2019-01-15");
                }
                t.addTask(new Deadline(splitStr[0], splitStr[1]));
                break;
            case EVENT:
                splitStr = body.split("/at");
                if (splitStr.length < 2) {
                    throw new TsundereException("Hmph you baka, gimme a correct format. For example, event sleep/at 2019-01-15");
                }
                t.addTask(new Event(splitStr[0], splitStr[1]));
                break;
        }

        u.printWrapper("New task! You better do it.\n" + t.getTaskStr(t.getCount()) + "\nYou have " + t.getCount() + " task(s) to do you lazy bum!");
        s.saveFile(t.tasksToString());
    }

    public boolean isExit() {
        return false;
    }
}
