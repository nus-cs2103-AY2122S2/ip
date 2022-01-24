package main.java;

/**
 * Events is a type of Task, containing extra information.
 */
public class Events extends Task {

  String type = "E";
  String info;

  /**
   * Constructor of Events
   * 
   * @param name name of the event.
   * @param info extra info of the event.
   */
  Events(String name, String info) {
    super(name);
    this.info = info;
  }

  Events(String name, boolean marked, String info) {
    super(name);
    this.info = info;
    this.isMarked = marked;
  }

  @Override
  String toStore() {
    return this.type + " | " + this.markStore() + " | " + this.name + " | " + this.info;
  }

  @Override
  String display() {
    return "[" + this.type + "] " + "[" + markDisplay() + "] " + this.name + " (at " + info + ")";
  }

}
