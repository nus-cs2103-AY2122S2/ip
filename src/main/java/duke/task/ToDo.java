package main.java.duke.task;

/**
 * A type of task.
 */
public class ToDo extends Task {

    String type = "T";
    
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
    return this.type + " | " + this.markStore() + " | " + this.name;
  }

  /**
   * method that returns the string representation of ToDo.
   * 
   * @return a String representation fo ToDo.
   */

  @Override
  public String display() {
    return "[" + this.type + "] " + "[" + markDisplay() + "] " + this.name;
  }
}
