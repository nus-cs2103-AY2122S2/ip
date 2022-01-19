import java.util.ArrayList;

public class Bot {
    ArrayList<Task> listOfTasks;

    public Bot() {
        this.listOfTasks = new ArrayList<>();
    }

    public String encloseWithin(String str) {
        String Header = "____________________________________________________________";
        StringBuilder string = new StringBuilder();
        string.append(Header).append("\n").append(str).append(Header).append("\n");
        return string.toString();
    }

    public void greet() {
        String greeting = encloseWithin("Hello! I'm Duke\nWhat can I do for you?\n");
        System.out.println(greeting);
    }

    public void sayGoodbye () {
        String goodbye = encloseWithin("Bye. Hope to see you again soon!\n");
        System.out.println(goodbye);
    }

    private String[] processInput(String inputMessage) throws DukeException {
        String[] msg = new String[] {"Empty"};
        // One word messages
        if (inputMessage.equals("list")) {
            msg = new String[] {"list"};
        } else {
            // Mark and unmark have the same syntax, so only process it once
            if (inputMessage.contains("todo")) {
                msg = inputMessage.split("todo");
                if (msg.length < 1) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but you need to provide a description for the Todo task\n");
                }
                msg[0] = "todo";
            } else if (inputMessage.contains("event")) {
                if (inputMessage.contains("/at")) {
                    msg = inputMessage.split(" /at");
                    if (msg.length != 3) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, the Event description cannot be empty\n");
                    }
                    msg = new String[]{msg[0].substring(0, 5), msg[0].substring(6), msg[1]};
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but you need to use the following format:\n" +
                            "event {description} /at {date}\n");
                }
            } else if (inputMessage.contains("deadline")) {
                if (inputMessage.contains("/by")) {
                    msg = inputMessage.split(" /by");
                    if (msg.length != 3) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, the Deadline description cannot be empty\n");
                    }
                    msg = new String[]{msg[0].substring(0, 8), msg[0].substring(9), msg[1]};
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but you need to use the following format:\n" +
                            "deadline {description} /by {date}\n");
                }
            } else if (inputMessage.contains("delete") || inputMessage.contains("mark")) {
                msg = inputMessage.split(" ");
                if (msg.length != 2) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but you need to enter a number as well!\n");
                }
            }
        }
        return msg;
    }

    private int processNumber(String userInput) throws DukeException {
        if (!isInteger(userInput)) {
            throw new DukeException("☹ OOPS!!! Please enter a appropriate integer\n");
        }
        return Integer.parseInt(userInput) - 1;
    }

    private String ListStatus() {
        return "Now you have " + this.listOfTasks.size() + " tasks in the list.\n";
    }

    private void taskAddedMessage(Task newTask) {
        StringBuilder toPrint = new StringBuilder("Got it. I've added this task:\n");
        toPrint.append(newTask.toString()).append("\n");
        toPrint.append(ListStatus());
        System.out.println(encloseWithin(toPrint.toString()));
    }

    private void addTodo(String task) throws DukeException {
        Task newTask = new ToDos(task);
        this.listOfTasks.add(newTask);
        taskAddedMessage(newTask);
    }

    private void addDeadline(String task, String deadline) throws DukeException {
        Task newTask = new DeadLines(task, deadline);
        this.listOfTasks.add(newTask);
        taskAddedMessage(newTask);
    }

    private void addEvent(String task, String timing) throws DukeException {
        Task newTask = new Event(task, timing);
        this.listOfTasks.add(newTask);
        taskAddedMessage(newTask);
    }

    private void deleteTask(String userInput) throws DukeException {
        int index = processNumber(userInput);
        if (index < 0 || index > listOfTasks.size() - 1) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the number you provided is invalid\n");
        } else {
            Task removedTask = this.listOfTasks.remove(index);
            StringBuilder toPrint = new StringBuilder("Noted. I've removed this task:\n");
            toPrint.append(removedTask.toString()).append("\n");
            toPrint.append(ListStatus());
            System.out.println(encloseWithin(toPrint.toString()));
        }
    }

    private boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void markTask(String userInput) throws DukeException {
        int index = processNumber(userInput);
        if (index < 0 || index > listOfTasks.size() - 1) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the number you provided is invalid\n");
        }
        Task toMark = this.listOfTasks.get(index);
        toMark.mark();
        StringBuilder toPrint = new StringBuilder("Nice! I've marked this task as done:\n").append(toMark).append("\n");
        System.out.println(encloseWithin(toPrint.toString()));
    }

    private void unmarkTask(String userInput) throws DukeException {
        int index = processNumber(userInput);
        if (index < 0 || index > listOfTasks.size() - 1) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the number you provided is invalid\n");
        }
        Task toUnmark = this.listOfTasks.get(index);
        toUnmark.unmark();
        StringBuilder toPrint = new StringBuilder("OK, I've marked this task as not done yet:\n").append(toUnmark).append("\n");
        System.out.println(encloseWithin(toPrint.toString()));
    }

    private void listTasks() {
        StringBuilder toPrint = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < listOfTasks.size(); i++) {
            int num = i + 1;
            String add = num + "." + listOfTasks.get(i) + "\n";
            toPrint.append(add);
        }
        System.out.println(encloseWithin(toPrint.toString()));
    }

    public void communicate(String userInput) throws DukeException {
        String[] processedInput = processInput(userInput);
        switch (processedInput[0]) {
            case "list":
                listTasks();
                break;
            case "unmark":
                unmarkTask(processedInput[1]);
                break;
            case "mark":
                markTask(processedInput[1]);
                break;
            case "delete":
                deleteTask(processedInput[1]);
                break;
            case "deadline":
                addDeadline(processedInput[1], processedInput[2]);
                break;
            case "event":
                addEvent(processedInput[1], processedInput[2]);
                break;
            case "todo":
                addTodo(processedInput[1]);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }
}

