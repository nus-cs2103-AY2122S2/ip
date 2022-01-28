import java.lang.Boolean;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> list;

    public static void main(String[] args) {
        System.out.println("Hello from Duke!");
        populateTasksFromFile();

        Scanner sc = new Scanner(System.in);
        list = new ArrayList<String>();
        while(sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] strArr = str.split(" ");
            String cmd = strArr[0];
            if (str.equals("bye")) {
                System.out.println("Bye for now!");
                return;
            }
            else if (str.equals("list")) showList();
            else if (strArr[0].equals("mark")) {
                int taskNum = String.parseInt(strArr[1]);
                list.get(taskNum).setStatus(true);
            }
            else if (cmd.equals("delete")) {
                int taskNum = String.parseInt(strArr[1]);
                list.delete(taskNum);
                System.out.println("I've deleted this task.");
            }
            else if (cmd.equals("unmark")) {
                int taskNum = String.parseInt(strArr[1]);
                list.get(taskNum).setStatus(false);
            }
            else if (strArr[0].equals("deadline")) { {
                String[] strParts = str.split("/by ");
                list.add(new Deadline(str, LocalDate.parse(strParts[1])));
                System.out.println(String.format("added: %s", str));
            }
            else if (strArr[0].equals("event")) { {
                String[] strParts = str.split("/at ");
                list.add(new Event(str, LocalDate.parse(strParts[1])));
                System.out.println(String.format("added: %s", str));
            }
            else if (strArr[0].equals("todo")) { {
                if (strArr.length < 2) throw new DukeException("Todo needs a description");
                list.add(new Todo(str));
                System.out.println(String.format("added: %s", str));
            }
            else throw new DukeException("I don't recognize that command.");
        }
    }

    public showList() {
        for(int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String status = task.getStatus() ? "X" : " ";
            System.out.println(String.format("%d. %s", i, task.toString()));
        }
    }

    public void populateTasksFromFile() {
        try {
            File file = new File("duke.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskString = fileScanner.nextLine();
                String[] data = taskString.split(",");
                String type = data[0];
                Boolean status = Boolean.parseBoolean(data[1]);
                String text = data[2];
                if (data[0].equals("T")) {
                    list.add(new Todo(data[2], data[1]))
                }
                else if (data[0].equals("D")) {
                    String date = data[3];
                    list.add(new Deadline(text, status, date));
                }
                else if (data[0].equals("E")) {
                    String date = data[3];
                    list.add(new Deadline(text, status, date));
                }
            }
            fileScanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Something went wrong");
        }
    }

}
