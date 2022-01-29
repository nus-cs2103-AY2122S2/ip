import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Duke {

    public static void main(String[] args) throws DukeException{
        Scanner sc = new Scanner(System.in);
        FileClass fc = new FileClass(); //file class 
        String home = System.getProperty("user.home"); //home directory

        String filePath = home + "/Desktop/ip/duke.txt";
        System.out.println(filePath);
        fc.createFile(filePath);

        ArrayList<Task> taskArray = new ArrayList<Task>();
        ArrayList<String> stringArray = new ArrayList<String>();

        String beginning = "Hello! I'm Duke\n" +
                           "What can I do for you?";
        System.out.println(beginning);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] checkType = input.split(" ");
            String messageTask = "Got it. I've added this task: \n";

            try {
                if (input.equals("list")) {

                    if (taskArray.isEmpty()) {
                        System.out.println("The list is empty");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskArray.size(); i++) {
                            String index = String.valueOf(i + 1);
                            System.out.println(index + "." + taskArray.get(i));
                        }
                    }
                } else if (checkType[0].equals("mark") || checkType[0].equals("unmark")) { //this is to check whether it goes through marking
                
                    try {
                        int index = Integer.parseInt(checkType[1]) - 1;
                        Task tasks = taskArray.get(index);
                        System.out.println(tasks.marking(checkType[0]));
                        taskArray.set(index, tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("The input is not valid :(");
                    }


                } else if (checkType[0].equals("todo")) { //check todo

                    String toDoCondition = "todo ";
    
                    try {
                        int indexOfToDo = toDoCondition.length(); //to find todo
                        String stringSliced = input.substring(indexOfToDo,input.length());
                        stringArray.add(stringSliced);
                        Todo todoTask = new Todo(stringSliced);
                        taskArray.add(todoTask);
                        String noOfTask = String.valueOf(taskArray.size());
                        System.out.println(messageTask + todoTask.toString() + "\n"
                                        + "Now you have " + noOfTask + " tasks in the list.");

                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
    
    
                } else if (checkType[0].equals("deadline")) { //check deadline
    
                    String deadlineCondition = "/by ";

                    try {
                        int indexOfTime = input.indexOf(deadlineCondition); //to find /
                        String dateTime = input.substring(indexOfTime + deadlineCondition.length(), input.length()); // the date and time for by
                        //convert to the correct one
                        LocalDateTime deadlineTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/y Hmm"));
                        String convertedTime = deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
                        String stringSliced = input.substring(9, indexOfTime); // after deadline
                        stringArray.add(stringSliced);
                        Deadline deadlineTask = new Deadline(stringSliced, convertedTime);
                        taskArray.add(deadlineTask);
                        String noOfTask = String.valueOf(taskArray.size());
                        System.out.println(messageTask + deadlineTask.toString() + "\n"
                                            + "Now you have " + noOfTask + " tasks in the list.");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
    
                } else if (checkType[0].equals("event")) { // check event
    
                    String eventCondition = "/at "; 
                    try {
                        int indexOfTime = input.indexOf(eventCondition); //to find /
                        String dateTime = input.substring(indexOfTime + eventCondition.length(), input.length()); // the date and time for by
                        //convert to the correct one
                        LocalDateTime eventTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/y Hmm"));
                        String convertedTime = eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
                        String stringSliced = input.substring(6, indexOfTime); // after deadline
                        stringArray.add(stringSliced);
                        Event eventTask = new Event(stringSliced, convertedTime);
                        taskArray.add(eventTask);
                        String noOfTask = String.valueOf(taskArray.size());
                        System.out.println(messageTask + eventTask.toString() + "\n"
                                            + "Now you have " + noOfTask + " tasks in the list.");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                } else if (checkType[0].equals("delete")) {

                    try {
                        int index = Integer.parseInt(checkType[1]) - 1;
                        String toBeRemoved = taskArray.get(index).toString();
                        taskArray.remove(index);
                        String noOfTask = String.valueOf(taskArray.size());
                        String messageDeleted = "Noted. I've removed this task: \n" +
                                                toBeRemoved + "\n" +
                                                "Now you have " + noOfTask + " tasks in the list.";
                        System.out.println(messageDeleted);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("There are no tasks to be deleted!");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            
            input = sc.nextLine();
        
        }

        // Recording everything in the duke.txt
        for (int i = 0; i < taskArray.size(); i++ ) {
            Task tasks = taskArray.get(i);
            try {
                String firstInitial = tasks.getInitial() ; //first initial character
                String textToAdd = firstInitial + " | " + tasks.getStatusIcon() + " | " 
                                    + stringArray.get(i);
                fc.writeFile(filePath, textToAdd);
            } catch (IOException e) {
                System.out.println("File not found");
            }

        }

        String ending = "Bye. Hope to see you again soon!";

        System.out.println(ending);
        sc.close();
    }
}
        






