package tasks;

/** A class that functions as an abstraction of a borrowing or lending. */
public class MoneyChange extends Task {

    public static final String WRONG_LEND_FORMAT_ERROR_STRING =
            "Format for lending: 'lend [some amount] /to [name]'";
    public static final String WRONG_BORROW_FORMAT_ERROR_STRING =
            "Format for borrowing: 'borrow [some amount] /from [name]'";
    public static final String INVALID_MONEY_AMOUNT =
            "Remember to type a number for lending or borrowing!";

    private final Task.TaskType taskType = Task.TaskType.MONEYCHANGE;
    private final float changeAmount;
    private final String personName;

    /**
     * Constructor method for the Todo task.
     *
     * @param changeAmount Change in user's amount of money.
     * @param personName Name of other party of transaction.
     */
    public MoneyChange(float changeAmount, String personName) {
        this.changeAmount = changeAmount;
        this.personName = personName;
    }

    /**
     * The string representation of the task to be displayed to the user.
     *
     * @return A string representation of the task for the user.
     */
    @Override
    public String toString() {
        return String.format("[M][%s] %s",
                this.isDone() ? "X" : " ",
                this.changeAmount < 0
                        ? String.format("Lend %s $%.2f", this.personName, -this.changeAmount)
                        : String.format("Borrow $%.2f from %s", this.changeAmount, this.personName));
    }


    /**
     * Returns the description of the task.
     * @return The description of the todo.
     */
    public String getDescription() {
        return this.changeAmount + " " + this.personName;
    }

    /**
     * Converts the task into a string representation to be used to be saved on disk.
     *
     * @return The task in string format to be used to be saved on disk.
     */
    public String exportToString() {
        return String.format("%s %.2f %s %s",
                this.taskType, this.changeAmount, this.isDone(), this.personName);
    }
}
