//Author: Tan Ting Yu
//Student Number: A218235J


import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {
        //Program's opening message
        System.out.println("____________________________________________________________");
        System.out.println("    Wassup! I'm Puke! \n    What can I do for you?");
        System.out.println("____________________________________________________________");

        //Scanner initialization for user input
        Scanner sc = new Scanner(System.in);
        String userInput = "";

        //Task List
        ArrayList<Task> taskList = new ArrayList<>();
        //Counter for number of tasks in Task List
        int numOfTasks = 0;

        while (true) {
            //User Input dictates what the program does
            userInput = sc.nextLine();

            //Case 1: Terminate the program
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("    Bye Bye! Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                //Break to exit
                break;
            }
            //Case 2: List the todolist
            else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < numOfTasks; i++)
                    System.out.println("    " + (i + 1) + ". " + taskList.get(i));
                System.out.println("____________________________________________________________");
            }

            //Case 3: mark or unmark the task
            else if (userInput.contains("mark")) {
                String[] splitString = userInput.split(" ");
                //If no second input
                if (splitString[1].equals(""))
                    System.out.println("Please input a second input. Eg mark 1, unmark 2");

                //Handle error if the second input is not an integer
                try {
                    //Gets the index of the task in the task list
                    int index = Integer.parseInt(splitString[1]);
                    //If index is out of range, throw illegal argument exception
                    if (index < 0 || index > numOfTasks) {
                        throw new IllegalArgumentException();
                    }

                    //If no errors, continue
                    //Gets the task that we want to mark or unmark
                    Task thatTask = taskList.get(index - 1);

                    System.out.println("____________________________________________________________");
                    String displayMessage = "";
                    if (splitString[0].equals("mark")) {
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

                //IllegalArgumentException is thrown if the second input is out of range
                } catch (IllegalArgumentException e) {
                    System.out.println("    Out of range! Number has to be between 1 and " + numOfTasks);
                    System.out.println("____________________________________________________________");
                }

            }


            //Case 4: Add the task
            else {
                if (userInput.equals("")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("    Task cannot be empty!");
                    System.out.println("____________________________________________________________");
                } else {
                    taskList.add(new Task(userInput));
                    numOfTasks++;
                    System.out.println("____________________________________________________________");
                    System.out.println("    added: " + userInput);
                    System.out.println("____________________________________________________________");
                }
            }

        }





    }
}
