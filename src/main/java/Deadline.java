public class Deadline extends Task {

    private String date;

    public Deadline(String description, String dline) {
        super(description);
        this.date = dline;
    }

    @Override
    public String toString() {
        return "[D]" + this.getSymbol() + this.getName() + "(by: " + this.date.trim() + ")";
    }

    public static Deadline formatInput(String input) {
        String dlTask = input.substring(8); //Grabs all the text after the "deadline" command word
        String dLine = "/by";
        int dlDatePos = dlTask.indexOf(dLine);
        String dlDate = dlTask.substring(dlDatePos + 3); //Grabs all the text after the "/by" key word
        String dlDes = dlTask.substring(0, dlDatePos);
        return new Deadline(dlDes, dlDate);
    }

}
