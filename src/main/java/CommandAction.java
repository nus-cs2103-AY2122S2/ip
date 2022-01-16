public enum CommandAction {
    DEADLINE(ActionType.ADD), TODO(ActionType.ADD), EVENT(ActionType.ADD),
    LIST(ActionType.READ), MARK(ActionType.UPDATE), UNMARK(ActionType.UPDATE),
    EXIT(ActionType.NO_ACTION), UNKNOWN(ActionType.NO_ACTION);

    private ActionType actionType;

    CommandAction(ActionType actionType) {
        this.actionType = actionType;
    }

    public ActionType getCommandActionType() {
        return actionType;
    }
}
