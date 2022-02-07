package chatbot.contact;

import chatbot.datetime.Timestamp;
import chatbot.exception.ChatBotException;

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
    public Contact(String name, String phoneNumber, Timestamp birthday) throws ChatBotException {
        this.name = name;
        this.birthday = birthday;

        if (phoneNumber.length() != 9 || !isNumeric(phoneNumber)) {
            throw new ChatBotException("Phone number should be a 9 digit number traveller!");
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the contact's name.
     *
     * @return Name of contact.
     */
    public String getName() {
        return name;
    }

    private static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
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
