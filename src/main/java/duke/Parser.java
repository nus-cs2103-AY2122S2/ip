package duke;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Parser {

        private final Storage storage;
        private final TaskList taskListObj;
        private final Ui ui;
        private final DukeException dukeException;
        private final ArrayList<Task> taskListArr;
        private boolean isExit;

        public Parser(Storage storage, TaskList taskListObj, Ui ui) {
            this.taskListObj = taskListObj;
            this.taskListArr = taskListObj.getTaskList();
            dukeException = new DukeException();
            this.storage = storage;
            this.ui = ui;
            this.isExit = false;
        }

        public void parse(String command) {
            command = command.trim().strip();
            if (command.equals("bye")) {
                ui.displayBye();
                storage.saveToTaskList(taskListArr);
                isExit = true;
            } else if (command.equals("list")) {
                ui.displayTasks(taskListArr);
            } else if (command.startsWith("mark")) {
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                taskListObj.mark(value);
            } else if (command.startsWith("unmark")) {
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                taskListObj.unmark(value);
            } else if(command.startsWith("todo")) {
                try {
                    Todo todoTask = new Todo(command.substring(5));
                    taskListObj.addTask(todoTask);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(todoTask);
                    System.out.println("Now you have " + taskListObj.getSize() + " tasks in the list.");
                } catch (StringIndexOutOfBoundsException noDescription) {
                    dukeException.noDescriptionException();
                }
            } else if(command.startsWith("deadline")) {
                command = command.replace("deadline", "");
                String[] taskText = command.split(" /by");
                try {
                    DateTimeFormatter format1 = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    DateTimeFormatter format3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"); // Not working

                    // Create a formatter with the formats
                    DateTimeFormatter parser = new DateTimeFormatterBuilder()
                            .appendOptional(format1)
                            .appendOptional(format2)
                            .appendOptional(format3)
                            .toFormatter();
                    LocalDate parsedDate = LocalDate.parse(taskText[1].strip(),parser);
                    Deadline deadlineTask = new Deadline(taskText[0].strip(), parsedDate);
                    taskListObj.addTask(deadlineTask);
                } catch(ArrayIndexOutOfBoundsException invalidDeadlineSyntax) {
                    dukeException.invalidDeadlineSyntax();
                } catch(DateTimeParseException wrongDate) {
                    // This means that there is no date to be parsed, or incorrect format, so treat it as normal string
                    Deadline deadlineTask = new Deadline(taskText[0].strip(), taskText[1].strip());
                    taskListObj.addTask(deadlineTask);
                }
            } else if(command.startsWith("event")) {
                try {
                    command = command.replace("event", "");
                    String[] taskText = command.split(" /at");
                    Event eventTask = new Event(taskText[0].strip(), taskText[1].strip());
                    taskListObj.addTask(eventTask);
                } catch(ArrayIndexOutOfBoundsException invalidEventSyntax) {
                    dukeException.invalidEventSyntax();
                }
            } else if(command.startsWith("delete")) {
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                taskListObj.delete(value);
            } else {
                dukeException.noSuchTaskException();
            }
        }

        public boolean getExitTrigger() {
            return isExit;
        }
}
