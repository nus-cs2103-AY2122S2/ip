package ultoi.util;

import java.time.LocalDate;

public class DateTime {
    private final LocalDate date;
    private final int time;

    public DateTime(String dateTime) throws UltoiException {
        String[] dateTimes = dateTime.split(" ");

        try {
            this.date = LocalDate.parse(dateTimes[0]);
            this.time = Integer.parseInt(dateTimes[1]);
        } catch (Exception e) {
            throw new UltoiException("wrong time format!");
        }

        if (this.time < 0 || time % 100 > 59 || time / 100 > 23) {
            throw new UltoiException("wrong time format!");
        }
    }

    public String toInputString() {
        return this.date.toString() + " "
                + (this.time > 999 ? "" : "0") + time;
    }

    @Override
    public String toString() {
        return this.date.toString() + " " + (this.time > 999 ? "" : "0")
                + this.time / 100 + ":" + (this.time > 9 ? "" : "0") + this.time % 100;
    }
}
