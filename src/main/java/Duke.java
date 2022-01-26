import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> toDoList;
    private Ui ui;

    public static void main(String[] args) {
        new Duke().run();
    }

    public Duke() {
        ui = new Ui();
        toDoList = new ArrayList<Task>(100);
    }

    public void run() {
        ui.greeting();

        checkFile();

        readFile();

        respond();

        saveFile();
    }

    private void respond() {
        Scanner sc = new Scanner(System.in);
        label:
        while (sc.hasNext()) {
            String command = sc.next();
            switch (command) {
            case "bye":
                ui.goodbye();
                break label;
            case "list":
                ui.displayList(toDoList);
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
                        ui.showIllegalArgumentError(ex);
                        break;
                    }
                } else if (command.equals("deadline")) {
                    String[] s = sc.nextLine().split("/by");
                    try {
                        newTask = makeDeadline(s[0], s[1]);
                    } catch (IndexOutOfBoundsException ex) {
                        ui.showIncompleteArgumentError();
                        break;
                    }
                } else {
                    String[] s = sc.nextLine().split("/at");
                    try {
                        newTask = makeEvent(s[0], s[1]);
                    } catch (IndexOutOfBoundsException ex) {
                        ui.showIncompleteArgumentError();
                        break;
                    }
                }
                toDoList.add(newTask);
                ui.confirmAddition(newTask, toDoList);
                break;
            case "delete":
                remove(sc.nextInt());
                break;
            default:
                ui.doNotUnderstand();
            }
        }
    }


    private void mark(int idx) {
        toDoList.get(idx - 1).mark();
        if (toDoList.get(idx - 1).getDone()) {
            ui.markAsDone(toDoList, idx);
        } else {
            ui.unmarkAsDone(toDoList, idx);
        }
    }

    private Task makeToDo(String name) throws ToDoIllegalArgumentException {
        if (name.isEmpty()) {
            throw new ToDoIllegalArgumentException("Illegal Argument");
        }
        return new ToDo(name);
    }

    private Task makeDeadline(String name, String by) {
        return new Deadline(name, by.trim());
    }

    private Task makeEvent(String name, String at) {
        return new Event(name, at.trim());
    }

    private void remove(int idx) {
        Task removed = toDoList.remove(idx - 1);
        ui.confirmRemoval(removed, toDoList);
    }

    private void checkFile() {
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (IOException ignored) {
        }

        try {
            Files.createFile(Paths.get("data/duke.txt"));
        } catch (IOException ignored) {
        }
    }

    private void saveFile() {
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

    private void readFile() {
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