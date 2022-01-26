import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import exceptions.DukeException;
import exceptions.DukeToDoEmptyException;
import exceptions.DukeUnknownCommandException;



public class Duke {

    private ArrayList<Task> dukeList = new ArrayList<Task>();
    Scanner sc = new Scanner(System.in);

    public void startDuke() throws DukeException, IOException {
        loadData();
        displayHelloMessage();
        String command = sc.next();
        String description = sc.nextLine();
        while(!command.equals("Bye")) {
            executeCommand(command, description);
            command = sc.next();
            if(!command.equals("Bye")) {
                description = sc.nextLine();
            }
        }
        displayByeMessage();
    }


    public void displayHelloMessage() {
        display("Hello! I'm Duke\nWhat can I do for you?");
        displayLine();
    }

    public void displayLine() {
        display("--------------------------------------------------");
    }

    public void displayByeMessage() {
        displayLine();
        display("Bye. Hope to see you soon.");
        displayLine();
    }

    public void displayMessage(String command) {
        displayLine();
        display("added:" + command);
        displayLine();
    }

    public void addTaskToList(String command) {
        Task newTask = new Task(command);
        dukeList.add(newTask);
    }

    public void displayList() {
        displayLine();
        display("Here are the tasks in your list:");
        for (int i = 1;i <= dukeList.size(); i++) {
            display( (i) + ". " + dukeList.get(i-1));
        }
        displayLine();
    }

    void deleteTask(int taskIndex) {
        dukeList.remove(taskIndex);
    }

    void executeCommand(String command, String description) throws DukeException, IOException{
        try {
        if (command.equals("list")) {
            displayList();
        } else if (command.equals("mark")) {
            int taskIndex = Integer.parseInt(description.substring(1)) - 1;
            Task toBeCompleted = dukeList.get(taskIndex);
            toBeCompleted.isDone = true;
            toBeCompleted.isComplete();
            displayTaskCompletion(toBeCompleted);
        } else if (command.equals("unmark")) {
            int taskIndex = Integer.parseInt(description.substring(1)) - 1;
            Task toBeCompleted = dukeList.get(taskIndex);
            toBeCompleted.isDone = false;
            //toBeCompleted.isComplete();
            displayIncompleteTask(toBeCompleted);
        } else {
            if (command.equals("event")) {
                displayLine();
                display("Got it. I've added this task:");
                String[] descriptionAndTime = description.split("/"); //gives by 2019-12-09
                String eventDescription = (descriptionAndTime[0].split(" ", 2))[1];//
                LocalDate localDate = LocalDate.parse((descriptionAndTime[1].split(" ",3))[1]);
                // System.out.println(descriptionAndTime[1].split(" ", 3)[1]);
                String eventTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                 Event newEvent = new Event(eventDescription, eventTime);
                 dukeList.add(newEvent);
                 display(newEvent);
            } else if (command.equals("deadline")) {
                displayLine();
                display("Got it. I've added this task:");
                String[] descriptionAndTime = description.split("/"); //gives by 2019-12-09
                String deadlineDescription = (descriptionAndTime[0].split(" ", 2))[1];//
                LocalDate localDate = LocalDate.parse((descriptionAndTime[1].split(" ",3))[1]);
               // System.out.println(descriptionAndTime[1].split(" ", 3)[1]);
                String deadlineTime = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
//                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
              //System.out.println(deadlineTime);
//                LocalDateTime dateTime = (LocalDateTime.parse(((descriptionAndTime[1].split(" ",2))[1]), inputFormat));
                 Deadline newDeadline = new Deadline(deadlineDescription, deadlineTime);

                 dukeList.add(newDeadline);
                 display(newDeadline);
            } else if (command.equals("todo")) {
                if (description.trim().equals("")) {
                    throw new DukeToDoEmptyException();
                }
                displayLine();
                display("Got it. I've added this task:");
                ToDo newTodo = new ToDo(description.substring(1));
                dukeList.add(newTodo);
                display(newTodo);
            } else if (command.equals("delete")){
                int taskIndex = Integer.parseInt(description.substring(1)) - 1;
                     displayLine();
                     display("Noted. I've removed this task: ");
                     Task taskToBeDeleted = dukeList.get(taskIndex);
                     display("  " + taskToBeDeleted);
                     deleteTask(taskIndex);
            }
            else {
                throw new DukeUnknownCommandException();
            }
            display("Now you have " + dukeList.size() + " tasks in the list.");
            displayLine();
        }
            saveData();
} catch ( DukeToDoEmptyException | DukeUnknownCommandException e) {
    displayLine();
    display(e.getMessage());
    displayLine();
}


    }

    void displayIncompleteTask(Task complete){
        displayLine();
        display("OK, I've marked this task as not done yet:");
        display(complete);
        displayLine();
    }

    void displayTaskCompletion(Task incomplete) {
        displayLine();
        display("Nice! I've marked this task as done:");
        display(incomplete);
        displayLine();
    }
    
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke();
        duke.startDuke();
    }

    void display(Object command) {
        System.out.println(command);
    }

    public void saveData() throws IOException {
        try {
            File file = new File("../../../data/duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            boolean createDirectory = file.getParentFile().mkdirs();
            boolean created = file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, false);
            for (Task task : dukeList) {
                String text = "";
                String description = task.description;
                //task.isDone = false;
                if (task instanceof ToDo) {
                    text = "T | " + (task.isDone ? "1" : "0") + " | " + description + '\n';
                } else if (task instanceof Event) {
                    Event event = ((Event) task);
                    text = "E | " + (task.isDone ? "1" : "0") + " | " + event.description.trim() + " | " + event.time + '\n';
                } else {
                    Deadline deadline = ((Deadline) task);
                    text = "D | " + (task.isDone ? "1" : "0") + " | " + deadline.description.trim() + " | " + deadline.time + '\n';
                }
                fileWriter.write(text);
                fileWriter.flush();
            }
        } catch(IOException e) {
                e.printStackTrace();
            }

        }

        public void loadData() throws IOException {
            File file = new File("../../../data/duke.txt");
            if(!file.exists()) {
                file.createNewFile();
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] commandWords = input.split(" \\| ", 4);
                Task task = null;
                switch (commandWords[0]) {
                    case "D" -> {
                        task = new Deadline(commandWords[2], commandWords[3]);
                        display(input);
                    }
                    case "E" -> {
                        task = new Event(commandWords[2], commandWords[3]);
                        display(input);
                    }
                    case "T" -> {
                        task = new ToDo(commandWords[2]);
                        String text = "T | " + (task.isDone ? "1" : "0") + " | " + task.description + '\n';
                        display(input);
                    }
                    default -> System.err.println("error!!");
                }

                if (commandWords[1].equals("1")) {
                    task.isComplete();
                }
                dukeList.add(task);
            }
            sc.close();
        }
    }
