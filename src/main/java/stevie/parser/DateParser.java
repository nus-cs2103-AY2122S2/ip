package stevie.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import stevie.exception.ParserException;
import stevie.exception.messages.ParserExceptionMessages;

public class DateParser {
    /**
     * Parses a string input into a date object.
     *
     * @param input string input to be parsed
     * @return date object
     * @throws ParserException if input is unable to be parsed into date object due to error
     *                         in formatting
     */
    public static Date parse(String input) throws ParserException {
        Date date;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(input);
        } catch (ParseException ex) {
            throw new ParserException(ParserExceptionMessages.DateParseError);
        }
        return date;
    }
}
