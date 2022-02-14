package dukeclasses;

import java.time.Period;

/**
 * Represents the period for a recurring task to extend the dateline of the task.
 */
public class RecurPeriod {
    private Period period;
    private String periodAsString;

    private RecurPeriod(Period period, String periodAsString) {
        this.period = period;
        this.periodAsString = periodAsString;
    }

    /**
     * Creates an instance of RecurPeriod based on the input given.
     *
     * @param periodAsString String containing the recurPeriod.
     * @return RecurPeriod which represents the recurring period based on the input.
     * @throws DukeException Exception is thrown when periodAsString is not a valid period.
     */
    public static RecurPeriod createRecurPeriod(String periodAsString) throws DukeException {
        String[] periodAsStrings = periodAsString.split(" ", 2);
        if (periodAsStrings.length == 1) {
            return null;
        }

        try {
            Integer integerPeriod = Integer.parseInt(periodAsStrings[0].trim());

            if (integerPeriod <= 0) {
                return null;
            }

            periodAsStrings[1].trim();
            if (periodAsStrings[1].contains("months")) {
                return new RecurPeriod(Period.ofMonths(integerPeriod), periodAsString);
            } else if (periodAsStrings[1].contains("days")) {
                return new RecurPeriod(Period.ofDays(integerPeriod), periodAsString);
            } else if (periodAsStrings[1].contains("years")) {
                return new RecurPeriod(Period.ofYears(integerPeriod), periodAsString);
            } else if (periodAsStrings[1].contains("weeks")) {
                return new RecurPeriod(Period.ofWeeks(integerPeriod), periodAsString);
            } else {
                return null;
            }
        } catch (ArithmeticException | NumberFormatException error) {
            return null;
        }

    }

    public Period getPeriod() {
        return this.period;
    }

    @Override
    public String toString() {
        return periodAsString;
    }
}
