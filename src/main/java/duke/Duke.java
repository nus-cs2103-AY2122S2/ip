package main.java.duke;

import main.java.duke.dukeexceptions.DukeException;

public class Duke {
  public static void main(String[] args) {
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    try {
      ChatBot cb = new ChatBot();
      cb.run();
    } catch (DukeException e) {
      e.callback();
    }

  }
}
