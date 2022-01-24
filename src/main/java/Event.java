package main.java;

import java.time.LocalDateTime;

/**
 * Events is a type of Task, containing extra information.
 */
public class Event extends Task {

  String type = "E";
  String info;
  LocalDateTime dateInfo;
  
  /**
   * Constructor of Events
   * 
   * @param name name of the event.
   * @param dateInfo extra dateInfo of the event.
   */
  Event(String name, String dateInfo) {
    super(name);
    this.info = dateInfo;
    this.dateInfo = LocalDateTime.parse(dateInfo, this.dateTimeFormatIn);
  }

  Event(String name, boolean marked, String dateInfo) {
    super(name);
    this.dateInfo = this.dateInfo = LocalDateTime.parse(dateInfo, this.dateTimeFormatIn);;
    this.isMarked = marked;
  }

  @Override
  String toStore() {
    return this.type + " | " + this.markStore() + " | " + this.name + " | " + this.info;
  }

  @Override
  String display() {
    return "[" + this.type + "] " + "[" + markDisplay() + "] " + this.name + " (at " + dateInfo.format(this.dateTimeFormatterOut) + ")";
  }

}
