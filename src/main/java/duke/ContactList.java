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

    /**
     * Returns string representation of list for storage.
     *
     * @return Data of all contacts.
     */
    public String toDataString() {
        String string = "";
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            string += String.format("%s\n", contact.toDataString());
        }
        return string;
    }

}
