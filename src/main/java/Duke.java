import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

public class Duke {
    private static TaskList taskList = new TaskList();
    private static void writeData() {
        Path dirPath = Paths.get("data");
        Path filePath = Paths.get("data/taskList.txt");
        boolean fileExists = java.nio.file.Files.exists(filePath);
        try {
            Files.createDirectories(dirPath);
            if (!fileExists) {
                Files.createFile(filePath);
            }
            BufferedWriter myWriter = Files.newBufferedWriter(
                                          filePath, StandardOpenOption.CREATE,
                                          StandardOpenOption.TRUNCATE_EXISTING,
                                          StandardOpenOption.WRITE);
            myWriter.write(taskList.toData());
            myWriter.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
            System.out.println("got an IOException");

        }
    }

    private static void readData() {
        Path myPath = Paths.get("data/taskList.txt");
        boolean directoryExists = java.nio.file.Files.exists(myPath);
        if (directoryExists) {
            try {
                BufferedReader myReader = Files.newBufferedReader(myPath);
                myReader.lines().forEach(x -> {
                    String[] parsed = x.split(":");
                    String taskType = parsed[0];
                    String isFinished = parsed[1];
                    Task curTask;
                    if (taskType.equals("T")) {
                        curTask = new Todo(parsed[2]);
                    } else if ( taskType.equals("D") ) {
                        curTask = new Deadline(parsed[2], parsed[3]);
                    } else {
                        curTask = new Event(parsed[2], parsed[3]);
                    }
                    taskList.addTask(curTask);
                    if (isFinished.equals("1")) {
                        taskList.markFinished(taskList.size());
                    }
                });

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        readData();
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello from\n" + Response.LOGO);
        Format.wrapPrint(Response.RESPONSE_WELCOME);
        boolean over = false;
        try {

            while (!over) {
                String userInput = myScanner.nextLine();
                Command command = new Command(userInput);
                if (command.isBye()) {
                    Format.wrapPrint(Response.RESPONSE_GOODBYE);
                    over = true;
                    continue;

                } else if (command.isList()) {
                    //List command
                    Format.wrapPrint(taskList.toString());

                } else if (command.isMark())  {
                    //mark command
                    try {
                        String change = taskList.markFinished(command.taskNo());
                        Format.wrapPrint(change);
                    } catch (NumberFormatException e) {
                        Format.wrapPrint("mark command must precede with a decimal number!");
                    }

                } else if (command.isUnmark())  {
                    //unmark command
                    try {
                        String change = taskList.unmarkFinished(command.taskNo());
                        Format.wrapPrint(change);
                    } catch (NumberFormatException e) {
                        Format.wrapPrint("unmark command must precede with a decimal number!");
                    }

                } else if (command.isTodo())  {
                    //todo command
                    try {
                        Todo newTask = new Todo(command.todoContent());
                        taskList.addTask(newTask);
                        Format.wrapPrint(Response.RESPONSE_ADDED + "\n" + newTask.toString() + "\n"
                                         + Response.taskNo(taskList.size()));
                    } catch (IndexOutOfBoundsException e) {
                        Format.wrapPrint("please specify what to do");
                    }

                } else if (command.isDeadline())  {
                    //deadline command
                    try {
                        Deadline newTask = new Deadline(command.deadlineContent(), command.deadlineDate());
                        taskList.addTask(newTask);
                        Format.wrapPrint(Response.RESPONSE_ADDED + "\n" + newTask.toString() + "\n"
                                         + Response.taskNo(taskList.size()));
                    } catch (IndexOutOfBoundsException e) {
                        Format.wrapPrint(Response.RESPONSE_MISSINGDATE);
                    }

                } else if (command.isEvent())  {
                    //event command
                    try {
                        Event  newTask = new Event(command.eventContent(), command.eventDate());
                        taskList.addTask(newTask);
                        Format.wrapPrint(Response.RESPONSE_ADDED + "\n" + newTask.toString() + "\n"
                                         + Response.taskNo(taskList.size()));
                    } catch (IndexOutOfBoundsException e) {
                        Format.wrapPrint(Response.RESPONSE_MISSINGTIME);
                    }

                } else if (command.isDelete())  {
                    //delete command
                    try {
                        Task deletee = taskList.deleteTask(command.taskNo());
                        Format.wrapPrint(Response.RESPONSE_DELETED + "\n" + deletee.toString() + "\n"
                                         + Response.taskNo(taskList.size()));
                    } catch (NumberFormatException e) {
                        Format.wrapPrint("mark command must precede with a decimal number!");
                    }

                } else   {
                    //blah representative of random meaningless words
                    Format.wrapPrint(Response.RESPONSE_CANTUNDERSTAND);
                }
            }
            writeData();
        } finally {
            myScanner.close();
        }
    }
}
