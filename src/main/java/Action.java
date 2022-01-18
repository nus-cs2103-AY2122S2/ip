import java.util.Arrays;

public class Action {
    protected String[] inp;
    protected DukeList dL;

    public Action(String[] i, DukeList l) {
        inp = i;
        dL = l;
    }

    //Based on action, call the task to be created
    public void makeAction() {
        String act = actWord();
        switch (act) {
            case "todo":
                System.out.println(createTodo());
                break;
            case "deadline":
                System.out.println(createDeadline());
                break;
            case "event":
                System.out.println(createEvent());
                break;
            case "list":
                System.out.println(list());
                break;
            case "mark":
                System.out.println(mark());
                break;
            case "unmark":
                System.out.println(unmark());
                break;
            case "bye":
                bye();
                break;
            default:
                System.out.println("Supplied a wrong command! Please input again!\n");
        }
    }

    public String list() {
        return dL.printTasks();
    }

    public String mark() {
        return dL.mark(Integer.valueOf(inp[1]));
    }

    public String unmark() {
        return dL.unmark(Integer.valueOf(inp[1]));
    }

    public String actWord() {
        return inp[0];
    }

    public String createTodo() {
        Task t;
        String[] title;
        StringBuilder sb = new StringBuilder();
        title = Arrays.copyOfRange(inp, 1, inp.length);
        for (String s : title) {
            sb.append(s).append(" ");
        }
        t = new ToDo(sb.toString(), false);
        return dL.add(t);
    }

    public String createDeadline() {
        Task t;
        StringBuilder sb = new StringBuilder();
        StringBuilder by = new StringBuilder();
        boolean check = false;
        for (int i = 1; i < inp.length; i ++) {
            if (inp[i].equals("/by")) {
                by.append("(by: ");
                check = true;
            } else if (!check) {
                sb.append(inp[i]).append(" ");
            } else {
                by.append(inp[i]).append(" ");
            }
        }
        by.append(")");
        t = new Deadline(sb.toString(), false, by.toString());
        return dL.add(t);
    }

    public String createEvent() {
        Task t;
        StringBuilder sb = new StringBuilder();
        StringBuilder at = new StringBuilder();
        boolean check = false;
        for (int i = 1; i < inp.length; i ++) {
            if (inp[i].equals("/at")) {
                at.append("(at: ");
                check = true;
            } else if (!check) {
                sb.append(inp[i]).append(" ");
            } else {
                at.append(inp[i]).append(" ");
            }
        }
        at.append(")");
        t = new Event(sb.toString(), false, at.toString());
        return dL.add(t);
    }

    public void bye() {
        StringBuilder sb = new StringBuilder();
        String line = "____________________________________________________________\n";
        String byeMsg = "Bye. Hope to see you again soon!\n";
        sb.append(line).append(byeMsg).append(line);
        System.out.println(sb.toString());
        System.exit(0);
    }
}
