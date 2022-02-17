package duke.Exceptions;

public class MissingEnquiryDateException extends DukeException {
    /**
     * Throws exception when there is a missing enquiry period for stats.
     * @param exceptionMessage Exception message.
     */
    public MissingEnquiryDateException(String exceptionMessage) {
        super("Oops! Please key in a valid enquiry period\n"
                + "    such as 'stats today', 'stats this week', 'stats this month' or \n"
                + "    'stats 3' to enquire completed tasks for the past 3 days.");
    }
}
