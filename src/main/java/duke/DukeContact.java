package duke;

import java.util.ArrayList;

public class DukeContact {
    private final ArrayList<Contact> records = new ArrayList<>(100);

    public DukeContact() {
    }

    /**
     * A method that, when called, builds a String containing all the contacts stored
     * in the current instance of DukeContact.
     * @return A String containing all the contacts stored in the current instance of DukeContact.
     */
    public String printAll() {
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (Contact nextContact : records) {
            result.append(count).append(". ").append(nextContact.getContact());
            count++;
        }
        if (result.length() == 0) {
            return "There has been no recorded contacts!\n";
        } else {
            return result.toString();
        }
    }

    /**
     * A method that adds a Contact to an instance of DukeContact.
     * @param name String representation of the Name of the Contact.
     * @param contactNumber Contact Number associated with the Contact.
     * @return The Contact object created.
     */
    public Contact addContact(String name, int contactNumber) {
        Contact tempContact = new Contact(name, contactNumber);
        records.add(tempContact);
        return tempContact;
    }

    /**
     * A method that deletes a specified Contact that exists in this instance of DukeContact.
     * @param index Index of the specified Contact.
     * @return The Contact object that was deleted.
     */
    public Contact deleteContact(int index) {
        Contact tempContact = records.remove(index);
        return tempContact;
    }

    public int getSize() {
        return records.size();
    }
}
