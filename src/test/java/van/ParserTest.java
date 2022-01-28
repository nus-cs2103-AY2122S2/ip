package van;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
  @Test
  public void deadLineTester() {
    Command deadLine = Parser.parseCommand("deadline eat /by 22-10-2022 18:00");

    assertEquals("[D][ ] eat (by:Oct 22 2022 1800)"
      , deadLine.toString());
  }

  @Test
  public void eventTester() {
    Command eventTest = Parser.parseCommand("event sleep /at 12-01-2022 06:00");
    assertEquals("[E][ ] sleep (at:Jan 12 2022 0600)",
      eventTest.toString());
  }

  @Test
  public void toDoTester() {
    Command toDoTest = Parser.parseCommand("todo study");
    assertEquals("[T][ ] study", toDoTest.toString());
  }

}
