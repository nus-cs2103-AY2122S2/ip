public enum CommandAction {
    DEADLINE(ActionType.ADD, "description,by"), TODO(ActionType.ADD, "description"), EVENT(ActionType.ADD, "description,at"),
    LIST(ActionType.READ), MARK(ActionType.UPDATE,"index"), UNMARK(ActionType.UPDATE,"index"),
    EXIT(ActionType.NO_ACTION), UNKNOWN(ActionType.NO_ACTION);

    private ActionType actionType;
    private String argumentKeys;

    CommandAction(ActionType actionType) {
        this.actionType = actionType;
        this.argumentKeys = "";
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
