package dazz.command;

import dazz.CommandMapper;
import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;

public class AliasCommand extends Command {
    private final String alias;
    private final String command;

    /**
     * Constructs an <code>AliasCommand</code>.
     * @param command The command.
     * @param alias The alias.
     */
    public AliasCommand(String command, String alias) {
        this.alias = alias;
        this.command = command;
    }

    /**
     * Executes the adding of an alias.
     * @param taskList The <code>TaskList</code>.
     * @param ui The <code>Ui</code> of <code>Dazz</code>.
     * @param storage The <code>Storage</code>.
     * @return The execution message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        boolean hasUpdated = CommandMapper.putAlias(alias, command);
        return ui.messageForMapping(hasUpdated, alias, command);
    }
}
