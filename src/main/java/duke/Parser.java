package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Parser {
    public String[] words;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

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
            FileWriter fw = new FileWriter(storage.getPath(), true);
            PrintWriter wf = new PrintWriter(fw);
            switch (words[0]) {
                case "list" :{
                wf.flush();
                sb = new StringBuilder();
                sb.append("    Here are the tasks on your list:\n");
                int n = 1;
                for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                    Task t = tasks.get(i);
                    String by = "";
                    if (t.getType().equals("D") || t.getType().equals("E")) {
                        by = dateTimeFormatter(t.getBy());
                        sb.append(n).append(". [").append(t.getType()).append("][").append(t.getDone()).append("] ").append(t.desc).append("on " ).append(by).append("\n");
                    }
                    else {
                        sb.append(n).append(". [").append(t.getType()).append("][").append(t.getDone()).append("] ").append(t.desc).append("\n");
                    }
                    n++;
                }
                return sb.toString();
            }
                case "mark" : {
                wf.flush();
                int n = Integer.parseInt(words[1]) - 1;
                assert n <= tasks.getNumberOfTasks();
                tasks.set(n, tasks.get(n).mark());
                Task temp = tasks.get(n);
                if (!(temp instanceof ToDo)) {
                    String date = temp.getBy().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
                    tasks.updateTask(n + 1, tasks.get(n).type + " 1 " + tasks.get(n).desc + " | " + date);

                } else
                    tasks.updateTask(n + 1, tasks.get(n).type + " 1 " + tasks.get(n).desc);

                return "Alright! I've marked this as done:\n      [" + temp.getDone() + "] " + temp.desc;
            }
                case "unmark" : {
                wf.flush();
                int n = Integer.parseInt(words[1]) - 1;
                assert n <= tasks.getNumberOfTasks();
                tasks.set(n, tasks.get(n).unmark());
                Task temp = tasks.get(n);
                if (!(temp instanceof ToDo)) {
                    LocalDateTime dateTime = temp.getBy();
                    String date = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
                    tasks.updateTask(n + 1, tasks.get(n).type + " 0 " + tasks.get(n).desc + " | " + date);
                } else
                    tasks.updateTask(n + 1, tasks.get(n).type + " 0 " + tasks.get(n).desc);

                return
                        "Alright! I've marked this as not done:\n      [" + temp.getDone() + "] " + temp.desc;
            }
                case "delete" : {
                wf.flush();
                int n = Integer.parseInt(words[1]) - 1; // task number to be deleted
                assert n <= tasks.getNumberOfTasks();
                Task t = tasks.get(n);
                tasks.remove(n);
                tasks.deleteTask(n + 1);

                String by = "";
                if (t.getBy() != null) {
                    by = dateTimeFormatter(t.getBy());
                }
                return "Alright! This task has been deleted:\n      [" + t.getType() + "]["
                        + t.getDone() + "] " + t.desc + by;

            }
                case "today" : {
                wf.flush();
                int noOfMatched = 0;
                int dayOfYear = LocalDateTime.now().getDayOfYear();

                int[] ind = new int[tasks.getNumberOfTasks()]; // indexes of task with keyword
                for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                    if (!(tasks.get(i) instanceof ToDo)) {
                        LocalDateTime date = tasks.get(i).getBy();
                        if (dayOfYear == date.getDayOfYear()) {
                            ind[noOfMatched] = i;
                            noOfMatched++;
                        }
                    }
                }
                sb = new StringBuilder();
                sb.append("Here are the tasks you have today:\n");
                for(int i = 0; i < noOfMatched; i++) {
                    Task t = tasks.get(ind[i]);
                    String by = "";
                    by = dateTimeFormatter(t.getBy());
                    sb.append(i+1).append(". [").append(t.getType()).append("][").append(t.getDone()).append("] ").append(t.desc).append("on " ).append(by).append("\n");
                }
                return sb.toString();
            }


                case "find" : {
                wf.flush();
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
                sb.append("    Here are the matching tasks on your list:\n");
                for(int i = 0; i < noOfMatched; i++) {
                    Task t = tasks.get(ind[i]);
                    String by = "";
                    if (t.getType().equals("D") || t.getType().equals("E")) {
                        by = dateTimeFormatter(t.getBy());
                        sb.append(i+1).append(". [").append(t.getType()).append("][").append(t.getDone()).append("] ").append(t.desc).append("on " ).append(by).append("\n");
                    }
                    else {
                        sb.append(i+1).append(". [").append(t.getType()).append("][").append(t.getDone()).append("] ").append(t.desc).append("\n");
                    }
                }
                return sb.toString();
            }
                case "todo" : {
                words = command.split(" ", 2);
                if (words.length < 2) {
                    return "Oops!! Description of ToDo can't be empty!!\n ";
                } else {
                    sb = new StringBuilder();
                    sb.append("Okay! I've added this task:\n");
                    tasks.add(new ToDo(words[1]));
                    sb.append("      [T][ ] ").append(words[1]);
                    wf.println("T 0 " + words[1]);
                    wf.close();
                    sb.append("\nNow you have ").append(tasks.getNumberOfTasks()).append(" tasks on your list");
                    return sb.toString();
                }
            }
                case "event" : {
                sb = new StringBuilder();
                words = findDate(command.split(" "));
                sb.append("Okay! I've added this task into the list:\n  ");
                tasks.add(new Event(words[0], LocalDateTime.parse(words[1], formatter)));
                sb.append("      [E][ ] ").append(words[0]);
                wf.println("E 0 " + words[0] + " | " + words[1]);
                wf.close();
                sb.append("\nNow you have ").append(tasks.getNumberOfTasks()).append(" tasks on your list");
                return sb.toString();
            }
            // user add a deadline task
                case "deadline" : {
                sb = new StringBuilder();
                words = findDate(command.split(" "));
                sb.append("    Okay! I've added this task:\n  ");
                tasks.add(new Deadline(words[0], LocalDateTime.parse(words[1], formatter)));
                sb.append("      [D][ ] ").append(words[0]);
                wf.println("D 0 " + words[0] + " | " + words[1]);
                wf.close();
                sb.append("\nNow you have ").append(tasks.getNumberOfTasks()).append(" tasks on your list");
                return sb.toString();
            }
                default : {
                    return "Sorry! I don't know what that means :'(";
                }
            }
    }

    /**
     * Returns the date and time separately from user's input command.
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
        sb.append(full[i + 1]).append(" ");
        sb.append(full[i + 2]);

        end[1] = sb.toString();

        return end;
    }

    public String dateTimeFormatter(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm"));
    }
}
