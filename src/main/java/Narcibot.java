import java.util.Scanner;

public class Narcibot {
    private static Task[] list = new Task[100];
    private static int index = 0;
    public static void main(String[] args) {
        System.out.println("I'm Narcibot, the best bot ever created.\nOh it's you, what a bother.\nHere's a hello as a formality. What do you want this time?\n");
        String input;
        Scanner sc = new Scanner(System.in);
        while(true) {
            input = sc.nextLine();
            if(command(input)) {
                return;
            }
        }

    }

    private static boolean command(String command) {
        boolean end = false;
        String[] tokens = command.split(" ",2);
        switch(tokens[0]) {
            case "bye":
                System.out.println("So you finally decided to leave. Goodbye! Not that I really care.");
                end = true;
                break;
            case "list":
                System.out.println("Do I have to remind you again?");
                for(int i = 0; i < index; i++) {
                    System.out.println((i+1) + "." + list[i].getStatus());
                }
                break;
            case "mark":
                System.out.println("You finally did something? I'll mark it for you then.");
                list[Integer.parseInt((tokens[1])) - 1].markDone();
                break;
            case "unmark":
                System.out.println("As expected... another task that wasn't finished at all.");
                list[Integer.parseInt((tokens[1])) - 1].markNotDone();
                break;
            case "todo":
                System.out.println("I have added this task cause you won't remember it.");
                list[index++] = new ToDo(tokens[1]);
                System.out.println("You now have "+index+ " tasks");
                break;
            case "deadline":
                System.out.println("I have added this task cause you won't remember it.");
                String[] tokens2 = tokens[1].split("/by",2);
                list[index++] = new Deadline(tokens2[0],tokens2[1]);
                System.out.println("You now have "+index+ " tasks");
                break;
            case "event" :
                System.out.println("I have added this task cause you won't remember it.");
                String[] tokens3 = tokens[1].split("/at",2);
                list[index++] = new Event(tokens3[0],tokens3[1]);
                System.out.println("You now have "+index+ " tasks");
                break;
            default:
                System.out.println("added: " + command);
                list[index++] = new Task(command);
        }
        return end;
    }

}
