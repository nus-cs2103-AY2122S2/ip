package tasks;

import exceptions.InvalidOperationException;

public abstract class Task {
    public abstract void mark() throws InvalidOperationException;
    public abstract void unmark() throws InvalidOperationException;
}
