package ultoi.util;

import ultoi.task.Task;
import ultoi.task.ToDo;
import ultoi.task.Deadline;
import ultoi.task.Event;

import ultoi.command.Command;
import ultoi.command.AddCommand;
import ultoi.command.ByeCommand;
import ultoi.command.DeleteCommand;
import ultoi.command.ListCommand;
import ultoi.command.MarkCommand;

import java.time.LocalDate;

public class DateTime {
    private final LocalDate date;
    private final int time;

    // format: YYYY-MM-DD tttt
    public DateTime(String datetime) throws UltoiException {
        String[] datetimes = datetime.split(" ");
        try {
            this.date = LocalDate.parse(datetimes[0]);
            this.time = Integer.parseInt(datetimes[1]);
        } catch (Exception e) {
            throw new UltoiException("wrong time format!");
        }
        if (this.time < 0 || time % 100 > 59 || time / 100 > 23) {
            throw new UltoiException("wrong time format!");
        }
    }

    public String toInputString() {
        return this.date.toString() + " " + (this.time > 999 ? "" : "0") + time;
    }

    @Override
    public String toString() {
        return this.date.toString() + " " + (this.time > 999 ? "" : "0")
                + this.time / 100 + ":" + (this.time > 9 ? "" : "0") + this.time % 100;
    }
}
