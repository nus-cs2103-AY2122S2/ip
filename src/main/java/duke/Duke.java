package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Duke class that contains the main method to run Duke.
 */
public class Duke {
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException, IOException {
        return reply(input);
    }
    /**
     * Main method to run Duke.
     *
     * @param
     * @throws IOException
     * @throws DukeException
     */
    public String reply(String userInput) throws IOException, DukeException {
        Ui ui = new Ui();
        ui.greet();

        // reading user input
        Scanner sc = new Scanner(System.in);

        // initializing variable
        boolean isBye = false;

        // array container
        ArrayList<Task> todoLists = new ArrayList<Task>();
        TaskList taskLists = new TaskList(todoLists);

        // initializing storage
        Storage storage = new Storage();

        // load data when duke starts up
        try {
            todoLists = storage.load();
            taskLists = new TaskList(todoLists);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (!isBye) {
            if (userInput.equals("bye")) {
                return "Bye. See you again next time! Have a nice day 😊!";
            } else {
                // storing input task in todoLists
                String[] userInputArr = userInput.split(" ");
                String userCommand = userInputArr[0];
                String userInputTask = String.join(" ",
                        Arrays.copyOfRange(userInputArr, 1, userInputArr.length));

                try {
                    Parser.userCommandValidator(userCommand);
                } catch (DukeException e) {
                   return "OOPS!!! I'm sorry, but I don't know what that means.";
                }

                switch (userCommand) {
                case "list":
                    return Parser.parserList(taskLists);
                case "todo":
                    // handle error from empty task description
                    try {
                        return Parser.parserTodo(taskLists, userInputTask, storage);
                    } catch (DukeException e) {
                        return "OOPS!!! The description of a todo cannot be empty.";
                    }
                case "deadline":
                    // handle error from empty task description
                    try {
                        Parser.parserDeadlineValidator(userInputTask);
                    } catch (DukeException e) {
                        if (e.getMessage().equals("The description of a deadline cannot be empty.")) {
                            return "The description of a deadline cannot be empty.";
                        } else if (e.getMessage().equals("Deadline tasks require a by day.")) {
                            return "Deadline tasks require a by day.";
                        } else if (e.getMessage().equals("Deadline tasks can only have one by day.")) {
                            return "Deadline tasks can only have one by day.";
                        }
                    }

                    // splitting deadline into description and by
                    String[] deadlineTaskArr = userInputTask.split(" /by ");
                    String[] byAndTime = deadlineTaskArr[1].split(" ");
                    String by = byAndTime[0];

                    String time = byAndTime[1];
                    assert !time.isEmpty() : "Deadline time is required";

                    try {
                        String deadlineTaskTime = byAndTime[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return "Deadline time is required";
                    }

                    String deadlineTime = byAndTime[1];

                    // handle error when time is not in the hh:mm 24hr clock format
                    try {
                        LocalTime.parse(deadlineTime);
                    } catch (DateTimeParseException e) {
                        return "Time must be in the hh:mm 24hr format";
                    }

                    // handle error when there is invalid deadline date format
                    try {
                        Parser.deadlineDateFormatValidator(by);
                    } catch (DukeException e) {
                        return "OOPS!!! Deadline tasks can only be in the YYYY-MM-DD format.";
                    }

                    return Parser.parserDeadline(taskLists, userInputTask, storage);

                case "event":
                    try {
                        Parser.parserEventValidator(userInputTask);
                    } catch (DukeException e) {
                        if (e.getMessage().equals("The description of an event cannot be empty.")) {
                            return "The description of an event cannot be empty.";
                        } else if (e.getMessage().equals("Event tasks require an at date and time.")) {
                            return "Event tasks require an at date and time.";
                        } else {
                            return "Event tasks can only have one at date and time.";
                        }
                    }

                    // splitting event into description and dateTime
                    String[] eventTaskArr = userInputTask.split(" /at ");
                    String[] eventDateAndTime = eventTaskArr[1].split(" ");
                    String eventDate = eventDateAndTime[0];

                    try {
                        String eventTime = eventDateAndTime[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return "Event time is required";
                    }

                    String eventTime = eventDateAndTime[1];

                    // handle error when time is not in the hh:mm 24hr clock format
                    try {
                        LocalTime.parse(eventTime);
                    } catch (DateTimeParseException e) {
                        return "Time must be in the hh:mm 24hr format";
                    }

                    // handle error when there is invalid deadline date format
                    try {
                        Parser.eventDateFormatValidator(eventDate);
                    } catch (DukeException e) {
                        return "OOPS!!! Deadline tasks can only be in the YYYY-MM-DD format.";
                    }
                    return Parser.parserEvent(taskLists, userInputTask, storage);
                case "mark":
                    return Parser.parserMark(taskLists, userInputArr, storage);
                case "unmark":
                    return Parser.parserUnmark(taskLists, userInputArr, storage);
                case "delete":
                    try {
                        Parser.parserDeleteValidator(taskLists, userInputTask);
                    } catch (DukeException e) {
                        if (e.getMessage().equals("Delete command must have a specified task number to be deleted.")) {
                            return "Delete command must have a specified task number to be deleted.";
                        } else {
                            return "Invalid task number to be deleted.";
                        }
                    }
                    return Parser.parserDelete(taskLists, userInputArr, storage);
                case "find":
                    return Parser.parserFind(taskLists, userInputTask);
                case "tag":
                    return Parser.parserTag(taskLists, userInputArr, storage);
                }
            }
        }
        return userInput;
    }
}
