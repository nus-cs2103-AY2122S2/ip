package main.java.duke.task;

/**
 * A Type of task.
 */
public class ToDo extends Task {

    private static String TYPE = "T";
    
  /**
   * Constructs the ToDo task with the given name.
   * 
   * @param name given name of the task.
   */
  public ToDo(String name) {
    super(name);
  }

  public ToDo(String name, boolean mark) {
    super(name);
    this.isMarked = mark;
  }

  @Override
  public String toStore() {
    return TYPE + " | " + this.markStore() + " | " + this.name;
  }

  /**
   * method that returns the string representation of ToDo.
   * 
   * @return a String representation fo ToDo.
   */

  @Override
  public String display() {
    return "[" + TYPE + "] " + "[" + markDisplay() + "] " + this.name;
  }
}
