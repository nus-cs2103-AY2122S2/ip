import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.NoSuchElementException;
import java.time.format.DateTimeParseException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;
import java.nio.charset.StandardCharsets;

public class Duke {
    public static void main(String[] args) throws IOException {

        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nextLine = br.readLine().strip().replaceAll(" +", " ");

        ArrayList<Task> list = new ArrayList<Task>();

        Path newFile;
        java.nio.file.Path path = java.nio.file.Paths.get("../../../data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        try {
            if (!directoryExists) {
                java.nio.file.Files.createDirectories(path);
            }
            Path dukeFile = path.resolve("duke.txt");
            newFile = Files.createFile(dukeFile);
        } catch (FileAlreadyExistsException e) {
            newFile = Paths.get("../../../data/duke.txt");
            list = populateList(newFile.toString());
        }
        while (!nextLine.equals("bye")) {
            System.out.println("__________________________________________________");
            String firstWord = nextLine.split(" ")[0];
            if (firstWord.equals("mark")) {
                try {
                    int indexToMark = Integer.parseInt(nextLine.substring(5)) - 1;
                    list.set(indexToMark, list.get(indexToMark).mark());
                    System.out.println("Nice! I've marked this task as done:\n  " 
                            + list.get(indexToMark));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please choose a valid task! (Your list has " 
                            + list.size() + " tasks)");
                } catch (NumberFormatException e) {
                    System.out.println("Please input an integer!");
                }
            } else if (firstWord.equals("unmark")) {
                try {
                    int indexToUnmark = Integer.parseInt(nextLine.substring(7)) - 1;
                    list.set(indexToUnmark, list.get(indexToUnmark).unmark());
                    System.out.println("OK, I've marked this task as not done yet:\n  " 
                            + list.get(indexToUnmark));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please choose a valid task! (Your list has " 
                            + list.size() + " tasks)");
                } catch (NumberFormatException e) {
                    System.out.println("Please input an integer!");
                }
            } else if (firstWord.equals("list")) {
                if (nextLine.split(" ").length > 1) {
                    System.out.println("Uh-oh! There should not be any words after 'list'.");
                } else if (list.isEmpty()) {
                    System.out.println("Uh-oh! List is empty!");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                }
            } else if (firstWord.equals("delete")) {
                try {
                    int indexToDelete = Integer.parseInt(nextLine.substring(7)) - 1;
                    Task deletedTask = list.remove(indexToDelete);
                    System.out.println("Noted. I've removed this task:\n  " 
                            + deletedTask 
                            + "\n Now you have " + list.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please choose a valid task! (Your list has " 
                            + list.size() + " tasks)");
                } catch (NumberFormatException e) {
                    System.out.println("Please input an integer!");
                }
            } else {
                String taskType = nextLine.split(" ")[0];
                Optional<Task> task = Optional.empty();
                if (taskType.equals("todo")) {
                    if (nextLine.split(" ").length == 1) {
                        System.out.println("Uh-oh! There is nothing to do here!");
                    } else {
                        task = Optional.of(new Todo(nextLine.substring(5)));
                    }
                } else if (taskType.equals("deadline")) {
                    try {
                        String description = nextLine.split(" /by ")[0].substring(9);
                        String by = nextLine.split(" /by ")[1];
                        task = Optional.of(new Deadline(description, by));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Eh? Did you mistype the format?");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Uh-oh! There's no deadline here!");
                    } catch (DateTimeParseException e) {
                        System.out.println("Please type a valid date! (Format: YYYY-MM-DD)");
                    }
                } else if (taskType.equals("event")) {
                    try {
                        String description = nextLine.split(" /at ")[0].substring(6);
                        String at = nextLine.split(" /at ")[1];
                        task = Optional.of(new Event(description, at));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Eh? Did you mistype the format?");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Uh-oh! There's no event here!");
                    } catch (DateTimeParseException e) {
                        System.out.println("Please type a valid date! (Format: YYYY-MM-DD)");
                    }
                } else {
                    System.out.println("Sorry! Please speak Duke :(");
                }
                try {
                    list.add(task.get());
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.get());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } catch (NoSuchElementException e) {
                    System.out.println("Please try again!");
                }
            }

            System.out.println("__________________________________________________");
            ArrayList<String> data = new ArrayList<String>();
            for (Task task : list) {
                data.add(task.toString());
            }
            java.nio.file.Files.write(newFile, data, StandardCharsets.UTF_8);
            nextLine = br.readLine().strip().replaceAll(" +", " ");
        }

        System.out.println("__________________________________________________\n" 
                + "Bye. Hope to see you again soon!"
                + "\n__________________________________________________");
        br.close();
    }

    static ArrayList<Task> populateList(String fileName) throws IOException {
        ArrayList<Task> list = new ArrayList<Task>();
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String line = file.readLine();
        while (line != null) {
            boolean isMarked = String.valueOf(line.charAt(4)).equals("X");
            if (String.valueOf(line.charAt(1)).equals("T")) {
                Task task = new Todo(line.substring(7));
                if (isMarked) {
                    task = task.mark();
                }
                list.add(task);
            } else if (String.valueOf(line.charAt(1)).equals("D")) {
                String tempDescription = line.split("by: ")[0].substring(7);
                int tempDescLength = tempDescription.length();
                String description = tempDescription.substring(0, tempDescLength - 2);
                String tempTimeBy = line.split("by: ")[1];
                int endIdx = tempTimeBy.lastIndexOf(")");
                String timeBy = tempTimeBy.substring(0, endIdx);
                Task task = new Event(description, timeBy);
                if (isMarked) {
                    task = task.mark();
                }
                list.add(task);
            } else if (String.valueOf(line.charAt(1)).equals("E")) {
                String tempDescription = line.split("at: ")[0].substring(7);
                int tempDescLength = tempDescription.length();
                String description = tempDescription.substring(0, tempDescLength - 2);
                String tempTimeBy = line.split("at: ")[1];
                int endIdx = tempTimeBy.lastIndexOf(")");
                String timeBy = tempTimeBy.substring(0, endIdx);
                Task task = new Event(description, timeBy);
                if (isMarked) {
                    task = task.mark();
                }
                list.add(task);
            }
            line = file.readLine();
        }
        file.close();
        return list;


    }
}
