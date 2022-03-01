import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Pac {
    static String logo = " ____     ___    _____\n"
            + "|  _ \\   / _ \\  |  ___|\n"
            + "| |_| | | |_| | | |\n"
            + "|  __/  | | | | | |___\n"
            + "|_|     |_| |_| |_____|\n";
    static String newline = "----------------------------------------------------";

    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException{

        System.out.println("\n" + newline + "\n" + newline + "\n" + logo);
        System.out.println("Hello there! I'm Pac, your very own Personal Assistant ChatBot.\nHow may I help you?");
        System.out.println(newline + "\n");

        Scanner sc = new Scanner(System.in);
        File file = new File("data/pac.txt");
        File folder = new File("data/");

        if (!folder.exists()) {
            folder.mkdir();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        while (true) {
            String input = sc.nextLine();
            try {
                String[] inputArray = input.split(" ", 2);
                String firstWord = inputArray[0].toLowerCase();
                Keyword keyword;

                switch (firstWord) {
                    case "bye":
                        keyword = Keyword.BYE;
                        break;
                    case "list":
                        keyword = Keyword.LIST;
                        break;
                    case "mark":
                        keyword = Keyword.MARK;
                        break;
                    case "unmark":
                        keyword = Keyword.UNMARK;
                        break;
                    case "todo":
                        keyword = Keyword.TODO;
                        break;
                    case "deadline":
                        keyword = Keyword.DEADLINE;
                        break;
                    case "event":
                        keyword = Keyword.EVENT;
                        break;
                    case "delete":
                        keyword = Keyword.DELETE;
                        break;
                    default:
                        keyword = Keyword.ERROR;
                        break;
                }

                switch (keyword) {
                    case BYE:
                        exitPac();
                        break;
                    case LIST:
                        displayList();
                        break;
                    case MARK:
                        if (inputArray.length == 1) {
                            throw new PacException("MARK should be followed by a integer.");
                        }
                        try {
                            markTask(Integer.parseInt(inputArray[1]) - 1);
                            write("data/pac.txt", tasks.get(0).toString());
                            for (int i = 1; i < tasks.size(); i++) {
                                append("data/pac.txt", tasks.get(i).toString());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(newline + "\nSorry! MARK should be followed by a integer.\n"
                                    + newline + "\n");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(newline + "\nSorry! Please mention a valid task number.\n"
                                    + newline + "\n");
                        } catch (IOException e) {
                            System.out.println(newline + "\nSorry! File cannot be written.\n"
                                    + newline + "\n");
                        }
                        break;
                    case UNMARK:
                        if (inputArray.length == 1) {
                            throw new PacException("UNMARK should be followed by a integer.");
                        }
                        try {
                            unmarkTask(Integer.parseInt(inputArray[1]) - 1);
                            write("data/pac.txt", tasks.get(0).toString());
                            for (int i = 1; i < tasks.size(); i++) {
                                append("data/pac.txt", tasks.get(i).toString());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(newline + "\nSorry! MARK should be followed by a integer.\n"
                                    + newline + "\n");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(newline + "\nSorry! Please mention a valid task number.\n"
                                    + newline + "\n");
                        } catch (IOException e) {
                            System.out.println(newline + "\nSorry! File cannot be written.\n"
                                    + newline + "\n");
                        }
                        break;
                    case TODO:
                        if (inputArray.length == 1) {
                            throw new PacException("Please mention a task.");
                        }
                        try {
                            addToDo(inputArray[1]);
                            append("data/pac.txt", tasks.get(tasks.size() - 1).toString());
                        } catch (IOException e) {
                            System.out.println(newline + "\nSorry! File cannot be written.\n"
                                    + newline + "\n");
                        }
                        break;
                    case DEADLINE:
                        if (inputArray.length == 1) {
                            throw new PacException("Please mention a task.");
                        }
                        try {
                            addDeadline(inputArray[1]);
                            append("data/pac.txt", tasks.get(tasks.size() - 1).toString());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(newline + "\nSorry! Command format is incorrect.\n"
                                    + newline + "\n");
                        } catch (IOException e) {
                            System.out.println(newline + "\nSorry! File cannot be written.\n"
                                    + newline + "\n");
                        }
                        break;
                    case EVENT:
                        if (inputArray.length == 1) {
                            throw new PacException("Please mention a task.");
                        }
                        try {
                            addEvent(inputArray[1]);
                            append("data/pac.txt", tasks.get(tasks.size() - 1).toString());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(newline + "\nSorry! Command format is incorrect.\n"
                                    + newline + "\n");
                        } catch (IOException e) {
                            System.out.println(newline + "\nSorry! File cannot be written.\n"
                                    + newline + "\n");
                        }
                        break;
                    case DELETE:
                        if (inputArray.length == 1) {
                            throw new PacException("DELETE should be followed by a integer.");
                        }
                        try {
                            deleteTask(Integer.parseInt(inputArray[1]) - 1);
                            write("data/pac.txt", tasks.get(0).toString());
                            for (int i = 1; i < tasks.size(); i++) {
                                append("data/pac.txt", tasks.get(i).toString());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(newline + "\nSorry! MARK should be followed by a integer.\n"
                                    + newline + "\n");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(newline + "\nSorry! Please mention a valid task number.\n"
                                    + newline + "\n");
                        } catch (IOException e) {
                            System.out.println(newline + "\nSorry! File cannot be written.\n"
                                    + newline + "\n");
                        }
                        break;
                    case ERROR:
                        throw new PacException("I don't know what this means");
                }
            } catch (PacException e) {
                System.out.println(newline + "\n" + e.getMessage() + "\n" + newline + "\n");
            }
        }
    }

    public static  void write(String file, String text) throws IOException{
        FileWriter fw = new FileWriter(file);
        fw.write(text);
        fw.close();
    }

    public static  void append(String file, String text) throws IOException{
        FileWriter fw = new FileWriter(file, true);
        fw.write(text);
        fw.close();
    }

    public static void addToDo(String description) {
        try {
            if (description.isBlank()) {
                throw new PacException("Please mention a task.");
            }
            Task task = new ToDo(description);
            tasks.add(task);
            System.out.println(newline + "\nadded: " + task.toString());
            System.out.println("You have " + tasks.size() + " tasks in your list" + "\n" + newline + "\n");
        } catch (PacException e) {
            System.out.println(newline + "\n" + e.getMessage() + "\n" + newline + "\n");
        }
    }

    public static void addDeadline(String input) {
       try {
           String[] inputArray = input.split("/by", 2);
           inputArray[1] = inputArray[1].trim();
           if (inputArray[1].isBlank()) {
               throw new PacException("Please enter a valid date or time.");
           }
           Task task = new Deadline(inputArray[0], LocalDate.parse(inputArray[1]));
           tasks.add(task);
           System.out.println(newline + "\nadded: " + task.toString());
           System.out.println("You have " + tasks.size() + " tasks in your list" + "\n" + newline + "\n");
       } catch (PacException e) {
           System.out.println(newline + "\n" + e.getMessage() + "\n" + newline + "\n");
       } catch (DateTimeParseException e) {
           System.out.println(newline + "\n" + "Please enter in correct format" + "\n" + newline + "\n");
       }
    }

    public static void addEvent(String input) {
        try {
            String[] inputArray = input.split("/at", 2);
            inputArray[1] = inputArray[1].trim();
            if (inputArray[1].isBlank()) {
                throw new PacException("Please enter a valid date or time.");
            }
            Task task = new Event(inputArray[0], LocalDate.parse(inputArray[1]));
            tasks.add(task);
            System.out.println(newline + "\nadded: " + task.toString());
            System.out.println("You have " + tasks.size() + " tasks in your list" + "\n" + newline + "\n");
        } catch (PacException e) {
            System.out.println(newline + "\n" + e.getMessage() + "\n" + newline + "\n");
        } catch (DateTimeParseException e) {
            System.out.println(newline + "\n" + "Please enter in correct format" + "\n" + newline + "\n");
        }
    }

    public static void markTask(int index) {
        tasks.get(index).markAsDone();
        System.out.println(newline);
        System.out.println("Task is marked as done. \n" + tasks.get(index));
        System.out.println(newline + "\n");
    }

    public static void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
        System.out.println(newline);
        System.out.println("Task is marked as  not done. \n" + tasks.get(index));
        System.out.println(newline + "\n");
    }

    public static void deleteTask(int index) {
        System.out.println(newline + "\nTask has been deleted: " + tasks.get(index));
        tasks.remove(index);
        System.out.println("You have " + tasks.size() + " tasks in your list" + "\n" + newline + "\n");
    }

    public static void displayList() {
        int i = 1;
        System.out.println(newline);
        if(tasks.isEmpty()) {
            System.out.println("There are no tasks left to complete");
        }
        for (Task task : tasks) {
            System.out.println(i++ +". " + task);;
        }
        System.out.println(newline + "\n");
    }

    public static void exitPac() {
        System.out.println(newline + "\n" + "Goodbye! See you soon. :)\n" + newline);
        System.exit(0);
    }
}