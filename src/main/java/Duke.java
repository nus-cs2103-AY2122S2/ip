import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;

public class Duke {

    private static final String TEXT_DATA_FILE_PATH = "data.txt";

    private static void appendToFile(String filePath, String textToAdd) throws IOException {
        File file = new File(TEXT_DATA_FILE_PATH);
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void updateFile(String filePath, ArrayList<Task> dataArrList) throws IOException {
        File file = new File(TEXT_DATA_FILE_PATH);
        String updatedFileContents = "";
        for (int i = 0; i < dataArrList.size(); i++) {
            updatedFileContents = updatedFileContents.concat(
                String.format("    %s\n", dataArrList.get(i).identify()));
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(updatedFileContents);
        fw.close();
    }

    private static void printFileContents(String filePath) throws FileNotFoundException{
        File file = new File(TEXT_DATA_FILE_PATH);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
    }

    private static void loadDataFromFile(String filePath, ArrayList<Task> taskArrayList)throws FileNotFoundException{
        File file = new File(TEXT_DATA_FILE_PATH);
        if (Files.notExists(Paths.get(TEXT_DATA_FILE_PATH))) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                new DukeException();
            }
        } else {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String data = sc.nextLine();
                String[] processedData = data.trim().split("]", 3);
                if (processedData[0].contains("T")) {
                    ToDo todo = new ToDo(processedData[2].trim());
                    if (processedData[1].contains("X")) {
                        todo.setIsDone(true);
                    }
                    taskArrayList.add(todo);
                } else {
                    String[] processedDataDescription = processedData[2].split("\\(by:", 2);
                    String temp = processedDataDescription[1].replace(")", "");
                    LocalDate date = null;
                    try {
                        date = LocalDate.parse(temp.trim(),DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    } catch (DateTimeParseException e) {
                        new DukeException();
                        break;
                    }
                    if (processedData[0].contains("E")) {
                        Event event = new Event(processedDataDescription[0].trim(), date);
                        if (processedData[1].contains("X")) {
                            event.setIsDone(true);
                        }
                        taskArrayList.add(event);
                    } else if (processedData[0].contains("D")) {
                        Deadline deadline = new Deadline(processedDataDescription[0].trim(), date);
                        if (processedData[1].contains("X")) {
                            deadline.setIsDone(true);
                        }
                        taskArrayList.add(deadline);
                    } else {
                        System.out.println("Error in loading data");
                        new DukeException();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(Commands.HI.toString());
        ArrayList<Task> tasks = new ArrayList<Task>();
        loadDataFromFile(TEXT_DATA_FILE_PATH, tasks);
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String comm = sc.nextLine();
            String[] processedCommand = comm.split(" ", 2);
            Integer taskIndex = null;
            switch (processedCommand[0]) {
            case "hi":
                System.out.println(Commands.HI.toString());
                break;
            case "bye":
                System.out.println(Commands.BYE.toString());
                isExit = true;
                break;
            case "list":
                System.out.println(Commands.LIST.toString());
                printFileContents(TEXT_DATA_FILE_PATH);
                break;
            case "mark":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                }
                try {
                    taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                } catch (NumberFormatException e) {
                    new DukeException();
                    break;
                }

                //Mark task
                tasks.get(taskIndex).setIsDone(true);
                String output = String.format(
                    "%s    %s", Commands.MARK.toString(), tasks.get(taskIndex).identify());
                System.out.println(output);

                //Updating file
                try {
                    updateFile(TEXT_DATA_FILE_PATH, tasks);
                } catch (IOException e) {
                    new DukeException();
                    break;
                }
                break;
            case "unmark":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                }
                try {
                    taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                } catch (NumberFormatException e) {
                    new DukeException();
                    break;
                }

                //Unmark task
                tasks.get(taskIndex).setIsDone(false);
                System.out.println(String.format(
                    "%s      %s", Commands.UNMARK.toString(), tasks.get(taskIndex).identify()));

                //Updating file
                try {
                    updateFile(TEXT_DATA_FILE_PATH, tasks);
                } catch (IOException e) {
                    new DukeException();
                    break;
                }
                break;
            case "deadline":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                } else if (!processedCommand[1].contains("/by")) {
                    new DukeException();
                    break;
                }

                //Processing input
                String[] inputForDeadlineConstructor = processedCommand[1].split("/by", 2);
                inputForDeadlineConstructor[0] = inputForDeadlineConstructor[0].trim();
                LocalDate deadlineDate = null;
                try {
                    deadlineDate = LocalDate.parse(inputForDeadlineConstructor[1].trim());
                } catch (DateTimeParseException e) {
                    new DukeException();
                    break;
                }

                //Create deadline
                Deadline deadline = new Deadline(inputForDeadlineConstructor[0], deadlineDate);
                System.out.println(String.format(
                    "%s      %s", Commands.ADD.toString(), deadline.identify()));
                tasks.add(deadline);
                System.out.println(String.format("    Now you have %d tasks in the list.", tasks.size()));
                try {
                    appendToFile(TEXT_DATA_FILE_PATH, "    " + deadline.identify() + "\n");
                } catch (IOException e) {
                    new DukeException();
                    break;
                }
                break;
            case "todo":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                }

                //Create Todo
                ToDo todo = new ToDo(processedCommand[1]);
                System.out.println(String.format(
                    "%s      %s", Commands.ADD.toString(), todo.identify()));
                tasks.add(todo);
                System.out.println(String.format("    Now you have %d tasks in the list.", tasks.size()));
                try {
                    appendToFile(TEXT_DATA_FILE_PATH, "    " + todo.identify() + "\n");
                } catch (IOException e) {
                    new DukeException();
                    break;
                }
                break;
            case "event":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                } else if (!processedCommand[1].contains("/by")) {
                    new DukeException();
                    break;
                }

                //Input processing
                String[] inputForEventConstructor = processedCommand[1].split("/by", 2);
                inputForEventConstructor[0] = inputForEventConstructor[0].trim();
                LocalDate eventDate = null;
                try {
                    eventDate = LocalDate.parse(inputForEventConstructor[1].trim());
                } catch (DateTimeParseException e) {
                    new DukeException();
                    break;
                }

                //Create event
                Event event = new Event(inputForEventConstructor[0], eventDate);
                System.out.println(String.format(
                    "%s      %s", Commands.ADD.toString(), event.identify()));
                tasks.add(event);
                System.out.println(String.format("    Now you have %d tasks in the list.", tasks.size()));
                try {
                    appendToFile(TEXT_DATA_FILE_PATH, "    " + event.identify() + "\n");
                } catch (IOException e) {
                    new DukeException();
                    break;
                }
                break;
            case "delete":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                }
                try {
                    taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                } catch (NumberFormatException e) {
                    new DukeException();
                    break;
                }

                Task deletedTask = tasks.remove((int)taskIndex);
                System.out.println(String.format(
                    "%s      %s\n    Now you have %d tasks in the list",
                        Commands.DELETE.toString(), deletedTask.identify(), tasks.size()));
                try {
                    updateFile(TEXT_DATA_FILE_PATH, tasks);
                } catch (IOException e) {
                    new DukeException();
                    break;
                }
                break;
            default:
                new DukeException();
            }
        }
        sc.close();
    }
}
