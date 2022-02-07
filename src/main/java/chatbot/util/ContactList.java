package chatbot.util;

import java.util.ArrayList;
import java.util.List;

import chatbot.contact.Contact;
import chatbot.datetime.Timestamp;
import chatbot.exception.ChatBotException;


public class ContactList {

    private final List<Contact> list;

    public ContactList() {
        this.list = new ArrayList<>();
    }

    /**
     * Add a contact to the contact list.
     *
     * @param args The arguments parsed by the Parser.
     * @return The response to be outputted by the UI.
     * @throws ChatBotException If the arguments are formatted incorrectly.
     */
    public String add(String[] args) throws ChatBotException {
        try {
            String name = args[0];
            String phoneNumber = args[1];
            String birthdayString = args[2];
            Timestamp birthday = new Timestamp(birthdayString);
            list.add(new Contact(name, phoneNumber, birthday));
            return "Contact added!";
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatBotException(
                    "The correct format for adding a contact is contact <name> <phone number> <birthday>"
            );
        }
    }

    /**
     * Delete a contact from the contact list.
     *
     * @param index The index of the contact to be deleted.
     * @return The response to be outputted by the UI.
     */
    public String delete(int index) {
        if (list.isEmpty()) {
            return "Your contact list is empty traveller! Add some contacts first";
        }
        list.remove(index);
        return "This contact was successfully deleted";
    }
}
