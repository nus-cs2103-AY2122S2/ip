package doge;

import doge.command.Command;
import doge.exception.DogeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Doge {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Doge() {
        this.storage = new Storage();
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DogeException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        boolean isExit = false;
        this.ui.showLine();
        this.ui.greet();
        this.ui.showLine();

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                this.ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                storage.save(this.tasks.getTaskList());
                ui.respond(c);
                isExit = c.isExit();
            } catch (DogeException e) {
                ui.showError(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Doge().run();
    }

    public static LocalDateTime getDateTime(String input) throws DogeException {
        LocalDateTime currDateTime = LocalDateTime.now();

        try {
            String[] temp = input.trim().split(" ");
            String[] tempTime = temp[1].split(":");
            LocalDate date = LocalDate.parse(temp[0]);
            LocalTime time = LocalTime.of(Integer.parseInt(tempTime[0]), Integer.parseInt(tempTime[1]));
            LocalDateTime inputDateTime = LocalDateTime.of(date, time);

            if (inputDateTime.isAfter(currDateTime)) {
                return inputDateTime;
            } else {
                throw new DateTimeException("Invalid date/time!");
            }
        } catch (DateTimeException e) {
            throw new DogeException("Are you lacking common sense? Invalid date/time!");
        }
    }

}
