import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Utils {
    public static void printLogo() {
        System.out.println("____________________________________________________________");
        System.out.println("**       **  ******  ******    *****");
        System.out.println("* *     * *  *       *         *    *");
        System.out.println("*  *   *  *  ******  ******    *****");
        System.out.println("*   * *   *  *       *         *");
        System.out.println("*    *    *  ******  ******    *");
        System.out.println("Hello! I'm Meep\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static String[] parseUserInput(String userInput, List<Task> tasks) throws InvalidInputException {
        // catch empty input
        if (userInput.trim().length() == 0)
            throw new InvalidInputException("Empty");
        String[] arr = userInput.split(" ", 2);
        // command might be "list" or "bye"
        if (arr[0].equals("bye") && arr.length == 1) {
            return arr;
        } else if (arr[0].equals("list")&& (arr.length == 1||arr.length==2)) {
            return arr;
        } else {
            if (arr[0].equals("mark") || arr[0].equals("unmark") || arr[0].equals("delete")) {
                String theRest = arr[1];
                int num;
                try {
                    num = Integer.parseInt(theRest);
                    if (num < 0 || num > tasks.size())
                        throw new InvalidInputException("Task number out of range");
                    return arr;
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Invalid task number. Please enter a valid integer.");
                }
            } else if (arr[0].equals("deadline") || arr[0].equals("event")) {
                String[] output = arr[1].split("/", 2);

                if (output.length == 1)
                    throw new InvalidInputException("Invalid " + arr[0] + " format. eg. deadline return book /by 02/12/2019 18:00.");

                String[] date = output[1].split(" ", 2);

                // check Prepositions of Time/Date
                if (!date[0].equals("on") && !date[0].equals("by") && !date[0].equals("at") && !date[0].equals("in"))
                    throw new InvalidInputException("Invalid Prepositions of Time. Please use 'in' 'at' 'by' or 'on'.");

                String[] result = new String[4];
                result[0] = arr[0];
                result[1] = output[0];
                result[2] = date[0];
                result[3] = date[1];

                return result;
            } else if (arr[0].equals("todo")) {
                return arr;
            } else {
                throw new InvalidInputException("Invalid command. Please try 'list/bye/mark/unmark/event/deadline/todo'. ");
            }
        }
    }

    public static LocalDateTime parseDate(String date) {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime formattedDate = LocalDateTime.parse(date, format);
            return formattedDate;
        } catch (DateTimeException e) {
            throw new DateTimeException(date + " can't be formatted! Please format the date/time as dd/MM/yyyy HH:mm");
        }
    }

    public static String printDate(LocalDateTime date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return date.format(format);
    }


    public static void printTaskList(List<Task> taskList) {
        int i = 1;
        for (Task task : taskList) {
            System.out.println("     " + i + ".  " + task.toString());
            i++;
        }
    }

    public static void printTaskList(List<Task> taskList,LocalDateTime date) {
        int i = 1;
        for (Task task : taskList) {
            if(task instanceof Deadline){
                if(((Deadline) task).getDate().isBefore(date)){
                    System.out.println("     " + i + ".  " + task.toString());
                }
            }else if(task instanceof Event) {
                if (((Event) task).getDate().isBefore(date)) {
                    System.out.println("     " + i + ".  " + task.toString());
                }
            }
            i++;
        }
    }

    private static String getPath() {
        String home = System.getProperty("user.dir");
        // works on *nix
        // works on Windows
        String path = home + "/src/main/java/data.txt";
        boolean directoryExists = new java.io.File(path).exists();
        return path;
    }

    public static List<Task> readTaskFile() throws IOException {

        List<Task> result = new ArrayList<>();
        String path = getPath();
        // Open the file
        FileInputStream fstream = null;

        try {
            fstream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
//            System.out.println("File not found under " + path);
            throw new FileNotFoundException("File not found under " + path);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        // Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            System.out.println(strLine);
            String[] parts = strLine.split("\\|");
            //  remove leading and trailing spaces
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }

            if (parts[0].equals("T")) {
                result.add(new ToDo(parts[2], parts[1].equals("1")));
            } else if (parts[0].equals("D")) {
                result.add(new Deadline(parts[2], parts[1].equals("1"), parseDate(parts[3])));
            } else if (parts[0].equals("E")) {
                result.add(new Event(parts[2], parts[1].equals("1"), parseDate(parts[3])));
            } else {
                System.out.println(parts[0]);
            }
        }

        // Close the input stream
        fstream.close();
        return result;
    }


    public static boolean saveTasktoFile(List<Task> taskList) throws FileNotFoundException {
        String path = getPath();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            FileWriter fw = new FileWriter(path);
            for (Task task : taskList) {
                System.out.println(task.getClass());
                if (task.getClass() == ToDo.class) {
                    fw.write("T | " + (task.getDone() ? "1 | " : "0 | ") + task.getTitle() + System.lineSeparator());
                } else if (task.getClass() == Deadline.class) {
                    fw.write("D | " + (task.getDone() ? "1 | " : "0 | ") + task.getTitle() + " | " + ((Deadline) task).getDate().format(format) + System.lineSeparator());
                } else if (task.getClass() == Event.class) {
                    fw.write("E | " + (task.getDone() ? "1 | " : "0 | ") + task.getTitle() + " | " +  ((Event) task).getDate().format(format) + System.lineSeparator());
                }
            }

            fw.close();
        } catch (IOException e) {
            throw new FileNotFoundException("File not found under " + path);
        }


        return true;
    }
}