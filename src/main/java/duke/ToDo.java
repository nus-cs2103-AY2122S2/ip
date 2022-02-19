package duke;

/**
 * This class is a subclass of Task.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class ToDo extends Task {

    /**
     * Assigns desc and done to this instance.
     *
     * @param desc the task description
     * @param isDone the current completion status of the task.
     */
    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        String str = "[T]";
        if (super.isDone) {
            str += "[X] " + super.desc;
        } else {
            str += "[ ] " + super.desc;
        }
        for (Tag tag: tags) {
            str = str + " " + tag;
        }
        return str;
    }

    /**
     * Changes the string format of this event object.
     *
     * @return a string in this format T,{done},{desc}.
     */
    @Override
    public String changeFormat(TagList tagList) {
        String str = "";
        if (super.isDone) {
            str = str + "T,1," + super.desc;
        } else {
            str = str + "T,0," + super.desc;
        }
        for (Tag tag: tags) {
            str = str + "," + tagList.getList().indexOf(tag);
        }
        return str;
    }

}
