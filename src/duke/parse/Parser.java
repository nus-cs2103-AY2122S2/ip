package parse;

import instruction.Instruction;
import main.DukeException;
import task.TaskManager;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Represents the input parser for main.Duke.
 * This class is responsible for parsing user commands and interpret them as <code>instruction.Instruction</code>s.
 */
public class Parser {

    private InputStream inputStream;

    /**
     * Constructors a parser, with the specified input stream.
     *
     * @param inputStream The input stream to be used.
     */
    public Parser(InputStream inputStream) {

        this.inputStream = inputStream;
    }

    /**
     * Parses a string into an instruction.
     *
     * @param tasks The task manager to be used by the instruction.
     * @return The instruction generated.
     * @throws DukeException If the instruction is not recognized.
     */
    public Instruction parseInstruction(TaskManager tasks)
            throws DukeException {

        Scanner sc = new Scanner(inputStream);
        return Instruction.of(sc.nextLine(), tasks);
    }
}
