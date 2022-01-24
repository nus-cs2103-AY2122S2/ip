import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static Scanner sc;
    private static String filepath = "./data";
    private static String savefilepath = "./data/dukesave.txt";

    enum TaskType{
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Reads the existing file. If there is existing file, then add to the ArrayList.
     *
     * @throws IOException If files cannot be read or created.
     */
    private static void getSaveFile() throws IOException{
        try {
            File saveFolder = new File(filepath);
            File saveFile = new File(savefilepath);
            saveFolder.mkdir();
            saveFile.createNewFile();
            Scanner scanner = new Scanner(saveFile);
            while (scanner.hasNext()) {
                String currLine = scanner.nextLine();
                String[] currTaskLine = currLine.split("\\|");
                switch (currTaskLine[0]) {
                case "T":
                    ToDo todoTask = new ToDo(currTaskLine[2]);
                    if (currTaskLine[1].equals("1")) {
                        todoTask.setChecked();
                    }
                    taskList.add(todoTask);
                    break;
                case "D":
                    Deadline deadlineTask = new Deadline(currTaskLine[2], currTaskLine[3]);
                    if (currTaskLine[1].equals("1")) {
                        deadlineTask.setChecked();
                    }
                    taskList.add(deadlineTask);
                    break;
                case "E":
                    Event eventTask = new Event(currTaskLine[2], currTaskLine[3]);
                    if (currTaskLine[1].equals("1")) {
                        eventTask.setChecked();
                    }
                    taskList.add(eventTask);
                    break;
                default:
                    System.out.println("Opps! Your save files cannot be read. @.@");
                }
            }
        } catch (IOException e) {
            System.out.println("Opps! An error occurred. @.@");
        }

    }

    /**
     * Writes the task in ArrayList into the file.
     *
     * @throws IOException if cannot write into the file.
     */
    public static void writeSaveFile() throws IOException {
        try {
            FileWriter writer = new FileWriter(savefilepath);
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i).saveToFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred. @.@");
        }
    }

    /**
     * To handle the user input.
     */
    private static void getUserInput(){
        String first_word = sc.next();
        String remaining_word = sc.nextLine().trim();
        try {
            switch (first_word) {
            case "bye":
                exit();
                break;
            case "list":
                displayList();
                break;
            case "deadline":
                if (remaining_word.equals("")) {
                    errorMessage("OOPS!!! The description of a deadline cannot be empty. :<");
                }
                addTasks(remaining_word, TaskType.DEADLINE);
                break;
            case "event":
                if (remaining_word.equals("")) {
                    errorMessage("OOPS!!! The description of a event cannot be empty. :<");
                }
                addTasks(remaining_word,TaskType.EVENT);
                break;
            case "todo":
                if (remaining_word.equals("")) {
                    errorMessage("OOPS!!! The description of a todo cannot be empty. :<");
                }
                addTasks(remaining_word,TaskType.TODO);
                break;
            case "mark":
                if (remaining_word.equals("")) {
                    errorMessage("OOPS!!! Please input a number. :<");
                }
                markTaskList(remaining_word);
                break;
            case "unmark":
                if (remaining_word.equals("")) {
                    errorMessage("OOPS!!! Please input a number. :<");
                }
                unmarkTaskList(remaining_word);
                break;
            case "delete":
                if (remaining_word.equals("")) {
                    errorMessage("OOPS!!! Please input a number. :<");
                }
                deleteTask(remaining_word);
                break;
            default:
                errorMessage("OOPS!!! I'm sorry, but I don't know what that means :<");
            }
        } catch (DukeException error) {
            System.out.println(error.getMessage());
            getUserInput();
        } catch (IOException e) {
            System.out.println("Opps! Saving error occurred. @.@");
        }
    }

    /**
     * To show the error message to user.
     *
     * @param message error message to be display to warn user.
     * @throws DukeException error exception shown if empty.
     */
    private static void errorMessage(String message) throws DukeException {
        throw new DukeException(message);
    }

    /**
     * To exit when user input "bye".
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * To display the list of tasks.
     */
    private static void displayList() {
        if (taskList.size() == 0) {
            System.out.println("No task for now");
            getUserInput();
        } else {
            System.out.println("Here are the tasks in your list:");
            for(int i = 1; i < taskList.size() + 1; i++){
                Task currTask = taskList.get(i - 1);
                System.out.println(i + ". " + currTask);
            }
            getUserInput();
        }
    }

    /**
     * To add the different tasks into the list.
     *
     * @param task task to be added.
     * @param type type of task.
     */
    private static void addTasks(String task, TaskType type) throws IOException {
        System.out.println("Got it. I've added this task:");

        switch (type) {
        case TODO:
            ToDo todoTask = new ToDo(task);
            taskList.add(todoTask);
            System.out.println(todoTask + "\n" + "Now you have " + taskList.size()
                    + " tasks in the list.");
            writeSaveFile();
            getUserInput();
            break;
        case EVENT:
            String[] eventActions = task.split("/at", 2);
            Event eventTask = new Event(eventActions[0].trim(), eventActions[1].trim());
            taskList.add(eventTask);
            System.out.println(eventTask + "\n" + "Now you have " + taskList.size()
                    + " tasks in the list.");
            writeSaveFile();
            getUserInput();
            break;
        case DEADLINE:
            String[] deadlineActions = task.split("/by", 2);
            Deadline deadlineTask = new Deadline(deadlineActions[0].trim(), deadlineActions[1].trim());
            taskList.add(deadlineTask);
            System.out.println(deadlineTask + "\n" + "Now you have " + taskList.size()
                    + " tasks in the list.");
            writeSaveFile();
            getUserInput();
            break;
        }
    }

    /**
     * To mark the task as completed.
     *
     * @param task the task that needed to be mark.
     */
    private static void markTaskList(String task) throws IOException {
        int currTaskNum = Integer.parseInt(task);
        Task currTask = taskList.get(currTaskNum - 1);
        currTask.setChecked();
        System.out.println("Nice! I've marked this task as done:\n"
                + currTask);
        writeSaveFile();
        getUserInput();
    }

    /**
     * To unmark the task as not completed.
     *
     * @param task the task to be unmarked.
     */
    private static void unmarkTaskList(String task) throws IOException {
        int currTaskNum = Integer.parseInt(task);
        Task currTask = taskList.get(currTaskNum - 1);
        currTask.setUnchecked();
        System.out.println("OK, I've marked this task as not done yet:\n"
                + currTask);
        writeSaveFile();
        getUserInput();
    }

    /**
     * To delete the tasks from the list.
     *
     * @param task task to be deleted.
     */
    private static void deleteTask(String task) throws IOException {
        System.out.println("Noted. I've removed this task:");
        int currTaskNum = Integer.parseInt(task);
        Task currTask = taskList.get(currTaskNum - 1);
        taskList.remove(currTaskNum - 1);
        System.out.println(currTask + "\n" + "Now you have " + taskList.size()
                + " tasks in the list.");
        writeSaveFile();
        getUserInput();
    }

    public static void main(String[] args) throws IOException {
        getSaveFile();
        String greet = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greet);
        sc = new Scanner(System.in);
        getUserInput();
        sc.close();
    }
}