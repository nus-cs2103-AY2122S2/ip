package Taskmaster.Commands;
import Taskmaster.Exception.DukeExceptions;
import Taskmaster.util.TaskList;
import Taskmaster.Task.Task;



public class MarkCommands extends Commands {
    private TaskList tasklist;
    public MarkCommands(String command, TaskList tasklist) {
        super(command);
        this.tasklist = tasklist;
    }

    private void parseCommand() {
        String splitString[] = this.command.split(" ");
        String firstWord = splitString[0];

        try {
            if (splitString.length == 1) {
                throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg mark 1, unmark 2, delete 3\n");
            } else if (splitString.length > 2) {
                //Handle the case of having more than 2 inputs
                throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg mark 1, unmark 2, delete 3\n");
            }

            //Handle error if the second input is not an integer
            //Gets the index of the task in the task list
            int index = Integer.parseInt(splitString[1]);
            //If index is out of range, throw illegal argument exception
            if (index <= 0 || index > tasklist.currentSize) {
                throw new DukeExceptions("BRAT ! Your index is out of range! Number has to in the range of the list\n");
            }


            if (firstWord.equals("mark")) {
                mark(index);
            } else if (firstWord.equals("unmark")) {
                unmark(index);
            }


        } catch (NumberFormatException nfe) {
            System.out.println("What? Second input has to be an integer! Eg mark 1, unmark 2\n");
            //Out of task range is thrown if the second input is out of range
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }

    }

    private void mark(int index) {
        Task selectedTask = tasklist.get(index - 1);
        System.out.println("Aye! I've marked this task as completed:\n");
        tasklist.mark(index - 1);
        printTask(selectedTask);
    }

    private void unmark(int index) {
        Task selectedTask = tasklist.get(index - 1);
        System.out.println("Aye kid! I've marked this task as uncompleted:\n");
        tasklist.unmark(index - 1);
        printTask(selectedTask);
    }

    private void printTask(Task task) {
        System.out.println("    " + task.toString());
    }

    public void execute() {
        parseCommand();
    }


}
