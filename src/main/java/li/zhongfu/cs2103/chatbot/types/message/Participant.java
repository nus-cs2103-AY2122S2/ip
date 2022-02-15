package li.zhongfu.cs2103.chatbot.types.message;

/**
 * A class representing a participant in a multi-party chat.
 */
public class Participant {
    private static Participant duke;
    private static Participant user;

    private String name;

    /**
     * Creates a new instance representing a participant in a conversation.
     *
     * @param name the name of the participant
     */
    public Participant(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this participant.
     *
     * @return the name of this participant
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a {@code Participant} instance representing the Duke chatbot.
     *
     * @return a {@code Participant} instance representing the Duke chatbot
     */
    public static Participant getDukeParticipant() {
        if (Participant.duke == null) {
            Participant.duke = new Participant("Duke");
        }

        return Participant.duke;
    }

    /**
     * Returns a {@code Participant} instance representing the user.
     *
     * @return a {@code Participant} instance representing the user
     */
    public static Participant getUserParticipant() {
        if (Participant.user == null) {
            Participant.user = new Participant("User");
        }

        return Participant.user;
    }
}
