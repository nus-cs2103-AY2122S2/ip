import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Duke {

    private static void writeToFile(String filePath, List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String s = "";
        for (Task t: tasks) {
            s += t.toString() + "\n";
        }


        fw.write(s);
        fw.close();
    }


    private static void loadFile(File f, List<Task> tasks) throws IOException {
        if (!f.exists()) {
            return;
        } else {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String s = sc.nextLine();
                Task t = parseTextFile(s);
                tasks.add(t);
            }
        }
    }

    private static Task parseTextFile(String s) {
        Task t;
        String[] strarr = s.split(" ");
        if (s.contains("[X]")) {
            if (s.contains("[T]")) {
                t = new Todo(strarr[1]);
            } else if (s.contains("[E]")){
                t = new Event(strarr[3], strarr[2].substring(0, strarr[3].length()- 1));
            } else {
                t = new Deadline(strarr[1], strarr[3].substring(0, strarr[3].length()- 1));
            }
            t.markAsDone();
        } else {
            if (s.contains("[T]")) {
                t = new Todo(strarr[2]);
            } else if (s.contains("[E]")){
                t = new Event(strarr[2], strarr[4].substring(0, strarr[4].length()- 1));
            } else {
                t = new Deadline(strarr[2], strarr[4].substring(0, strarr[4].length()- 1));
            }

        }
        return t;
    }



    public static void main(String[] args) throws DukeException, IOException {

        Scanner sc = new Scanner(System.in);

        File newFile = new File("data/duke.txt");


        System.out.println(chatBox("Yawn... You woke me up! Urgh\n    What do you need?"));

        if (!newFile.exists()) {
            throw new DukeException("Please create file under directory data");
        }

        List<Task> tasks = new ArrayList<Task>();
        loadFile(newFile, tasks);

        String tab = "    ";

        String[] inp = sc.nextLine().split(" ");
        String task = inp[0];
        String item = "";

        for (int i = 1; i < inp.length; i++) {
            if (i != inp.length - 1) {
                item += inp[i] + " ";
            } else {
                item += inp[i];
            }

        }

        while (!task.equals("bye")) {
            if (task.equals("todo")) {
                if (!item.equals("")) {
                    tasks.add(new Todo(item));
                    System.out.println(chatBox(addTask(tasks.get(tasks.size() - 1), tasks.size())));
                } else {
                    throw new DukeException("Can read instructions or not? Todo cannot be empty :/");
                }

            } else if (task.equals("deadline")) {
                if (!item.equals("")) {
                    String thing = item.split(" /by ")[0];
                    String time = item.split(" /by ")[1];
                    tasks.add(new Deadline(thing, time));
                    System.out.println(chatBox(addTask(tasks.get(tasks.size() - 1), tasks.size())));
                } else {
                    System.out.println(chatBox("Can read instructions or not? Deadline cannot be empty :/"));
                }

            } else if (task.equals("event")) {
                if (!item.equals("")) {
                    String thing = item.split(" /at ")[0];
                    String time = item.split(" /at ")[1];
                    tasks.add(new Event(thing, time));
                    System.out.println(chatBox(addTask(tasks.get(tasks.size() - 1), tasks.size())));
                } else {
                    System.out.println(chatBox("Can read instructions or not? Event cannot be empty :/"));
                }

            } else if (task.equals("list")){
                String lists = "";
                for (int i = 0; i < tasks.size(); i++) {
                    if (i != 0) {
                        lists += "\n" + tab;
                    }
                    lists += String.format("%d. %s", i + 1, tasks.get(i).toString());

                }

                System.out.println(chatBox(lists));

            } else if (task.equals("mark")) {
                try {
                    int index = Integer.parseInt(item);
                    tasks.get(index - 1).markAsDone();
                    System.out.println(chatBox("Good job for accomplishing something today! I've marked this task as done:\n      "
                            + tasks.get(index - 1).toString()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You can't do that! It's not in the list!");
                }

            } else if (task.equals("unmark")) {
                try {
                    int index = Integer.parseInt(item);
                    tasks.get(index - 1).markAsUndone();
                    System.out.println(chatBox("Stop procrastinating you lazy prick! I've marked this task as not done yet:\n      "
                            + tasks.get(index - 1).toString()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You can't do that! It's not in the list!");
                }

            } else if (task.equals("delete")) {
                try {
                    int index = Integer.parseInt(item);
                    Task t = tasks.get(index - 1);
                    tasks.remove(index - 1);
                    System.out.println(chatBox(removeTask(t, tasks.size())));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You can't do that! It's not in the list!");
                }


            } else {
                System.out.println(chatBox("What is this? Can you read English?"));
            }

            inp = sc.nextLine().split(" ");
            task = inp[0];
            item = "";

            for (int i = 1; i < inp.length; i++) {
                if (i != inp.length - 1) {
                    item += inp[i] + " ";
                } else {
                    item += inp[i];
                }

            }
        }


        writeToFile(newFile.getPath(), tasks);



        System.out.println(chatBox("Bye. I don't hope to see you again soon :D"));

        sc.close();
    }

    private static String chatBox(String command){
        String tab = "    ";
        String horizontalLines = tab + "___________________________________";

        return horizontalLines + "\n" + tab + command + "\n" + horizontalLines;

    }

    private static String addTask(Task task, int total) {
        String tab = "    ";
        String firstLine = "Ah sure. I've added this task:\n";
        String secondLine = tab + "  " + task.toString() + "\n";
        String thirdLine;
        if (total == 1) {
            thirdLine = tab + "Now you have " + total + " task in the list.";
        } else {
            thirdLine = tab + "Now you have " + total + " tasks in the list.";
        }

        return firstLine + secondLine + thirdLine;

    }

    private static String removeTask(Task task, int total) {
        String tab = "    ";
        String firstLine = "Less work for you then less work for me then. I've removed this task:\n";
        String secondLine = tab + "  " + task.toString() + "\n";
        String thirdLine;

        if (total == 1) {
            thirdLine = tab + "Now you have " + total + " task in the list.";
        } else {
            thirdLine = tab + "Now you have " + total + " tasks in the list.";
        }
        return firstLine + secondLine + thirdLine;




    }

}




