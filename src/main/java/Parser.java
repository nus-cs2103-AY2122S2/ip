import java.util.ArrayList;

public class Parser {
    Parser() {}

    public String parse(String input) {
        String DASH = "____________________________________________________________";
        String[] inputArr = input.split(" ");
        String output = "";
        String command = inputArr[0];
        ArrayList<Task> taskList = TaskBank.getBank();

        switch(command) {
            case "list":
                Action.showList(taskList);
                break;
            case "bye":
                Action.bye();
                System.out.println(DASH);
                break;
            case "mark":
                try {
                    int num = Integer.valueOf(inputArr[1]) - 1;
                    taskList.get(num).markAsDone();
                    output = String.format("Nice! I've marked this task as done: %s\n", taskList.get(num));
                } catch (NumberFormatException error) {
                    output = "The index you have entered is not a number.\n";
                } catch (ArrayIndexOutOfBoundsException error) {
                    output = "The index you have entered is out of range.\n";
                }
                break;
            case "unmark":
                try {
                    int num = Integer.valueOf(inputArr[1]) - 1;
                    taskList.get(num).markAsNotDone();
                    output = String.format("OK, I've marked this task as not done yet: %s\n", taskList.get(num));
                } catch (NumberFormatException error) {
                    output = "The index you have entered is not a number.\n";
                } catch (ArrayIndexOutOfBoundsException error) {
                    output = "The index you have entered is out of range.\n";
                }
                break;
            case "todo":
                String remainingWordsTodo = "";
                for (int i = 1; i < inputArr.length; i++) {
                    remainingWordsTodo = remainingWordsTodo + " " + inputArr[i];
                }
                Todo td = new Todo(remainingWordsTodo);
                taskList.add(td);
                output = String.format("Got it. I've added this task:\n %s\n", td);
                String noOfTaskTodo = String.format("Now you have %d tasks in the list.\n", taskList.size());
                output = output + noOfTaskTodo;
                break;
            case "deadline":
                String remainingWordsDeadline = "";
                String deadline = "";
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/by")) {
                        for (int j = i + 1; j < inputArr.length; j++) {
                            deadline = deadline + " " + inputArr[j];
                        }
                        break;
                    } else {
                        remainingWordsDeadline = remainingWordsDeadline + " " + inputArr[i];
                    }
                }
                Deadline dl = new Deadline(remainingWordsDeadline, deadline);
                taskList.add(dl);
                output = String.format("Got it. I've added this task:\n %s\n", dl);
                String noOfTaskDeadline = String.format("Now you have %d tasks in the list.\n", taskList.size());
                output = output + noOfTaskDeadline;
                break;
            case "event":
                String remainingWordsEvent = "";
                String dayAndTime = "";
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/at")) {
                        for (int j = i + 1; j < inputArr.length; j++) {
                            dayAndTime = dayAndTime + " " + inputArr[j];
                        }
                        break;
                    } else {
                        remainingWordsEvent = remainingWordsEvent + " " + inputArr[i];
                    }
                }
                Event e = new Event(remainingWordsEvent, dayAndTime);
                taskList.add(e);
                output = String.format("Got it. I've added this task:\n %s\n", e);
                String noOfTaskEvent = String.format("Now you have %d tasks in the list.\n", taskList.size());
                output = output + noOfTaskEvent;
                break;
            case "delete":
                int num = Integer.valueOf(inputArr[1]) - 1;
                try {
                    Task d = taskList.get(num);
                    taskList.remove(num);
                    output = String.format("Noted. I've removed this task:\n %s\n", d);
                    String noOfTaskDelete = String.format("Now you have %d tasks in the list.\n", taskList.size());
                    output = output + noOfTaskDelete;
                } catch (NumberFormatException error) {
                    output = "The index you have entered is not a number.\n";
                } catch (ArrayIndexOutOfBoundsException error) {
                    output = "The index you have entered is out of range.\n";
                }
                break;
            case "help":
                output = "Here are all the commands available:\n"
                        + "list - to list out all the task\n"
                        + "bye - to end program\n"
                        + "todo <input something you want to do> - to add something you want to tasks\n"
                        + "deadline <input what is going be due> /by <input date> - to add a deadline to tasks\n"
                        + "event <input event description> /at <input date>\n - to add an event to tasks"
                        + "mark <input task number> - to mark task as done\n"
                        + "unmark <input task number> - to mark task as not done\n"
                        + "delete <input task number> - to delete task from tasks\n";
                break;
            default:
                output = "invalid command! Use 'help' for help";
        }

        return output;
    }
}
