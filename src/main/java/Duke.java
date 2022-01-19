import java.util.Scanner;

public class Duke {
    static TaskManager manager = new TaskManager();
    private static final String line = "-------------------------------------------------------------------------------------";
    public static void main(String[] args) {

        start();

        Scanner s = new Scanner(System.in);
        String input;

        while (true) {
            input = s.nextLine();

            if (input.equals("bye")) {
                bye();
                return;
            } else if (input.equals("list")) {
                list();
            } else if (input.startsWith("unmark") || input.startsWith("mark")) {
                marking(input);
            } else if (input.startsWith("delete")) {
                delete(input);
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){
                add(input);
            } else {
                add(input);
            }
        }
    }
    public static void add(String input){
        System.out.println(line);
        String response = manager.addTask(input);
        System.out.println(response);
        System.out.println(line);
    }
    public static void delete(String input){
        System.out.println(line);
        try {
            int index = Integer.parseInt(input.replaceFirst("delete", "").strip());
            System.out.println(manager.deleteTask(index));
        } catch (NumberFormatException n){
            System.out.println("Invalid number entered! Please enter an integer");
        }
        System.out.println(line);
    }
    public static void list(){
        System.out.println(manager.getTaskList());
    }
    public static void marking(String input){

        if (input.startsWith("mark")){
            try {
                int index = Integer.parseInt(input.replaceFirst("mark", "").strip());
                System.out.println(manager.markTaskDone(index));
            } catch (NumberFormatException n){
                System.out.println("Invalid number entered! Please enter an integer");
            }
        } else if (input.startsWith("unmark")){
            try {
                int index = Integer.parseInt(input.replaceFirst("unmark", "").strip());
                System.out.println(manager.markTaskUndone(index));
            } catch (NumberFormatException n){
                System.out.println("Invalid number entered! Please enter an integer");
            }
        }


    }
    public static void bye(){
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(bye);
        System.out.println(line);
    }
    public static void start(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String intro = "I am a Personal Assistant Chatbot that helps you to keep track of various things.\n"
                + "Let's get started.\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(intro);
        System.out.println(line);
    }
    public static void invalid(){
        String invalidText =    "Invalid Entry!\n" +
                                "List of available commands:\n\n" +
                                "\"list\": View Current Task List\n" +
                                "\"todo <name>\": Add a new Task\n" +
                                "\"deadline <name> /by <date/time>\": Add a new Deadline\n" +
                                "\"event <name> /at <date/time>\": Add a new Event\n" +
                                "\"delete <number>\": delete the corresponding entry in your task list\n" +
                                "\"mark <number>\": mark the corresponding entry in your task list as done\n" +
                                "\"unmark <number>\": mark the corresponding entry in your task list as not done\n" +
                                "\"bye\": Exit the chatbot application";

        System.out.println(line);
        System.out.println(invalidText);
        System.out.println(line);
    }
}
