package lily.task;

import lily.LilyException;

/**
 * A Task which records the duration needed to complete it.
 * 
 * @author Hong Yi En, Ian
 * @version Feb 2022 (AY21/22 Sem 2)
 */
public class Job extends Task {
    private double duration;

    /**
     * Creates a Job object.
     * 
     * @param s The description of what is happening.
     * @param dur How long the task will take
     * @throws LilyException When the input is not a number
     */
    public Job(String s, String dur) throws LilyException {
        super(s);
        try {
            duration = Double.parseDouble(dur);
        } catch (NumberFormatException nfe) {
            throw new LilyException(LilyException.FORMAT_IDX);
        }
    }

    /**
     * Returns the Job as a String.
     * 
     * @return Deadline in the form of "[J][ ] Description (needs: dur)".
     */
    @Override
    public String toString() {
        return "[J]" + super.toString() + " (needs: " + duration + "h)";
    }
} 