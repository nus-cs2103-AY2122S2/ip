package main.commands;

import main.enums.CommandType;

public class CBye extends Command{
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public CBye() {
        super(CommandType.BYE);
    }

    @Override
    public void runCommand() {
        Command.exitDuke();
        System.out.println(GOODBYE_MESSAGE);
    }
}
