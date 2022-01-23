package Taskmaster.Commands;

import Taskmaster.Exception.DukeExceptions;
import Taskmaster.util.TaskList;
import Taskmaster.Task.Task;



public class DeleteCommands extends Commands {
    private TaskList tasklist;

    public DeleteCommands(String command, TaskList tasklist) {
        super(command);
        this.tasklist = tasklist;
    }

    private void parseCommand() {
        String splitString[] = this.command.split(" ");
        String firstWord = splitString[0];

        try {
            if (splitString.length == 1) {
                throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg delete 1\n");
            } else if (splitString.length > 2) {
                //Handle the case of having more than 2 inputs
                throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg delete 3\n");
            }

            //Handle error if the second input is not an integer
            //Gets the index of the task in the task list
            int index = Integer.parseInt(splitString[1]);
            //If index is out of range, throw illegal argument exception
            if (index <= 0 || index > tasklist.currentSize) {
                throw new DukeExceptions("BRAT ! Your index is out of range! Number has to in the range of the list\n");
            }

            deleteTask(index);


        } catch (NumberFormatException nfe) {
            System.out.println("What? Second input has to be an integer! Eg mark 1, unmark 2\n");
            //Out of task range is thrown if the second input is out of range
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }

    }

    private void deleteTask(int index) {
        Task selectedTask = tasklist.get(index - 1);
        System.out.println("YES! I've removed this task and soon I'll remove you as well!:\n");
        printTask(selectedTask);
        tasklist.delete(index - 1);
        System.out.println("Now you have " + tasklist.currentSize + " tasks in the list\n");
    }


    private void printTask(Task task) {
        System.out.println("    " + task.toString());

    }

    public void execute() {
        parseCommand();
    }


}
