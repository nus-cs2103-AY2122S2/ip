package duke.time;

/**
 * The ManagerDate class handles validation check for
 * dates and formatting dates into the String form
 * from the number form.
 *
 * @author  Melvin Chan Zijun
 */
public class ManagerDate {
    /**
     * int format of day
     */
    private final int day;

    /**
     * int format of month
     */
    private final int month;

    /**
     * int format of year
     */
    private final int year;

    /**
     * flag to indicate whether date is valid
     */
    private boolean isValid = false;

    /**
     * Sole constructor.
     *
     * @param date - date in DDMMYYYY format
     */
    public ManagerDate(String date) {
        this.day = Integer.parseInt(date.substring(0, 2));
        this.month = Integer.parseInt(date.substring(2, 4));
        this.year = Integer.parseInt(date.substring(4, 8));

        // check valid month
        if (this.month < 13 && this.month > 0) {
            // check for leap year
            if (this.year % 4 != 0) { // no leap year
                // check for valid day
                int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                if (daysPerMonth[this.month - 1] >= this.day && this.day > 0) {
                    this.isValid = true;
                }
            } else { // leap year
                // check valid day
                int[] daysPerMonthLeapYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                if (daysPerMonthLeapYear[this.month - 1] >= this.day && this.day > 0) {
                    this.isValid = true;
                }
            }
        }
    }

    /**
     * This method returns the validity of the date
     *
     * @return boolean - whether date is valid
     */
    public boolean isDateValid() {
        return this.isValid;
    }

    /**
     * This method returns a more user-friendly format of
     * the input date. Date validity should be checked
     * before using this method.
     *
     * @param date date in DDMMYYYY format
     * @return boolean - whether date is valid
     */
    public static String formatDate(String date) {
        String[] nameOfMonths = {"January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"};
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4, 8));

        return nameOfMonths[month - 1] + " " + String.format("%02d", day) + " " + year;
    }
}
