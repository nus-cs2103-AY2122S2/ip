package duke.commands;

import java.time.LocalDate;

public interface DateValidator {
    LocalDate validDate(String dateStr);
}
