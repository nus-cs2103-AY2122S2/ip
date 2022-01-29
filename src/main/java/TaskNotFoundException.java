public class TaskNotFoundException extends Throwable {

    @Override
    public String getMessage() {
        return "Oops, I'm not able to retrieve this task";
    }
}
