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
        try {
            switch (tokens[0]) {
                case "bye":
                    if(tokens.length != 1) {
                        throw new IncorrectFormatException("Please enter only one word for this command");
                    }
                    System.out.println("So you finally decided to leave. Goodbye! Not that I really care.");
                    end = true;
                    break;
                case "list":
                    if(tokens.length != 1) {
                        throw new IncorrectFormatException("Please enter only one word for this command.");
                    }
                    System.out.println("Do I have to remind you again?");
                    for (int i = 0; i < index; i++) {
                        System.out.println((i + 1) + "." + list[i].getStatus());
                    }
                    break;
                case "mark":
                    if(tokens.length != 2) {
                        throw new IncorrectFormatException("Please enter mark followed by a number for this command. Example: mark 8");
                    }
                    int index1 = Integer.parseInt((tokens[1]));
                    if(index1 < 1 || index1 > index) {
                        throw new IncorrectFormatException("Please enter a valid task number.");
                    }
                    System.out.println("You finally did something? I'll mark it for you then.");
                    list[index1 - 1].markDone();
                    break;
                case "unmark":
                    if(tokens.length != 2) {
                        throw new IncorrectFormatException("Please enter unmark followed by a number for this command. Example: unmark 7");
                    }
                    int index2 = Integer.parseInt((tokens[1]));
                    if(index2 < 1 || index2 > index) {
                        throw new IncorrectFormatException("Please enter a valid task number.");
                    }
                    System.out.println("As expected... another task that wasn't finished at all.");
                    list[Integer.parseInt((tokens[1])) - 1].markNotDone();
                    break;
                case "todo":
                    if(tokens.length != 2) {
                        throw new IncorrectFormatException("You want me to remind you of something but you won't tell me of what it is?");
                    }
                    list[index++] = new ToDo(tokens[1]);
                    System.out.println("I have added this task cause you won't remember it.");
                    System.out.println("You now have " + index + " tasks");
                    break;
                case "deadline":
                    if(tokens.length != 2) {
                        throw new IncorrectFormatException("You want me to remind you of something but you won't tell me of what it is? The format is deadline (task) /by (time)");
                    }
                    String[] tokens2 = tokens[1].split("/by", 2);
                    if(tokens2.length != 2 || tokens2[1].equals("")) {
                        throw new IncorrectFormatException("Please tell me the deadline time using /by. Format: deadline (task) /by (time)");
                    }
                    list[index++] = new Deadline(tokens2[0], tokens2[1]);
                    System.out.println("I have added this task cause you won't remember it.");
                    System.out.println("You now have " + index + " tasks");
                    break;
                case "event":
                    if(tokens.length != 2) {
                        throw new IncorrectFormatException("You want me to remind you of something but you won't tell me of what it is? The format is event (task) /at (time)");
                    }
                    String[] tokens3 = tokens[1].split("/at", 2);
                    if(tokens3.length != 2 || tokens3[1].equals("")) {
                        throw new IncorrectFormatException("Please tell me the time of the event using /at. Format: event (task) /at (time)");
                    }
                    list[index++] = new Event(tokens3[0], tokens3[1]);
                    System.out.println("I have added this task cause you won't remember it.");
                    System.out.println("You now have " + index + " tasks");
                    break;
                default:
                    System.out.println("Could you please talk some sense? I can't seem to comprehend what you're saying.");
            }
        }  catch (NumberFormatException e) {
            System.out.println("Are you even trying to specify a task? Please enter in digits if you're wondering.");
        } catch (IncorrectFormatException e) {
            System.out.println(e.getMessage());
        }
        return end;
    }

}
