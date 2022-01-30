package luke.commands;

public enum CommandAction {
    DEADLINE(ActionType.ADD, "description,by"),
    TODO(ActionType.ADD, "description"),
    EVENT(ActionType.ADD, "description,at"),
    MARK(ActionType.UPDATE, "index"),
    UNMARK(ActionType.UPDATE, "index"),
    DELETE(ActionType.UPDATE, "index"),
    EXIT(ActionType.NO_ACTION),
    INVALID(ActionType.ERROR),
    LIST(ActionType.READ),
    FIND(ActionType.READ, "keyword");

    private ActionType actionType;
    private String argumentKeys;

    CommandAction(ActionType actionType) {
        this(actionType, "");
    }

    CommandAction(ActionType actionType, String argumentKeys) {
        this.actionType = actionType;
        this.argumentKeys = argumentKeys;
    }

    public String getArgumentKeys() {
        return this.argumentKeys;
    }

    public ActionType getCommandActionType() {
        return this.actionType;
    }
}
