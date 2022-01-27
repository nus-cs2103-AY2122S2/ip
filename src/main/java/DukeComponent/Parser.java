package DukeComponent;

import exceptions.*;

public class Parser {
    String userInput;

    public Parser(String userInput) throws TaskException {
        checkUserInput(userInput);
        this.userInput = userInput;
    }

    public void executeCommand(TaskList tasks) {
        Command c = new Command(userInput, tasks);
        c.execute();
    }

    private void checkUserInput(String userInput) throws TaskException {
        String[] splitInput = userInput.split(" ");
        if (splitInput.length == 0 || notKeyWord(splitInput[0])) {
            throw new IncorrectInputException();

        } else if (splitInput.length == 1) {
            String command = splitInput[0];
            switch (command) {
            case "todo":
                throw new ToDosException();
            case "deadline":
                throw new DeadlineException();
            case "event":
                throw new EventException();
            }
        } else {
            String command = splitInput[0];
            if (command.equals("mark") || command.equals("unmark")) {
                try {
                    Integer.parseInt(splitInput[1]);
                } catch (NumberFormatException e) {
                    throw new WrongInputException();
                }
            }
        }
    }

    private static boolean notKeyWord(String command) {
        return (!command.equals("bye") && !command.equals("list")
                && !command.equals("delete")  && !command.equals("mark") && !command.equals("unmark")
                && !command.equals("todo") && !command.equals("deadline") && !command.equals("event") &&
                !command.equals("find"));
    }


}
