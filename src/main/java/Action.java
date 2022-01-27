import java.io.IOException;
import java.util.Arrays;
/**
 * This is an Action class that obtains a sentence as input that
 * can be deciphered to create tasks in the Duke system
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Action {
    protected String[] inp;
    protected DukeList dL;
    protected FileAction fA;

    String line = "\n____________________________________________________________\n";
    enum Commands {
        todo, deadline, event, list, mark, unmark, delete, bye;
    }

    public Action(String[] i, DukeList l, FileAction f) {
        inp = i;
        dL = l;
        fA = f;
    }

    /**
     * Based on supplied Action word, run the action
     */
    public void makeAction() throws DukeException, IOException {
        Commands act = Commands.valueOf(actWord());
        switch (act) {
            case todo:
                System.out.println(createTodo());
                break;
            case deadline:
                System.out.println(createDeadline());
                break;
            case event:
                System.out.println(createEvent());
                break;
            case list:
                System.out.println(list());
                break;
            case mark:
                System.out.println(mark());
                break;
            case unmark:
                System.out.println(unmark());
                break;
            case delete:
                System.out.println(delete());
                break;
            case bye:
                bye();
                break;
            default:
                String s = line +
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                        line;
                throw new DukeException(s);
        }
    }

    /**
     * Makes a call on DukeList's printTasks()
     */
    public String list() {
        return dL.printTasks();
    }

    /**
     * Makes a call on DukeList's mark()
     */
    public String mark() {
        return dL.markTask(Integer.valueOf(inp[1]), fA);
    }

    /**
     * Makes a call on DukeList's unmark()
     */
    public String unmark() {
        return dL.unmarkTask(Integer.valueOf(inp[1]), fA);
    }

    /**
     * Obtains the Action word from user input
     */
    public String actWord() {
        return inp[0];
    }

    /**
     * Makes a call on DukeList's delete()
     */
    public String delete() throws DukeException {
        return dL.delete(Integer.valueOf(inp[1]), fA);
    }

    /**
     * Checks if user input is valid, then
     * creates a Todo Task and adds into the list
     */
    public String createTodo() throws DukeException {
        String[] title;
        title = Arrays.copyOfRange(inp, 1, inp.length);
        String desc;
        StringBuilder sb = new StringBuilder();

        try {
            desc = inp[1];
        } catch (Exception e) {
            sb.append(line).append("☹ OOPS!!! The description of a todo cannot be empty.");
            sb.append(line);
            throw new DukeException(sb.toString());
        }
        //Continue running if description is valid
        for (String s : title) {
            sb.append(s).append(" ");
        }
        sb.toString().trim();
        Task t = new ToDo(sb.toString(), 0);
        return dL.add(t, fA);
    }

    /**
     * Checks if user input is valid, then
     * creates a Deadline Task and adds to the list
     */
    public String createDeadline() throws DukeException {
        Task t;
        StringBuilder sb = new StringBuilder();
        StringBuilder by = new StringBuilder();
        boolean check = false;
        String desc;

        try {
            desc = inp[1];
        } catch (Exception e) {
            StringBuilder s = new StringBuilder();
            sb.append(line +
                    "☹ OOPS!!! The description of a deadline cannot be empty." +
                    line);
            throw new DukeException(s.toString());
        }

        //Continue running if description is valid
        for (int i = 1; i < inp.length; i ++) {
            if (inp[i].equals("/by")) {
                by.append(" by: ");
                check = true;
            } else if (!check) {
                sb.append(inp[i]).append(" ");
            } else {
                by.append(inp[i]).append(" ");
            }
        }
        sb.toString().trim();
        t = new Deadline(sb.toString(), 0, by.toString());
        return dL.add(t, fA);
    }

    /**
     * Checks if user input is valid, then
     * creates a Event Task and adds to the list
     */
    public String createEvent() throws DukeException {
        Task t;
        StringBuilder sb = new StringBuilder();
        StringBuilder at = new StringBuilder();
        boolean check = false;
        String desc;
        try {
            desc = inp[1];
        } catch (Exception e) {
            StringBuilder s = new StringBuilder();
            sb.append(line +
                    "☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                    line);
            throw new DukeException(s.toString());
        }

        //Continue running if description is valid
        for (int i = 1; i < inp.length; i ++) {
            if (inp[i].equals("/at")) {
                at.append(" at: ");
                check = true;
            } else if (!check) {
                sb.append(inp[i]).append(" ");
            } else {
                at.append(inp[i]).append(" ");
            }
        }
        sb.toString().trim();
        t = new Event(sb.toString(), 0, at.toString());
        return dL.add(t, fA);
    }

    /**
     * Stops the program
     */
    public void bye() throws IOException {
        StringBuilder sb = new StringBuilder();
        String byeMsg = "Bye. Hope to see you again soon!\n";
        sb.append(line).append(byeMsg).append(line);
        try {
            dL.saveAllTasks(fA);
            fA.closeWriteFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(sb.toString());
        System.exit(0);
    }
}
