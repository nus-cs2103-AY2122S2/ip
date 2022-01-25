package duke.io;

import duke.exception.DukeWrongTimeFormatException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static UserInput parseTask(String input) {
        char typeOfTask = input.charAt(1);
        boolean isTaskDone = input.charAt(4) == 'X';

        if (typeOfTask == 'T') {
            int descriptionStart = 6; // Format: [<typeOfTask>][<isTaskDone>] <DescriptionStart> ...
            String description = input.substring(descriptionStart);
            description = description.replaceFirst(" ", "");
            return new UserInput("T", "todo", description, isTaskDone);

        } else if (typeOfTask == 'D') {
            int descriptionStart = 6; // Format: [<typeOfTask>][<isTaskDone>] <DescriptionStart> ...
            int timeStart = input.indexOf("(by:");
            String description = input.substring(descriptionStart, timeStart);
            description = description.replaceFirst(" ", "");
            String time = input.substring(timeStart + 1, input.length() - 1);
            time = time.replaceFirst(":", "");

            return new UserInput("D", "deadline", description, time, isTaskDone);

        } else if (typeOfTask == 'E') {
            int descriptionStart = 6; // Format: [<typeOfTask>][<isTaskDone>] <DescriptionStart> ...
            int timeStart = input.indexOf("(at:");
            String description = input.substring(descriptionStart, timeStart);
            description = description.replaceFirst(" ", "");
            String time = input.substring(timeStart + 1, input.length() - 1);
            time = time.replaceFirst(":", "");

            return new UserInput("E", "event", description, time, isTaskDone);
        } else {
            return null;
        }
    }

    public UserInput parseInput(String input) throws DukeWrongTimeFormatException {
        UserInput userInput = new UserInput();
        int descriptionStart = input.indexOf(' ');
        int timeStart = input.indexOf('/');
        String command = "";
        String description = "";
        String time = "";

        // process the user input into different segments
        if (descriptionStart != -1) {
            // sentence input
            command = input.substring(0, descriptionStart);
            userInput.setCommand(command);

            if (timeStart != -1) {
                // has time input
                description = input.substring(descriptionStart, timeStart);
                description = description.replaceFirst(" ", "");
                time = input.substring(timeStart);
                time = time.replaceFirst("/", "");

                userInput.setDescription(description);
                userInput.setTime(time);

                if (command.equals("deadline")) {
                    try {
                        LocalDate date = LocalDate.parse(time.replaceFirst("by ", ""));
                        time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                        time = "by " + time;
                        userInput.setDescription(description);
                        userInput.setTime(time);

                    } catch (DateTimeException e) {
                        throw new DukeWrongTimeFormatException("OOPS!!! Invalid deadline format! (yyyy-mm-dd)");
                    }
                }
            } else {
                // no time input
                description = input.substring(descriptionStart);
                description = description.replaceFirst(" ", "");
                userInput.setDescription(description);
            }
        } else {
            // single word input
            command = input;
            userInput.setCommand(command);
        }

        return userInput;
    }
}
