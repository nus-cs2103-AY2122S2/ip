package chatbots;

import java.util.Scanner;

import exceptions.InvalidInputException;
import exceptions.SaveFileModifiedException;
import file.management.FileManager;
import instructions.ExitInst;
import instructions.Instruction;
import instructions.listinstructions.DisplayListInst;
import instructions.listinstructions.FindInst;

/**
 * This class encapsulates the chatbot Fluffers for CS2103T's Individual Project.
 * Fluffers is a chatbot that helps to manage a task list.
 *
 * ASCII art credit: All ASCII art was found on https://www.asciiart.eu/animals/cats .
 *
 * @author Ong Han Yang
 */
public class Fluffers extends TaskManagerChatbot {
    private enum AsciiArt {
        /** ASCII art for when Fluffers just wakes up */
        AWAKE("    /\\_____/\\\n"
                + "   /  o   o  \\\n"
                + "  ( ==  ^  == )\n"
                + "   )         (\n"
                + "  (           )\n"
                + " ( (  )   (  ) )\n"
                + "(__(__)___(__)__)"
        ),
        /** ASCII art for when Fluffers goes back to sleep */
        ASLEEP("      |\\      _,,,---,,_\n"
                + "ZZZzz /,`.-'`'    -.  ;-;;,_\n"
                + "     |,4-  ) )-,_. ,\\ (  `'-'\n"
                + "    '---''(_/--'  `-'\\_)"
        ),
        /** ASCII art for when Fluffers is displaying a list */
        LIST_TOP("    |\\__/,|   (`\\\n"
                + "  _.|o o  |_   ) )\n"
                + "-(((---(((--------"
        );

        private final String art;
        private AsciiArt(String art) {
            this.art = art;
        }
    }

    /** Name of the saved file */
    private static String fileName = "";

    /** Folder path to the saved file */
    private static String filePath = "";

    /**
     * Constructs an awake Fluffers.
     *
     * @throws SaveFileModifiedException when the save file contains invalid symbols that could
     *          not have been from the string representation of a task, indicating external
     *          modification to the file.
     */
    private Fluffers() throws SaveFileModifiedException {
        super(FileManager.loadTaskListFromFile(filePath, fileName));
    }

    /**
     * Gets a Fluffers object and sets the file path and file name for Fluffers to save/load from.
     *
     * @param filePath the file path to the file that Fluffers saves/loads from.
     * @param fileName the file name that Fluffer saves/loads from.
     * @return the Fluffers object
     * @throws SaveFileModifiedException when the save file contains invalid symbols that could
     *          not have been from the string representation of a task, indicating external
     *          modification to the file.
     */
    public static Fluffers get(String filePath, String fileName) throws SaveFileModifiedException {
        Fluffers.filePath = filePath;
        Fluffers.fileName = fileName;
        return new Fluffers();
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
     * Greets the user, with fancy formatting.
     *
     * @return String representation of the greeting.
     */
    protected String greet() {
        return "Activating Cat Translator 2000...\nWaking Fluffers up...\n\nMeow! (Hello!)\n"
                + AsciiArt.AWAKE.art;
    }

    /**
     * Says goodbye to the user, with fancy formatting.
     *
     * @return String representation of the Farewell.
     */
    protected String farewell() {
        return "Meooow~ (Bye bye! See you again next time!)\n"
                + AsciiArt.ASLEEP.art;
    }

    /**
     * Processes a command and gives a reply according to the command.
     *
     * @param input the input String or command to be given to Fluffers.
     * @return the response given with respect to the given input.
     */
    public String feedCommandAndReply(String input) {
        Instruction inst;
        try {
            inst = Instruction.of(input);
        } catch (InvalidInputException e) {
            return this.speak(e.getMessage());
        }
        // check for special inst
        if (inst instanceof ExitInst) {
            this.isAwake = false;
            return farewell();
        }
        if (inst instanceof DisplayListInst || inst instanceof FindInst) {
            String output = String.format("%s\n%s------------------", AsciiArt.LIST_TOP.art,
                    super.instHandler.doInstruction(inst));
            if (inst instanceof FindInst) {
                output = this.speak("Here's what I found! (the numbering is the kept the "
                        + "same as the \"list\" command") + output;
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

    /**
     * Starts the program/wakes Fluffers up.
     *
     * @param args input CLI arguments.
     */
    public static void main(String[] args) {
        Fluffers f;
        String filePath = "data";
        String fileName = "saved-taskList.txt";
        try {
            f = Fluffers.get(filePath, fileName);
        } catch (SaveFileModifiedException e) {
            System.out.println(String.format("The save file at %s/%s has been modified, and is unreadable."
                    + " Either delete the file, or fix it manually by deleting any wrong entries.", filePath,
                    fileName));
            return;
        }

        System.out.println(f.greet());

        Scanner sc = new Scanner(System.in);

        while (f.isAwake) {
            String input = sc.nextLine();
            System.out.println(f.feedCommandAndReply(input));
        }
    }
}
