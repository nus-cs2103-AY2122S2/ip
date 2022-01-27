import java.util.Arrays;
import java.util.Scanner;

import javax.annotation.processing.FilerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> list;
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Greetings
        System.out.println("Greetings! I'm Duke" + "\n" + "What can I do for you today?\n" + logo);

        try {
            list = LoadFile.load();

            if (!LoadFile.loaded) {
                list = new ArrayList<>();
            }
            //to read user input
            Scanner scanned = new Scanner(System.in);
            System.out.println("");
            String input = scanned.nextLine();

            //continue the scanning if user does not say bye, else continue repeating what the user says
            while (!input.equals("bye")) {
                System.out.println("=======================================================================");

                //prints the list of items when user inputs list
                if (input.equals("list")) {
                    printList(list);
                }

                //marks a task as done if the user inputs a string in format "mark xx"
                //NOTE: currently, inputting "mark" alone results in a task named "mark" to be added to the list of tasks
                else if (input.length() > 4 && "mark".equals(input.substring(0, 4))) {
                    ArrayList<String> arr = new ArrayList<>(Arrays.asList(input.split(" ")));
                    if (arr.get(1) == "") {
                        throw new DukeException(
                                "Oops! Which task would you like to mark?"
                        );
                    }
                    int i = Integer.parseInt(arr.get(1));
                    if (i <= list.size() && i > 0) {
                        Task toBeMarked = list.get(Integer.parseInt(input.substring(5)) - 1);
                        toBeMarked.markAsDone();
                    } else {
                        throw new DukeException(
                                "That is not a valid task number!"
                        );
                    }
                }

                //unmarks a task as undone if the user inputs a string in format "unmark xx"
                //NOTE: currently, inputting "unmark" alone results in a task named "unmark" to be added to the list of tasks
                else if (input.length() > 6 && "unmark".equals(input.substring(0, 6))) {
                    ArrayList<String> arr = new ArrayList<>(Arrays.asList(input.split(" ")));
                    if (arr.get(1) == "") {
                        throw new DukeException(
                                "Oops! Which task would you like to unmark?"
                        );
                    }
                    int i = Integer.parseInt(arr.get(1));
                    if (i <= list.size() && i > 0) {
                        Task toBeMarked = list.get(Integer.parseInt(input.substring(7)) - 1);
                        toBeMarked.markAsUndone();
                    } else {
                        throw new DukeException(
                                "That is not a valid task number!"
                        );
                    }
                }

                //adds a task to the list if user inputs "todo xxx"
                else if (input.length() >= 4 && "todo".equals(input.substring(0, 4))) {
                    if (stripDescription(input)[0] == "") {
                        throw new DukeException(
                           "Oops, the description of todo cannot be empty! Please tell me what you want to do."
                        );
                    }
                    Task toDo = new Todo(stripDescription(input)[0]);
                    list.add(toDo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(toDo.toString());
                    System.out.println("Now you have " + list.size() + " in the list.");
                }

                //schedules an event and stores the location of the event in the list in the format
                //"event xxx /at xxx"
                else if (input.length() >= 5 && "event".equals(input.substring(0, 5))) {
                    if (stripDescription(input)[0] == "") {
                        throw new DukeException(
                                "Oops, the description of event cannot be empty!"
                        );
                    }

                    if (stripDescription(input)[1] == "") {
                        throw new DukeException(
                                "Oops, please tell me where this will occur!"
                        );
                    }
                    Task event = new Event(stripDescription(input)[0], stripDescription(input)[1]);
                    System.out.println("Got it. I've added this task:");
                    list.add(event);
                    System.out.println(event.toString());
                    System.out.println("Now you have " + list.size() + " in the list.");
                }

                //adds a deadline and stores the date/time of the deadline in the list in the format
                //"deadline xxx /by xxx"
                else if (input.length() >= 8 && "deadline".equals(input.substring(0, 8))) {
                    if (stripDescription(input)[0] == "") {
                        throw new DukeException(
                                "Oops, the description of deadline cannot be empty!"
                        );
                    }

                    if (stripDescription(input)[1] == "") {
                        throw new DukeException(
                                "Oops, please tell me when is this due!"
                        );
                    }
                    Task deadline = new Deadline(stripDescription(input)[0], stripDescription(input)[1]);
                    System.out.println("Got it. I've added this task:");
                    list.add(deadline);
                    System.out.println(deadline.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }

                //delete a task from the list
                else if (input.length() >= 6 && "delete".equals(input.substring(0, 6))) {
                    ArrayList<String> arr = new ArrayList<>(Arrays.asList(input.split(" ")));
                    if (arr.get(1) == "") {
                        throw new DukeException(
                                "Oops! Which task would you like to delete?"
                        );
                    }

                    int i = Integer.parseInt(arr.get(1));
                    if (i <= list.size() && i > 0) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(list.get(i-1).toString());
                        list.remove(i - 1);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");

                    } else {
                        throw new DukeException(
                                "That is not a valid task number!"
                        );
                    }
                }


                //unrecognised input, throw exception
                else {
                    throw new DukeException(
                            "Sorry I didn't recognise that command, please try again."
                    );
                }

                saveFile();
                System.out.println("Saved!");

                //to continue reading user input
                scanned = new Scanner(System.in);
                System.out.println("");
                input = scanned.nextLine();
                
            }

            //goodbye msg
            System.out.println("Sad to see you leave, come back soon!");
        } catch (DukeException e) {
            System.err.print(e);
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    //to add the user's input to list
    public static void addToList(String description) {
        Task item = new Task(description);
        list.add(item);
    }

    //to print the current list of items
    public static void printList(ArrayList<Task> arraylist) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arraylist.size(); i++) {
            int count = i + 1;
            System.out.println(count + ". " + arraylist.get(i).toString());
        }
    }

    public static String[] stripDescription(String s) throws DukeException {

        ArrayList<String> arr = new ArrayList<>(Arrays.asList(s.split(" ")));
        if (arr.contains("/at")) {
            int i = arr.indexOf("/at");
            String str1 = String.join(" ", arr.subList(1, i));
            String str2 = String.join(" ", arr.subList(i + 1, arr.size()));
            String[] str = new String[2];



            str[0] = str1;
            str[1] = str2;
            return str;
        } else if (arr.contains("/by")) {
            int i = arr.indexOf("/by");
            String str1 = String.join(" ", arr.subList(1, i));
            String str2 = String.join(" ", arr.subList(i + 1, arr.size()));
            String[] str = new String[2];

            str[0] = str1;
            str[1] = str2;
            return str;
        } else {
            String str1 = String.join(" ", arr.subList(1, arr.size()));
            String str2 = "";
            String[] str = new String[2];
            str[0] = str1;
            str[1] = str2;
            return str;
        }

    }


    //save file to the hard disk
    public static void saveFile() throws IOException {
        String str = ReadFile.readFile(list);
        WriteFile.writeToFile(str);
    }
}
