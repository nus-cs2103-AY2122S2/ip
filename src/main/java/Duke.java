import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> list = new ArrayList<>();
    private String lineBreak = "-------------------------------\n";
    private String welcomeMessage =  lineBreak
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + lineBreak;
    private String goodbyeMessage = "Bye. Hope to see you again soon!";
    public boolean isListening = true;
    private final String filePath = "data/data.bin";

    public Duke(){
        File f = new File(filePath);
        try {
            if (f.exists()) {
                FileInputStream fis = new FileInputStream(filePath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.list = (ArrayList<Task>) ois.readObject();
                ois.close();
            } else {
                f.getParentFile().mkdir();
                f.createNewFile();
            }
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    private void saveToFile(){
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void ask(String input){
        // Format input to remove leading and trailing spaces
        input = input.trim();

        // Ignore empty input
        if(input.equals("")){
           return;
        }

        System.out.println(lineBreak);

        // Exits the program
        if (input.equals("bye")){
            goodbye();
            return;
        }

        // List out the current list
        if(input.equals("list")){
            list();
            return;
        }

        // Check for keywords
        String[] strArr = input.split(" ",2);
        String[] result;
        String keyword = strArr[0];
        Task task = null;

        try {
            switch (keyword) {
                case "mark":
                    System.out.println(mark(strArr[1]));
                    break;
                case "unmark":
                    System.out.println(unmark(strArr[1]));
                    break;
                case "todo":
                    task = new Todo((strArr[1]));
                    break;
                case "deadline":
                    result = strArr[1].split("/by", 2);
                    task = new Deadline(result[0], result[1]);
                    break;
                case "event":
                    result = strArr[1].split("/at", 2);
                    task = new Event(result[0], result[1]);
                    break;
                case "delete":
                    System.out.println(delete(strArr[1]));
                    break;
                default:
                    // Unknown keyword
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not enough fields, please check your inputs and try again.");
        }

        if(task != null){
            list.add(task);
            saveToFile();
            System.out.println(taskAddedMessage(task));
        }

        System.out.println(lineBreak);
    }

    public void introduce(){
        System.out.println(welcomeMessage);
    }

    public void goodbye(){
        this.isListening = false;
        System.out.println(goodbyeMessage);
        System.out.println(lineBreak);
    }

    public void list(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++){
            System.out.println(String.valueOf(i + 1) + ". " + list.get(i));
        }
        System.out.println(lineBreak);
    }

    private String mark(String input){
        int index = parseInt(input) - 1;
        // Check out of bounds
        if(index < 0 || index >= list.size()) {
            return "This is an invalid index, please try again!";
        }
        Task task = list.get(index);
        task.mark();
        saveToFile();
        return "Nice! I've marked this task as done:\n" + task;
    }

    private String unmark(String input){
        int index = parseInt(input) - 1;
        // Check out of bounds
        if(index < 0 || index >= list.size()) {
            return "This is an invalid index, please try again!";
        }
        Task task = list.get(index);
        task.unmark();
        saveToFile();
        return "OK! I've marked this task as not done yet:\n" + task;
    }

    private String taskAddedMessage(Task task){
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + list.size() + " tasks in the list.";
    }

    private String delete(String input){
        int index = parseInt(input) - 1;
        // Check out of bounds
        if(index < 0 || index >= list.size()) {
            return "This is an invalid index, please try again!";
        }
        Task task = list.remove(index);
        saveToFile();
        return  "OK! I've removed this task:\n" + task
                    + "\nNow you have " + list.size() + " tasks in the list.";
    }

    private int parseInt(String s) {
        int index;
        try {
            index = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            index = -1;
        }
        return index;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke chatbot = new Duke();

        chatbot.introduce();
        while(chatbot.isListening){
            String input = sc.nextLine();
            chatbot.ask(input);
        }
        sc.close();
    }
}
