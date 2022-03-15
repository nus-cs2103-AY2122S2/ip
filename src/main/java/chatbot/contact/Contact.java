package chatbot.contact;

import chatbot.datetime.Timestamp;
import chatbot.exception.ChatBotException;


/**
 * Represents a contact of the user.
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

        assert isNumeric(phoneNumber) : "Phone number should be a number";
        assert phoneNumber.length() == 8 : "Phone number should have 8 digits";
        if (phoneNumber.length() != 8 || !isNumeric(phoneNumber)) {
            throw new ChatBotException("Phone number should be a 8 digit number traveller!");
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

    /**
     * Gets the contact's phone number.
     *
     * @return Phone number of contact.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the contact's birthday.
     *
     * @return Birthday of contact.
     */
    public Timestamp getBirthday() {
        return birthday;
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
        return String.format("%s, %s (birthday: %s)", name, phoneNumber, birthday);
    }

    @Override
    public int compareTo(Contact other) {
        return name.compareTo(other.getName());
    }

}
