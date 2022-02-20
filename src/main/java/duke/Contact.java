package duke;

/**
 * Represents a Contact that is created by the user and stored in DukeLCH.
 */
public class Contact {

    private String name;
    private int contactNumber;

    /**
     * Constructor used to instantiate a Contact object.
     * @param name Name of the Contact.
     * @param contactNumber Contact Number associated with the Object.
     */
    public Contact(String name, int contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    /**
     * Returns the String representation of the Contact class for users to read.
     * @return Contact information for user to read.
     */
    public String getContact() {
        return this.name + " [" + this.contactNumber + "]\n";
    }
}
