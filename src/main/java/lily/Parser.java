package lily;
import lily.task.LilyException;
import java.io.IOException;

/**
 * Makes sense of commands input by the user and executes them.
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Parser {
    TaskList tasks;
    Ui ui;
    Storage st;
    private String sentence;
    private String[] parsedSentence;
    private String command;

    public Parser(TaskList t, Ui ui, Storage st) {
        this.tasks = t;
        this.ui = ui;
        this.st = st;
    }

    // Lily accepts inputs from user
    public void parse() {
        userInteracting: while (true) {
            try {
                sentence = ui.readCommand();
                parsedSentence = sentence.split(" ");
                command = parsedSentence[0];
                switch (command) {
                case "bye":
                    st.save(tasks);
                    break userInteracting;
    
                case "list":
                    ui.showList(tasks);
                    break;
    
                case "mark":
                    int addIdx = Integer.parseInt(parsedSentence[1]); // base 1
                    ui.showMarked(tasks.mark(addIdx - 1), addIdx);
                    /*
                        * if (tasks.isEmpty())
                        * throw new Error("you cant mark something that isn't there");
                        * else if (already marked)
                        * throw new error you've already finished this
                        * if input doesn't have an int, ask which number you want to mark also.
                        */
                    break;
    
                case "unmark":
                    int delIdx = Integer.parseInt(parsedSentence[1]);
                    ui.showUnmarked(tasks.unmark(delIdx - 1), delIdx);
                    /*
                        * if (tasks.isEmpty())
                        * throw new Error("you can't unmark something thaj isn't there");
                        * else if (not marked yet)
                        * throw new error you havent done this
                        * if input doesn't have an int, ask which number you want to mark also.
                        */
                    break;
    
                case "todo":
                    ui.showTaskAdded(tasks.addTodo(findTodoDescStart(sentence)), tasks.size());
                    break;
                case "deadline":
                    /*
                        if user didn't type "/by" (byIdx == -1)
                            throw new Error "you didnt' type /by bro, try again"
                        if user didnt' type a desc
                            throw new error you didnt type a description man, try again
                    */
                    String[] parsedDeadline = findDeadlineDescStart(sentence);
                    ui.showTaskAdded(tasks.addDeadline(parsedDeadline[0], parsedDeadline[1]), tasks.size());
                    break;
                case "event":
                    /*
                        if user didn't type "/at" (atIdx == -1)
                            throw new Error "you didnt' type /at bro, try again"
                        if user didnt' type a desc
                            throew new error you didnt type a description man, try again
                    */
                    String[] parsedEvent = findEventDescStart(sentence);
                    ui.showTaskAdded(tasks.addEvent(parsedEvent[0], parsedEvent[1]), tasks.size());
                    break;
    
                case "delete":
                case "remove":
                    ui.showTaskRemoved(tasks.remove(
                            Integer.parseInt(parsedSentence[1]) - 1), 
                            tasks);
                    break;
    
                default:
                    ui.showInvalidCommand(sentence, tasks);
                }

            } catch (LilyException le) {
                // what throws this exception ah?
                ui.showError("Description cannot be empty!");
            } catch (IOException ioe) {
                ui.showError("I had trouble saving the file.");
            } catch (IndexOutOfBoundsException oob) {
                ui.showError("eh bro your list is shorter than that");
            }
        }
        ui.closeUi();
    }


    private static String findTodoDescStart(String s) throws LilyException {
        try {
            return s.substring(5); // "todo " is 5 char long
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException("You gotta tell me what the todo is about!");
        }
    }

    private static String[] findDeadlineDescStart(String s) throws LilyException {
        String[] result = new String[2];
        try {
            result[0] = s.substring(9, s.indexOf("/by") - 1);
            result[1] = s.substring(s.indexOf("/by") + 4);
            return result;
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException("You gotta tell me what the deadline is about!");
        }
    }
    
    private static String[] findEventDescStart(String s) throws LilyException {
        String[] result = new String[2];
        try {
            result[0] = s.substring(6, s.indexOf("/at") - 1);
            result[1] = s.substring(s.indexOf("/at") + 4); // different exception is thrown if indexOf cant find /at
            return result;
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException("You gotta tell me what the event is about!");
        }
    }
}