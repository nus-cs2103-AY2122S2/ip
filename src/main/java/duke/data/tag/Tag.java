package duke.data.tag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.data.exception.IllegalValueException;

/**
 * Represents a tag in the task list.
 * Adapted from AB3 Tag
 */
public class Tag {

    private final String tagValidationRegex = "^[a-zA-Z0-9 ]+$";
    private final String tagInvalidMessage = "Tag names should be alphanumeric";

    private final String tagName;


    /**
     * Constructs a tag.
     *
     * @param tagName name of the tag.
     * @throws IllegalValueException if the name given is invalid.
     */
    public Tag(String tagName) throws IllegalValueException {
        String trimmedTagName = tagName.trim();
        if (!isValidTag(trimmedTagName)) {
            throw new IllegalValueException(tagInvalidMessage);
        }
        this.tagName = trimmedTagName;
    }

    /**
     * Checks if the given name is valid.
     *
     * @param tagName tag name to be validated.
     * @return whether the tag is valid.
     */
    public boolean isValidTag(String tagName) {
        Pattern pattern = Pattern.compile(tagValidationRegex);
        if (tagName == null) {
            return false;
        }
        Matcher m = pattern.matcher(tagName);
        return m.matches();
    }

    /**
     * Checks if the other tag is equal to this tag, ignores case
     *
     * @param other Tag to be compared.
     * @return whether this tag equals the other tag.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Tag
                && this.tagName.equalsIgnoreCase(((Tag) other).tagName));
    }

    @Override
    public String toString() {
        return this.tagName;
    }
}
