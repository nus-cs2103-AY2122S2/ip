import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Control {
    private ArrayList<Task> tasks;

    public Control() {
        String start =
                "________________________________\n"
                        + "Hello! I am Duke_two.\n"
                        + "Your Personal Assistant.\n"
                        + "What can I do for you?\n"
                        + "________________________________";
        this.tasks = new ArrayList<>();
        System.out.println(start);
    }

//    public void add(String taskStr) {
//        Task task = new Task(taskStr, false);
//        this.tasks.add(task);
//        System.out.println("________________________________");
//        System.out.println("From Duke_two: \n\tAdded to your tasks: " + task.toString());
//    }

    public void bye() {
        String bye = "GoodBye! I hope to see you again!";
        System.out.println(bye);
    }

    public void list() {
        int leng = tasks.toArray().length;
        if (leng == 0) {
            System.out.println("There are no pending tasks!");
        } else {
            System.out.println("");
            for (int i = 0; i < leng; i++) {
                Task task = tasks.get(i);
                int num = i + 1;
                System.out.println(num + ": " + task.toString());
            }
        }
    }

    public void taskCheck(String taskStr) {
        try {
            String[] taskArr = taskStr.split(" ");
            int index = Integer.parseInt(taskArr[1]) - 1;
            Task task = this.tasks.get(index);
            String isTaskCheck = "";
            if (taskArr[0].equals("mark")) {
                task.setChecked(true);
                System.out.println("Nice! I've marked this task as done: \n\t" + task.toString());
            } else {
                this.tasks.get(index).setChecked(false);
                System.out.println("Alright, I've marked this task as not done yet: \n\t" + task.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("An invalid task index has been inputted");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That index number is out of range! Please try again!");
        }
    }

    public void todo(String taskStr) {
        // eg to_do borrow book (without the _)
        String[] taskArr = taskStr.split(" ", 2);
        String taskName = taskArr[1];
        ToDo task = new ToDo(taskName, false, "T");
        this.tasks.add(task);
        System.out.println("Added to your tasks: \n\t" + task.toString());
        System.out.println("You now have " + tasks.toArray().length + " tasks in your list");
    }

    public void deadline(String taskStr) {
        try {
            // deadline return book /by Sunday
            String[] taskArr = taskStr.split(" ", 2);
            String[] taskDetails = taskArr[1].split("/by ");
            String taskName = taskDetails[0];
            String date = taskDetails[1];
            Deadline task = new Deadline(taskName, false, "D", date);
            this.tasks.add(task);
            System.out.println("Added to your tasks: \n\t" + task.toString());
            System.out.println("You now have " + tasks.toArray().length + " tasks in your list");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.");
        }

    }

    public void event(String taskStr) {
        try {
            String[] taskArr = taskStr.split(" ", 2);
            String[] taskDetails = taskArr[1].split("/at ");
            String taskName = taskDetails[0];
            String date = taskDetails[1];
            Event task = new Event(taskName, false, "E", date);
            this.tasks.add(task);
            System.out.println("Added to your tasks: \n\t" + task.toString());
            System.out.println("You now have " + tasks.toArray().length + " tasks in your list");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.");
        }

    }

    public void delete(String taskStr) {
        try {
            String[] taskArr = taskStr.split(" ", 2);
            String deleteIndStr = taskArr[1];
            int deleteInd = Integer.parseInt(deleteIndStr) - 1;
            Task task = this.tasks.get(deleteInd);
            System.out.println("Removed from your tasks: \n\t" + task.toString());
            int num = tasks.toArray().length - 1;
            System.out.println("You now have " + num + " tasks in your list");
            this.tasks.remove(deleteInd);
        } catch (NumberFormatException e) {
            System.out.println("An invalid task index has been inputted! PLease try again!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That index number is out of range! Please try again!");
        }
    }

    public void save() {

        writeTasksToFile();
        System.out.println("Your Tasks has been saved into your device!");
    }

    public void load(String fileName) {
        String command = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while((command = reader.readLine()) != null){
                String[] commandArr = command.split("]");
                String cmdTemp = commandArr[0];
                String firstWord = cmdTemp.substring(cmdTemp.length() - 1);
                String taskName = command.substring(10);
                String isMarked = command.substring(7, 8);
                boolean isMarkedBool = isMarked.equals("X");

                if (firstWord.equals("T")) {
                    ToDo task = new ToDo(taskName, isMarkedBool, "T");
                    this.tasks.add(task);

                } else if (firstWord.equals("D")) {
                    String[] detailsArr = taskName.split(" \\(by: ");
                    String detail = detailsArr[1].substring(0, detailsArr[1].length() - 1);
                    Deadline task = new Deadline(detailsArr[0], isMarkedBool, "D", detail);
                    this.tasks.add(task);

                } else if (firstWord.equals("E")) {
                    String[] detailsArr = taskName.split(" \\(at: ");
                    String detail = detailsArr[1].substring(0, detailsArr[1].length() - 1);
                    Event task  = new Event(detailsArr[0], isMarkedBool, "E", detail);
                    this.tasks.add(task);
                }
            }
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTasksToFile() {
        WriteFile writeFile = new WriteFile();
        writeFile.clearFile();
        int leng = tasks.toArray().length;
        for (int i = 0; i < leng; i++) {
            Task task = tasks.get(i);
            int num = i + 1;
            writeFile.writeToFile(num + ": " + task.toString() + System.lineSeparator());
        }
    }
}