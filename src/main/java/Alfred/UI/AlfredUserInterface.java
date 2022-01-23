package Alfred.UI;

import Alfred.Exceptions.AlfredException;
import java.util.Scanner;

public class AlfredUserInterface {
  // class constants
  private static final String GREETING = "Hello! My name is Alfred.\n"
      + "How can I be of service?";
  private static final String BYE = "Bye! Hope I was of service.";
  private static final int BREAK_CHAR_LENGTH = 100;
  private static final String BREAK_LINE = AlfredUserInterface.line();

  // instance variables
  Scanner io;

  public AlfredUserInterface() {
    io = new Scanner(System.in);
  }

  private static String line() {
    String out = "";
    for (int i = 0; i < AlfredUserInterface.BREAK_CHAR_LENGTH; i++) {
      out += "-";
    }
    out += "\n";
    return out;
  }

  public void sandwichAndPrint(String text) {
    String out = "";
    out += AlfredUserInterface.BREAK_LINE;
    out += text + "\n";
    out += AlfredUserInterface.BREAK_LINE;
    System.out.println(out);
  }

  public void greeting() {
    this.sandwichAndPrint(AlfredUserInterface.GREETING);
  }

  public void bye() {
    this.sandwichAndPrint(AlfredUserInterface.BYE);
  }

  public void handleAlfredException(AlfredException e) {
    this.sandwichAndPrint(e.getMessage());
  }

  public String readInput() {
    return this.io.nextLine();
  }

}
