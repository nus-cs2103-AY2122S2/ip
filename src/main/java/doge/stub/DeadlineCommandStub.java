package doge.stub;

import doge.command.DeadlineCommand;

public class DeadlineCommandStub extends DeadlineCommand {
    public DeadlineCommandStub() {
        super(new DeadlineStub());
    }
}
