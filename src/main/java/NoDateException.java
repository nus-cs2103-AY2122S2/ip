public class NoDateException extends Exception{
    @Override
    public String toString() {
        return "Error! Please enter a date for your deadline or event following a slash after the task description";
    }
}
