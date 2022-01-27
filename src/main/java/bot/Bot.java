package bot;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.Optional;
import java.util.Scanner;

import java.time.LocalDate;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import taskservice.TaskService;
import taskservice.exceptions.TaskServiceException;

public class Bot {
    private static final String INDENTATION = "    ";
    private static final String DIVIDER = "____________________________________________________________";
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String FAREWELL = "Bye. Hope to see you again soon!";

    private final TaskService taskService;

    public Bot(TaskService taskService) {
        this.taskService = taskService;
    }

    public void start(InputStream inputStream, OutputStream outputStream) throws Exception {
        final Scanner input = new Scanner(inputStream);
        final PrintStream output = new PrintStream(outputStream);

        try {
            output.println(this.greet());
            String query = input.nextLine();
            while (!this.detectTerminationRequest(query)) {
                try {
                    output.println(this.process(query));
                } catch (BotException e) {
                    output.println(this.constructResponse(e.getMessage()));
                }
                query = input.nextLine();
            }
            output.println(this.bidFarewell());
        } finally {
            input.close();
            output.close();
        }
    }

    private String process(String query) throws BotException {
        try {
            final String[] tokens = query.split(" ", 2);
            switch (tokens[0]) {
            case "list":
                return this.getTaskList();
            case "mark":
                return this.markTask(Integer.parseInt(tokens[1]) - 1);
            case "unmark":
                return this.unmarkTask(Integer.parseInt(tokens[1]) - 1);
            case "todo":
                return this.addTask(new Todo(tokens[1].trim()));
            case "deadline":
                final String[] deadlineArgs = tokens[1].split(" /by ");
                return this.addTask(new Deadline(
                        deadlineArgs[0].trim(),
                        LocalDate.parse(deadlineArgs[1].trim(), Deadline.DATE_INPUT_FORMAT)));
            case "event":
                final String[] eventArgs = tokens[1].split(" /at ");
                return this.addTask(new Event(eventArgs[0].trim(), eventArgs[1].trim()));
            case "delete":
                return this.deleteTask(Integer.parseInt(tokens[1]) - 1);
            default:
                throw new BotException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (TaskServiceException ex) {
            throw new BotException(ex.getMessage());
        }
    }

    private boolean detectTerminationRequest(String query) {
        return query.equals("bye");
    }

    private String greet() {
        return this.constructResponse(Bot.GREETING);
    }

    private String bidFarewell() {
        return this.constructResponse(Bot.FAREWELL);
    }

    private String getTaskList() throws TaskServiceException {
        final String taskList = "Here are the tasks in your list:\n"
                + this.constructTaskList(this.taskService.get());
        return this.constructResponse(taskList);
    }

    private String addTask(Task newTask) throws BotException, TaskServiceException {
        if (newTask.getDescription().equals("")) {
            throw new BotException("The description of a task cannot be empty.");
        }
        this.taskService.create(newTask);
        final String response =
                "Got it. I've added this task:\n  " + newTask
                        + "\nNow you have " + this.taskService.getNumberOfTasks() + " tasks in the list.";
        return this.constructResponse(response);
    }

    private String markTask(int taskId) throws BotException, TaskServiceException {
        final Optional<Task> result = this.taskService.getById(taskId);
        if (result.isEmpty()) {
            throw new BotException("Task to mark doesn't exist");
        }

        result.get().markAsDone();
        this.taskService.update(taskId, result.get());

        final String response = "Nice! I've marked this task as done:\n  " + result.get();
        return this.constructResponse(response);
    }

    private String unmarkTask(int taskId) throws BotException, TaskServiceException {
        final Optional<Task> result = this.taskService.getById(taskId);
        if (result.isEmpty()) {
            throw new BotException("Task to unmark doesn't exist");
        }

        result.get().markAsUndone();
        this.taskService.update(taskId, result.get());

        final String response = "OK, I've marked this task as not done yet:\n  " + result.get();
        return this.constructResponse(response);
    }

    private String deleteTask(int taskId) throws BotException, TaskServiceException {
        final Optional<Task> result = this.taskService.getById(taskId);
        if (result.isEmpty()) {
            throw new BotException("Task to remove doesn't exist");
        }

        this.taskService.delete(taskId);

        final String response = "Noted. I've removed this task:\n  " + result.get()
                + "\nNow you have " + this.taskService.getNumberOfTasks() + " tasks in the list.";
        return this.constructResponse(response);
    }

    private String constructResponse(String content) {
        final String divider = this.INDENTATION + this.DIVIDER + "\n";
        final String response =
                this.INDENTATION + " " + content.replaceAll("\n", "\n " + this.INDENTATION) + "\n";
        return divider + response + divider;
    }

    private String constructTaskList(Task[] tasks) {
        String taskList = "";
        for (int i = 0; i < tasks.length; i++) {
            if (i > 0) {
                taskList += "\n";
            }
            taskList += (i + 1) + "." + tasks[i];
        }
        return taskList;
    }
}
