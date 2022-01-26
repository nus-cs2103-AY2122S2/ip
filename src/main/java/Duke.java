import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.MissingFormatArgumentException; // Imported MissingFormatArgumentException
import java.util.Scanner; // Imported Scanner class

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
        File curr = null;
        try {
            curr = cmd.startup();
            cmd.restore(curr);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        label:
        while (true) {
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
                            + "* Arguments detected *\n"
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
                            + "* Arguments detected *\n"
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
                            + "* Invalid entry detected *\n"
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
                            + "* Invalid entry detected *\n"
                            + "Please provide a valid entry!\n"
                            + "_______________________________________________________\n";
                    System.out.println(err);
                    break;
                }
            }
            case "todo": {
                try {
                    if (tokens.length <= 1) {
                        throw new MissingFormatArgumentException("no argument detected");
                    }
                    cmd.todo(tokens);
                    break;
                } catch (MissingFormatArgumentException ex) {
                    String err = "_______________________________________________________\n"
                            + "* No arguments detected *\n"
                            + "Please provide a description for your ToDo!\n"
                            + "_______________________________________________________\n";
                    System.out.println(err);
                    break;
                }
            }
            case "deadline": {
                try {
                    if (tokens.length <= 1) {
                        throw new MissingFormatArgumentException("no argument detected");
                    }
                    cmd.deadline(tokens);
                    break;
                } catch (MissingFormatArgumentException ex) {
                    String err = "_______________________________________________________\n"
                            + "* No arguments detected *\n"
                            + "Please provide a description for your Deadline!\n"
                            + "_______________________________________________________\n";
                    System.out.println(err);
                    break;
                } catch (DukeException ex) {
                    String err = "_______________________________________________________\n"
                            + "* Time frame not detected *\n"
                            + "Please provide a time frame for your Deadline!\n"
                            + "Check if you have typed '/by' to indicate the time frame!\n"
                            + "e.g. deadline return book /by Sunday\n"
                            + "_______________________________________________________\n";
                    System.out.println(err);
                    break;
                }
            }
            case "event": {
                try {
                    if (tokens.length <= 1) {
                        throw new MissingFormatArgumentException("no argument detected");
                    }
                    cmd.event(tokens);
                    break;
                } catch (MissingFormatArgumentException ex) {
                    String err = "_______________________________________________________\n"
                            + "* No arguments detected *\n"
                            + "Please provide a description for your Event!\n"
                            + "_______________________________________________________\n";
                    System.out.println(err);
                    break;
                } catch (DukeException ex) {
                    String err = "_______________________________________________________\n"
                            + "* Time frame not detected *\n"
                            + "Please provide a time frame for your Event!\n"
                            + "Check if you have typed '/at' to indicate the time frame!\n"
                            + "e.g. event project meeting /at Mon 2-4pm\n"
                            + "_______________________________________________________\n";
                    System.out.println(err);
                    break;
                }
            }
            case "delete": {
                try {
                    int index = Integer.parseInt(tokens[1]);
                    cmd.delete(index - 1);
                    break;
                } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException ex) {
                    String err = "_______________________________________________________\n"
                            + "* Invalid entry detected *\n"
                            + "Please provide a valid entry!\n"
                            + "_______________________________________________________\n";
                    System.out.println(err);
                    break;
                }
            }
            case "update": {
                try {
                    if (curr == null) {
                        throw new FileNotFoundException();
                    }
                    cmd.update(curr);
                    break;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            default:
                try {
                    throw new MissingFormatArgumentException("invalid keywords");
                } catch (MissingFormatArgumentException ex) {
                    String err = "_______________________________________________________\n"
                            + "* Unrecognised keyword used *\n"
                            + "Please try the keywords provided below:\n"
                            + "    1. list\n"
                            + "    2. todo [arg]\n"
                            + "    3. deadline [arg] /by [arg]\n"
                            + "    4. event [arg] /at [arg]\n"
                            + "    5. mark [arg]"
                            + "    6. unmark [arg]"
                            + "    7. delete [arg]"
                            + "    7. bye\n"
                            + "_______________________________________________________\n";
                    System.out.println(err);
                    break;
                }
            }
        }
    }
}
