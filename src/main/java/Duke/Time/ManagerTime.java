package Duke.Time;

/**
 * The ManagerTime class handles validation check for
 * time and formatting time into twelve-hour format or
 * the twenty-four-hour format.
 *
 * @author  Melvin Chan Zijun
 */
public class ManagerTime {
    /**
     * time in twenty-four-hour format
     */
    private final String format24;
    /**
     * time in twelve-hour format
     */
    private final String format12;
    /**
     * flag to indicate whether time is valid
     */
    boolean isValid = false;

    /**
     * Sole constructor.
     *
     * @param time - time in HHMM format
     */
    public ManagerTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));

        if (hour >= 0 && hour <= 23) {
            if (minute >= 0 && minute <= 59) {
                this.isValid = true;
            }
        }

        this.format24 = time;

        String postfix = "";

        if (this.isValid) {
            if (hour > 11) {
                postfix += "pm";
            } else {
                postfix += "am";
            }

            if (hour > 12) {
                hour -= 12;
            }

            this.format12 = String.format("%d:%02d%s", hour, minute, postfix);
        } else {
            this.format12 = "";
        }
    }

    /**
     * This method returns the validity of the time
     *
     * @return boolean - whether time is valid
     */
    public boolean isTimeValid() {
        return this.isValid;
    }

    /**
     * This method returns the time in twelve-hour format
     *
     * @return time in twelve-hour format
     */
    public String getFormat12() {
        return this.format12;
    }

    /**
     * This method returns the time in twenty-four-hour format
     *
     * @return time in twenty-four-hour format
     */
    public String getFormat24() {
        return this.format24;
    }
}
