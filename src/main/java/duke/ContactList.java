package duke;

import java.util.ArrayList;

/**
 * Contact list class
 */
public class ContactList {

    private ArrayList<Contact> contacts;

    /**
     * Construct contact list.
    `*/
    public ContactList(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Construct contact list.
     */
    public ContactList() {
        this(new ArrayList<Contact>());
    }

    /**
     * Add contact to list.
     *
     * @param contact Contact to add.
     */
    public void add(Contact contact) {
        contacts.add(contact);
    }

    /**
     * Delete contact with specified id.
     *
     * @param contactNumber Contact id.
     */
    public void delete(int contactNumber) {
        contacts.remove(contactNumber);
    }

    /**
     * Returns contact list.
     *
     * @return String representation of contact list.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Contact contact : contacts) {
            builder.append(contact.toString()).append("\n");
        }
        return builder.toString();
    }

}
