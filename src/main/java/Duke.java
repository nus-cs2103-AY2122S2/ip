import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List<Task> store = new ArrayList<>();

        File outputDir = new File("./data");
        File output = new File("./data/output.txt");
        try {
            if (output.exists()) {
                Scanner fr = new Scanner(output);
                while (fr.hasNextLine()) {
                    String currTask = fr.nextLine();
                    String[] tokens = currTask.split("\\| ");
                    if (tokens[0].charAt(0) == "T".charAt(0)) {
                        Task task = new Todo("todo " + tokens[2]);
                        if (Integer.parseInt(tokens[1].trim()) == 1) {
                            task.toggleStatus();
                        }
                        store.add(task);
                    }
                    if (tokens[0].charAt(0) == "D".charAt(0)) {
                        Task task = new Deadline("deadline " + tokens[2] + "/by " + tokens[3]);
                        if (Integer.parseInt(tokens[1].trim()) == 1) {
                            task.toggleStatus();
                        }
                        store.add(task);
                    }
                    if (tokens[0].charAt(0) == "E".charAt(0)) {
                        Task task = new Event("event " + tokens[2] + "/at " + tokens[3]);
                        if (Integer.parseInt(tokens[1].trim()) == 1) {
                            task.toggleStatus();
                        }
                        store.add(task);
                    }

                }
                fr.close();

            } else {
                if (!outputDir.exists()) {
                    outputDir.mkdir();
                }
            }
        } catch (IOException e) {
            System.err.println("Something went wrong with loading backup file!");
        }


//        File dataDir = new File("./data");
//        if (!dataDir.exists()) {
//            dataDir.mkdir();
//        } else {
//            System.out.println("directory exists");
//            System.out.println();
//        }
//        try {
//            FileWriter myWriter = new FileWriter("./output.txt");
//            myWriter.write("hi");
//            myWriter.close();
//        } catch (Exception e) {
//
//        }

        Scanner sc = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Ron\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        String input = sc.nextLine();
        String bye = "bye";
        String list = "list";
        String mark = "mark";
        String unmark = "unmark";

        while (!input.equals(bye)) {
            System.out.println("____________________________________________________________");
            try {
                String trimmedText = input.trim();
                if (input.contains(unmark)) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= store.size() || index < 0) {
                        throw new IndexOutOfStoreException();
                    }
                    if (!store.get(index).getIsDone()) {
                        throw new ToggleException(false);
                    }
                    System.out.println("No problem! The following task is marked as not done yet:");
                    store.get(index).toggleStatus();
                    printTask(store.get(index));
                } else if (input.contains(mark)) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index >= store.size() || index < 0) {
                        throw new IndexOutOfStoreException();
                    }
                    if (store.get(index).getIsDone()) {
                        throw new ToggleException(true);
                    }
                    System.out.println("Good job! The following task is marked as done:");
                    store.get(index).toggleStatus();
                    printTask(store.get(index));
                } else if (input.contains("delete")) {
                    if (trimmedText.length() == "delete".length()) {
                        throw new DeleteIndexException();
                    }
                    int index = Integer.parseInt(input.substring(7));
                    if (index > store.size()) {
                        throw new InvalidIndexException();
                    }
                    System.out.println("OK, the following task is removed:");
                    System.out.println(store.remove(index - 1));
                    System.out.println("There are " + store.size() + " task(s) in the list.");
                } else if (input.equals(list)) {
                    if (store.isEmpty()) {
                        System.out.println("You have no pending tasks on your list :)");
                    } else {
                        System.out.println("The tasks on your list are as follows:");
                        int i = 1;
                        for (Task task : store) {
                            System.out.print(i + ".");
                            printTask(task);
                            i++;
                        }
                    }
                } else if (input.contains("todo")) {
                    if (input.replace("todo", "").trim().length() == 0) {
                        throw new EmptyDescriptionException("todo");
                    }
                    Task task = new Todo(input);
                    store.add(task);
                    System.out.println("Task added!");
                    System.out.println(task);
                    System.out.println("There are " + store.size() + " task(s) in the list.");
                } else if (input.contains("deadline")) {
                    if (input.replace("deadline", "").trim().length() == 0) {
                        throw new EmptyDescriptionException("deadline");
                    } else if (trimmedText.charAt(trimmedText.length() - 1) == "/".charAt(0)) {
                        throw new MissingDateException();
                    } else if (!input.contains("/")) {
                        throw new WrongDateSyntaxException();
                    }
                    Task task = new Deadline(input);
                    if (task.description.trim().length() == 0) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    store.add(task);
                    System.out.println("Task added!");
                    System.out.println(task);
                    System.out.println("There are " + store.size() + " task(s) in the list.");
                } else if (input.contains("event")) {
                    if (input.replace("event", "").trim().length() == 0) {
                        throw new EmptyDescriptionException("event");
                    } else if (trimmedText.charAt(trimmedText.length() - 1) == "/".charAt(0)) {
                        throw new MissingDateException();
                    } else if (!input.contains("/")) {
                        throw new WrongDateSyntaxException();
                    }
                    Task task = new Event(input);
                    store.add(task);
                    System.out.println("Task added!");
                    System.out.println(task);
                    System.out.println("There are " + store.size() + " task(s) in the list.");
                } else {
                    throw (new UnidentifiedException());
                }
            } catch (RonException e) {
                System.out.println(e);
            }

            System.out.println("____________________________________________________________");
            if (!sc.hasNext()) {
                break;
            }
            input = sc.nextLine();
        }

        try {
            FileWriter fw = new FileWriter(output, false);
            for (Task task : store) {
                fw.write(new StorageParser(task).toString());
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Something went wrong with loading backup file!");
        }

        System.out.println("Bye. Stay safe and have a nice day!");
    }

    public static void printTask(Task task) {
        System.out.println(task);
    }
}
