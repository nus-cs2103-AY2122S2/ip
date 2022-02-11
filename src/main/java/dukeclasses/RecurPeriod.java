package dukeclasses;

import java.time.Period;

public class RecurPeriod {
    private Period period;
    private String periodAsString;

    private RecurPeriod(Period period, String periodAsString) {
        this.period = period;
        this.periodAsString = periodAsString;
    }

    public static RecurPeriod createRecurPeriod(String periodAsString) throws DukeException{
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
                return new RecurPeriod(Period.ofMonths(integerPeriod), periodAsString) ;
            } else if (periodAsStrings[1].contains("days")){
                return new RecurPeriod(Period.ofDays(integerPeriod), periodAsString);
            } else if (periodAsStrings[1].contains("years")) {
                return new RecurPeriod(Period.ofYears(integerPeriod), periodAsString);
            } else if (periodAsStrings[1].contains("week")) {
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
    public String toString(){
        return periodAsString;
    }
}
