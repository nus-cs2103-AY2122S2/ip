package chatbots;

import exceptions.InvalidInputException;
import exceptions.SaveFileModifiedException;
import file.management.FileManager;
import instructions.ExitInst;
import instructions.HelloInst;
import instructions.Instruction;
import instructions.taskinstructions.listinstructions.DisplayListInst;
import instructions.taskinstructions.listinstructions.FindInst;
import tasks.TaskList;

/**
 * This class encapsulates the chatbot Fluffers for CS2103T's Individual Project.
 * Fluffers is a chatbot that helps to manage a task list.
 *
 * @author Ong Han Yang
 */
public class Fluffers extends TaskManagerChatbot {
    /** Name of the saved file */
    private String fileName = "";

    /** Folder path to the saved file */
    private String filePath = "";

    /**
     * Constructs an asleep Fluffers.
     *
     * @param filePath the file path to the file to load from.
     * @param fileName the file name to load from.
     * @throws SaveFileModifiedException when the save file contains invalid symbols that could
     *          not have been from the string representation of a task, indicating external
     *          modification to the file.
     */
    private Fluffers(String filePath, String fileName) throws SaveFileModifiedException {
        super(FileManager.loadTaskListFromFile(filePath, fileName));
        this.fileName = fileName;
        this.filePath = filePath;
    }

    /**
     * Constructs an asleep Fluffers without setting any file path or file name. This initialises
     * Fluffers with an empty task list.
     */
    private Fluffers() {
        super(new TaskList());
    }

    /**
     * Produces a Fluffers object and sets the file path and file name for Fluffers to save/load from.
     *
     * @param filePath the file path to the file that Fluffers saves/loads from.
     * @param fileName the file name that Fluffer saves/loads from.
     * @return the Fluffers object, asleep
     * @throws SaveFileModifiedException when the save file contains invalid symbols that could
     *          not have been from the string representation of a task, indicating external
     *          modification to the file.
     */
    public static Fluffers of(String filePath, String fileName) throws SaveFileModifiedException {
        return new Fluffers(filePath, fileName);
    }

    /**
     * Produces a Fluffers object without setting the file path and file name. Fluffers will then
     * contain an empty task list.
     *
     * @return The Fluffers object, asleep.
     */
    public static Fluffers of() {
        return new Fluffers();
    }

    /**
     * Loads a file to use instead of the current task list. WARNING: THIS WILL OVERRIDE ALL CURRENT
     * TASKS IN THE TASKLIST IF IT IS NOT SAVED.
     *
     * @param filePath the file path to the file that Fluffers saves/loads from.
     * @param fileName the file name that Fluffer saves/loads from.
     * @throws SaveFileModifiedException when the save file contains invalid symbols that could
     *          not have been from the string representation of a task, indicating external
     *          modification to the file.
     */
    public void loadFile(String filePath, String fileName) throws SaveFileModifiedException {
        this.fileName = fileName;
        this.filePath = filePath;
        this.setTaskList(FileManager.loadTaskListFromFile(filePath, fileName));
    }

    /**
     * Wakes Fluffers up.
     */
    public void wakeUp() {
        this.isAwake = true;
    }

    /**
     * Asks Fluffers to speak with fancy formatting.
     *
     * @param input the text that Fluffers is asked to speak.
     * @return the formatted String that Fluffers is asked to speak.
     */
    protected String speak(String input) {
        return String.format("Meow! (%s)\n", input);
    }

    /**
     * Says goodbye to the user, with fancy formatting.
     *
     * @return String representation of the Farewell.
     */
    protected String farewell() {
        return "Meooow~ (Bye bye! See you again next time!)";
    }

    /**
     * Processes a command and gives a reply according to the command.
     *
     * @param input the input String or command to be given to Fluffers.
     * @return the response given with respect to the given input.
     */
    public String getReply(String input) {
        if (!this.isAwake && !input.startsWith("hi")) {
            return "Fluffers is asleep... (Type \"hi\" to wake her up!)";
        }

        Instruction inst;
        try {
            inst = Instruction.of(input);
        } catch (InvalidInputException e) {
            return e.getMessage();
        }

        // check for special instructions
        if (inst instanceof ExitInst) {
            this.isAwake = false;
            return farewell();
        }
        if (inst instanceof HelloInst) {
            if (this.isAwake) {
                return "Meow! (Hello!)";
            } else {
                this.isAwake = true;
                return "Meoow~ (Hello again!)";
            }
        }
        if (inst instanceof DisplayListInst || inst instanceof FindInst) {
            String output = super.instHandler.doInstruction(inst);

            if (inst instanceof DisplayListInst && output.length() == 0) {
                return this.speak("Theres nothing in the list");
            }

            if (inst instanceof FindInst) {
                if (output.length() == 0) {
                    return this.speak("Sorry, I found nothing...");
                }
                output = this.speak("Here's what I found! (the numbering is the kept the "
                        + "same as the \"list\" command)") + output;
            }
            return output;
        }

        return this.speak(super.instHandler.doInstruction(inst));
    }

    /**
     * Represents the Fluffers object as a String, displaying the version and date last updated.
     *
     * @return the String representing Fluffers, with the version number and date last updated.
     */
    @Override
    public String toString() {
        return "Version 0.2, Last Updated: 30 Jan 2022";
    }
}
