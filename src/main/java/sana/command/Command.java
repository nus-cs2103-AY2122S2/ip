package sana.command;

import sana.Memory;
import sana.SanaResponse;
import sana.TaskList;

public abstract class Command {
    /** Generates sana's responses to various commands */
    protected SanaResponse sanaResponse = new SanaResponse();
    /** Where the data of sana's tasklist is stored */
    protected Memory memory = new Memory();

    /**
     * Defines the logic flow of each command
     *
     * @param args  the arguments in the command
     * @param taskList  sana's tasklist
     *
     * @return  sana's response to the command
     */
    public abstract String executeCommand(String[] args, TaskList taskList);

}
