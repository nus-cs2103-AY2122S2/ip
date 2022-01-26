import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> toDoList = new ArrayList<Task>(100);

    public static void main(String[] args) {
        String logo = "__  __ ____________\n" +
                "\\ \\/ /|_  /_  /_  /\n" +
                " >  <  / / / / / / \n" +
                "/_/\\_\\/___/___/___|";
        System.out.println(logo);
        System.out.println("Hello uwu! I'm xzzz,");
        System.out.println("You can check your schedwle here (ɔ◔‿◔)ɔ ♥!");

        checkFile();

        readFile();

        respond();

        saveFile();
    }

    private static void respond() {
        Scanner sc = new Scanner(System.in);
        label:
        while (sc.hasNext()) {
            String command = sc.next();
            switch (command) {
            case "bye":
                System.out.println("Cya later~ ≧◉◡◉≦");
                break label;
            case "list":
                displayList();
                sc.nextLine();
                break;
            case "mark":
            case "unmark":
                mark(sc.nextInt());
                sc.nextLine();
                break;
            case "todo":
            case "deadline":
            case "event":
                Task newTask;
                if (command.equals("todo")) {
                    command = sc.nextLine();
                    command.trim();
                    try {
                        newTask = makeToDo(command);
                    } catch (ToDoIllegalArgumentException ex) {
                        System.out.println(ex);
                        break;
                    }
                } else if (command.equals("deadline")) {
                    String[] s = sc.nextLine().split("/by");
                    try {
                        newTask = makeDeadline(s[0], s[1]);
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println(new DeadlineIllegalArgumentException("Invalid Argument"));
                        break;
                    }
                } else {
                    String[] s = sc.nextLine().split("/at");
                    try {
                        newTask = makeEvent(s[0], s[1]);
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println(new EventIllegalArgumentException("Invalid Argument"));
                        break;
                    }
                }
                toDoList.add(newTask);
                System.out.println("okie!! (✿◠‿◠)  i have added: \n" +
                        newTask + "\n" +
                        "now there are " + toDoList.size() + " tasks in the list! get to work (ง︡'-'︠)ง");
                break;
            case "delete":
                remove(sc.nextInt());
                break;
            default:
                System.out.println("sowwy i don't understand what that means ಠ_ಥ try something else pwease??");
            }
        }
    }

    private static void displayList() {
        int number = 1;
        System.out.println("here are your tasks ☜(ˆ▿ˆc)");
        for (Task item : toDoList) {
            System.out.println(number + ". " + item.toString());
            number++;
        }
    }

    private static void mark(int idx) {
        toDoList.get(idx - 1).mark();
        if (toDoList.get(idx - 1).getDone()) {
            System.out.println("yay!!! this task is now marked as done ٩(˘◡˘)۶");
        } else {
            System.out.println("this task is now marked as not done yet... do it soon! ᕙ(`▿´)ᕗ");
        }
        System.out.println(toDoList.get(idx - 1).toString());
    }

    private static Task makeToDo(String name) throws ToDoIllegalArgumentException {
        if (name.isEmpty()) {
            throw new ToDoIllegalArgumentException("Illegal Argument");
        }
        return new ToDo(name);
    }

    private static Task makeDeadline(String name, String by) {
        return new Deadline(name, by.trim());
    }

    private static Task makeEvent(String name, String at) {
        return new Event(name, at.trim());
    }

    private static void remove(int idx) {
        System.out.println("OKI!! i have removed this task: \n" +
                toDoList.remove(idx - 1) + "\n" +
                "now there are " + toDoList.size() + " tasks in the list! get to work (ง︡'-'︠)ง");
    }

    private static void checkFile() {
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (IOException ex) {
        }

        try {
            Files.createFile(Paths.get("data/duke.txt"));
        } catch (IOException ex) {
        }
    }

    private static void saveFile() {
        try {
            String textToAdd = "";
            for (Task task : toDoList) {
                textToAdd += task.toText();
            }

            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(textToAdd);
            fw.close();
        } catch (IOException ex) {
            checkFile();
            saveFile();
        }
    }

    private static void readFile() {
        File dataFile = new File("data/duke.txt");
        try {
            Scanner s = new Scanner(dataFile);
            String[] taskLine;
            while (s.hasNext()) {
                taskLine = s.nextLine().split(" \\| ");

                if (taskLine[0].equals("T")) {
                    taskLine[2].trim();
                    toDoList.add(new ToDo(taskLine[2]));
                } else if (taskLine[0].equals("D")) {
                    toDoList.add(new Deadline(taskLine[2], taskLine[3]));
                } else if (taskLine[0].equals("E")) {
                    toDoList.add(new Event(taskLine[2], taskLine[3]));
                }

                if (taskLine[1].equals("1")) {
                    toDoList.get(toDoList.size() - 1).mark();
                }
            }
        } catch (FileNotFoundException ex) {
            checkFile();
        }
    }
}