import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String BOT_ART =
            "     /\\   | |   | |          \n" +
            "    /  \\  | |__ | |__  _   _ \n" +
            "   / /\\ \\ | '_ \\| '_ \\| | | |\n" +
            "  / ____ \\| |_) | |_) | |_| |\n" +
            " /_/    \\_\\_.__/|_.__/ \\__, |\n" +
            "                        __/ |\n" +
            "                       |___/ ";
    private static final String BOT_NAME = "Abby";
    private static final String UNKNOWN_MSG =
            "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String INVALID_INDEX_MSG =
            "OOPS!!! I can't find that in your task list :-(";
    private static final String BROKEN_MSG =
            "YOU BROKE ME :-(";
    private static final String INVALID_MARK_MSG =
            "OOPS!!! I need a number to update that task :-(";
    private static final String INVALID_DELETE_MSG =
            "OOPS!!! I need a number to delete that task :-(";
    private static final String FILE_PATH = "data/";
    private static final String FILE_NAME = "duke.txt";
    private static final String INVALID_DATE_MSG =
            "OOPS!!! I cannot recognise that date format. :-("
                    + "\nAlso, in this date format yyyy-mm-dd please!";
    private static final String NO_TASK_SEARCH_MSG =
            "I can't find any tasks of that date. :-(";

    private ArrayList<Task> tasks;
    
    public Duke() {
        this.tasks = new ArrayList<>();
    }

    public void output(String output) {
        String result =
                "____________________________________________________________\n\n" +
                output +
                "\n____________________________________________________________\n\n";
        System.out.printf("%s", result);
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public void add(String task) {
        String[] taskArr = null;
        String type = "";
        String result = "Got it. I've added this task:\n";
        String textToAdd = "";

        try {
            taskArr = task.split(" ", 2);
            type = taskArr[0];

            if (type.equalsIgnoreCase("deadline")) {
                String[] taskData = taskArr[1].split(" /by ");
                LocalDate by = LocalDate.parse(taskData[1]);

                this.tasks.add(new Deadline(taskData[0], by));

                Deadline deadline = (Deadline) this.tasks.get(this.tasks.size() - 1);

                textToAdd = String.format(
                        "D | %s | %s | %s",
                        deadline.getCompleted() ? "1" : "0",
                        deadline.getDescription(),
                        taskData[1]);
            } else if (type.equalsIgnoreCase("event")) {
                String[] taskData = taskArr[1].split(" /at ");
                LocalDate at = LocalDate.parse(taskData[1]);

                this.tasks.add(new Event(taskData[0], at));

                Event event = (Event) this.tasks.get(this.tasks.size() - 1);

                textToAdd = String.format(
                        "E | %s | %s | %s",
                        event.getCompleted() ? "1" : "0",
                        event.getDescription(),
                        taskData[1]);
            } else if (type.equalsIgnoreCase("todo")) {
                if (taskArr[1].trim().length() == 0) {
                    throw new IndexOutOfBoundsException();
                }

                Todo todo = (Todo) this.tasks.get(this.tasks.size() - 1);

                textToAdd = String.format(
                        "T | %s | %s",
                        todo.getCompleted() ? "1" : "0",
                        todo.getDescription());
                this.tasks.add(new Todo(taskArr[1]));
            } else {
                throw new IndexOutOfBoundsException();
            }

            writeToFile(FILE_PATH + FILE_NAME, textToAdd);

            int noOfTasks = this.tasks.size();
            String pluralTask = noOfTasks > 1 ? "tasks" : "task";

            result += "  " + this.tasks.get(noOfTasks - 1).toString() + "\n";
            result += "Now you have " + noOfTasks + " " + pluralTask + " in the list.";

            output(result);
        } catch (IndexOutOfBoundsException e) {
            if (isValidType(type)) {
                if (isMissingData(taskArr)) {
                    output("OOPS!!! Some data of your " + type + " task is missing. :-(");
                    return;
                }

                output("OOPS!!! The description of a " + type + " cannot be empty. :-(");
            } else {
                output(UNKNOWN_MSG);
            }
        } catch (IOException e) {
            output("OOPS!!! Facing some issues in saving your task to disk. :-(");
        } catch (DateTimeParseException e) {
            output(INVALID_DATE_MSG);
        }
    }
    
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);

        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }

    public boolean isValidType(String type) {
        if (type.equalsIgnoreCase("todo") ||
                type.equalsIgnoreCase("event") ||
                type.equalsIgnoreCase("deadline")) {
            return true;
        }

        return false;
    }

    public boolean isMissingData(String[] taskArr) {
        if (taskArr.length < 3 && !taskArr[0].equalsIgnoreCase("todo")) {
            return true;
        }

        return false;
    }

    public void list() {
        int length = this.tasks.size();
        StringBuilder sb = new StringBuilder();

        if (length == 0) {
            output("No tasks found! Quit lazing around!");
            return;
        }

        sb.append("Here are the tasks in your list:\n");

        for (int i = 0; i < length; ++i) {
            sb.append(i + 1 + ". " + this.tasks.get(i).toString());

            if (i + 1 != length) {
                sb.append("\n");
            }
        }

        output(sb.toString());
    }

    public void toggleCompleted(boolean isMark, int index) {
        try {
            this.tasks.get(--index).setCompleted(isMark);

            Path filePath = Paths.get(FILE_PATH, FILE_NAME);
            ArrayList<String> fileContent = new ArrayList<>(
                    Files.readAllLines(filePath, StandardCharsets.UTF_8));
            Task updatedTask = this.tasks.get(index);
            String taskString = updatedTask.toString();
            String updateString = "";

            if (taskString.charAt(1) == 'D') {
                String date = ((Deadline) updatedTask)
                        .getDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                updateString = String.format(
                        "D | %s | %s | %s",
                        updatedTask.getCompleted() ? "1" : "0",
                        updatedTask.getDescription(),
                        date);
            } else if (taskString.charAt(1) == 'E') {
                String date = ((Event) updatedTask)
                        .getDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                updateString = String.format(
                        "E | %s | %s | %s",
                        updatedTask.getCompleted() ? "1" : "0",
                        updatedTask.getDescription(),
                        date);
            } else {
                updateString = String.format(
                        "T | %s | %s",
                        updatedTask.getCompleted() ? "1" : "0",
                        updatedTask.getDescription());
            }

            fileContent.set(index, updateString);

            Files.write(filePath, fileContent);
            
            String output = isMark ?
                    "Nice! I've marked this task as done:\n" :
                    "OK, I've marked this task as not done yet:\n";

            output(output + taskString);
        } catch (IndexOutOfBoundsException e) {
            output(INVALID_INDEX_MSG);
        } catch (IOException e) {
            output("OOPS!!! Facing some issues in updating your task to disk. :-(");
        }
    }

    public void delete(int index) {
        try {
            int noOfTasks = this.tasks.size();
            String task = this.tasks.get(index - 1).toString();

            this.tasks.remove(--index);

            noOfTasks = this.tasks.size();

            Path filePath = Paths.get(FILE_PATH, FILE_NAME);
            ArrayList<String> fileContent = new ArrayList<>(
                    Files.readAllLines(filePath, StandardCharsets.UTF_8));

            fileContent.remove(index);

            Files.write(filePath, fileContent);

            String result = "Noted. I've removed this task:\n";
            String pluralTask = noOfTasks > 1 ? "tasks" : "task";

            result += noOfTasks > 0 ?
                    "  " + task +
                    "\nNow you have " + noOfTasks + " " + pluralTask + " in the list." :
                    "  " + task +
                    "\nNow you have no task left.";

            output(result);
        } catch (IndexOutOfBoundsException e) {
            output(INVALID_INDEX_MSG);
        } catch (NumberFormatException e) {
            output(INVALID_INDEX_MSG);
        } catch (IOException e) {
            output("OOPS!!! Facing some issues in deleting your task to disk. :-(");
        }
    }

    public boolean isNumeric(String arg) {
        if (arg == null) {
            output(INVALID_INDEX_MSG);
            return false;
        }

        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException nfe) {
            output(INVALID_INDEX_MSG);
            return false;
        }

        return true;
    }

    public void search(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString);
            int index = 0;
            int length = this.tasks.size();
            StringBuilder sb = new StringBuilder();

            if (length == 0) {
                output("No tasks found based on given date! Also, quit lazing around!");
                return;
            }

            sb.append("Here are the tasks with date, "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + ", in your list:\n");

            for (int i = 0; i < length; ++i) {
                Task task = this.tasks.get(i);

                if (task.getType() == 'D') {
                    Deadline deadline = (Deadline) this.tasks.get(i);

                    if (deadline.getDate().isEqual(date)) {
                        sb.append(++index + ". " + deadline.toString());
                    }
                } else if (task.getType() == 'E') {
                    Event event = (Event) this.tasks.get(i);

                    if (event.getDate().isEqual(date)) {
                        sb.append(++index + ". " + event.toString());
                    }
                }

                if (i + 1 != length) {
                    sb.append("\n");
                }
            }

            output(index > 0 ? sb.toString() : NO_TASK_SEARCH_MSG);
        } catch (DateTimeParseException e) {
            output(INVALID_DATE_MSG);
        }
    }

    public void start() {
        Input input = null;
        Scanner sc = null;

        try {
            File directory = new File(FILE_PATH);

            if (!directory.exists()) {
                directory.mkdir();
            }

            File file = new File(FILE_PATH + FILE_NAME);

            if (!file.exists()) {
                file.createNewFile();
            }

            sc = new Scanner(file);

            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] taskData = task.split("\\s\\|\\s");
                Boolean completed = Integer.parseInt(taskData[1]) == 1;

                if (task.charAt(0) == 'T') {
                    Todo todo = new Todo(taskData[2]);

                    todo.setCompleted(completed);
                    this.tasks.add(todo);
                } else if (task.charAt(0) == 'D') {
                    Deadline deadline = new Deadline(
                            taskData[2], LocalDate.parse(taskData[3]));

                    deadline.setCompleted(completed);
                    this.tasks.add(deadline);
                } else if (task.charAt(0) == 'E') {
                    Event event = new Event(
                            taskData[2], LocalDate.parse(taskData[3]));

                    event.setCompleted(completed);
                    this.tasks.add(event);
                }
            }
        } catch (IOException e) {
            output("OOPS!!! Facing some issues in reading your tasks from disk. :-(");
        }

        sc = new Scanner(System.in);

        output(BOT_ART + "\nHello! I'm " + BOT_NAME + "\nWhat can I do for you?\n");
            
        while (true) {
            try {
                input = new Input(sc.nextLine());

                switch (input.getStates()) {
                case ECHO:
                    output(input.getInput());

                    break;
                case BYE:
                    output(bye());

                    break;
                case ADD:
                    add(input.getInput());

                    break;
                case LIST:
                    list();

                    break;
                case TOGGLE:
                    boolean isMark =
                            input.getInput().split(" ")[0].equalsIgnoreCase("mark");

                    if (input.getArgs().length() == 0) {
                        output(INVALID_MARK_MSG);
                        continue;
                    } else if (isNumeric(input.getArgs())) {
                        toggleCompleted(isMark, Integer.parseInt(input.getArgs()));
                    } else {
                        continue;
                    }

                    break;
                case DELETE:
                    if (input.getArgs().length() == 0) {
                        output(INVALID_DELETE_MSG);
                        continue;
                    } else if (isNumeric(input.getArgs())) {
                        delete(Integer.parseInt(input.getArgs()));
                    } else {
                        continue;
                    }

                    break;
                case SEARCH:
                    search(input.getArgs());

                    break;
                default:
                    throw new DukeException(UNKNOWN_MSG);
                }

                if (input.getStates() == Input.States.BYE) {
                    break;
                }
            } catch (DukeException e) {
                output(UNKNOWN_MSG);
            } catch (IllegalArgumentException e) {
                output(BROKEN_MSG + " " + e.getMessage());
            }
        }

        sc.close(); 
    }

    public static void main(String[] args) {
        Duke abby = new Duke();

        abby.start();
    }
}