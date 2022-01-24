package main.java;

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
  ToDo(String name) {
    super(name);
  }

  ToDo(String name, boolean mark) {
    super(name);
    this.isMarked = mark;
  }

  @Override
  String toStore() {
    return this.type + " | " + this.markStore() + " | " + this.name;
  }

  /**
   * method that returns the string representation of ToDo.
   * 
   * @return a String representation fo ToDo.
   */

  @Override
  String display() {
    return "[" + this.type + "] " + "[" + markDisplay() + "] " + this.name;
  }
}
