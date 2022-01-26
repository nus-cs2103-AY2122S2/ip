public class EventCommand extends AddCommand {
    public static final CommandAction COMMAND_ACTION = CommandAction.EVENT;

    EventCommand(Event event) {
        super(event);
    }
}
