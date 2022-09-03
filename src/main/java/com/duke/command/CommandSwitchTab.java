package com.duke.command;

public class CommandSwitchTab extends Command {
    private String tab;
    private String args;

    /**
     * Constructor for this class.
     *
     * @param tab   The name of the tab to switch to.
     * @param args  Action to be done in the tab.
     */
    public CommandSwitchTab(String tab, String args) {
        this.tab = tab;
        this.args = args;
    }

    @Override
    public CommandResult execute() {
        if (args != "") {
            return new CommandResult("<GUI->" + tab + "->" + args + ">");
        } else {
            return new CommandResult("<GUI->" + tab + ">");
        }
    }
}
