package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;

public class Parser {
    public String[] words; // user input split by spaces to get words
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    FileWriter fw = new FileWriter("data/duke.txt", true);
    PrintWriter wf = new PrintWriter(fw);
    TaskList tasks = null;
    Storage storage = null;
    String command = null;

    public Parser(String command, TaskList tasks, Storage storage) throws IOException {
        this.words = command.split(" "); // split user command
        this.tasks = tasks;
        this.command = command;
        this.storage = storage;
    }
    public String getResponse() throws IOException {
            StringBuilder sb = new StringBuilder();

//         "bye" to end the program
            switch (words[0]) {
            case "bye" -> System.out.println("    Bye!! See you again soon!!");

            // if user requests to list their tasks
            case "list" -> {
                sb = new StringBuilder();

                sb.append("    Here are the tasks in your lists:");
                int n = 1;
                for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                    Task t = tasks.get(i);
                    String by = "";
                    if (t.getType().equals("D") || t.getType().equals("E"))
                        by = t.getBy().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
                    sb.append("    ").append(n).append(". [").append(t.getType()).append("][").append(t.getDone()).append("] ").append(t.desc).append(by).append("\n");
                    n++;
                }
                return sb.toString();
            }
            case "mark" -> {
                wf.flush(); // need to flush first cause updates were stored in buffer
                int n = Integer.parseInt(words[1]) - 1;
                assert n <= tasks.getNumberOfTasks();
                tasks.set(n, tasks.get(n).mark());
                Task temp = tasks.get(n);
                if (!(temp instanceof ToDo)) {
                    String date = temp.getBy().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
                    tasks.updateTask(n + 1, tasks.get(n).type + " 1 " + tasks.get(n).desc + " | " + date);

                } else
                    tasks.updateTask(n + 1, tasks.get(n).type + " 1 " + tasks.get(n).desc);

                return "    Alright! I've marked this as done:\n      [" + temp.getDone() + "] " + temp.desc;
            }
            case "unmark" -> {
                wf.flush(); // need to flush first cause updates were stored in buffer
                int n = Integer.parseInt(words[1]) - 1;
                assert n <= tasks.getNumberOfTasks();
                tasks.set(n, tasks.get(n).unmark());
                Task temp = tasks.get(n);
                if (!(temp instanceof ToDo)) {
                    String date = temp.getBy().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
                    tasks.updateTask(n + 1, tasks.get(n).type + " 0 " + tasks.get(n).desc + " | " + date);
                } else
                    tasks.updateTask(n + 1, tasks.get(n).type + " 0 " + tasks.get(n).desc);

                return
                        "    Alright! I've marked this as not done:\n      [" + temp.getDone() + "] " + temp.desc;
            }
            case "delete" -> {
                wf.flush(); // need to flush first cause updates were stored in buffer
                int n = Integer.parseInt(words[1]) - 1; // the task number to be deleted
                assert n <= tasks.getNumberOfTasks();
                Task t = tasks.get(n);
                tasks.remove(n);
                tasks.deleteTask(n + 1);
                String by = "";
                if (t.getBy() != null) {
                    by = t.getBy().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));

                }
                return"    Alright! This task has been deleted:\n      [" + t.getType() + "]["
                        + t.getDone() + "] " + t.desc + by;

            }
            case "find" -> {
                wf.flush(); // need to flush first cause updates were stored in buffer
                String key = words[1]; // keyword to be found
                int[] ind = new int[tasks.getNumberOfTasks()]; // indexes of task with keyword
                int noOfMatched = 0;
                for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                    String[] split = tasks.get(i).desc.split(" ");

                    for(String s : split) {
                        if(s.equals(key)) {
                            ind[noOfMatched] = i;
                            noOfMatched++;
                            break;
                        }
                    }
                }
                sb = new StringBuilder();
                sb.append("    Here are the matching tasks in your list:\n");
                for(int i = 0; i < noOfMatched; i++) {
                    Task t = tasks.get(ind[i]);
                    String by = "";
                    if (t.getType().equals("D") || t.getType().equals("E"))
                        by = t.getBy().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
                    sb.append("    ").append(i).append(1).append(". [").append(t.getType()).append("][").append(t.getDone()).append("] ").append(t.desc).append(by).append("\n");
                    return sb.toString();
                }
            }

            // user add a todo task
            case "todo" -> {
                words = command.split(" ", 2);
                if (words.length < 2) {
                    return "    Oops!! Description of ToDo can't be empty!!\n ";
                } else {
                    sb = new StringBuilder();
                    sb.append("    Okay! I've added this task:\n");
                    tasks.add(new ToDo(words[1]));
                    sb.append("      [T][ ] ").append(words[1]);
                    wf.println("T 0 " + words[1]);
                    sb.append("\n    Now you have ").append(tasks.getNumberOfTasks()).append(" tasks on your list");
                    return sb.toString();
                }
            }
            // user add a event task
            case "event" -> {
                sb = new StringBuilder();
                words = findDate(command.split(" "));
                sb.append("    Okay! I've added this task into the list:\n  ");
                tasks.add(new Event(words[0], LocalDateTime.parse(words[1], formatter)));
                sb.append("      [E][ ] ").append(words[0]);
                wf.println("E 0 " + words[0] + " | " + words[1]);
                sb.append("\n    Now you have ").append(tasks.getNumberOfTasks()).append(" tasks on your list");
                return sb.toString();
            }
            // user add a deadline task
            case "deadline" -> {
                sb = new StringBuilder();
                words = findDate(command.split(" "));
                sb.append("    Okay! I've added this task:\n  ");
                tasks.add(new Deadline(words[0], LocalDateTime.parse(words[1], formatter)));
                sb.append("      [D][ ] ").append(words[0]);
                wf.println("D 0 " + words[0] + " | " + words[1]);
                sb.append("\n    Now you have ").append(tasks.getNumberOfTasks()).append(" tasks on your list");
                return sb.toString();

            }
            // user add items to list
                default -> {
                    return sb.toString();

                }
            }
        wf.close();
        return  "    Sorry! I don't know what that means :'(";
    }

    /**
     * Returns the date and time seperately from user's input command.
     *
     * @param full Command input by user.
     * @return Array of strings representing the date and time.
     */
    public String[] findDate(String[] full) {
        StringBuilder sb = new StringBuilder();
        String[] end = new String[100];
        int i = 0;
        for (String s : full) {
            if (s.charAt(0) == '/')
                break;
            i++;
        }
        // get the desc
        for (int j = 1; j < i; j++) {
            sb.append(full[j]).append(" ");
        }

        // get the time
        end[0] = sb.toString();
        sb = new StringBuilder();
        // }
        sb.append(full[i + 1]).append(" ");
        sb.append(full[i + 2]);

        end[1] = sb.toString();
        return end;
    }
}
