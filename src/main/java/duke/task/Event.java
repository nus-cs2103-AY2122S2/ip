package duke.task;

import duke.dukeexceptions.DukeDateExceptions;
import duke.dukeexceptions.DukeException;

import java.time.LocalDateTime;

/**
 * Events is a Type of Task, containing extra information.
 */
public class Event extends Task {

  private static String TYPE = "E";
  private String info;
  private LocalDateTime dateInfo;
  
  /**
   * Constructor of Events
   * 
   * @param name name of the event.
   * @param dateInfo extra dateInfo of the event.
   */
  public Event(String name, String dateInfo) throws DukeException {
    super(name);
    this.info = dateInfo;
    try {
      this.dateInfo = LocalDateTime.parse(dateInfo, this.dateTimeFormatIn);
    } catch (Exception e) {
      throw new DukeDateExceptions("");
    }
  }

  public Event(String name, boolean marked, String dateInfo) {
    super(name);
    this.dateInfo = this.dateInfo = LocalDateTime.parse(dateInfo, this.dateTimeFormatIn);;
    this.isMarked = marked;
  }

  @Override
  public String toStore() {
    return TYPE + " | " + this.markStore() + " | " + this.name + " | " + this.info;
  }

  @Override
  public String display() {
    return "[" + TYPE + "] " + "[" + markDisplay() + "] " + this.name + " (at " + dateInfo.format(this.dateTimeFormatterOut) + ")";
  }

}
