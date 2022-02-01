public class DateManager {
    private final int day;
    private final int month;
    private final int year;
    private boolean isValid = false;

    DateManager(String date) {
        this.day = Integer.parseInt(date.substring(0, 2));
        this.month = Integer.parseInt(date.substring(2, 4));
        this.year = Integer.parseInt(date.substring(4, 8));

        // check valid month
        if (this.month < 13 && this.month > 0) {
            // check for leap year
            if (this.year % 400 != 0) { // no leap year
                // check for valid day
                int[] daysPerMonth = {31, 28, 31, 30,
                        31, 30, 31, 31,
                        30, 31, 30, 31};
                if (daysPerMonth[this.month - 1] >= this.day && this.day > 0) {
                    this.isValid = true;
                }
            } else { // leap year
                // check valid day
                int[] daysPerMonthLeapYear = {31, 29, 31, 30,
                        31, 30, 31, 31,
                        30, 31, 30, 31};
                if (daysPerMonthLeapYear[this.month - 1] >= this.day && this.day > 0) {
                    this.isValid = true;
                }
            }
        }
    }

    public boolean isDateValid() {
        return this.isValid;
    }

    public static String formatDate(String date) {
        String[] nameOfMonths
                = {"January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December"};
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(2, 4));
        int year = Integer.parseInt(date.substring(4, 8));

        return day + nameOfMonths[month] + year;
    }
}
