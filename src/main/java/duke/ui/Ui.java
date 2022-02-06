package duke.ui;

import javafx.scene.image.Image;

/**
 * Ui is a utility class for reading and writing input to the Duke program.
 * <p/>
 * Ui is a wrapper around BufferedReader and PrintWriter that reads from System.in InputStream
 * and writes to System.out OutputStream.
 * </p>
 * An Ui instance also stores the message to be shown before showResponse flushes the message to the outputsteam.
 */
public class Ui {
    public static final String BYE_MESSAGE = "Roarrr....Let's burn more tasks next time!";
    public static final String ADD_MESSAGE = "Charizard is ready to burn task:";
    public static final String REMOVE_MESSAGE = "Charizard got tired of waiting and deleted this task";
    public static final String MARK_MESSAGE = "Charizard breathe out fire and burned the task.";
    public static final String UNMARK_MESSAGE = "Oh no! The task was not burnt completely!";
    public static final Image GENERAL_IMAGE = new Image(Ui.class.getResourceAsStream("/images/General.png"));

    private static final int BORDER_LENGTH = 38;
    private static final String GREET_MESSAGE = "Roarrr.... I'm Burning Charizard, tasked to burnnn down your tasks.\n"
            + "Which task shall we burn today?";

    private static final Image ADD_OR_DELETE_IMAGE = new Image(Ui.class.getResourceAsStream("/images/Add.png"));
    private static final Image MARK_IMAGE = new Image(Ui.class.getResourceAsStream("/images/Mark.png"));
    private static final Image UNMARK_IMAGE = new Image(Ui.class.getResourceAsStream("/images/Unmark.PNG"));
    private static final Image ERROR_IMAGE = new Image(Ui.class.getResourceAsStream("/images/Error.PNG"));
    private Image responseImage = GENERAL_IMAGE;
    private StringBuilder message;

    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
        message = new StringBuilder();
    }

    /**
     * Appends the message to the stored message in Ui.
     *
     * @param message The message to be appended.
     */
    public void appendMessage(String message) {
        this.message.append(message);
    }

    /**
     * Appends the line border to the stored message in Ui.
     */
    public void appendBorder() {
        appendMessage("\n");
        for (int i = 0; i < BORDER_LENGTH; i++) {
            appendMessage("-");
        }
        appendMessage("\n");
    }

    public void setRespondImage(ImageType imageType) {
        if (imageType == ImageType.ADD_OR_DELETE) {
            responseImage = ADD_OR_DELETE_IMAGE;
        } else if (imageType == ImageType.MARK) {
            responseImage = MARK_IMAGE;
        } else if (imageType == ImageType.UNMARK) {
            responseImage = UNMARK_IMAGE;
        } else if (imageType == ImageType.ERROR) {
            responseImage = ERROR_IMAGE;
        } else {
            responseImage = GENERAL_IMAGE;
        }
    }

    public void clear() {
        message.setLength(0);
    }

    public boolean hasEmptyMessage() {
        return message.length() <= 0;
    }

    /**
     * Flushes the stored message in the Ui and displays the message to the user.
     */
    public String getResponse() {
        assert message.length() > 0 : "Response is empty!";
        String respondMessage = message.toString();
        message.setLength(0);
        assert message.length() == 0 : "Message buffer not cleared";
        return respondMessage;
    }

    public Image getResponseImage() {
        return responseImage;
    }

    public Image getErrorImage() {
        return ERROR_IMAGE;
    }

    /**
     * Immediately displays the welcome message to the user.
     */
    public String getWelcomeMessage() {
        return GREET_MESSAGE;
    }
}
