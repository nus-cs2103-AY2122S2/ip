package chatbot.list;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import chatbot.contact.Contact;
import chatbot.datetime.Timestamp;
import chatbot.exception.ChatBotException;


/**
 * Represents a list of Contacts managed by ChatBot for the user.
 */
public class ContactList extends ChatBotList<Contact> {

    /**
     * Instantiates a new Contact list.
     */
    public ContactList() {
        super("contact");
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
            String name = args[1];
            String phoneNumber = args[2];
            String birthdayString = args[3];
            Timestamp birthday = new Timestamp(birthdayString);
            Contact contact = new Contact(name, phoneNumber, birthday);
            insert(contact);
            return String.format(
                    "This contact has been added to your contact list!%n             %d. %s",
                    getNumItems(),
                    contact
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatBotException(
                    "The correct format for adding a contact is contact <name> <phone number> <birthday>"
            );
        }
    }

    @Override
    public void load(File saveFile) throws ChatBotException {
        try (Scanner sc = new Scanner(saveFile)) {
            while (sc.hasNext()) {
                String[] data = sc.nextLine().split("&");
                String name = data[0];
                String phoneNumber = data[1];
                Timestamp birthday = new Timestamp(data[2]);
                insert(new Contact(name, phoneNumber, birthday));
            }
        } catch (FileNotFoundException e) {
            throw new ChatBotException(
                    "I couldn't find your save file traveller! "
                            + "You can create it in the data directory and name it data.txt."
            );
        } catch (ChatBotException e) {
            throw new ChatBotException(
                    "One of your saved tasks was formatted incorrectly traveller! I've removed it from your list."
            );
        }
    }

    @Override
    public void save(File saveFile) throws ChatBotException {
        List<Contact> list = getList();
        try (FileWriter fw = new FileWriter(saveFile)) {
            for (Contact c : list) {
                String name = c.getName();
                String phoneNumber = c.getPhoneNumber();
                Timestamp birthday = c.getBirthday();

                String data = String.format("%s&%s&%s", name, phoneNumber, birthday.toSaveString());
                fw.write(data);
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new ChatBotException(
                    "Oops! Something went wrong while writing to your save file traveller!"
            );
        }
    }

}
