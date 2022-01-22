//Author: Tan Ting Yu
//Student Number: A218235J


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/*
 * Commands encapsulates the operations that will be performed based on the user's response
 */

public class Commands {
    private String command;

     /**
     * Constructor for Command Objects
     *
     * @param command - The command entered by the user
     */

    public Commands(String command) {
        this.command = command;
    }
    
    //Default case when user enters wrong command
    public void invalidCommand() {
        System.out.println("\n" + this.command + "?");
        System.out.println("What are you on about?");
        System.out.println("Type list if you want to know the commands, kid!\n");
    }

    //Contains the list of commands when user enters help
    public void listOfCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("\nCommands: ");
        System.out.println("    List                                    -list out all your current tasks");
        System.out.println("    todo <task name>                        -Add a todo task without any deadline specified");
        System.out.println("    deadline <task name> /by <deadline>     -Adds a task that has to be done before the specified deadline");
        System.out.println("    event <task name> /at <deadline>        -Adds a task that occurs at the specified deadline");
        System.out.println("    mark <task number>                      -marks task as completed");
        System.out.println("    unmark <task number>                    -marks a completed task as uncompleted");
        System.out.println("    bye                                     -exits the program :'( ");
        System.out.println("____________________________________________________________\n");
    }


    /**
     * Function that works as a switch for user's input
     * 
     * @param tasklist - Tasklist that is created by collating all the tasks
     */
    public void performCommand(TaskList tasklist) {
        
        //Splits up the string to identify the main command
        String splitString[] = this.command.split(" ");
        String firstWord = splitString[0];
        
        //Case "List"
         if (firstWord.equals("list")) {
                tasklist.list();
         } 
         
        //Case "mark" & Case("unmark") & Case"delete"
        //Collated together because it works similarly
        else if (firstWord.equals("mark") || firstWord.equals("unmark") || firstWord.equals("delete")) {
            try {
                //String to contain successful deleted message if its a delete command
                String deleted = "";
                //Handle the case of having no second input
                if (splitString.length == 1 ) 
                    throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg mark 1, unmark 2, delete 3\n");
                //Handle the case of having more than 2 inputs
                if (splitString.length > 2)
                    throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg mark 1, unmark 2, delete 3\n");
            
                //Handle error if the second input is not an integer
                //Gets the index of the task in the task list
                int index = Integer.parseInt(splitString[1]);
                //If index is out of range, throw illegal argument exception
                if (index <= 0 || index > tasklist.currentSize) {
                         throw new DukeExceptions("BRAT ! Your index is out of range! Number has to in the range of the list\n");
                }
                //If no errors, continue
                //Task that we are going to mark/unmark/delete
                Task thatTask = tasklist.get(index - 1);
                String displayMessage = "";
                if (firstWord.equals("mark")) {
                    displayMessage = "Aye! I've marked this task as completed:\n";
                    tasklist.mark(index - 1);
                } else if (firstWord.equals("unmark")){
                    displayMessage = "Aye kid! I've marked this task as uncompleted:\n";
                    tasklist.unmark(index - 1);
                } else {
                    tasklist.delete(index - 1);
                    displayMessage = "YES! I've removed this task and soon I'll remove you as well!:\n";
                    deleted = "Now you have " + tasklist.currentSize + " tasks in the list\n";
                }
                    
                System.out.println(displayMessage);
                System.out.println("    " + thatTask.toString());
                System.out.println(deleted);
                    
                //NumberFormatException is thrown if the second input is not an integer
                } catch (NumberFormatException nfe) {
                    System.out.println("What? Second input has to be an integer! Eg mark 1, unmark 2\n");
                    //Out of task range is thrown if the second input is out of range
                } catch (DukeExceptions e) {
                    System.out.println(e.getMessage());
                }

             }
         
          
            
            else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                //Pass to check whether task is to be created
                boolean created = false;
                try {
                //Handle the case of having no second input

                if (splitString.length == 1 ) 
                    throw new DukeExceptions("What?! Task description cannot be empty. Eg todo eat, deadline eat food /by 12pm, event concert /at 8pm\n");
                
                String taskName = command.substring(command.indexOf(" "));
                String msg = "Quit fooling around kid!";
                Task newTask = new Task("");
                if (firstWord.equals("todo")) {
                    newTask = new TodoTask(taskName);
                    created = true;
                } else {
                    //Handle the case of having no "/" to specify deadline or time of occurrences for deadline and event tasks
                    if (!taskName.contains("/")) {
                        msg.concat("Deadline and event tasks require /by and /at to specify the deadline or time of occurrence.\n");
                        msg.concat("Eg Deadline eat food /by 12pm, event concert /at 8pm\n");
                        throw new DukeExceptions(msg);
                    }
                   
                    String temp = taskName.substring(taskName.indexOf("/"));
                    String taskNameWithoutBack = taskName.substring(0, taskName.indexOf("/"));
                    String oldDateTime = temp.substring(temp.indexOf(" ") + 1);
                    
                    DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(oldDateTime, oldFormat);


                    //String that states by ... or at....
                    if (firstWord.equals("deadline")) {
                        //Handle the case of deadline task having no /by
                        if (!temp.contains("by")) {
                                msg.concat("Deadline tasks require /by specify the deadline.");
                                msg.concat("Eg deadline eat food /by 12pm\n");
                                throw new DukeExceptions(msg);
                        } 
                        
                        newTask = new DeadlineTask(taskNameWithoutBack, dateTime);
                        created = true;
                        
                    } else {
                        //Handle the case of event task having no /at
                        if (!temp.contains("at")) {
                            msg.concat("Event tasks require /at specify the time of occurrence.");
                            msg.concat("music concert /at 8pm\n");
                            throw new DukeExceptions(msg);
                        } 
                        
                        newTask = new EventTask(taskNameWithoutBack, dateTime);
                        created = true;
                    }
                        
                }

                //Only tasks that pass the check can be created
                if (created) {
                    tasklist.add(newTask);
                    System.out.println("Quit ordering me around!");
                    System.out.println("I've added this task to our list:");
                    System.out.println("    " + newTask);
                    System.out.println("Now you have " + tasklist.currentSize + " tasks in the list.\n");
                }
                    
            
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("ERROR! Expected Numbers for date and time!\n");
            } catch (DateTimeParseException e) {
                System.out.println("ERROR! Time or Date is in wrong format! 2/12/2019 1800\n");
            }
        } 

        //Case "Find"
        else if (firstWord.equals("find")) {
            try {
                if (splitString.length == 1)
                    throw new DukeExceptions("ERROR: find command requires a parameter to specify what keyword to find");
                
                String toFind = this.command.substring(this.command.indexOf(" ") + 1);
                
                tasklist.find(toFind);

            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
            }
                
        }

        //Case "help""
        //List out all commands 
        else if (firstWord.equals("help")) {
          listOfCommand();
        }
        //Invalid command case
        else {
           invalidCommand();
        }
           
    }
}