import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final ArrayList<Task> items;
    private int count;
    private final String path;

    public Duke(String path) {
        this.path = path;
        this.items = readFile();
        this.count = items.size();
    }

    private ArrayList<Task> readFile() {
        File f = new File(path);
        ArrayList<Task> arr = new ArrayList<>();
        try {
            if(!f.createNewFile()) {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String[] list = s.nextLine().split(" - ");
                    switch (list[0]) {
                        case "T":
                            Task td = new Todo(list[2]);
                            if (list[1].equals("1")) {
                                td.markAsDone();
                            }
                            arr.add(td);
                            break;
                        case "D":
                            Task d = new Deadline(list[2], list[3]);
                            if (list[1].equals("1")) {
                                d.markAsDone();
                            }
                            arr.add(d);
                            break;
                        default:
                            Task event = new Event(list[2], list[3]);
                            if (list[1].equals("1")) {
                                event.markAsDone();
                            }
                            arr.add(event);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    private void writeToFile() {
        try {
            FileWriter fw = new FileWriter(path);
            StringBuilder text = new StringBuilder();
            for (Task t: items) {
                if (t instanceof Todo) {
                    if (t.isDone) {
                        text.append("T").append(" - 1 - ").append(t.description).append("\n");
                    }
                    text.append("T").append(" - 0 - ").append(t.description).append("\n");
                } else if (t instanceof Deadline) {
                    if (t.isDone) {
                        text.append("D").append(" - 1 - ").append(t.description).append(" - ").append(((Deadline) t).by).append("\n");
                    }
                    text.append("D").append(" - 0 - ").append(t.description).append(" - ").append(((Deadline) t).by).append("\n");
                } else {
                    if (t.isDone) {
                        text.append("E").append(" - 1 - ").append(t.description).append(" - ").append(((Event) t).at).append("\n");
                    }
                    text.append("E").append(" - 0 - ").append(t.description).append(" - ").append(((Event) t).at).append("\n");
                }
            }
            fw.write(text.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMsg(String input) {
        try {
            inputMsg(input);
        } catch (ToDoException e) {
            System.out.println(e.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputMsg(String input) throws DukeException, ToDoException {
        if (input.equals("bye")) {
            System.out.println("\t Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            System.out.println("\t Here are the tasks in your list:");
            for (int i=0; i<count; i++) {
                System.out.println("\t " + (i+1) + "." + items.get(i));
            }
        } else if (input.split(" ", 2)[0].equals("mark")) {
            int pos = Integer.parseInt(input.split(" ", 2)[1]);
            items.get(pos-1).markAsDone();
            System.out.println("\t Nice! I've marked this task as done:\n" + "\t  " + items.get(pos-1));
            writeToFile();
        } else if (input.split(" ", 2)[0].equals("deadline")) {
            String date = input.split("/", 2)[1].split(" ", 2)[1];
            String des = input.split(" /", 2)[0].split(" ", 2)[1];
            items.add(new Deadline(des, date));
            count++;
            System.out.println("\t Got it, I've added this task:");
            System.out.println("\t  " + items.get(count-1));
            System.out.println("\t Now you have " + count + " tasks in the list.");
            writeToFile();
        } else if (input.split(" ", 2)[0].equals("todo")) {
            String[] ls = input.split(" ", 2);
            if (ls.length <= 1) {
                throw new ToDoException();
            }
            String des = ls[1];
            items.add(new Todo(des));
            count++;
            System.out.println("\t Got it, I've added this task:");
            System.out.println("\t  " + items.get(count-1));
            System.out.println("\t Now you have " + count + " tasks in the list.");
            writeToFile();
        } else if (input.split(" ", 2)[0].equals("event")) {
            String date = input.split("/", 2)[1].split(" ", 2)[1];
            String des = input.split(" /", 2)[0].split(" ", 2)[1];
            items.add(new Deadline(des, date));
            count++;
            System.out.println("\t Got it, I've added this task:");
            System.out.println("\t  " + items.get(count-1));
            System.out.println("\t Now you have " + count + " tasks in the list.");
            writeToFile();
        }else if (input.split(" ", 2)[0].equals("delete")) {
            int pos = Integer.parseInt(input.split(" ", 2)[1]);
            Task t = items.remove(pos-1);
            count--;
            System.out.println("\t Noted, I've removed this task:");
            System.out.println("\t  " + t);
            System.out.println("\t Now you have " + count + " tasks in the list.");
            writeToFile();
        }else {
            throw new DukeException();
        }
    }

    public static void main(String[] args) throws IOException {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        String line = "\t_______________________________________________";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Duke duke = new Duke("duke.txt");

        System.out.println(line + "\n" + logo + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n" + line);

        while (true) {
            String input = reader.readLine();

            System.out.println(line);
            duke.printMsg(input);
            System.out.println(line);

            if (input.equals("bye")) {
                break;
            }
        }

    }
}
