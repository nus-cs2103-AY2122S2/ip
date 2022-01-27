import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class DateTime {
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

    @Override
    public String toString() {
        return this.date.toString() + " " + (this.time > 999 ? "" : "0")
                + this.time / 100 + ":" + this.time % 100;
    }
}
