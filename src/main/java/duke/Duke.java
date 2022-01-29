package duke;

/**
 * Main class
 */
public class Duke {

    /**
     * Runs the Duke Program
     *
     * @param args No Args needed
     */
    public static void main(String[] args) {
        Ui.printWelcome();

        TaskList list = DataStore.loadData();

        Command actionType = Ui.getCommand();
        String[] parsedInput = Ui.getInputs();

        while (actionType != Command.BYE) {
            int indexOfList = -1;
            Boolean wasAddSuccess = null;


            switch (actionType) {
            case LIST:
                list.print();
                break;
            case MARK:
                indexOfList = Integer.parseInt(parsedInput[0]);
                list.markComplete(indexOfList);
                System.out.println("Marked as complete");
                list.print(indexOfList);
                break;
            case UNMARK:
                indexOfList = Integer.parseInt(parsedInput[0]);
                list.markIncomplete(indexOfList);
                System.out.println("Marked as incomplete");
                list.print(indexOfList);
                break;
            case EVENT:
                try {
                    wasAddSuccess = list.add(TaskType.EVENT, parsedInput);
                } catch (EmptyDescriptionException e) {
                    System.out.println("Description of task can't be empty");
                    wasAddSuccess = false;
                }
                if (wasAddSuccess) {
                    System.out.println("added: " + parsedInput[0].strip());
                } else {
                    System.out.println("Failed to add " + parsedInput[0].strip());
                }
                System.out.print("Number of tasks: ");
                System.out.println(list.getLength());
                break;
            case DEADLINE:
                try {
                    wasAddSuccess = list.add(TaskType.DEADLINE, parsedInput);
                } catch (EmptyDescriptionException e) {
                    System.out.println("Description of task can't be empty");
                    wasAddSuccess = false;
                }
                if (wasAddSuccess) {
                    System.out.println("added: " + parsedInput[0].strip());
                } else {
                    System.out.println("Failed to add " + parsedInput[0].strip());
                }
                System.out.print("Number of tasks: ");
                System.out.println(list.getLength());
                break;
            case TODO:
                try {
                    wasAddSuccess = list.add(TaskType.TODO, parsedInput);
                } catch (EmptyDescriptionException e) {
                    System.out.println("Description of task can't be empty");
                    wasAddSuccess = false;
                }
                if (wasAddSuccess) {
                    System.out.println("added: " + parsedInput[0].strip());
                } else {
                    System.out.println("Failed to add " + parsedInput[0].strip());
                }
                System.out.print("Number of tasks: ");
                System.out.println(list.getLength());
                break;
            case DELETE:
                indexOfList = Integer.parseInt(parsedInput[0]);
                System.out.println("Noted. I've removed this task:");
                list.print(indexOfList);
                list.delete(indexOfList);
                System.out.println("Now you have " + Integer.toString(list.getLength()) + " tasks in the list.");
                break;
            case FIND:
                list.findTask(parsedInput[0]);
                break;
            default:
                break;
            }

            DataStore.saveData(list);

            actionType = Ui.getCommand();
            parsedInput = Ui.getInputs();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }

}
