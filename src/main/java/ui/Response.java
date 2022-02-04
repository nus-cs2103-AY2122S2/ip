package ui;

import java.util.ArrayList;

/**
 * A response class that is responsible for managing all the
 * response messages.
 */
public class Response {
    protected ArrayList<String> responseList = new ArrayList<>();

    /**
     * Default constructor.
     *
     * @param responses Varags to cater for a varying number of response
     */
    public Response (String... responses) {
        for (String response : responses) {
            responseList.add(response);
        }
    }

    /**
     * Second constructor for created responses.
     *
     * @param responses ArrayList format of response
     */
    public Response (ArrayList<String> responses) {
        this.responseList = responses;
    }

    /**
     * Get bot's response.
     *
     * @return Bot's response
     */
    public ArrayList<String> getResponseList() {
        return this.responseList;
    }
}
