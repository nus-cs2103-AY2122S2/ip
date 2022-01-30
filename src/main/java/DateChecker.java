public class DateChecker {
    private int day;
    private int month;
    private int year;

    private final int[] daysPerMonth
            = {31, 28, 31, 30,
            31, 30, 31, 31,
            30, 31, 30, 31};

    private final int[] daysPerMonthLeapYear
            = {31, 29, 31, 30,
            31, 30, 31, 31,
            30, 31, 30, 31};

    private final String[] nameOfMonths
            = {"January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"};

    private boolean isValid = false;

    DateChecker(String date) {
        this.day = Integer.parseInt(date.substring(0, 2));
        this.month = Integer.parseInt(date.substring(2, 3));
        this.year = Integer.parseInt(date.substring(4, 8));

        // check valid month
        if (this.month < 13 && this.month > 0) {
            // check for leap year
            if (this.year % 400 != 0) { // no leap year
                // check for valid day
                if (this.daysPerMonth[this.month - 1] >= this.day && this.day > 0) {
                    this.isValid = true;
                }
            } else { // leap year
                // check valid day
                if (this.daysPerMonthLeapYear[this.month - 1] >= this.day && this.day > 0) {
                    this.isValid = true;
                }
            }
        }
    }

    public boolean isDateValid() {
        return this.isValid;
    }

    public String formatDate() {
        return this.day + this.nameOfMonths[this.month] + this.year;
    }
}
