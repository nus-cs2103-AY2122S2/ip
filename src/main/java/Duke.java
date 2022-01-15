//Author: Tan Ting Yu
//Student Number: A218235J


import java.util.Scanner;
import java.util.ArrayList;


public class Duke {


    public static void main(String[] args) {
        //Program's opening message
        System.out.println("____________________________________________________________");
        System.out.println("    Whats up! I'm Puke! \n    What can I do for you?");
        System.out.println("____________________________________________________________");

        //Scanner initialization for user input
        Scanner sc = new Scanner(System.in);
        //Command is the command that will be executed
        String command = "";
        //User input is the raw user input
        String userInput = "";


        //Task List
        ArrayList<Task> taskList = new ArrayList<>();
        //Counter for number of tasks in Task List
        int numOfTasks = 0;

        while (true) {
            //User Input dictates what the program does
            userInput = sc.nextLine();

            //Retrieves just the command
            String splitString[] = userInput.split(" ");
            command = splitString[0];


            //Case 1: Terminate the program
            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("    Bye Bye! Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                //Break to exit
                break;
            }

            //Case 2: List the todolist
            else if (command.equals("list")) {

                //Handle the case of list being empty
                if (numOfTasks == 0) {
                    System.out.println("____________________________________________________________");
                    System.out.println("    You have yet to add any tasks!");
                    System.out.println("____________________________________________________________");
                } else {
                    //Display the task list
                    System.out.println("____________________________________________________________");
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < numOfTasks; i++)
                        System.out.println("    " + (i + 1) + ". " + taskList.get(i));
                    System.out.println("____________________________________________________________");
                }
            }

            //Case 3: mark or unmark the task
            else if (command.equals("mark") || command.equals("unmark")) {


                //Handle the case of having no second input

                if (splitString.length == 1 ) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Please input a second input. Eg mark 1, unmark 2");
                    System.out.println("____________________________________________________________");
                }  else if (splitString.length > 2) {
                    //Handle the case of having more than 2 inputs
                    System.out.println("____________________________________________________________");
                    System.out.println("Please enter only 2 inputs. Eg mark 1, unmark 2");
                    System.out.println("____________________________________________________________");
                } else {

                    try {
                        //Handle error if the second input is not an integer

                        //Gets the index of the task in the task list
                        int index = Integer.parseInt(splitString[1]);
                        //If index is out of range, throw illegal argument exception
                        if (index <= 0 || index > numOfTasks) {
                            throw new DukeExceptions("Index is out of task range");
                        }

                        //If no errors, continue
                        //Gets the task that we want to mark or unmark
                        Task thatTask = taskList.get(index - 1);

                        System.out.println("____________________________________________________________");
                        String displayMessage = "";
                        if (command.equals("mark")) {
                            displayMessage = "    Nice! I've marked this task as done:";
                            thatTask.markTask();
                        } else {
                            displayMessage = "  OK, I've marked this task as not done yet:";
                            thatTask.unmarkTask();
                        }

                        System.out.println(displayMessage);
                        System.out.println("    " + thatTask);
                        System.out.println("____________________________________________________________");


                        //NumberFormatException is thrown if the second input is not an integer
                    } catch (NumberFormatException nfe) {
                        System.out.println("    Second input has to be an integer! Eg mark 1, unmark 2");
                        System.out.println("____________________________________________________________");

                        //Out of task range is thrown if the second input is out of range
                    } catch (DukeExceptions e) {
                        System.out.println("    Out of range! Number has to in the range of the list");
                        System.out.println("    You can check by using the list command");
                        System.out.println("____________________________________________________________");
                    }

                }
            }


            //Case 4: Add the task under todo/deadline/event
            else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                //Pass to check whether task is to be created
                boolean pass = false;

                //Handle the case of having no second input

                if (splitString.length == 1 ) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Task description/name cannot be empty. Eg todo eat, deadline eat food /by 12pm, event concert /at 8pm");
                    System.out.println("____________________________________________________________");
                } else {
                    String taskName = userInput.substring(userInput.indexOf(" "));
                    Task newTask = new Task("");
                    if (command.equals("todo")) {
                        newTask = new TodoTask(taskName);
                        pass = true;

                    } else {
                        //Handle the case of having no "/" to specify deadline or time of occurrences for deadline and event tasks
                        if (!taskName.contains("/")) {
                            System.out.println("____________________________________________________________");
                            System.out.println("Deadline and event tasks require /by and /at to specify the deadline or time of occurrence.");
                            System.out.println("Eg Deadline eat food /by 12pm, event concert /at 8pm");
                            System.out.println("____________________________________________________________");
                        } else {
                            String temp = taskName.substring(taskName.indexOf("/"));
                            //String that states by ... or at....
                            if (command.equals("deadline")) {
                                //Handle the case of deadline task having no /by
                                if (!temp.contains("by")) {
                                    System.out.println("____________________________________________________________");
                                    System.out.println("Deadline tasks require /by specify the deadline.");
                                    System.out.println("Eg deadline eat food /by 12pm");
                                    System.out.println("____________________________________________________________");
                                } else {
                                    String timeOfOccurrence = temp.substring(temp.indexOf(" ") + 1);
                                    newTask = new DeadlineTask(taskName, timeOfOccurrence);
                                    pass = true;
                                }
                            } else {
                                //Handle the case of event task having no /at
                                if (!temp.contains("at")) {
                                    System.out.println("____________________________________________________________");
                                    System.out.println("Event tasks require /at specify the time of occurrence.");
                                    System.out.println("Eg music concert /at 8pm");
                                    System.out.println("____________________________________________________________");
                                } else {
                                    String timeOfOccurrence = temp.substring(temp.indexOf(" ") + 1);
                                    newTask = new EventTask(taskName, timeOfOccurrence);
                                    pass = true;
                                }
                            }
                        }
                    }

                    //Only tasks that pass the check can be created
                    if (pass) {
                        taskList.add(newTask);
                        numOfTasks++;
                        System.out.println("____________________________________________________________");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("    " + newTask);
                        System.out.println("    Now you have " + numOfTasks + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }

                }
            }

            //Case 5: Help lists all the commands
            else if (userInput.equals("help")) {
                System.out.println("____________________________________________________________");
                System.out.println("Commands: ");
                System.out.println("    List                                    -list out all your current tasks");
                System.out.println("    todo <task name>                        -Add a todo task without any deadline specified");
                System.out.println("    deadline <task name> /by <deadline>     -Adds a task that has to be done before the specified deadline");
                System.out.println("    event <task name> /at <deadline>        -Adds a task that occurs at the specified deadline");
                System.out.println("    mark <task number>                      -marks task as completed");
                System.out.println("    unmark <task number>                    -marks a completed task as uncompleted");
                System.out.println("    bye                                     -exits the program :'( ");
                System.out.println("____________________________________________________________");
            }

            //Case 6: User enters an invalid command
            else {
                    System.out.println("____________________________________________________________");
                    System.out.println("    " + userInput + "?");
                    System.out.println("    I'm sorry, i did not understand that command!");
                    System.out.println("    Try typing in help for the list of commands!");
                    System.out.println("____________________________________________________________");
            }

        }





    }
}
