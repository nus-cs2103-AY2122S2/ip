package duke;

/**
 * Represents a contact
 */
public class Contact {

    // Identity fields
    private String name;
    private String telegram;

    /**
     * Constructs contact.
     */
    public Contact(String name, String telegram) {
        this.name = name;
        this.telegram = telegram;
    }

    public String getName() {
        return name;
    }

    public String getTelegram() {
        return telegram;
    }

    /**
     * Returns string representation of contact.
     *
     * Returns string containing contact details.
     */
    @Override
    public String toString() {
        return String.format("%s: %s", getName(), getTelegram());
    }

    /**
     * Returns string representation of a contact.
     * @return Contact as a string
     */
    public String toDataString() {
        return String.format("%s,%s", getName(), getTelegram());
    }

}
