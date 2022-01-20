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
                    cmd.bye();
                    break label;
                case "list":
                    cmd.list();
                    break;
                case "mark": {
                    try {
                        int index = Integer.parseInt(tokens[1]);
                        if (index <= 0) {
                            throw new DukeException("index is negative or zero");
                        }
                        cmd.mark(index - 1);
                    } catch (NullPointerException ex) {
                        System.out.println("This entry does not exist, please provide a valid entry");
                    } catch (DukeException ex) {
                        System.out.println("Please provide a non-zero, positive entry value!");
                    }
                    finally {
                        break;
                    }
                }
                case "unmark": {
                    try {
                        int index = Integer.parseInt(tokens[1]);
                        if (index <= 0) {
                            throw new DukeException("index is negative or zero");
                        }
                        cmd.unmark(index - 1);
                    } catch (NullPointerException ex) {
                        System.out.println("This entry does not exist, please provide a valid entry!");
                    } catch (DukeException ex) {
                        System.out.println("Please provide a non-zero, positive entry value!");
                    }
                    finally {
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
