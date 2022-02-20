package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    Parser(){

    }

    duke.TaskList parse(duke.Ui ui, duke.TaskList tasks, String value) {
        String[] splitStr = value.split("\\s+");

        if (value.equals("bye")) {
            ui.finalBye();
            return tasks;

        } else if (value.equals("list")) {
            assert (splitStr.length == 4) : "The input LIST was not entered but the list was returned to the user";
            tasks.print();

        } else if (splitStr[0].equals("mark")) {
            int index = Integer.parseInt(splitStr[1]);
            duke.Task task = tasks.get(index - 1);
            task.markAsDone();
            ui.markDone(tasks.get(index - 1));


        } else if (splitStr[0].equals("unmark")) {
            int index = Integer.parseInt(splitStr[1]);
            duke.Task task = tasks.get(index - 1);
            task.unmarkAsDone();
            ui.unmarkDone(tasks.get(index - 1));

        } else if (splitStr[0].equals("delete")) {
            int index = Integer.parseInt(splitStr[1]);
            duke.Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            ui.removedTask(task, tasks);

        } else if (splitStr[0].equals("find")) {
            String findString = value.substring(5);
            duke.TaskList foundTasks = new duke.TaskList();
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).toString().contains(findString)) {
                    foundTasks.add(tasks.get(i));
                }
            }
            ui.findTasks(foundTasks);

        } else if (splitStr[0].equals("todo") || splitStr[0].equals("deadline") || splitStr[0].equals("event")) {

            String[] parts = value.split("/");
            String description = parts[0];
            if (parts.length > 1) {
                if (parts[1].length() == 13) {
                    LocalDate d1 = LocalDate.parse(parts[1].substring(3));
                    parts[1] = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                }
                description += "(" + parts[1] + ")";
            }

            try {
                if (splitStr[0].equals("todo")) {
                    description = description.substring(5);
                    tasks.add(new duke.ToDo(description));
                }
            } catch (Exception e) {
                ui.emptyInput();
            }
            try {
                if (splitStr[0].equals("deadline")) {
                    description = description.substring(9);
                    tasks.add(new duke.Deadline(description));
                }
            } catch (Exception e) {
                ui.emptyInput();
            }
            try {
                if (splitStr[0].equals("event")) {
                    description = description.substring(6);
                    tasks.add(new duke.Event(description));
                }
            } catch (Exception e) {
                ui.emptyInput();
            }

            ui.addTask(tasks);

        } else {
            ui.doNotUnderstand();

        }
        return tasks;
    }
}
