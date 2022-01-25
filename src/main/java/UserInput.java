import java.time.DateTimeException;

public class UserInput {
    private String command = "";
    private String description = "";
    private String time = "";

    public UserInput(String input) {
        processInput(input);
    }

    public String getCommand() {
        return this.command;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTime() {
        return this.time;
    }

    private void processInput(String input) throws DateTimeException {
        int descriptionStart = input.indexOf(' ');
        int timeStart = input.indexOf('/');

        // process the user input into different segments
        if (descriptionStart != -1) {
            // sentence input
            this.command = input.substring(0, descriptionStart);
            if (timeStart != -1) {
                // has time input
                this.description = input.substring(descriptionStart, timeStart);
                this.description = this.description.replaceFirst(" ", "");
                this.time = input.substring(timeStart);
                this.time = this.time.replaceFirst("/", "");
            } else {
                // no time input
                this.description = input.substring(descriptionStart);
                this.description = this.description.replaceFirst(" ", "");
            }
        } else {
            // single word input
            this.command = input;
        }
    }
}