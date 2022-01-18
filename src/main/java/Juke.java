import java.util.Scanner;

public class Juke {
    private static final Juke INSTANCE = new Juke();
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        INSTANCE.run(input);
        
        input.close();
    }
    
    private void run(Scanner input) {
        this.greet();
        while(true) {
            this.printMarker();
            String cmd = input.nextLine();
            
            switch(cmd) {
                case "bye":
                    this.bye();
                    break;
                default:
                    this.echo(cmd);
            }
        }
    }
    
    private void greet() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    
    private void bye() {
        this.formattedPrint("See you again!");
        System.exit(0);
    }
    
    private void echo(String text) {
        this.formattedPrint(text);
    }
    
    private void formattedPrint(String text) {
        System.out.println(text + "\n");
    }
    
    private void printMarker() {
        System.out.print("> ");
    }
}
