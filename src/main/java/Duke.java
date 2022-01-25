import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\r\nWhat can I do for you?");
        ArrayList<Task> taskList = new ArrayList<>(); // init arraylist outside infinite loop.
        DukeException dukeException = new DukeException();
        loadTask(taskList);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().trim(); // Can also convert result to lower-case to handle cases.
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                    writeToTaskList(taskList);
                break;
            } else if (command.equals("list")) {
                for(int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + "." + taskList.get(i).toString());
                }
            } else if (command.startsWith("mark")) {
                try {
                    int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                    Task currentTask = taskList.get(value-1);
                    currentTask.setDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(currentTask.getStatusIcon() + " " + currentTask.getDescription());
                } catch (Exception e) {
                    System.out.println("Task is invalid, please select a valid task number.");
                }
            } else if (command.startsWith("unmark")) {
                try {
                    int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                    Task currentTask = taskList.get(value - 1);
                    currentTask.setUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(currentTask.getStatusIcon() + " " + currentTask.getDescription());
                } catch (Exception e) {
                    System.out.println("Task is invalid, please select a valid task number.");
                }
            } else if(command.startsWith("todo")) {
                try {
                    Todo todoTask = new Todo(command.substring(5));
                    taskList.add(todoTask);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(todoTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch (StringIndexOutOfBoundsException noDescription) {
                    dukeException.noDescriptionException();
                }
            } else if(command.startsWith("deadline")) {
                command = command.replace("deadline", "");
                String[] taskText = command.split(" /by");
                try {
                    DateTimeFormatter format1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    DateTimeFormatter format3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"); // Not working

                    // Create a formatter with the formats
                    DateTimeFormatter parser = new DateTimeFormatterBuilder()
                            .appendOptional(format1)
                            .appendOptional(format2)
                            .appendOptional(format3)
                            .toFormatter();
                    LocalDate parsedDate = LocalDate.parse(taskText[1].strip(),parser);
                    Deadline deadlineTask = new Deadline(taskText[0].strip(), parsedDate);
                    createDeadlineTask(deadlineTask, taskList);
                } catch(ArrayIndexOutOfBoundsException invalidDeadlineSyntax) {
                    dukeException.invalidDeadlineSyntax();
                } catch(DateTimeParseException wrongDate) {
                    // This means that there is no date to be parsed, or incorrect format, so treat it as normal string
                    Deadline deadlineTask = new Deadline(taskText[0].strip(), taskText[1].strip());
                    createDeadlineTask(deadlineTask, taskList);
                }
            } else if(command.startsWith("event")) {
                try {
                    command = command.replace("event", "");
                    String[] taskText = command.split(" /at");
                    Event eventTask = new Event(taskText[0].strip(), taskText[1].strip());
                    taskList.add(eventTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(eventTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch(ArrayIndexOutOfBoundsException invalidEventSyntax) {
                    dukeException.invalidEventSyntax();
                }
            } else if(command.startsWith("delete")) {
                try {
                    int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                    Task removedTask = taskList.remove(value - 1);
                    System.out.println(" Noted. I've removed this task: ");
                    System.out.println(removedTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch (NumberFormatException noTaskNumber) {
                    dukeException.noTaskNumber();
                } catch (IndexOutOfBoundsException invalidTaskNumber) {
                    dukeException.invalidTaskNumber();
                }
             } else {
                dukeException.noSuchTaskException();
            }
        }
    }
    // Reusable code for loading task into arrayList
    public static void loadTask(ArrayList<Task> taskList) {
        String path = "src\\main\\java\\data\\duke.txt";
        path = path.replace("\\", "/");
        try {
            File taskFile = new File(path);
            Scanner reader = new Scanner(taskFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if(data.contains("(by:")) {
                    data = data.substring(9);
                    String[] taskText = data.split("\\(by:");
                    StringBuilder sb = new StringBuilder(taskText[1].strip());
                    sb.deleteCharAt(sb.length()-1);
                    Deadline deadlineTask = new Deadline(taskText[0].strip(), sb.toString());
                      if(data.contains("[X]")) {
                          deadlineTask.setDone();
                      }
                    taskList.add(deadlineTask);
                } else if(data.contains("(at: ")) {
                    data = data.substring(9);
                    String[] taskText = data.split("\\(at:");
                    StringBuilder sb = new StringBuilder(taskText[1].strip());
                    sb.deleteCharAt(sb.length()-1);
                    Event eventTask = new Event(taskText[0].strip(),sb.toString());
                    if(data.contains("[X]")) {
                        eventTask.setDone();
                    }
                    taskList.add(eventTask);
                } else {
                    data = data.substring(9);
                    Todo todoTask = new Todo(data);
                    if(data.contains("[X]")) {
                        todoTask.setDone();
                    }
                    taskList.add(todoTask);
                }
            }
        } catch(NullPointerException fileInvalid) {
            System.out.println("File is Invalid!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Reusable code for writing into duke.txt task list
    public static void writeToTaskList(ArrayList<Task> taskList) {
        String path = "src\\main\\java\\data\\duke.txt";
        path = path.replace("\\", "/");

        try {
            // Remove current file tasks
            PrintWriter pw = new PrintWriter(path);
            pw.close();

            File taskFile = new File(path);
            FileWriter myWriter = new FileWriter(taskFile,true);
            for(int i = 0; i < taskList.size(); i++) {
                myWriter.write((i + 1) + "." + taskList.get(i).toString() + "\r\n");
            }
            myWriter.close();
        } catch(NullPointerException | IOException fileInvalid) {
            System.out.println("File is Invalid!");
        }
    }

    public static void createDeadlineTask(Deadline task, ArrayList<Task> taskList) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}