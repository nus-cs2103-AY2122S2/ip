import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TaskManager {

    protected ArrayList<Task> tasks;

    public TaskManager(ArrayList<Task> saved){
        this.tasks = saved;
    }

    public void handleLogic() {

        String userInput = "";
        Scanner sc = new Scanner(System.in);

        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            System.out.println("-------------------");
            StringTokenizer st = new StringTokenizer(userInput, " ");
            String curr = st.nextToken();

            try {
                this.handleCommand(curr, userInput, st);
            } catch (DukeException e){
                System.out.println(e);
            }

            System.out.println("-------------------");
        }

        Storage.saveListToDisk(tasks);
        System.out.println("Bye! Hope to see you again soon");

    }

    private void printList(){
        if(tasks.size() == 0) {
            System.out.println("No Tasks Right Now");
        } else {
            for (int x = 0; x < tasks.size(); x++) {
                System.out.println((x + 1) + ". " + tasks.get(x).toString());
            }
        }
    }

    private void addToDo(String desc) throws DukeException.DukeNoTaskGivenException {

        if (desc.replace(" ", "").equals("")) {
            throw new DukeException.DukeNoTaskGivenException();
        }
        ToDo curr = new ToDo(desc, false);
        this.tasks.add(curr);
        this.printTaskAddition(curr);
    }

    private void addDeadline(String desc, String date) throws DukeException.DukeNoTimeProvided {

        try {
            Deadline curr = new Deadline(desc, false, LocalDate.parse(date));
            this.tasks.add(curr);
            this.printTaskAddition(curr);
        } catch (DateTimeParseException e) {

            throw new DukeException.DukeNoTimeProvided();
        }
    }

    private void addEvent(String desc, String date) throws DukeException.DukeNoTimeProvided {

        try {
            Event curr = new Event(desc, false, LocalDate.parse(date));
            this.tasks.add(curr);
            this.printTaskAddition(curr);
        } catch (DateTimeParseException e) {
            throw new DukeException.DukeNoTimeProvided();
        }
    }

    private void printTaskAddition(Task curr){
        System.out.println("Got it! I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
    }

    private void printTaskDeletion(Task curr){
        System.out.println("Got it! I've removed this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
    }


    private void deleteTask(int index){
        Task toBeRemoved = tasks.get(index - 1);
        this.tasks.remove(index - 1);
        this.printTaskDeletion(toBeRemoved);
    }

    private void handleCommand(String curr, String userInput, StringTokenizer st) throws DukeException {
        switch (curr){

            case "list":
                this.printList();
                break;

            case "mark":

                try {
                    int toMark = Integer.parseInt(st.nextToken());
                    if(toMark < 0 || toMark > tasks.size()) {
                        throw new DukeException.DukeInvalidNumberException();
                    }
                    tasks.get(toMark - 1).markCompleted();
                } catch (DukeException | NumberFormatException e){
                    throw new DukeException.DukeInvalidNumberException();
                }
                break;

            case "unmark":

                try {
                    int toUnmark = Integer.parseInt(st.nextToken());
                    if(toUnmark < 0 || toUnmark > tasks.size()) {
                        throw new DukeException.DukeInvalidNumberException();
                    }
                    tasks.get(toUnmark - 1).markNotCompleted();
                } catch (DukeException | NumberFormatException e) {
                    throw new DukeException.DukeInvalidNumberException();
                }
                break;

            case "todo":

                try {
                    this.addToDo(st.nextToken(""));
                } catch (NoSuchElementException e){
                    throw new DukeException.DukeNoTaskGivenException();
                }

                break;

            case "deadline":

                try {
                    userInput = userInput.replace(curr, "");
                    String[] spl = userInput.split("/by ");
                    if(spl.length <= 1){
                        throw new DukeException.DukeNoTimeProvided();
                    }
                    this.addDeadline(spl[0], spl[1]);
                } catch (DukeException e) {
                    throw e;
                }
                break;

            case "event":

                try {
                    userInput = userInput.replace(curr, "");
                    String[] splo = userInput.split("/at ");
                    if (splo.length <= 1) {
                        throw new DukeException.DukeNoTimeProvided();
                    }
                    this.addEvent(splo[0], splo[1]);
                } catch (DukeException e){
                    throw e;
                }
                break;

            case "delete":

                try {
                    int toDelete = Integer.parseInt(st.nextToken());
                    if(toDelete < 0 || toDelete > tasks.size()){
                        throw new DukeException.DukeInvalidNumberException();
                    }
                    this.deleteTask(toDelete);
                } catch (NumberFormatException | DukeException e){
                    throw new DukeException.DukeInvalidNumberException();
                }
                break;

            case "bye":

                break;

            default:
                throw new DukeException.DukeInvalidCommandException();
        }
    }


}
