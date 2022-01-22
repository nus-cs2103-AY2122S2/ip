import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "       __  \n"
                + "(____()'`; \n"
                + "/,    /` \n"
                + "\\\\\"--\\\\\n";

        System.out.println("Woof, I am (supposed to look like) a dog bot. \n" + logo + "\n" +  "What do you want from me?\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        List<Task> tasks = new ArrayList<>();
        while (!str.equals("bye")){
            try {
                errorHandling(str);
                if (str.equals("list")){
                    try {
                        Scanner s = new Scanner(new File("Data/tasks.txt"));
                        while (s.hasNext()) {
                            System.out.println(s.nextLine());
                        }
                        s.close();
                    }
                    catch (IOException e){
                        System.out.println("List is empty!");
                    }
                }
                else {
                    String[] temp = str.split(" ");
                    if (temp[0].equals("unmark") || temp[0].equals("mark") || temp[0].equals("delete")) {
                            int taskNumber = Integer.parseInt(temp[1]);
                            Path path = Paths.get("Data/tasks.txt");
                            long lines = 0;
                            try {
                                lines = Files.lines(path).count();
                                if (lines < taskNumber) {
                                    System.out.println("Invalid Task number!");
                                } else {
                                    if (temp[0].equals("mark")) {
                                        Task currTask = tasks.get(taskNumber - 1);
                                        currTask.setDone();
                                        System.out.println("Nice! I've marked this task as done: \n" + "  " + currTask);
                                    } else if (temp[0].equals("delete")) {
                                        int index = Integer.parseInt(str.substring(7));
                                        String text = "";
                                        Scanner scanner = new Scanner(new File("Data/tasks.txt"));
                                        File nFile = new File("Data/task2.txt");
                                        for (int i = 0; scanner.hasNext(); i++) {
                                            if (i != index - 1) {
                                                writeToFile("Data/tasks2.txt", scanner.nextLine() + System.lineSeparator());
                                            } else {
                                                text = scanner.nextLine();
                                            }
                                        }
                                        Files.move(Paths.get("Data/tasks2.txt"), Paths.get("Data/tasks.txt"), StandardCopyOption.REPLACE_EXISTING);
                                        System.out.println("Okay, I have deleted " + text);
                                    } else {
                                        Task currTask = tasks.get(taskNumber - 1);
                                        currTask.setNotDone();
                                        System.out.println("OK, I've marked this task as not done yet:: \n" + "  " + currTask);
                                    }
                                }
                            }
                            catch (IOException e) {
                                System.out.println("List is empty!");
                            }
                    }
                    else {
                        if (temp[0].equals("todo")){
                            Todo todo = new Todo(str.substring(5));
                            try {
                                writeToFile("Data/tasks.txt", todo.toString() + System.lineSeparator());
                            }
                            catch (IOException e){
                                File f = new File("Data");
                                f.mkdirs();
                                writeToFile("Data/tasks.txt", todo.toString() + System.lineSeparator());
                            }
                            System.out.println("Got it. I've added this task: \n  " + todo);
                        }
                        else if (temp[0].equals("event")){
                            Event event = new Event(str.substring(6));
                            try {
                                writeToFile("Data/tasks.txt", event.toString() + System.lineSeparator());
                            }
                            catch (IOException e){
                                File f = new File("Data");
                                f.mkdirs();
                                writeToFile("Data/tasks.txt", event.toString() + System.lineSeparator());
                            }
                            System.out.println("Got it. I've added this task: \n  " + event);
                        }
                        else if (temp[0].equals("deadline")){
                            Deadline deadline = new Deadline(str.substring(9));
                            try {
                                writeToFile("Data/tasks.txt", deadline.toString() + System.lineSeparator());
                            }
                            catch (IOException e){
                                File f = new File("Data");
                                f.mkdirs();
                                writeToFile("Data/tasks.txt", deadline.toString() + System.lineSeparator());
                            }
                            System.out.println("Got it. I've added this task: \n  " + deadline);
                        }
                    }
                }
           }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            finally {
                str = sc.nextLine();
            }
        }
        System.out.println("Bye! Hope not to see you again :)");
        sc.close();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void errorHandling(String str) throws MissingDescriptionException, DukeException {
        String[] strArr = str.split(" ");
        String firstWord = strArr[0];
        if (firstWord.equals("deadline") || firstWord.equals("event") || firstWord.equals("todo")
                || firstWord.equals("list") || firstWord.equals("delete") || firstWord.equals("unmark")
                || firstWord.equals("mark")){
            if ((firstWord.equals("deadline") || firstWord.equals("event") || firstWord.equals("todo")) && strArr.length == 1) {
                throw new MissingDescriptionException();
            }
            if (firstWord.equals("unmark") || firstWord.equals("mark") || firstWord.equals("delete")){
                if (strArr.length == 1) {
                    throw new DukeException();
                }
                else {
                    try {
                        int index = Integer.parseInt(strArr[1]);
                    }
                    catch (NumberFormatException e){
                        throw new DukeException();
                    }
                }
            }
        }
        else{
            throw new DukeException();
        }
    }
}
