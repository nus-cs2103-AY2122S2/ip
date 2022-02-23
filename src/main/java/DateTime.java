import java.time.*;

public class DateTime {
    private final LocalDateTime dateTime;

    DateTime(String[] dateTimeArr) {
        this.dateTime = LocalDateTime.of(Integer.parseInt(dateTimeArr[1])
                , Integer.parseInt(dateTimeArr[2])
                , Integer.parseInt(dateTimeArr[3])
                , Integer.parseInt(dateTimeArr[4].substring(0, 2))
                , Integer.parseInt(dateTimeArr[4].substring(2)));
    }

    @Override
    public String toString() {
        String minutes = "";
        if (this.dateTime.getMinute() < 10) {
            minutes = "0" + this.dateTime.getMinute();
        } else {
            minutes = String.valueOf(this.dateTime.getMinute());
        }
        return this.dateTime.getMonth().toString().substring(0, 3) + " "
                + this.dateTime.getDayOfMonth() + " "
                + this.dateTime.getYear() + " "
                + this.dateTime.getHour()  + ":"
                + minutes;
    }
}
