import java.util.Arrays;

public class DukeException extends Exception{

    String[] commands = new String[] {"deadline", "todo", "event"};

    public DukeException(String message) {
        super(message);
    }

    public DukeException() {}

//    public void hasDescription (int size) throws DukeException{
//        if (size == 1) {
//            throw new DukeException("Oopss!! The description cannot be empty");
//        }
//    }

    public void invalidCommands (String inp) throws DukeException{
        String[] temp =  inp.split(" ",2);
        String cmd = temp[0];
        int size = temp.length;
//        String descrip = temp[1];


        if ( !Arrays.asList(commands).contains(cmd)) {
            throw new DukeException("Sorry!! There is no such command!!");
        } else if (size == 1) {
            throw new DukeException("Oopss!! The description cannot be empty");
        } else if (cmd.equals("deadline")) {
            invalidDeadline(temp[1]);
        } else if (cmd.equals("event")) {
            invalidEvent(temp[1]);
        }
    }

    public void invalidDeadline (String descrip) throws DukeException {
         if ((descrip.split("/by",2)[0].equals(""))) {
            throw new DukeException("Please indicate the a task for this deadline");
        } else if ( descrip.indexOf("/by") == -1) {
            throw new DukeException("Please indicate the deadline for this task");
        } else if ((descrip.split("/by",2)[1].equals(""))) {
            throw new DukeException("Please indicate the a deadline for this task, e.g /by Sunday");
        }
    }

    public void invalidEvent (String descrip) throws DukeException {
        if ((descrip.split("/at",2)[0].equals(""))) {
            throw new DukeException("Please indicate the a task for this event");
        } else if ( descrip.indexOf("/at") == -1) {
            throw new DukeException("Please indicate the time for this event");
        } else if ((descrip.split("/at",2)[1].equals(""))) {
            throw new DukeException("Please indicate the a time for this event, e.g /at 4pm");
        }
    }

}
