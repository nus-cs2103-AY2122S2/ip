
package util;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

public class Ui {

    private Parser parser = new Parser();

    /**
     * greets the user
     */



    public String processInput(String input, TaskList tasks) {
        parser.parse(input);
        String task = parser.getTask();
        String item = parser.getItem();
        String tab = "    ";


        switch (task) {
            case "todo":
                if (!item.equals("")) {
                    return tasks.add(new Todo(item));
                } else {
                    return "Can read instructions or not? Todo cannot be empty :/";
                }

            case "deadline":
                if (!item.equals("")) {
                    String thing = item.split(" /by ")[0];
                    String time = item.split(" /by ")[1];
                    return tasks.add(new Deadline(thing, time));
                } else {
                    return "Can read instructions or not? Deadline cannot be empty :/";
                }

            case "event":
                if (!item.equals("")) {
                    String thing = item.split(" /at ")[0];
                    String time = item.split(" /at ")[1];
                    return tasks.add(new Event(thing, time));
                } else {
                    return "Can read instructions or not? Event cannot be empty :/";
                }

            case "list":
                StringBuilder lists = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    if (i != 0) {
                        lists.append("\n").append(tab);
                    }
                    lists.append(String.format("%d. %s", i + 1, tasks.get(i).toString()));

                }
                return lists.toString();

            case "mark":
                try {
                    int index = Integer.parseInt(item);
                    tasks.get(index - 1).markAsDone();
                    return "Good job for accomplishing something today! I've marked this task as done:\n      "
                            + tasks.get(index - 1).toString();
                } catch (IndexOutOfBoundsException e) {
                    return "You can't do that! It's not in the list!";
                }

            case "unmark":
                try {
                    int index = Integer.parseInt(item);
                    tasks.get(index - 1).markAsUndone();
                    return "Stop procrastinating you lazy prick! I've marked this task as not done yet:\n      "
                            + tasks.get(index - 1).toString();
                } catch (IndexOutOfBoundsException e) {
                    return "You can't do that! It's not in the list!";
                }


            case "delete":
                return tasks.deleteItem(item);

            case "find":
                return tasks.find(item);


            default:
                return "What is this? Can you read English?";

        }


    }

    public String greet() {
        return "Yawn... You woke me up! Urgh\n    What do you need?";
    }

    /**
     * prints the exit message to the user
     */

    public String exit() {
        return "Bye. I don't hope to see you again soon :D";
    }


}