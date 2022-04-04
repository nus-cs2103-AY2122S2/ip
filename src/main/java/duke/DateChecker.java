package duke;

/**
 * Checks the date inputted by the user and filters it into the various Enum dates.
 */
public class DateChecker {
    private String dateInput;
    private Dates date;

    public enum Dates {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY,
        NODAYS
    };

    /**
     * Constructor of the DateChecker class.
     * Used to classify the types of dates to be passed into DateHelper.
     *
     * @param input Day inputted by the user. E.g. Mon, Sun, Thur
     */
    // Solution to get current day of week adapted from https://coderanch.com/t/385117/java/date-Monday
    public DateChecker(String input) {
        dateInput = input.toLowerCase();

        boolean isMonday = dateInput.equals("mon") || dateInput.equals("monday");
        boolean isTuesday = dateInput.equals("tues") || dateInput.equals("tuesday") || dateInput.equals("tue");
        boolean isWednesday = dateInput.equals("weds") || dateInput.equals("wednesday") || dateInput.equals("wed");
        boolean isThursday = dateInput.equals("thurs") || dateInput.equals("thursday") || dateInput.equals("thur");
        boolean isFriday = dateInput.equals("fri") || dateInput.equals("friday");
        boolean isSaturday = dateInput.equals("sat") || dateInput.equals("saturday");
        boolean isSunday = dateInput.equals("sun") || dateInput.equals("sunday");

        if (isMonday) {
            date = Dates.MONDAY;
        } else if (isTuesday) {
            date = Dates.TUESDAY;
        } else if (isWednesday) {
            date = Dates.WEDNESDAY;
        } else if (isThursday) {
            date = Dates.THURSDAY;
        } else if (isFriday) {
            date = Dates.FRIDAY;
        } else if (isSaturday) {
            date = Dates.SATURDAY;
        } else if (isSunday) {
            date = Dates.SUNDAY;
        } else {
            date = Dates.NODAYS;
        }
    }

    /**
     * Gets the enum date generated from the input date.
     *
     * @return Date based on the input. E.g. mon, tues, thurs.
     */
    public Dates getDate() {
        return date;
    }
}
