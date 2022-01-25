package commands;


import exceptions.InvalidOperationException;

public abstract class Command {

    public abstract void execute() throws InvalidOperationException;


}
