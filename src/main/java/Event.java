public class Event extends Task {

    private String date;

    public Event(String description, String d) {
        super(description);
        this.date = d;
    }

    @Override
    public String toString() {
        return "[E]" + this.getSymbol() + this.getName() + "(at: " + this.date.trim() + ")";
    }

    public static Event formatInput(String input) {
        String evTask = input.substring(5); //Grabs all the text after the "event" command word
        String event = "/at";
        int evDatePos = evTask.indexOf(event);
        String evDate = evTask.substring(evDatePos + 3); //Grabs all the text after the "/at" key word
        String evDes = evTask.substring(0, evDatePos);
        return new Event(evDes, evDate);
    }

}
