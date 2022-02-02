import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DukeTest {
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static boolean isExit(String input) {
        return input.equals("bye");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    private static void deleteTask(int idx) {
        Task temp = taskList.get(idx - 1);
        taskList.remove(idx - 1);
        System.out.println("Noted. I've removed this task:\n" + temp.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.");

    }

    private static void list() {
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + "." + taskList.get(i - 1).toString());
        }
    }

    private static void addToSave(File save, String type, boolean mark, String description) throws DukeException {
        try {
            int marked = mark ? 1 : 0;

            FileWriter saveWriter = new FileWriter(save, true);
            saveWriter.write(type + " xxx " + marked + " xxx " + description);
            saveWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to new task [" + type + "] to hard disk due to an IO error.");
            e.printStackTrace();
        }
    }

    private static void modifyFromSave(File save, int idx, boolean mark) {
        try {
            int lineCounter = 0;
            BufferedReader saveReader = new BufferedReader(new FileReader(save));
            String content = "";
            String line = saveReader.readLine();

            while (line != null) {
                if (lineCounter == idx) {
                    if (line.charAt(6) == '1') {
                        if (!(mark)) {
                            line = line.replaceFirst("xxx 1 xxx", "xxx 0 xxx");
                        }
                    } else {
                        if (mark) {
                            line = line.replaceFirst("xxx 0 xxx", "xxx 1 xxx");
                        }
                    }
                }

                content = content + line + System.lineSeparator();
                line = saveReader.readLine();
                lineCounter++;
            }

            FileWriter saveWriter = new FileWriter(save);
            saveWriter.write(content);

            saveReader.close();
            saveWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the file to save changes to in the hard disk");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Unable to save changes to hard disk due to IO Error");
            e.printStackTrace();
        }
    }

    private static void deleteFromSave(File save, int idx) {
        try {
            int lineCounter = 0;
            BufferedReader saveReader = new BufferedReader(new FileReader(save));
            String content = "";
            String line = saveReader.readLine();

            while (line != null) {
                if (lineCounter != idx) {
                    content = content + line + System.lineSeparator();
                }

                line = saveReader.readLine();
                lineCounter++;
            }

            FileWriter saveWriter = new FileWriter(save);
            saveWriter.write(content);

            saveReader.close();
            saveWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the file to save changes to in the hard disk");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Unable to save changes to hard disk due to IO Error");
            e.printStackTrace();
        }
    }

    private static void load(String data) {
        String[] dataArr = data.split(" xxx ");

        boolean marked = (Integer.valueOf(dataArr[1]) > 0);

        switch (dataArr[0]) {
            case "D":
                taskList.add(new Deadlines(dataArr[2], marked));
                break;
            case "T":
                taskList.add(new ToDos(dataArr[2], marked));
                break;
            case "E":
                taskList.add(new Events(dataArr[2], marked));
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        try {
            File save = new File("./duke.txt");

            BufferedReader saveReader = new BufferedReader(new FileReader(save));
            String dataLine = saveReader.readLine();

            while (dataLine != null) {
                load(dataLine);
                dataLine = saveReader.readLine();
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Hello! I'm Duke\nWhat can I do for you?");

            String input = scanner.nextLine();

            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];

            while (!(isExit(command))) {
                switch (command) {
                    case "list":
                        if (splitInput.length > 1) {
                            throw new DukeException("There should not be anything else after list.");
                        } else {
                            list();
                            break;
                        }
                    case "mark":
                        if (splitInput.length < 2) {
                            throw new DukeException("Please indicate which task you want to mark!");
                        } else {
                            int idx = Integer.parseInt(splitInput[1]) - 1;
                            taskList.get(idx).mark();
                            modifyFromSave(save, idx, true);
                            break;
                        }
                    case "unmark":
                        if (splitInput.length < 2) {
                            throw new DukeException("Please indicate which task you want to unmark!");
                        } else {
                            int idx = Integer.parseInt(splitInput[1]) - 1;
                            taskList.get(idx).unmark();
                            modifyFromSave(save, idx, false);
                            break;
                        }
                    case "todo":
                        if (splitInput.length < 2) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        } else {
                            addTask(new ToDos(splitInput[1]));
                            addToSave(save, "T", false, splitInput[1]);
                            break;
                        }
                    case "deadline":
                        if (splitInput.length < 2) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else {
                            addTask(new Deadlines(splitInput[1]));
                            addToSave(save, "D", false, splitInput[1]);
                            break;
                        }
                    case "event":
                        if (splitInput.length < 2) {
                            throw new DukeException("The description of an event cannot be empty.");
                        } else {
                            addTask(new Events(splitInput[1]));
                            addToSave(save, "E", false, splitInput[1]);
                            break;
                        }
                    case "delete":
                        int idx = Integer.parseInt(splitInput[1]);
                        if (taskList.size() <= idx) {
                            throw new DukeException("Invalid index input");
                        } else {
                            deleteTask(idx);
                            deleteFromSave(save, idx);
                            break;
                        }
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }

                input = scanner.nextLine();
                splitInput = input.split(" ", 2);
                command = splitInput[0];
            }

            exit();
            scanner.close();
            saveReader.close();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
