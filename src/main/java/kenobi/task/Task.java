package kenobi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
  protected final String name;
  protected boolean isDone;

  public enum Type {
    DEADLINE,
    EVENT,
    TODO,
  }

  public Task(String name) {
    this.name = name;
    this.isDone = false;
  }

  public Task done() {
    isDone = true;
    return this;
  }

  public Task undone() {
    isDone = false;
    return this;
  }

  public String formatDate(LocalDate date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy");

    return date.format(formatter);
  }

  public abstract Type type();
  public abstract String toSave();

  @Override
  public String toString() {
    String check = this.isDone ? "X" : " ";
    return String.format("[%s] %s", check, this.name);
  }
}
