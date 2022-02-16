import java.time.LocalDate;

public class Parser {
    /**
     *
     * @param fullCommand
     * @return
     * @throws DukeException
     */
    /** Interprets the input and determines the respective command*/
    public static Command parseCommand(String fullCommand) throws DukeException {
        assert fullCommand.isEmpty() == false : "Command cannot be empty";
        String[] initial = fullCommand.split(" ", 2);
        switch (initial[0]) {
            case ("bye"):
                System.out.println("Bye. Hope to see you again soon!\n");
            case ("list"):
                return new ListCommand();
            case ("todo"):
                return new AddCommand(new Todo(initial[1]));
            case ("event"): {
                String[] second = initial[1].split("/at", 2);
                return new AddCommand(new Event(second[0], second[1]));
            }
            case ("deadline"): {
                String[] second = initial[1].split("/by", 2);
                return new AddCommand(new Deadline(second[0], (second[1])));
            }
            case ("doAfterTask"): {
                String[] second = initial[1].split("/after", 2);
                return new AddCommand(new DoAfterTask(second[0], (second[1])));
            }
            case ("markdone"):
                return new DoneCommand(Integer.parseInt(initial[1]));
            case ("delete"):
                return new DeleteCommand(Integer.parseInt(initial[1]));
            case ("find"):

                return new FindCommand(first[1].split(" "));

            default:
                throw new DukeException("Unknown command received.");
        }
    }
}