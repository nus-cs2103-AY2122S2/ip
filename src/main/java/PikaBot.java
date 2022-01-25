import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
//import java.io.IOException;

//import java.time.format.DateTimeParseException;


public class PikaBot {

    static String FILEPATH = "data/tasks.txt";

   /* public static void invalidTask() throws InvalidTaskCommandException {
        throw new InvalidTaskCommandException();
    } */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Storage storage = new Storage("data/tasks.txt");

        Ui.printWelcomeText();

        TaskList taskList = new TaskList(new ArrayList<Task>());

        if (storage.doesFileExist()) {
            try {
                taskList = Storage.fileToTaskList(new File(FILEPATH));
            } catch (FileNotFoundException e) {
                Ui.printExceptionMessage(e);
            }
        }

        String input = sc.nextLine();
        String[] strInputArr = input.split(" ", 2);


        while (!strInputArr[0].equals("bye")) {

            Command command = Parser.parseCommand(strInputArr);
            command.execute(taskList, storage);

            input = sc.nextLine();
            strInputArr = input.split(" ", 2);

            /*switch (strInputArr[0]) {
                case "list": {
                    Ui.printListOfTasks(taskList);
                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }

                case "mark": {
                    int taskToMark = Integer.parseInt(strInputArr[1]);
                    taskList.markTaskAsDone(taskToMark);

                    try {
                        storage.TaskListToFile(taskList);
                    } catch (IOException e) {
                       Ui.printExceptionMessage(e);
                    }

                    Ui.indicateMarked(taskList.get(taskToMark - 1));

                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }

                case "unmark": {
                    int taskToUnmark = Integer.parseInt(strInputArr[1]);
                    taskList.markTaskAsUndone(taskToUnmark);

                    try {
                        storage.TaskListToFile(taskList);
                    } catch (IOException e) {
                        Ui.printExceptionMessage(e);
                    }

                    Ui.indicateUnmarked(taskList.get(taskToUnmark - 1));
                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }
                case "todo": {
                    try {
                        Todo currTodo = Parser.parseTodo(strInputArr);
                        taskList.add(currTodo);
                        Ui.indicateAddedTask(currTodo, taskList);
                        storage.appendToFile(currTodo);

                    } catch (TodoException | IOException e) {
                        Ui.printExceptionMessage(e);

                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }

                case "deadline": {
                    try {
                        Deadline currDeadline = Parser.parseDeadline(strInputArr);
                        taskList.add(currDeadline);
                        Ui.indicateAddedTask(currDeadline, taskList);
                        storage.appendToFile(currDeadline);

                    } catch (DeadlineException | IOException e) {
                        Ui.printExceptionMessage(e);

                    } catch (DateTimeParseException e) {
                        Ui.printExceptionCustomisedMessage("Invalid deadline! Deadline has to " +
                                "be a valid date in numerical format YYYY-MM-DD.");

                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }

                case "event": {
                    try {
                        Event currEvent = Parser.parseEvent(strInputArr);
                        taskList.add(currEvent);
                        Ui.indicateAddedTask(currEvent, taskList);
                        storage.appendToFile(currEvent);

                    } catch (EventException | IOException e) {
                        Ui.printExceptionMessage(e);

                    } catch (DateTimeParseException e) {
                        Ui.printExceptionCustomisedMessage("The description of an event must" +
                                "contain a date in the numerical format YYYY-MM-DD");

                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }

                case "delete": {
                    int taskNumberToDelete = Integer.parseInt(strInputArr[1]);
                    Task taskToDelete = taskList.get(taskNumberToDelete - 1);
                    taskList.delete(taskNumberToDelete);

                    try {
                        storage.TaskListToFile(taskList);
                    } catch (IOException e) {
                        Ui.printExceptionMessage(e);
                    }

                    Ui.indicateRemovedTask(taskToDelete, taskList);
                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }

                default: {
                    try {
                        invalidTask();
                    } catch (InvalidTaskCommandException e) {
                        Ui.printExceptionMessage(e);
                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }
            } */
        }

        Ui.printClosingText();

        sc.close();
    }
}
