package li.zhongfu.cs2103.chatbot.types.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class UserInterface {
    private static final String HLINE = "____________________________________________________________";

    private static final String MOTD = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private PrintStream output;
    private BufferedReader br;
    private Parser parser;

    public UserInterface(InputStream input, PrintStream output) {
        this.parser = new Parser();
        this.output = output;
        this.br = new BufferedReader(new InputStreamReader(input));
    }

    public void printDialog(String[] lines) {
        this.output.println("    " + HLINE);
        for (String line : lines) {
            if (line.contains("\n")) {
                String[] sublines = line.split("\n");
                for (String subline : sublines) {
                    this.output.println("    " + subline);
                }
            } else {
                this.output.println("    " + line);
            }
        }
        this.output.println("    " + HLINE + "\n");
    }

    public void printDialog(String line) {
        this.printDialog(line.split("\n"));
    }

    public void printMotd() {
        this.output.println(MOTD); // extra newline
    }

    public void println(String str) {
        this.output.println(str);
    }

    public void print(String str) {
        this.output.print(str);
    }

    public ParserResult getInput() throws IOException {
        String input = this.br.readLine();
        String[] parts = input.split("\\s+", 2); // split into command and args
        String cmd = parts[0];
        if (parts.length == 1) { // only command
            return new ParserResult(cmd, new HashMap<>());
        } else { // length is 2
            Map<String, String> args = this.parser.parseArgString(parts[1]);
            return new ParserResult(cmd, args);
        }
    }

    // i know this really really really doesn't belong here
    // but i kinda can't be arsed
    // todo fix this when you implement... idk... command objects, handlers, whatever
    public LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        return this.parser.parseDateTime(dateTimeString);
    }
}