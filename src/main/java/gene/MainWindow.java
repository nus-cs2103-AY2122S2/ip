package gene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String GREETING = "Hello! I'm \n";
    private static final String LOGO = "  GGGG                      \n"
            + "G         G     eeee      n  nnn       eeee \n"
            + "G               e        e   nn        n  e         e\n"
            + "G     GGG   eeeeee   n           n  eeeeee\n"
            + "G           G  e             n           n  e     \n"
            + "G          G    e      e    n            n  e        e\n"
            + "  GGGG        eeee     n            n    eeee ";
    private static final String HELP = "\nType \"help\" for the list of commands available.";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Gene gene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/EvilGene.png"));
    private Image geneImage = new Image(this.getClass().getResourceAsStream("/images/Gene.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getGeneDialog(GREETING + "\n" + LOGO + "\n" + HELP, geneImage));
    }

    public void setGene(Gene g) {
        gene = g; //sets g. here this is the connection to main logic
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        //In
        String input = userInput.getText();

        /* Parse and Out*/
        String response = gene.handleUserInput(input);

        /* front end */
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGeneDialog(response, geneImage)
        );

        userInput.clear();
    }
}
