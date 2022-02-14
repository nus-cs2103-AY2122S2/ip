package li.zhongfu.cs2103.chatbot.types.message;

public class Participant {
    private static Participant duke;
    private static Participant user;

    private String name;

    public Participant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Participant getDukeParticipant() {
        if (Participant.duke == null) {
            Participant.duke = new Participant("Duke");
        }

        return Participant.duke;
    }

    public static Participant getUserParticipant() {
        if (Participant.user == null) {
            Participant.user = new Participant("User");
        }

        return Participant.user;
    }
}
