package duke;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a contact
 */
public class Contact {

    // Identity fields
    private Name name;
    private Telegram telegram;

    /**
     * Constructs contact.
     */
    public Contact(Name name, Telegram telegram) {
        this.name = name;
        this.telegram = telegram
    }

    public Name getName() {
        return name;
    }

    public Phone getTelegram() {
        return phone;
    }

    /**
     * Returns string representation of contact.
     *
     * Returns string containing contact details.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Telegram: ")
                .append(getTelegram());
        return builder.toString();
    }

}
