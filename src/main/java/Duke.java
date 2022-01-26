import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> listOfThings = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        String line = "____________________________________________________________";
        String indentation = "    ";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);
        System.out.println(indentation + line);
        System.out.println(indentation + "Hello! I'm Duke\n" + indentation + "What can I do for you?");
        System.out.println(indentation + line);
        Scanner sc = new Scanner(System.in);
        String str = " ";

        File dataFile = loadFile();

        while(true) {
            str = sc.nextLine();
            if (str.equals("list")) {

                if (listOfThings.size() == 0) {
                    System.out.println(indentation + line);
                    System.out.println(indentation + "List is currently empty");
                    System.out.println(indentation + line);
                    continue;
                }

                System.out.println(indentation + line);
                int numbering = 1;
                for (Task t: listOfThings) {
                    System.out.println(indentation + String.valueOf(numbering) + ". "  + t.toString() + t.getStatus() + " " + t.getDescription());
                    numbering++;
                }

                System.out.println(indentation + line);

            }  else if(str.equals("bye")) {
                System.out.println(indentation + line);
                System.out.println(indentation + "Bye. Hope to see you again soon!");
                System.out.println(indentation + line);
                break;
            } else if (str.contains("unmark")) {
                try {
                    int num = Integer.parseInt(str.split(" ")[1]);
                    listOfThings.get(num - 1).unmarkDone();
                    System.out.println(indentation + line);
                    System.out.println(indentation + "OK, I've marked this task as not done yet:");
                    System.out.println(indentation + "  " + listOfThings.get(num - 1).toString() + listOfThings.get(num - 1).getStatus() + " " + listOfThings.get(num - 1).getDescription());
                    System.out.println(indentation + line);
                    saveDataToFile(listOfThings);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indentation + line);
                    System.out.println(indentation + "No such element in the list. Try again");
                    System.out.println(indentation + line);
                }
            } else if (str.contains("mark")) {
                try {
                    int num = Integer.parseInt(str.split(" ")[1]);
                    listOfThings.get(num - 1).markDone();
                    System.out.println(indentation + line);
                    System.out.println(indentation + "Nice! I've marked this task as done:");
                    System.out.println(indentation + "  " + listOfThings.get(num - 1).toString() + listOfThings.get(num - 1).getStatus() + " " + listOfThings.get(num - 1).getDescription());
                    System.out.println(indentation + line);
                    saveDataToFile(listOfThings);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indentation + line);
                    System.out.println(indentation + "No such element in the list. Try again");
                    System.out.println(indentation + line);
                }
            } else if (str.contains("delete")) {
                int num = Integer.parseInt(str.substring(7));
                System.out.println(indentation + line);
                System.out.println(indentation + "Noted. I've removed this task: ");
                Task task = listOfThings.get(num - 1);
                System.out.println(indentation + "  " + task.toString() + task.getStatus() +  " " + task.getDescription());
                listOfThings.remove(num - 1);
                System.out.println(indentation + "Now you have " + listOfThings.size() +" tasks in the list.");
                System.out.println(indentation + line);
                saveDataToFile(listOfThings);
            } else {
                    if (str.contains("todo")) {
                        try {
                            String newString = str.substring(5).trim();
                            if (newString.length() == 0) {
                                throw new StringIndexOutOfBoundsException();
                            }

                            ToDos newToDo = new ToDos(newString);
                            listOfThings.add(newToDo);
                            System.out.println(indentation + line);
                            System.out.println(indentation + "Got it. I've added this task:");
                            System.out.println(indentation + "  " + newToDo.toString() + newToDo.getStatus() + " " + newToDo.getDescription());
                            System.out.println(indentation + "Now you have " + String.valueOf(listOfThings.size()) + " tasks in the list.");
                            System.out.println(indentation + line);
                            appendToFile(dataFile, newToDo);
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println(indentation + line);
                            System.out.println("      OOPS!!! The description of a todo cannot be empty.");
                            System.out.println(indentation + line);

                        }
                    } else if (str.contains("deadline")) {
                        try {
                            String des = str.substring(9, str.indexOf('/') - 1).trim();
                            String date = str.substring((str.indexOf('/') + 4)).trim();
                            Deadline newDeadline = new Deadline(des, date);
                            listOfThings.add(newDeadline);
                            System.out.println(indentation + line);
                            System.out.println(indentation + "Got it. I've added this task:");
                            System.out.println(indentation + "  " + newDeadline.toString() + newDeadline.getStatus() + " " + newDeadline.getDescription());
                            System.out.println(indentation + "Now you have " + String.valueOf(listOfThings.size()) + " tasks in the list.");
                            System.out.println(indentation + line);
                            appendToFile(dataFile, newDeadline);
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println(indentation + line);
                            System.out.println("      OOPS!!! Incorrect format for entering deadlines");
                            System.out.println(indentation + line);

                        }
                    } else if (str.contains("event")) {
                        try {
                            String des = str.substring(6, str.indexOf('/') - 1).trim();
                            String date = str.substring((str.indexOf('/') + 4)).trim();
                            Event newEvent = new Event(des, date);
                            listOfThings.add(newEvent);
                            //listOfThings[counter] = new Event(des, date);
                            System.out.println(indentation + line);
                            System.out.println(indentation + "Got it. I've added this task:");
                            System.out.println(indentation + "  " + newEvent.toString() + newEvent.getStatus() + " " + newEvent.getDescription());
                            System.out.println(indentation + "Now you have " + String.valueOf(listOfThings.size()) + " tasks in the list.");
                            System.out.println(indentation + line);
                            appendToFile(dataFile, newEvent);

                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println(indentation + line);
                            System.out.println("      OOPS!!! Incorrect format for entering events");
                            System.out.println(indentation + line);
                        }
                    } else {
                        System.out.println(indentation + line);
                        System.out.println(indentation + " OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println(indentation + line);

                    }
            }
        }
    }

    public static void saveDataToFile(ArrayList<Task> taskArrayList) throws IOException {


        String basePath = new File("").getAbsolutePath();
        File file = new File(basePath + "/data/myfile.txt");
        FileWriter fileWriter = new FileWriter(file, false);

        for (Task task: taskArrayList) {
            String toPrint = task.toString() + task.getStatus() +  task.getDescription();
            fileWriter.write(toPrint + "\n");

        }
        fileWriter.close();
    }

    public static File loadFile() throws IOException {


        String basePath = new File("").getAbsolutePath();
        String dataDirectory = basePath + "/data";
        File directory = new File(dataDirectory);
        if (! directory.exists()){
            directory.mkdir();
            System.out.println("Data folder does not exist. Creating directory... ");
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        File file = new File(basePath + "/data/Duke.txt");


        if (file.createNewFile()) {
            System.out.println("Duke Text File does not exist. Creating file...");
            return file;
        }

        System.out.println("Loading up data...");

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (currentLine.contains("[T]")) {
                ToDos todo;
                if (currentLine.charAt(4) == 'X') {
                    todo = new ToDos(currentLine.substring(6), true);
                    listOfThings.add(todo);
                } else {
                    todo = new ToDos(currentLine.substring(6));
                    listOfThings.add(todo);
                }
            } else if (currentLine.contains("[D]")) {
                String date = currentLine.substring(currentLine.indexOf('(')+5, currentLine.indexOf(')'));
                Deadline deadline;
                if (currentLine.charAt(4) == 'X') {
                    deadline = new Deadline(currentLine.substring(6, currentLine.indexOf('(')-1), true, date);
                } else {
                    deadline = new Deadline(currentLine.substring(6, currentLine.indexOf('(')-1), date);
                }
                listOfThings.add(deadline);
            } else if (currentLine.contains("[E]")) {
                String date = currentLine.substring(currentLine.indexOf('(')+5, currentLine.indexOf(')'));
                Event event;
                if (currentLine.charAt(4) == 'X') {
                    event = new Event(currentLine.substring(6, currentLine.indexOf('(')-1), true, date);
                } else {
                    event = new Event(currentLine.substring(6, currentLine.indexOf('(')-1), date);
                }
                listOfThings.add(event);
            }
        }

        return file;
    }

    static void appendToFile(File file, Task task) throws IOException {

        FileWriter fileWriter = new FileWriter(file, true);
        String toPrint = task.toString() + task.getStatus() +  task.getDescription();
        fileWriter.write(toPrint + "\n");
        fileWriter.close();

    }
}




