package main.java;

import java.util.concurrent.DelayQueue;

/**
 * Deadline is a type of Task that contains addition.
 */

public class Deadline extends Task {
  String type = "D";
  String info;

  /**
   * Constructor for deadline.
   * 
   * @param name name of task.
   * @param info additional info of the task.
   */

  Deadline(String name, String info) {
    super(name);
    this.info = info;
  }

  Deadline(String name, boolean marked, String info) {
    super(name);
    this.info = info;
    this.isMarked = marked;
  }

  @Override
  String toStore() {
    return this.type + " | " + this.markStore() + " | " + this.name + " | " + this.info;
  }

  /**
   * @return String representation of the task.
   */

  @Override
  String display() {
    return "[" + this.type + "] " + "[" + markDisplay() + "] " + this.name + " (by " + info + ")";
  }
}
