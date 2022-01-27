import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parser {

    protected BufferedReader br;
    protected boolean isTerminated = false;

    public Parser() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readInput() throws IOException {
        return this.br.readLine();
    }

    public void terminate() {
        this.isTerminated = true;
    }
}
