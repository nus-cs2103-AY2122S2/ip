package duke.data.tag;

import duke.data.exception.IllegalValueException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a tag in the task list.
 */
public class Tag {

    private final String TAG_VALIDATION_REGEX = "^[a-zA-Z0-9 ]+$";
    private final String TAG_INVALID_MESSAGE = "Tag names should be alphanumeric";

    public final String tagName;


    public Tag(String tagName) throws IllegalValueException{
        String trimmedTagName = tagName.trim();
        if (!isValidTag(trimmedTagName)) {
            throw new IllegalValueException(TAG_INVALID_MESSAGE);
        }
        this.tagName = trimmedTagName;
    }

    public boolean isValidTag(String tagName) {
        Pattern pattern = Pattern.compile(TAG_VALIDATION_REGEX);
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
