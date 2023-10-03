package lily;

/**
 * Makes sense of commands input by the user and executes them.
 * 
 * @author Hong Yi En, Ian
 * @version Feb 2022 (AY21/22 Sem 2)
 */
public class Parser {
    private final TaskList tasks;
    private final Ui ui;
    private final Storage st;

    /**
     * Creates a new Parser Object.
     * 
     * @param t The TaskList referred to.
     * @param ui The UI needed to interact with the user.
     * @param st The Storage needed to store data.
     */
    public Parser(TaskList t, Ui ui, Storage st) {
        this.tasks = t;
        this.ui = ui;
        this.st = st;
    }

    /**
     * Reads user's input for parsing.
     *
     * @param s The sentence the user had input.
     */
    public void readCommand(String s) {
        parse(s);
    }

    /**
     * Decodes the user's input and calls the relevant functions.
     */
    public void parse(String sentence) {
        try {
            String[] parsedSentence = sentence.split(" ");
            String command = parsedSentence[0];
            switch (command) {
            case "bye":
                ui.closeUi();
                st.save(tasks);
                break;

            case "list":
                ui.showList(tasks);
                break;

            case "mark":
            // Fallthrough
            case "done":
            // Fallthrough
            case "do":
                assert parsedSentence[1] != null : "User should have input an index to mark";
                int addIdx = Integer.parseInt(parsedSentence[1]); // base 1
                ui.showMarked(tasks.mark(addIdx - 1), addIdx);
                break;

            case "unmark":
                assert parsedSentence[1] != null : "User should have input an index to unmark";
                int delIdx = Integer.parseInt(parsedSentence[1]);
                ui.showUnmarked(tasks.unmark(delIdx - 1), delIdx);
                break;

            case "todo":
                ui.showTaskAdded(tasks.addTodo(findDescDate(TaskType.TODO, sentence)[0]), tasks.getSize());
                break;

            case "deadline":
                String deadDescription = findDescDate(TaskType.DEADLINE, sentence)[0];
                String deadDate = findDescDate(TaskType.DEADLINE, sentence)[1];
                ui.showTaskAdded(tasks.addDeadline(deadDescription, deadDate), tasks.getSize());
                break;

            case "event":
                String eventDescription = findDescDate(TaskType.EVENT, sentence)[0];
                String eventDate = findDescDate(TaskType.EVENT, sentence)[1];
                ui.showTaskAdded(tasks.addEvent(eventDescription, eventDate), tasks.getSize());
                break;
    
            case "job":
                String jobDescription = findDescDate(TaskType.JOB, sentence)[0];
                String jobDuration = findDescDate(TaskType.JOB, sentence)[1];
                ui.showTaskAdded(tasks.addJob(jobDescription, jobDuration), tasks.getSize());
                break;

            case "delete":
            // Fallthrough
            case "remove":
                assert parsedSentence[1] != null : "User should have input an index to mark";
                ui.showTaskRemoved(tasks.remove(Integer.parseInt(parsedSentence[1]) - 1), tasks);
                break;

            case "find":
            // Fallthrough
            case "search":
                assert parsedSentence[1] != null : "User should have input a search term";
                if (parsedSentence.length > 2) {
                    throw new LilyException(LilyException.ERROR_TOO_MANY_SEARCH_TERMS);
                } else {
                    ui.showFind(parsedSentence[1], tasks);
                }
                break;
    
            default:
                ui.showInvalidCommand(sentence);
            }

        } catch (LilyException le) {
            ui.showError(le.getMessage());

        } catch (IndexOutOfBoundsException oob) {
            // caught when user types "mark", "unmark" or "delete" without giving an index
            // if user types "mark 5" when the list has 2 items, 
            // it throws lily exception instead
            ui.showError(LilyException.ERROR_MISSING_INDEX);

        } catch (NumberFormatException nfe) {
            // caught when user types "mark two"
            ui.showError(LilyException.FORMAT_IDX);
        }
    }

    /**
     * Types of tasks the user can add.
     */
    private enum TaskType {
        TODO (5, 0, "unused tag"), 
        DEADLINE (9, 4, "/by"), 
        EVENT (6, 4, "/at"),
        JOB (4, 5, "/for");

        private final int cmdCharLen;
        private final int dateCharLen;
        private final String dateTag;

        TaskType(int cmdLength, int dateTagLength, String tag) {
            cmdCharLen = cmdLength;
            dateCharLen = dateTagLength;
            dateTag = tag;
        }

        public int getCmdLen() {
            return cmdCharLen;
        } 

        public int getDateCharLen() {
            return dateCharLen;
        }

        public String getDateTag() {
            return dateTag;
        }
    }

    /**
     * Finds the description and date of each kind of task added.
     * 
     * @param type of task to find the desc and date for.
     * @param s The user's input sentence.
     * @return The description and date of the event.
     * @throws LilyException When there is no description.
     */
    private static String[] findDescDate(TaskType type, String s) throws LilyException {
        String[] result = new String[2];

        // find the description
        try {
            switch(type) {
            case TODO:
                result[0] = s.substring(TaskType.TODO.getCmdLen());
                break;

            case DEADLINE:
                result[0] = s.substring(TaskType.DEADLINE.getCmdLen(), 
                        s.indexOf(TaskType.DEADLINE.getDateTag()) - 1);
                break;

            case EVENT:
                result[0] = s.substring(TaskType.EVENT.getCmdLen(), 
                        s.indexOf(TaskType.EVENT.getDateTag()) - 1);
                break;

            case JOB:
                result[0] = s.substring(TaskType.JOB.getCmdLen(), 
                        s.indexOf(TaskType.JOB.getDateTag()) - 1);
                break;

            default:
                throw new LilyException(LilyException.ERROR_UNKNOWN_TASK_TYPE);
            }
        } catch (StringIndexOutOfBoundsException e) {
            // problem: triggers when user uses the wrong tag. 
            // eg. /at or /by for deadline and event respectively
            throw new LilyException(LilyException.ERROR_MISSING_DESC);
        }

        // find the date
        try {
            switch(type) {
            case TODO:
                break;

            case DEADLINE:
                result[1] = s.substring( s.indexOf(TaskType.DEADLINE.getDateTag()) 
                        + TaskType.DEADLINE.getDateCharLen());
                break;

            case EVENT:
                result[1] = s.substring( s.indexOf(TaskType.EVENT.getDateTag()) 
                        + TaskType.EVENT.getDateCharLen());
                break;

            case JOB:
                result[1] = s.substring( s.indexOf(TaskType.JOB.getDateTag()) 
                        + TaskType.JOB.getDateCharLen());
                break;

            default:
                throw new LilyException(LilyException.ERROR_UNKNOWN_TASK_TYPE);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException(LilyException.ERROR_MISSING_DATE);
        }

        return result;
    }
}

