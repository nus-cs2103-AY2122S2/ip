import java.util.Scanner; // Imported Scanner class
import static java.lang.System.exit; // Imported System.exit

public class Duke {
    public static void main(String[] args) {
        String logo = "_______________________________________________________\n"
                + " ____        _         _    ____ _   _ \n"
                + "|  _ \\ _   _| | _____ | | /  ___| | | |\n"
                + "| | | | | | | |/ / _ \\| | | |   | |_| |\n"
                + "| |_| | |_| |   <  __/| |_| |___|  _  |\n"
                + "|____/ \\__,_|_|\\_\\___||___|\\____|_| |_|\n"
                + "Hello! I'm DukeLCH\n"
                + "How can I be of service?\n" //Simple Greet
                + "_______________________________________________________\n";
        System.out.println(logo);
        Commands cmd = new Commands();
        Scanner io = new Scanner(System.in); // Scanner object created

        label:
        while(true) {
            String input = io.nextLine();
            String[] tokens = input.split("\\s");
            String keyword = tokens[0];
            switch (keyword) {
                case "bye":
                    try {
                        if (tokens.length != 1) {
                            throw new DukeException("argument for bye detected");
                        }
                        cmd.bye();
                        break label;
                    } catch (DukeException ex) {
                        String err = "_______________________________________________________\n"
                                + "Arguments detected!\n"
                                + "Are you trying to type 'bye' instead? If so, please try again!\n"
                                + "_______________________________________________________\n";
                        System.out.println(err);
                        break;
                    }
                case "list":
                    try {
                        if (tokens.length != 1) {
                            throw new DukeException("argument for list detected");
                        }
                        cmd.list();
                        break;
                    } catch (DukeException ex) {
                        String err = "_______________________________________________________\n"
                                + "Arguments detected!\n"
                                + "Are you trying to type 'list' instead? If so, please try again!\n"
                                + "_______________________________________________________\n";
                        System.out.println(err);
                        break;
                    }
                case "mark": {
                    try {
                        int index = Integer.parseInt(tokens[1]);
                        cmd.mark(index - 1);
                        break;
                    } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
                        String err = "_______________________________________________________\n"
                                + "Invalid entry detected!\n"
                                + "Please provide a valid entry!\n"
                                + "_______________________________________________________\n";
                        System.out.println(err);
                        break;
                    }
                }
                case "unmark": {
                    try {
                        int index = Integer.parseInt(tokens[1]);
                        cmd.unmark(index - 1);
                        break;
                    } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
                        String err = "_______________________________________________________\n"
                                + "Invalid entry detected!\n"
                                + "Please provide a valid entry!\n"
                                + "_______________________________________________________\n";
                        System.out.println(err);
                        break;
                    }
                }
                case "todo": {
                    cmd.todo(tokens);
                    break;
                }
                case "deadline": {
                    cmd.deadline(tokens);
                    break;
                }
                case "event": {
                    cmd.event(tokens);
                    break;
                }
                default:
                    System.out.println("Keyword not recognised!");
                    break;
            }
        }
    }
}
