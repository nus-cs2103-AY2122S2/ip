import java.util.Scanner;

public class Duke {

    public void print(String str){
        System.out.println("\t" + str);
        System.out.println("_".repeat(100));
    }

    public void printBye(){
        this.print("Bye. Hope I've been helpful todayyy, and see you again!");
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Duke duke = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        duke.print("Hello, My Dear Friend... I'm Duke!");
        Quote quoteOfTheDay = new Quote();
        duke.print(quoteOfTheDay.generateQuote());
        duke.print("What can i do for you today?");

        while (true){
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("bye")){
                duke.printBye();
                break;
            } else {
                duke.print(userInput);
            }
        }
    }
}
