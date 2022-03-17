package duke.controller;

import duke.ui.Markdown;
import duke.ui.Tag;
import duke.ui.ValidText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for parsing the mark down component
 */
public class MarkdownParser implements Parser<List<Markdown>> {
    private String string;

    public MarkdownParser(String string) {
        this.string = string;
    }

    /**
     * Check whether the command string contains tag
     * @param str
     * @return
     */
    private boolean hasTag(String str) {
        return str.contains(Tag.START_PATTERN) && str.contains(Tag.END_PATTERN);
    }

    /**
     * Parser for mark down command
     * @return list of mark down attributes
     */
    @Override
    public List<Markdown> parse()  {
        List<Markdown> result = new ArrayList<>();
        String parsedStr = this.string;
        while (hasTag(parsedStr)) {
            int tagStartIdx = parsedStr.indexOf(Tag.START_PATTERN);
            int startIdx = tagStartIdx + Tag.START_PATTERN.length();
            int endIdx = parsedStr.indexOf(Tag.END_PATTERN);
            String text = parsedStr.substring(0, tagStartIdx);
            String tag = parsedStr.substring(startIdx, endIdx);
            if (!Objects.equals(text, "")) {
                result.add(new ValidText(text));
            }
            result.add(new Tag(tag));
            parsedStr = parsedStr.substring(endIdx + Tag.END_PATTERN.length());
        }

        if (!Objects.equals(parsedStr, "")) {
            result.add(new ValidText(parsedStr));
        }
        return result;
    }


}
