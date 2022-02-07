package chatbot.contact;

import chatbot.datetime.Timestamp;

/**
 * The type Contact.
 */
public class Contact implements Comparable<Contact> {
    private final String name;
    private final String phoneNumber;
    private final Timestamp birthday;

    /**
     * Instantiates a new Contact.
     *
     * @param name The contact's name.
     * @param phoneNumber The contact's phone number.
     * @param birthday The contact's birthday.
     */
    public Contact(String name, String phoneNumber, Timestamp birthday) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    /**
     * Gets the contact's name.
     *
     * @return Name of contact.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, phoneNumber);
    }

    @Override
    public int compareTo(Contact other) {
        return name.compareTo(other.getName());
    }

}
