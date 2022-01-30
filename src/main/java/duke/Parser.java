package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import duke.command.Command;
import duke.dukeException.NoTimeGivenException;
import duke.command.*;

/**
 * Encapsulates the parsing logic of string commands
 */
class Parser {
    /**
     * returns the Command object that the user indicates through userInput
     * @param userInput the String value that the user typed
     * @param taskList the current list of tasks that duke stores, encapsulated in TaskList class
     * @param over the boolean value that decides when to kill the program, encapsulated in custom wrapper class
     * @return the Command object that can be executed
     * @throws NoTimeGivenException if the user types the wrong format of time
     */
    public static Command parse(String userInput, TaskList taskList, Over over)
    throws NoTimeGivenException {
        String[] words = userInput.split(" ");
        String firstWord = words[0];
        if (firstWord.equals("bye")) {
            return new CommandBye(over);
        } else if (firstWord.equals("list")) {
            return new CommandList(taskList);
        } else if (firstWord.equals("mark") && words.length == 2) {
            int taskNo = Integer.parseInt(words[1]);
            return new CommandMark(taskList, taskNo);
        } else if (firstWord.equals("unmark") && words.length == 2) {
            int taskNo = Integer.parseInt(words[1]);
            return new CommandUnmark(taskList, taskNo);
        } else if (firstWord.equals("todo")) {
            String todoContent = words[1];
            for (int i = 2; i < words.length; i++) {
                todoContent = todoContent + " " + words[i];
            }
            return new CommandTodo(taskList, todoContent);
        } else if (firstWord.equals("deadline")) {
            String deadlineContent = words[1];
            for (int i = 2; i < words.length; i++) {
                if (words[i].equals("/by")) {
                    break;
                }
                deadlineContent = deadlineContent + " " + words[i];
            }
            String[] separateTime = userInput.split("/by ");
            if (separateTime.length < 1) {
                throw new NoTimeGivenException("No time specified");
            }
            String dateString = separateTime[1];
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
            return new CommandDeadline(taskList, deadlineContent, date);
        } else if (firstWord.equals("event")) {
            String eventContent = words[1];
            for (int i = 2; i < words.length; i++) {
                if (words[i].equals("/at")) {
                    break;
                }
                eventContent = eventContent + " " + words[i];
            }
            String timeString = userInput.split("/at ")[1];
            LocalDate time = LocalDate.parse(timeString, DateTimeFormatter.ISO_DATE);
            return new CommandEvent(taskList, eventContent, time);
        } else if (firstWord.equals("delete") && words.length == 2) {
            int taskNo = Integer.parseInt(words[1]);
            return new CommandDelete(taskList, taskNo);
        } else if (firstWord.equals("find") && words.length == 2){
            String wordSecond = words[1];
            System.out.println("reached line 58");
            return new CommandFind(wordSecond, taskList);
        } else {
            return new CommandUnclear();
        }
    }
}
