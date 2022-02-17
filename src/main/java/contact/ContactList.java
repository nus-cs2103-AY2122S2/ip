
package contact;

import java.util.ArrayList;
import java.util.List;

import util.CommandList;



public class ContactList {

    private List<Contact> contacts = new ArrayList<>();
    private final CommandList cl = new CommandList();

    /**
     * executes the command and returns a response
     * @param command command to be executed
     * @param description description of command
     * @return response of command executed
     */

    public String execute(String command, String description) {
        switch (command) {
        case "add":
            return processAdd(description);

        case "update":
            return processUpdate(description);

        case "delete":
            return processDelete(description);

        case "list":
            return processList();

        case "commandlist":
            return processCommandList();


        default:
            return "This command does not exists!\nUse 'commandlist' to list out the commands";

        }
    }

    private String processCommandList() {
        StringBuilder commands = new StringBuilder();
        for (String s: cl.getContactCommands()) {
            commands.append(s).append("\n");
        }
        return "Here are the list of commands:\n" + commands;
    }

    private String processAdd(String description) {
        try {
            String name = "";
            String[] strarr = description.split(" ");
            for (int i = 0; i < strarr.length - 1; i++) {
                name += strarr[i];
                if (i != strarr.length - 2) {
                    name += " ";
                }
            }
            String number = description.split(" ")[strarr.length - 1];
            assert !number.equals("");
            for (Contact c: contacts) {
                if (c.getName().equals(name)) {
                    return "Name already exists! Please use another name!";
                }
            }
            contacts.add(new Contact(name, number));
            return "Contact added: " + name;

        } catch (IndexOutOfBoundsException e) {
            return "Please input your number in the format 'add {name} {number}";
        }

    }


    private String processUpdate(String description) {
        try {
            String name = "";
            String[] strarr = description.split(" ");
            for (int i = 0; i < strarr.length - 1; i++) {
                name += strarr[i];
                if (i != strarr.length - 2) {
                    name += " ";
                }
            }
            String number = description.split(" ")[strarr.length - 1];
            assert !number.equals("");
            for (Contact c: contacts) {
                if (c.getName().equals(name)) {
                    c.updateContactNumber(number);
                    return "Contact updated: " + name;
                }
            }
            return "Contact not found!";

        } catch (IndexOutOfBoundsException e) {
            return "Please input your number in the format 'update {name} {number}";
        }

    }

    private String processDelete(String description) {
        try {
            assert !description.equals("");
            int index = Integer.parseInt(description);
            Contact c = contacts.get(index - 1);
            contacts.remove(index - 1);
            return "Contact " + c.getName() + " removed!";
        } catch (IndexOutOfBoundsException e) {
            return "You can't do that! It's not on the contacts!";
        } catch (NumberFormatException e) {
            return "Please input the index of the contact that you want to delete!";
        }
    }

    private String processList() {
        StringBuilder lists = new StringBuilder();
        for (int i = 0; i < contacts.size(); i++) {
            if (i != 0) {
                lists.append("\n");
            }
            lists.append(String.format("%d. %s", i + 1, contacts.get(i).toString()));

        }
        return "Here are the list of your contacts:\n" + lists;
    }

    public List<Contact> getContactList() {
        return contacts;
    }
}
