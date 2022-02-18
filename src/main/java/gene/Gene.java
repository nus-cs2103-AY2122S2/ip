package gene;

import gene.command.Command;
import gene.component.LocationList;
import gene.component.LocationStorage;
import gene.component.Parser;
import gene.component.TaskList;
import gene.component.TaskStorage;
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
    private final TaskStorage geneTaskStorage;
    private final LocationStorage geneLocationStorage;
    private final TaskList geneTasks;
    private final LocationList geneLocs;
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
        this.geneTaskStorage = new TaskStorage("geneTasks.txt");
        this.geneLocationStorage = new LocationStorage("geneLocs.txt");
        this.geneTasks = new TaskList(geneTaskStorage);
        this.geneLocs = new LocationList(geneLocationStorage);
    }

    /**
     * The constructor for the Gene class
     * @param taskFilePath the name of the file to be initiated to store user input
     * @param locsFilePath the name of the location file to be initiated to store user input
     */
    public Gene(String taskFilePath, String locsFilePath) {
        this.geneUi = new Ui();
        this.geneTaskStorage = new TaskStorage(taskFilePath);
        this.geneLocationStorage = new LocationStorage(locsFilePath);
        this.geneTasks = new TaskList(geneTaskStorage);
        this.geneLocs = new LocationList(geneLocationStorage);
    }

    /**
     * This method is the main method that runs the entire program,
     * it initializes a file,
     * then takes in user input from the system.
     * Finally, when an exit command is given, the program will be exited.
     * todo extract greeting in order to print upon GUI startup
     */
    void run() {
        geneTasks.initFile();
    }

    void initFile() {
        geneTasks.initFile();
        geneLocs.initFile();
        System.out.println("File inilization process started");
    }

    String handleUserInput(String nextLine) {
        try {
            Command currCommand = Parser.parseCommand(nextLine);
            String toReturn = currCommand.execute(geneTasks, geneUi, geneTaskStorage, geneLocs, geneLocationStorage);
            System.out.println(toReturn);
            return toReturn;
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
        Gene toRun = new Gene("geneTasks.txt", "geneLocs.txt");
        toRun.run();
    }
}
