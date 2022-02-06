//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import org.junit.jupiter.api.Test;
//
//import duke.command.Parser;
//import duke.error.DukeException;
//import duke.task.DeadlineTask;
//import duke.task.EventTask;
//import duke.task.TaskList;
//import duke.ui.Ui;
//
//
//
//public class DukeParserTest {
//    @Test
//    public void createTaskCommand() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
//        Parser parser = new Parser();
//        Ui p = new Ui();
//        TaskList tl = new TaskList();
//        parser.run("todo 111", p, tl);
//        assertEquals("111", tl.getLast().getTaskName());
//        parser.run("deadline 222 /by 11/11/1998", p, tl);
//        assertEquals("222", tl.getLast().getTaskName());
//        assertEquals(LocalDate.parse("11/11/1998", formatter), ((DeadlineTask) tl.getLast()).getDueDate());
//        parser.run("event 333 /at 11/11/1998", p, tl);
//        assertEquals("333", tl.getLast().getTaskName());
//        assertEquals(LocalDate.parse("11/11/1998", formatter), ((EventTask) tl.getLast()).getDate());
//    }
//
//    @Test
//    public void markTaskCommand() {
//        Parser parser = new Parser();
//        Ui p = new Ui();
//        TaskList tl = new TaskList();
//        parser.run("todo 111", p, tl);
//        parser.run("todo 222", p, tl);
//        parser.run("todo 333", p, tl);
//        parser.run("mark 1", p, tl);
//        assertEquals(true, tl.getTask(0).isDone());
//        parser.run("mark 1", p, tl);
//        assertEquals(false, tl.getTask(1).isDone());
//    }
//
//    @Test
//    public void deleteTaskCommand() {
//        Parser parser = new Parser();
//        Ui p = new Ui();
//        TaskList tl = new TaskList();
//        parser.run("todo 111", p, tl);
//        parser.run("todo 222", p, tl);
//        parser.run("todo 333", p, tl);
//        parser.run("delete 1", p, tl);
//        assertNotEquals("111", tl.getTask(0).getTaskName());
//        assertEquals(2, tl.getSize());
//    }
//
//    @Test
//    public void validateCommand() {
//        TaskList tl = new TaskList();
//
//        //LIST
//        assertEquals(-1, checkErrorMessage("list /on", "Provide the date in the format dd-mm-yyyy!", tl));
//        assertEquals(-1, checkErrorMessage("list /after", "Provide the date in the format dd-mm-yyyy!", tl));
//        assertEquals(-1, checkErrorMessage("list /before", "Provide the date in the format dd-mm-yyyy!", tl));
//        assertEquals(-1, checkErrorMessage("list /on adsf54fasd54",
//                "Provide the date in the format dd-mm-yyyy!", tl));
//        assertEquals(-1, checkErrorMessage("list /before adsf54fasd54",
//                "Provide the date in the format dd-mm-yyyy!", tl));
//        assertEquals(-1, checkErrorMessage("list /after adsf54fasd54",
//                "Provide the date in the format dd-mm-yyyy!", tl));
//
//        //MARK, UNMARK, DELETE
//        assertEquals(-1, checkErrorMessage("mark", "Task ID must be provided!", tl));
//        assertEquals(-1, checkErrorMessage("unmark", "Task ID must be provided!", tl));
//        assertEquals(-1, checkErrorMessage("delete", "Task ID must be provided!", tl));
//        assertEquals(-1, checkErrorMessage("mark ASDF", "Task ID must be an integer!", tl));
//        assertEquals(-1, checkErrorMessage("unmark ASDF", "Task ID must be an integer!", tl));
//        assertEquals(-1, checkErrorMessage("delete ASDF", "Task ID must be an integer!", tl));
//        assertEquals(-1, checkErrorMessage("mark 1", "Task List is empty!", tl));
//        assertEquals(-1, checkErrorMessage("unmark 1", "Task List is empty!", tl));
//        assertEquals(-1, checkErrorMessage("delete 1", "Task List is empty!", tl));
//        tl.addTask("111", false, null, 0);
//        assertEquals(-1, checkErrorMessage("mark 2", "Task ID out of range!", tl));
//        assertEquals(-1, checkErrorMessage("unmark 2", "Task ID out of range!", tl));
//        assertEquals(-1, checkErrorMessage("delete 2", "Task ID out of range!", tl));
//
//        //TASKS
//        assertEquals(-1, checkErrorMessage("todo", "Task Name must be provided!", tl));
//        assertEquals(-1, checkErrorMessage("event", "Task Name must be provided!", tl));
//        assertEquals(-1, checkErrorMessage("deadline", "Task Name must be provided!", tl));
//        assertEquals(-1, checkErrorMessage("deadline 111",
//                "/by flag not detected. Please specify date using /by!", tl));
//        assertEquals(-1, checkErrorMessage("event 111", "/at flag not detected. Please specify date using /at!", tl));
//        assertEquals(-1, checkErrorMessage("deadline 111 /by", "Please specify deadline date after /by!", tl));
//        assertEquals(-1, checkErrorMessage("event 111 /at", "Please specify deadline date after /at!", tl));
//        assertEquals(-1, checkErrorMessage("deadline 111 /by aaa", "Provide the date in the format dd-mm-yyyy!", tl));
//        assertEquals(-1, checkErrorMessage("event 111 /at aaa", "Provide the date in the format dd-mm-yyyy!", tl));
//        assertEquals(-1, checkErrorMessage("deadline /by 11/11/1998", "Task Name must be provided!", tl));
//        assertEquals(-1, checkErrorMessage("event /at 11/11/1998", "Task Name must be provided!", tl));
//
//
//    }
//
//    public int checkErrorMessage(String message, String expectedMessage, TaskList tl) {
//        Parser parser = new Parser();
//        Ui p = new Ui();
//        String[] args = message.split("\\s+");
//        String action = args[0];
//        try {
//            parser.validate(message, action, args, tl);
//        } catch (DukeException de) {
//            assertEquals(expectedMessage, de.getMessage());
//            return -1;
//        }
//        return 0;
//    }
//}
