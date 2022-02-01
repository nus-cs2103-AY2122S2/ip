import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }


    public static void main(String[] args) throws DukeException, IOException {

        Scanner myObj = new Scanner(System.in);
        int counter = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        File f = new File("data/file.txt");
        File f1 = new File("data");
        String file1 = "data/file.txt";
        //  System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        if(!f.exists()) {
            if (!f1.exists()) {
                f1.mkdir();
            }
            f.createNewFile();
        }

        while (true) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < counter; i++) {
                    System.out.println( Integer.toString(i + 1) + "." + tasks.get(i));
                }
            } else if(input.length() > 3 && input.substring(0,4).equals("mark")){
                try {
                    int toBeMarked = Integer.parseInt(input.substring(5));
                    Task currentTask = tasks.get(toBeMarked - 1);
                    currentTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currentTask);
                    writeToFile(file1, tasks.get(0).toString());
                    for(int i = 1; i < tasks.size(); i++) {
                        appendToFile(file1, tasks.get(i).toString());
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Please tell me which task to mark as done!");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }

            } else if(input.length() > 5 && input.substring(0,6).equals("unmark")){
                try {
                    int toBeUnmarked = Integer.parseInt(input.substring(7));
                    Task currentTask = tasks.get(toBeUnmarked - 1);
                    currentTask.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(currentTask);
                    writeToFile(file1, tasks.get(0).toString());
                    for(int i = 1; i < tasks.size(); i++) {
                        appendToFile(file1, tasks.get(i).toString());
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Please tell me which task you want me to mark as not done!");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }

            } else if(input.length() > 3 && input.substring(0, 4).equals("todo")) {
                try {
                    String[] arrOfStr = input.split(" ", 2);
                    tasks.add(new Todo(arrOfStr[1]));
                    counter = counter + 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(counter - 1));
                    appendToFile(file1, tasks.get(counter - 1).toString());
                    if (counter == 1) {
                        System.out.println("Now you have " + String.valueOf(counter) + " task in the list.");
                    } else {
                        System.out.println("Now you have " + String.valueOf(counter) + " tasks in the list.");
                    }

                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }

            } else if(input.length() > 7  && (input.substring(0, 8).equals("deadline"))) {
                try {
                    String[] arrOfStr = input.split(" ", 2);
                    String[] arrOfStr2 = arrOfStr[1].split("/", 2);
                    String[] arrOfStr3 = arrOfStr2[1].split(" ", 2);

                    tasks.add(new Deadline(arrOfStr2[0], arrOfStr3[1]));
                    counter = counter + 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(counter - 1));
                    appendToFile(file1, tasks.get(counter - 1).toString());
                    if (counter == 1) {
                        System.out.println("Now you have " + String.valueOf(counter) + " task in the list.");
                    } else {
                        System.out.println("Now you have " + String.valueOf(counter) + " tasks in the list.");
                    }

                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Please provide a deadline for your task.");

                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }

            } else if(input.length() > 4 && input.substring(0, 5).equals("event")) {
                try {
                    String[] arrOfStr = input.split(" ", 2);
                    String[] arrOfStr2 = arrOfStr[1].split("/", 2);
                    String[] arrOfStr3 = arrOfStr2[1].split(" ", 2);

                    tasks.add(new Event(arrOfStr2[0], arrOfStr3[1]));
                    counter = counter + 1;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(counter - 1));
                    appendToFile(file1, tasks.get(counter - 1).toString());
                    if (counter == 1) {
                        System.out.println("Now you have " + String.valueOf(counter) + " task in the list.");
                    } else {
                        System.out.println("Now you have " + String.valueOf(counter) + " tasks in the list.");
                    }

                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Please provide a timing for your event.");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }

            } else if(input.length() > 5 && input.substring(0, 6).equals("delete")) {
                try {
                    int toBeDeleted = Integer.parseInt(input.substring(7));

                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(toBeDeleted- 1));
                    counter = counter - 1;
                    tasks.remove(toBeDeleted- 1);
                    if (counter == 1) {
                        System.out.println("Now you have " + String.valueOf(counter) + " task in the list.");
                    } else {
                        System.out.println("Now you have " + String.valueOf(counter) + " tasks in the list.");
                    }
                    writeToFile(file1, tasks.get(0).toString());
                    for(int i = 1; i < tasks.size(); i++) {
                        appendToFile(file1, tasks.get(i).toString());
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Please tell me which task you want me to mark as not done!");

                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
