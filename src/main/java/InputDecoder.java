import java.util.ArrayList;

public class InputDecoder {
    private Commands currentCommand = null;
    protected ArrayList<Task> tasks;

    public InputDecoder(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private String[] parseInput(String input) throws IllegalArgumentException {
        String[] commandSections = input.split(" ", 2);
        currentCommand = Commands.valueOf(commandSections[0]);
        return commandSections;
    }

    public ArrayList<Task> decode(String input) {
        String[] commandSections = null;
        try {
            commandSections = this.parseInput(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry I didnt catch that! Please make sure it is a valid command!");
            return tasks;
        }
        switch (currentCommand) {

            // list
            case list: {
                for (int i = 0; i < tasks.size(); i++) {
                    Task currentTask = tasks.get(i);
                    int index = i + 1;
                    System.out.println(index + "." + currentTask.toString());
                }
                break;
            }

            // mark
            case mark: {
                try {
                    int indexMarked = Integer.parseInt(commandSections[1]) - 1;
                    Task currentTask = tasks.get(indexMarked);
                    currentTask.isDone = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currentTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new DukeException("task does not exist!"));
                } catch (NumberFormatException e) {
                    System.out.println(new DukeException("provide an index instead :)"));
                }
                break;
            }


            // unmark
            case unmark: {
                try {
                    int indexMarked = Integer.parseInt(commandSections[1]) - 1;
                    Task currentTask = tasks.get(indexMarked);
                    currentTask.isDone = false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(currentTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new DukeException("task does not exist!"));
                } catch (NumberFormatException e) {
                    System.out.println(new DukeException("provide an index instead :)"));
                }
                break;
            }
            case delete: {
                try {
                    int indexDelete = Integer.parseInt(commandSections[1]) - 1;
                    Task deletedTask = tasks.remove(indexDelete);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(deletedTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new DukeException("task does not exist!"));
                } catch (NumberFormatException e) {
                    System.out.println(new DukeException("provide an index instead :)"));
                }
                break;
            }

            // generate tasks
            case todo: {
                try {
                    if (commandSections[1].isBlank()) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Todo currentTodo = new Todo(commandSections[1]);
                    tasks.add(currentTodo);
                    System.out.println("added: " + currentTodo.toString());

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(new DukeException("The description of a todo cannot be empty"));
                }
                break;
            }
            case deadline: {
                try {
                    String actualTask = commandSections[1];
                    String[] segments = actualTask.split(" /by ");
                    if (segments[0].isBlank() || segments[0].isBlank()) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Deadline currentDeadline = new Deadline(segments[0], segments[1]);
                    tasks.add(currentDeadline);
                    System.out.println("added: " + currentDeadline.toString());

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(new DukeException("The description of a deadline cannot be empty"));
                }
                break;
            }

            case event: {
                try {
                    String actualTask = commandSections[1];
                    String[] segments = actualTask.split(" /at ");
                    if (segments[0].isBlank() || segments[0].isBlank()) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Event currentEvent = new Event(segments[0], segments[1]);
                    tasks.add(currentEvent);
                    System.out.println("added: " + currentEvent.toString());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(new DukeException("The description of a event cannot be empty"));
                }
                break;
            }
            default: {
                System.out.println("Sorry I didnt understand you!");
            }
        }
        return tasks;
    }

    public void updateTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}