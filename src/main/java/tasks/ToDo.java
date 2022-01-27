package tasks;

/**
 * Class for todo type tasks
 */
public class ToDo extends Task {

	/**
	 * Constructor for ToDo task
	 * @param detail details of task to be added
	 */
	public ToDo(String detail) {
		super(detail);
	}

	/**
	 * Returns the due date of the task
	 * @return Date if applicable
	 */
	@Override
	public String getDate(){
		return "No Date";
	}

	/**
	 * Getter function for a string representation for the type of task
	 * @return String representing the type of task
	 */
	@Override
	public String getType(){
		return "T";
	}

	/**
	 * Function to return string representation of Task with mark status date and detail
	 * @return String representation of Task
	 */
	@Override
	public String toString() {
		if (marked) {
			return "[T][X] " + detail;
		} else {
			return "[T][ ] " + detail;
		}
	}
}
