import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    
    private Scanner sc;
    private String fullCommand;
    
    public Parser() { 
        Scanner sc = new Scanner(System.in);
        this.sc = sc;
    }

    public void readFullCommand() {
        fullCommand = sc.nextLine();
        //return fullCommand;
    }
    
    public String getKeyCommand() {
        return fullCommand.split(" ", 2)[0];
    }
    
    public void close() {
        sc.close();
    }

    public String getDetails() throws invalidCommandException {
        String details = fullCommand.split(" ", 2)[1];
        if (details.isBlank())
            throw new invalidCommandException();
        return details;
    }

    public String getDeadlineTask() {
        return fullCommand.split(" ", 2)[1].split(" /by ")[0];
    }
    
    public String getDeadlineDate() {
        return fullCommand.split(" ", 2)[1].split(" /by ")[1].split(" ")[0];
    }
    
    public String getEventTask() {
        return fullCommand.split(" ", 2)[1].split(" /at ")[0];
    }
    
    public String getEventDate() {
        return fullCommand.split(" ", 2)[1].split(" /at ")[1].split(" ")[0];
    }

    public boolean isThereTime() {
        return fullCommand.split(" ", 2)[1].split(" /")[1].split(" ").length == 3;
    }

    public String getDeadlineTime() {
        return fullCommand.split(" ", 2)[1].split(" /by ")[1].split(" ")[1];
    }

    public String getEventTime() {
        return fullCommand.split(" ", 2)[1].split(" /at ")[1].split(" ")[1];
    }
}
