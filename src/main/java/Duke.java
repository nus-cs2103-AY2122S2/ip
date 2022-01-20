import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

public class Duke {
    private static final Set<Task> taskSet = new HashSet<>();
    private final static ArrayList<Task> tasksArrayList = new ArrayList<>();

    public static void greet() {
        String logo = "\n" +
                "   ____                  _                           \n" +
                "  / ___|   ___    _ __  | |_    __ _   _ __     __ _ \n" +
                " | |      / _ \\  | '__| | __|  / _` | | '_ \\   / _` |\n" +
                " | |___  | (_) | | |    | |_  | (_| | | | | | | (_| |\n" +
                "  \\____|  \\___/  |_|     \\__|  \\__,_| |_| |_|  \\__,_|\n" +
                "                                                     \n";
        System.out.println("Hello from\n" + logo + "\nMy name is Cortana, what can I do for you?");
    }

    public static void simpleTodo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        input += scanner.nextLine();

        while(true) {
            if (input.replaceAll("[ |\\t]", "").equalsIgnoreCase("bye")) { //bye, exit
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                if (input.replaceAll("[ |\\t]", "").equalsIgnoreCase("list")) { //list all tasks
                    list();
                } else if (input.toLowerCase().matches("^mark \\d+|^mark -\\d+")) { //mark task as done
                    int index = Integer.parseInt(input.replaceAll("mark ", "")) - 1;
                    markAsDone(index);
                } else if (input.toLowerCase().matches("^unmark \\d+|^unmark -\\d+")) { //mark task as undone
                    int index = Integer.parseInt(input.replaceAll("unmark ", "")) - 1;
                    markAsUndone(index);
                } else if (input.toLowerCase().matches("^delete \\d+|^delete -\\d+")) { //delete a task
                    int index = Integer.parseInt(input.replaceAll("delete ", "")) - 1;
                    delete(index);
                } else if (input.toLowerCase().matches("^delete all")) { //delete a task
                    deleteAll();
                } else { //add task
                    try {
                        //only input the task type and nothing else
                        boolean isEmptyDeadline = input.replaceAll("[ |\\t]", "").equalsIgnoreCase("deadline");
                        boolean isEmptyEvent = input.replaceAll("[ |\\t]", "").equalsIgnoreCase("event");
                        boolean isEmptyTodo = input.replaceAll("[ |\\t]", "").equalsIgnoreCase("todo");
                        String taskType = isEmptyDeadline ? "deadline" : isEmptyEvent ? "event" : "todo";
                        if (isEmptyDeadline || isEmptyEvent || isEmptyTodo ) {
                            String aOrAn = isEmptyEvent ? "an " : "a ";
                            throw new CortanaException("OOPS!!! The description of " + aOrAn + taskType + " cannot be empty!");
                        } else {
                            //input the task type with at least one character after it
                            boolean isNotEmptyDeadline = input.toLowerCase().matches("^deadline .*");
                            boolean isNotEmptyEvent = input.toLowerCase().matches("^event .*");
                            boolean isNotEmptyTodo = input.toLowerCase().matches("^todo .*");
                            boolean hasBy = Pattern.compile("/by .*").matcher(input).find();
                            boolean hasAt = Pattern.compile("/at .*").matcher(input).find();
                            if (isNotEmptyDeadline && hasBy) { //valid deadline command
                                String[] actualTask = input.replaceAll("deadline ", "").split("/by ");
                                String description = actualTask[0];
                                String by = actualTask[1];
                                boolean correctTimeFormat = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}( \\d{4})?").matcher(by).find();
                                if (correctTimeFormat) {
                                    boolean hasTime = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}").matcher(by).find();
                                    Deadline deadline;
                                    if (hasTime) {
                                        LocalDateTime localDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
                                        deadline = new Deadline(description, localDateTime);
                                    } else {
                                        LocalDate localDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-M-d"));
                                        deadline = new Deadline(description, LocalDateTime.of(localDate, LocalTime.MAX));
                                    }
                                    addTask(deadline);
                                } else {
                                    throw new CortanaException("Invalid date time format! Please follow the format yyyy-M-d HHmm!");
                                }
                            } else if (isNotEmptyEvent && hasAt) { //valid event command
                                String[] actualTask = input.replaceAll("event ", "").split("/at ");
                                String description = actualTask[0];
                                String at = actualTask[1];
                                boolean correctTimeFormat = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}( \\d{4})?").matcher(at).find();
                                if (correctTimeFormat) {
                                    boolean hasTime = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}").matcher(at).find();
                                    Event event;
                                    if (hasTime) {
                                        LocalDateTime localDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
                                        event = new Event(description, localDateTime);
                                    } else {
                                        LocalDate localDate = LocalDate.parse(at, DateTimeFormatter.ofPattern("yyyy-M-d"));
                                        event = new Event(description, LocalDateTime.of(localDate, LocalTime.MAX));
                                    }
                                    addTask(event);
                                } else {
                                    throw new CortanaException("Invalid date time format! Please follow the format yyyy-M-d HHmm!");
                                }
                            } else if (isNotEmptyTodo) { //valid todo command
                                String description = input.replaceAll("todo ", "");
                                Todo todo = new Todo(description);
                                addTask(todo);
                            } else if (isNotEmptyDeadline && hasAt) { //used /at for deadline
                                throw new CortanaException("Please use the /by keyword for deadline!");
                            } else if (isNotEmptyEvent && hasBy) { //used /by for event
                                throw new CortanaException("Please use the /at keyword for event!");
                            } else if (isNotEmptyDeadline) { //deadline without specifying time with /by
                                throw new CortanaException("Please specify the deadline time with the /by keyword!");
                            } else if (isNotEmptyEvent) { //event without specifying time with /at
                                throw new CortanaException("Please specify the event time with the /at keyword!");
                            } else { //invalid command
                                throw new CortanaException("I don't know what that means :(");
                            }
                        }
                    } catch (CortanaException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid input time!");
                    }
                }
            }
            input = scanner.next();
            input += scanner.nextLine();
        }
        scanner.close();
    }

    public static void list()  {
        if (tasksArrayList.size() == 0) {
            System.out.println("You are done for the day, or are you?");
        } else {
            for (int i = 0; i < tasksArrayList.size(); i ++) {
                System.out.println(i + 1 + "." + tasksArrayList.get(i));
            }
        }
    }

    public static void addTask(Task task) throws IOException {
        if (taskSet.contains(task)) {
            System.out.println("Task already exists!");
        } else {
            taskSet.add(task);
            tasksArrayList.add(task);
            writeFile();
            String taskOrTasks = tasksArrayList.size() <= 1 ? "task" : "tasks";
            System.out.println("Got it. I've added this task: \n" + " " + task +
                    "\nNow you have " + tasksArrayList.size() + " " + taskOrTasks + " in the list.");
        }
    }

    public static void markAsDone(int index) {
        try {
            Task task = tasksArrayList.get(index);
            task.markAsDone();
            writeFile();
            System.out.println("Nice! I've marked this task as done: \n " + task);
        } catch (Exception e) {
            System.out.println("No such task!");
        }
    }

    public static void markAsUndone(int index) {
        try {
            Task task = tasksArrayList.get(index);
            task.markAsUndone();
            writeFile();
            System.out.println("OK, I've marked this task as not done yet: \n " + task);
        } catch (Exception e) {
            System.out.println("No such task!");
        }
    }

    public static void delete (int index) {
        try {
            Task taskDeleted = tasksArrayList.get(index);
            tasksArrayList.remove(index);
            taskSet.remove(taskDeleted);
            writeFile();
            String taskOrTasks = tasksArrayList.size() <= 1 ? "task" : "tasks";
            System.out.println("Noted. I've removed this task: \n" + " " + taskDeleted + "\n" +
                    "Now you have " + tasksArrayList.size() + " " + taskOrTasks + " in the list.");
        } catch (Exception e) {
            System.out.println("No such task!");
        }
    }

    public static void deleteAll () {
        try {
            taskSet.clear();
            tasksArrayList.clear();
            writeFile();
            System.out.println("All tasks have been removed!");
        } catch (Exception e) {
            System.out.println("No such task!");
        }
    }

    public static void createDirectoryAndFileIfNotExist() {
        try {
            String path = Paths.get("").toAbsolutePath() + "/data/";
            File directory = new File(path);
            boolean wasDirectoryCreated = directory.mkdir();
            if (wasDirectoryCreated) {
                System.out.println("Created directory " + directory);
            }
            File taskFile = new File(path + "task.txt");
            boolean wasFileCreated = taskFile.createNewFile();
            if (wasFileCreated) {
                System.out.println("Created task.txt under " + directory);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong during creating directory or creating file");
        }
    }

    public static void loadFile() {
        try {
            String path = Paths.get("").toAbsolutePath() + "/data/";
            File taskFile = new File(path + "task.txt");
            FileInputStream fileInputStream = new FileInputStream(taskFile);
            Scanner scanner = new Scanner(fileInputStream);
            while(scanner.hasNextLine()) {
                Task taskToAdd = parseTask(scanner.nextLine());
                taskSet.add(taskToAdd);
                tasksArrayList.add(taskToAdd);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public static void writeFile() throws IOException {
        String path = Paths.get("").toAbsolutePath() + "/data/";
        File taskFile = new File(path + "task.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(taskFile, false);
        for (Task task : tasksArrayList) {
            String taskToWrite = task.toString() + '\n';
            fileOutputStream.write(taskToWrite.getBytes(StandardCharsets.UTF_8));
        }
    }

    public static Task parseTask(String taskInString) {
        char type = taskInString.charAt(1);
        boolean status = taskInString.charAt(4) == 'X';
        DateTimeFormatter formatter;
        LocalDateTime localDateTime;
        LocalDate localDate;
        try {
            if (type == 'D') {
                String[] actualTask = taskInString.substring(7).split("\\(by: ");
                String description = actualTask[0];
                String by = actualTask[1].replaceAll("\\)", "");
                boolean hasTime = Pattern.compile("[A-Za-z]* \\d{1,2}, \\d{4} \\d{4}(AM|PM)").matcher(by).find();
                if (hasTime) {
                    formatter = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy hhmma", Locale.ENGLISH);
                    localDateTime = LocalDateTime.parse(by, formatter);
                } else {
                    formatter = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy", Locale.ENGLISH);
                    localDate = LocalDate.parse(by, formatter);
                    localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
                }
                Deadline deadline = new Deadline(description, localDateTime);
                deadline.isDone = status;
                return deadline;
            } else if (type == 'E') {
                String[] actualTask = taskInString.substring(7).split("\\(at: ");
                String description = actualTask[0];
                String at = actualTask[1].replaceAll("\\)", "");
                boolean hasTime = Pattern.compile("[A-Za-z]* \\d{1,2}, \\d{4} \\d{4}(AM|PM)").matcher(at).find();
                if (hasTime) {
                    formatter = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy hhmma", Locale.ENGLISH);
                    localDateTime = LocalDateTime.parse(at, formatter);
                } else {
                    formatter = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy", Locale.ENGLISH);
                    localDate = LocalDate.parse(at, formatter);
                    localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
                }
                Event event = new Event(description, localDateTime);
                event.isDone = status;
                return event;
            } else {
                String description = taskInString.substring(7);
                Todo todo = new Todo(description);
                todo.isDone = status;
                return todo;
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        greet();
        createDirectoryAndFileIfNotExist();
        loadFile();
        simpleTodo();
    }
}