package duke;

/**
 * A class responsible for breaking down commands into the command type,
 * and constructing the commands to be executed.
 */
public class Parser {

    /**
     * Parses the user input into Command objects.
     *
     * @param command A string containing the user input.
     * @param ui Ui for reading user input and displaying output.
     * @return Command to be executed.
     */
    public static Command parse(String command, Ui ui) {
        assert !command.equals("") : "command should not be an empty string!";
        command = command.strip().replaceAll(" +", " ");
        String firstWord = command.split(" ")[0];
        String description;
        int taskNum;
        String time;
        try {
            switch (firstWord) {
            case "bye":
                if (!command.equals("bye")) {
                    throw new DukeException("No words should be after 'bye'.");
                }
                return new ExitCommand(firstWord);
            case "list":
                if (!command.equals("list")) {
                    throw new DukeException("No words should be after 'list'.");
                }
                return new ListCommand(firstWord);
            case "delete":
                if (command.equals("delete")) {
                    throw new DukeException("Please type what you want to delete!");
                }
                description = command.substring(firstWord.length() + 1);
                taskNum = Integer.parseInt(description);
                return new DeleteCommand(firstWord, taskNum);
            case "mark":
                if (command.equals("mark")) {
                    throw new DukeException("Please type what you want to mark!");
                }
                description = command.substring(firstWord.length() + 1);
                taskNum = Integer.parseInt(description);
                return new MarkCommand(firstWord, taskNum);
            case "unmark":
                if (command.equals("unmark")) {
                    throw new DukeException("Please type what you want to unmark!");
                }
                description = command.substring(firstWord.length() + 1);
                taskNum = Integer.parseInt(description);
                return new UnmarkCommand(firstWord, taskNum);
            case "deadline":
                description = command.substring(firstWord.length() + 1);
                assert !description.contains("(") || !description.contains(")") :
                        "Please don't use brackets in the deadline name";
                description = description.split(" /by ")[0];
                time = command.split(" /by ")[1];
                return new AddCommand(firstWord, description, time);
            case "todo":
                if (command.equals("todo")) {
                    throw new DukeException("Uh-oh! There is nothing to do here!");
                }
                description = command.substring(firstWord.length() + 1);
                return new AddCommand(firstWord, description);
            case "event":
                description = command.substring(firstWord.length() + 1);
                assert !description.contains("(") || !description.contains(")") :
                        "Please don't use brackets in the event name";
                description = description.split(" /at ")[0];
                time = command.split(" /at ")[1];
                return new AddCommand(firstWord, description, time);
            case "find":
                if (command.equals("find")) {
                    throw new DukeException("Uh-oh! There is nothing to find here!");
                }
                description = command.substring(firstWord.length() + 1);
                return new FindCommand(firstWord, description);
            case "addplace":
                if (command.equals("addplace")) {
                    throw new DukeException("Uh-oh! There is nothing to add here!");
                }
                description = command.substring(firstWord.length() + 1);
                assert !description.contains("[") || !description.contains("]") :
                        "Please don't use brackets in the place name";
                String name = description.split(" /desc ")[0];
                description = command.split(" /desc ")[1];
                return new AddPlaceCommand(firstWord, name, description);
            case "delplace":
                if (command.equals("delplace")) {
                    throw new DukeException("Please type what you want to delete!");
                }
                description = command.substring(firstWord.length() + 1);
                taskNum = Integer.parseInt(description);
                return new DeletePlaceCommand(firstWord, taskNum);
            case "viewplaces":
                if (!command.equals("viewplaces")) {
                    throw new DukeException("No words should be after 'viewplaces'.");
                }
                return new ViewPlacesCommand(firstWord);
            case "view":
                if (command.equals("view")) {
                    throw new DukeException("Please type what you want to view!");
                }
                description = command.substring(firstWord.length() + 1);
                int placeNum = Integer.parseInt(description);
                return new ViewCommand(firstWord, placeNum);
            default:
                return new InvalidCommand("Please try again!");
            }
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        } catch (NumberFormatException e) {
            return new InvalidCommand("Please input an integer!");
        } catch (ArrayIndexOutOfBoundsException e) {
            return new InvalidCommand("Eh? Did you mistype the format?");
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand("Uh-oh! There's no description here!");
        }
    }
}
