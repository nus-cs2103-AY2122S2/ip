package duke.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Quote {
    public ArrayList<String> alQuotes;

    /**
     * Contains quote list
     */
    public Quote(){
        alQuotes = new ArrayList<String>(Arrays.asList("Life is more than what is, its what it could be. What you could make it.\n",
                "It is our choices that show what we truly are for more than our abilities\n",
                "There comes a point in everybody’s life where we fell like giving up, its what we do at that point in time that defines who we are\n",
                "What’s coming will come, and we’ll meet it when it does.\n",
                "'And in the end,\n It is not the years in your life that count.\n It is the life in your years' - Abraham Lincoln",
                "What we need to recognise is that the things we truly want, the things that are deeply meaningful, \n" +
                        "the things that are genuinely fulfilling, all require patience, \n" +
                        "they all require work, they all require energy.\n",
                "So many of us choose our path out of fear disguised as practicality. \n" +
                        "What we really want seems impossibly out of reach and ridiculous to expect so we never dare to ask the universe for it. \n" +
                        "I am proof that you can. - Jim Carrey\n",
                "You can fail at what you don’t want, so you might as well take a chance doing what you love.\n" +  "- Jim Carrey",
                "“There is a reason God limits our days”\n" +
                        "“Why?”\n" +
                        "“To make each one precious”\n" +
                        "The Timekeeper, Mitch Albom"
        ));
    }

    /**
     * Generates random quote from a stored quote list
     * @return String of quote
     */
    public String generateQuote(){
        Random randGenerator = new Random();
        int randomNumber = randGenerator.nextInt(alQuotes.size());
        return "Heres your quote of the day! \n"+ alQuotes.get(randomNumber);
    }
}
