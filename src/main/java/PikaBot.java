import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;



public class PikaBot {

    static String LINE = "_________________________________";
    static String INDENTATION = "     ";
    static String FILEPATH = "data/tasks.txt";

    public static Todo addTodo(String[] todoArray) throws TodoException {
        if (todoArray.length == 1) {
            throw new TodoException();
        } else {
            String description = todoArray[1];
            return new Todo(description);
        }
    }

    public static Deadline addDeadline(String[] deadlineArray) throws DeadlineException {
        if (deadlineArray.length == 1) {
            throw new DeadlineException("The description of a deadline cannot be empty.");
        } else {
            String[] deadlineDetails = deadlineArray[1].split("/by ", 2);
            if (deadlineDetails.length == 1) {
                throw new DeadlineException("The description of a deadline must contain a deadline " +
                        "in the numerical format YYYY-MM-DD");
            } else {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
                    return new Deadline(deadlineDetails[0], LocalDate.parse(deadlineDetails[1]));
                } catch (DateTimeParseException e) {
                    throw new DeadlineException("Invalid deadline! Deadline has to be a valid date in " +
                            "numerical format YYYY-MM-DD");
                }
            }
        }
    }

    public static Event addEvent(String[] eventArray) throws EventException {
        if (eventArray.length == 1) {
            throw new EventException("The description of an event cannot be empty.");
        } else {
            String[] eventDetails = eventArray[1].split("/at ", 2);
            if (eventDetails.length == 1) {
                throw new EventException("The description of an event must contain a date in " +
                        "the numerical format YYYY-MM-DD");
            } else {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
                    return new Event(eventDetails[0], LocalDate.parse(eventDetails[1]));
                } catch (DateTimeParseException e) {
                    throw new EventException("Invalid exception! Event has to have a valid date in " +
                            "numerical format YYYY-MM-DD");
                }
            }
        }
    }

    public static void addedTask(Task task, ArrayList<Task> listOfTasks) {
        System.out.println(INDENTATION + LINE + "\n" +
                INDENTATION + "Got it. I've added this task:" + "\n" +
                INDENTATION + "  " + task + "\n" +
                INDENTATION + "Now you have " + listOfTasks.size() + " tasks in the list." +
                "\n" +
                INDENTATION + LINE);
    }

    public static void invalidTask() throws InvalidTaskCommandException {
        throw new InvalidTaskCommandException();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(INDENTATION + LINE + "\n" + INDENTATION + "Hello! I'm PikaBot" + "\n" + INDENTATION +
                "What can I do for you? シ\n" + INDENTATION + LINE);

        ArrayList<Task> inputArr = new ArrayList<>();

        if (Storage.doesFileExist(FILEPATH)) {
            try {
                inputArr = Storage.fileToArrayList(new File(FILEPATH));
            } catch (FileNotFoundException e) {
                System.out.println(INDENTATION + LINE);
                System.out.println(e + " File Not Found!");
                System.out.println(INDENTATION + LINE);
            }
        }

        String input = sc.nextLine();
        String[] strInputArr = input.split(" ", 2);


        while (!strInputArr[0].equals("bye")) {

            switch (strInputArr[0]) {
                case "list": {
                    System.out.println(INDENTATION + LINE);
                    System.out.println(INDENTATION + "Here are the tasks in your list:");

                    int taskNumber = 1;
                    int length = inputArr.size();

                    while (taskNumber <= length) {
                        System.out.println(INDENTATION + taskNumber + "." +
                                inputArr.get(taskNumber - 1));
                        taskNumber++;
                    }

                    System.out.println(INDENTATION + LINE);

                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);

                    break;
                }

                case "mark": {
                    int taskToMark = Integer.parseInt(strInputArr[1]);
                    Task currTask = inputArr.get(taskToMark - 1);
                    currTask.markAsDone();

                    try {
                        Storage.ArrayListToFile(inputArr, FILEPATH);
                    } catch (IOException e) {
                        System.out.println(e.getMessage() + " IO exception");
                    }

                    System.out.println(INDENTATION + LINE);
                    System.out.println(INDENTATION + "Nice! I've marked this task as done:");
                    System.out.println(INDENTATION + currTask);
                    System.out.println(INDENTATION + LINE);

                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }

                case "unmark": {
                    int taskToUnmark = Integer.parseInt(strInputArr[1]);
                    Task currTask = inputArr.get(taskToUnmark - 1);
                    currTask.markAsUndone();

                    try {
                        Storage.ArrayListToFile(inputArr, FILEPATH);
                    } catch (IOException e) {
                        System.out.println(INDENTATION + LINE);
                        System.out.println(INDENTATION + e.getMessage() + " IO exception");
                        System.out.println(INDENTATION + LINE);

                    }

                    System.out.println(INDENTATION + LINE);
                    System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
                    System.out.println(INDENTATION + currTask);
                    System.out.println(INDENTATION + LINE);

                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }
                case "todo": {
                    try {
                        Todo currTodo = addTodo(strInputArr);
                        inputArr.add(currTodo);
                        addedTask(currTodo, inputArr);
                        Storage.appendToFile(currTodo, FILEPATH);

                    } catch (TodoException e) {
                        System.out.println(INDENTATION + LINE);
                        System.out.println(INDENTATION + e.getMessage());
                        System.out.println(INDENTATION + LINE);

                    } catch (IOException e) {
                        System.out.println(INDENTATION + LINE);
                        System.out.println(INDENTATION + e.getMessage() + " IOException");
                        System.out.println(INDENTATION + LINE);

                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }

                case "deadline": {
                    try {
                        Deadline currDeadline = addDeadline(strInputArr);
                        inputArr.add(currDeadline);
                        addedTask(currDeadline, inputArr);
                        Storage.appendToFile(currDeadline, FILEPATH);

                    } catch (DeadlineException e) {

                        System.out.println(INDENTATION + LINE);
                        System.out.println(INDENTATION + e.getMessage());
                        System.out.println(INDENTATION + LINE);

                    } catch (IOException e) {
                        System.out.println(INDENTATION + LINE);
                        System.out.println(INDENTATION + e.getMessage() + " IOException");
                        System.out.println(INDENTATION + LINE);

                    } catch (DateTimeParseException e) {
                        System.out.println(INDENTATION + LINE);
                        System.out.println(INDENTATION + "Invalid deadline! Deadline has to be a valid date in" +
                                "numerical format YYYY-MM-DD.");
                        System.out.println(INDENTATION + LINE);

                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }

                case "event": {
                    try {
                        Event currEvent = addEvent(strInputArr);
                        inputArr.add(currEvent);
                        addedTask(currEvent, inputArr);
                        Storage.appendToFile(currEvent, FILEPATH);

                    } catch (EventException e) {
                        System.out.println(INDENTATION + LINE);
                        System.out.println(INDENTATION + e.getMessage());
                        System.out.println(INDENTATION + LINE);

                    } catch (IOException e) {
                        System.out.println(INDENTATION + LINE);
                        System.out.println(INDENTATION + e.getMessage() + " IOException");
                        System.out.println(INDENTATION + LINE);
                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }

                case "delete": {
                    int taskNumberToDelete = Integer.parseInt(strInputArr[1]);
                    Task taskToDelete = inputArr.get(taskNumberToDelete - 1);
                    inputArr.remove(taskNumberToDelete - 1);

                    try {
                        Storage.ArrayListToFile(inputArr, FILEPATH);
                    } catch (IOException e) {
                        System.out.println(INDENTATION + LINE);
                        System.out.println(INDENTATION + e.getMessage());
                        System.out.println(INDENTATION + LINE);
                    }

                    System.out.println(INDENTATION + LINE);
                    System.out.println(INDENTATION + "Noted. I've removed this task:");
                    System.out.println(INDENTATION + "  " + taskToDelete);
                    System.out.println(INDENTATION + "Now you have " + inputArr.size() + " tasks in the list");
                    System.out.println(INDENTATION + LINE);

                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }

                default: {
                    try {
                        invalidTask();
                    } catch (InvalidTaskCommandException e) {
                        System.out.println(INDENTATION + LINE);
                        System.out.println(INDENTATION + e.getMessage());
                        System.out.println(INDENTATION + LINE);
                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }
            }
        }

        System.out.println(INDENTATION + LINE + "\n" + INDENTATION + "Bye. Hope to see you again!" + "\n" + INDENTATION
                + LINE);

        sc.close();
    }
}
