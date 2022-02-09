package dazz.command;

import dazz.CommandMapper;
import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;

public class AliasCommand extends Command{
    private final String alias;
    private final String command;

    public AliasCommand(String command, String alias) {
        this.alias = alias;
        this.command = command;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        boolean hasUpdated = CommandMapper.putAlias(alias, command);
        return ui.messageForMapping(hasUpdated, alias, command);
    }
}
