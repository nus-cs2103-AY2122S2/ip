package saitama.tasks;

import static java.time.temporal.TemporalAdjusters.next;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import saitama.tags.RecurFrequency;

/**
 * A deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;
    private boolean shouldDeadlineReset;

    /**
     * Initialises an undone deadline task.
     *
     * @param description The details of the deadline task
     * @param by The deadline of the task
     * @param recurFrequency The frequency of recurrence of the deadline task
     */
    public Deadline(String description, LocalDateTime by, RecurFrequency recurFrequency) {
        super(description, recurFrequency);
        this.deadline = by;
    }

    /**
     * Initialises a deadline task.
     *
     * @param description The details of the deadline task
     * @param by The deadline of the task
     * @param isDone Whether the task is done
     * @param recurFrequency The frequency of recurrence of the deadline task
     * @param lastResetDate The last reset date of the task
     */
    public Deadline(String description, LocalDateTime by, boolean isDone,
                    RecurFrequency recurFrequency, LocalDate lastResetDate) {
        super(description, isDone, recurFrequency, lastResetDate);
        this.deadline = by;

        if (shouldDeadlineReset) {
            this.deadline = getNewDeadline(deadline);
            this.shouldDeadlineReset = false;
        }
    }

    /**
     * Resets the isDone status and sets shouldDeadlineReset to true.
     */
    @Override
    protected void reset() {
        super.reset();
        this.shouldDeadlineReset = true;
    }

    /**
     * Returns the new deadline of a recurring task if it is reset.
     *
     * @param deadline The original deadline
     * @return The new deadline of the task
     */
    private LocalDateTime getNewDeadline(LocalDateTime deadline) {
        if (!isRecurring()) {
            return deadline;
        }

        assert recurFrequency != null : "Deadline detects an error when resetting deadline!";

        LocalTime time = deadline.toLocalTime();
        LocalDate newDate = getNewDeadlineDate(deadline);
        LocalDateTime newDeadline = newDate.atTime(time);

        if (newDeadline.isAfter(deadline)) {
            return newDeadline;
        } else {
            return deadline;
        }
    }

    /**
     * Returns the new date of the deadline given the old deadline.
     *
     * @param by The old deadline
     * @return The LocalDate of the new deadline
     */
    private LocalDate getNewDeadlineDate(LocalDateTime by) {
        LocalDate today = LocalDate.now();
        LocalDate newDate = by.toLocalDate();
        switch (recurFrequency) {
        case DAILY:
            newDate = today;
            break;
        case WEEKLY:
            DayOfWeek dayOfWeek = by.getDayOfWeek();
            newDate = today.with(next(dayOfWeek));
            break;
        case BIWEEKLY:
            dayOfWeek = by.getDayOfWeek();
            newDate = today.with(next(dayOfWeek)).with(next(dayOfWeek));
            break;
        case MONTHLY:
            int dayOfMonth = by.getDayOfMonth();
            try {
                newDate = today.withDayOfMonth(dayOfMonth);
            } catch (DateTimeException e) {
                newDate = today.with(TemporalAdjusters.lastDayOfMonth());
            }
            break;
        default:
            assert false; //recurFrequency can only take the enumerated forms, or null
        }
        return newDate;
    }

    /**
     * Writes the data of the task to a text file.
     *
     * @param fw The file writer in charge of writing to file
     * @throws IOException if there is an error writing the file
     */
    public void saveTask(FileWriter fw) throws IOException {
        fw.write(String.format("D %s %s %s %s /by %s\n", isDone, recurFrequency, lastResetDate, description,
                deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
    }

    /**
     * Returns the string format of the task.
     *
     * @return The string format of the task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
