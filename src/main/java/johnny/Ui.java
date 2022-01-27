package johnny;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private InputList tasks;
    private Storage store;

    public Ui(InputList tasks, Storage store) {
        this.tasks = tasks;
        this.store = store;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void handleUi() throws InvalidArgumentsException, EmptyDescriptionException, NoDateException, DateTimeException{
        System.out.println("Hello! I'm Johnny.Johnny \n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input;
        while(true) {
            input = sc.nextLine();
            Parser parser = new Parser(input);
            ArrayList<String> parseOutput = parser.parse();
            String commandTag = parseOutput.get(0);

            if(commandTag.equals(Parser.TERMINATE)) {
                tasks.writeToFile(this.store);

                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(commandTag.equals(Parser.PRINT_LIST)) {
                System.out.println("Here are the tasks in your list:");
                tasks.printList();
            }
            else if(commandTag.equals(Parser.MARK)) {
                tasks.mark(Integer.parseInt(parseOutput.get(1)));
            }
            else if(commandTag.equals(Parser.UNMARK)) {
                tasks.unmark(Integer.parseInt(parseOutput.get(1)));
            }
            else if(commandTag.equals(Parser.DELETE)) {
                tasks.delete(Integer.parseInt(parseOutput.get(1)));
            }
            else if(commandTag.equals(Parser.ADD_TODO)) {

                Task newTask = new Todo(parseOutput.get(1), false);
                tasks.add(newTask);

                System.out.println("Got it! I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + tasks.getCount() + " tasks in your list.");
            }
            else if(commandTag.equals(Parser.ADD_DEADLINE)) {

                Task newTask = new Deadline(parseOutput.get(1),
                        LocalDate.parse(parseOutput.get(2)), false);

                tasks.add(newTask);

                System.out.println("Got it! I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + tasks.getCount() + " tasks in your list.");
            }
            else if(commandTag.equals(Parser.ADD_EVENT)) {

                Task newTask = new Event(parseOutput.get(1),
                        LocalDate.parse(parseOutput.get(2)), false);

                tasks.add(newTask);

                System.out.println("Got it! I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + tasks.getCount() + " tasks in your list.");
            }
            else {
                throw new InvalidArgumentsException(input);
            }
        }
    }
}

