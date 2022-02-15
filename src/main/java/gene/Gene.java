package gene;
import java.util.Scanner;

import gene.command.Command;
import gene.component.Parser;
import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;
import gene.exception.UnrecognizedCommandException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * The Gene program is as of right now a simple program that tracks
 * to do, event and deadline tasks. Crud actions are supported.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class Gene {
    private final Ui geneUi;
    private final Storage geneStorage;
    private final TaskList geneList;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/EvilGene.png"));
    private final Image gene = new Image(this.getClass().getResourceAsStream("/images/Gene.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Suppressed
     */
    public Gene() {
        this.geneUi = new Ui();
        this.geneStorage = new Storage("gene.txt");
        this.geneList = new TaskList(geneStorage);
    }

    /**
     * The constructor for the Gene class
     * @param filePath the name of the file to be initiated to store user input
     */
    public Gene(String filePath) {
        this.geneUi = new Ui();
        this.geneStorage = new Storage(filePath);
        this.geneList = new TaskList(geneStorage);
    }

    /**
     * This method is the main method that runs the entire program,
     * it initializes a file,
     * then takes in user input from the system.
     * Finally, when an exit command is given, the program will be exited.
     * todo extract greeting in order to print upon GUI startup
     */
    void run() {
        geneList.initFile();
    }

    void initFile() {
        geneList.initFile();
        System.out.println("File inilization process started");
    }

    String handleUserInput(String nextLine) {
        try {
            Command currCommand = Parser.parseCommand(nextLine);
            return currCommand.execute(geneList, geneUi, geneStorage);
        } catch (UnrecognizedCommandException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * main method for Gene
     * @param args something
     */
    public static void main(String[] args) {
        Gene toRun = new Gene("gene.txt");
        toRun.run();
    }
}
