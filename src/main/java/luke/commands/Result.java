package luke.commands;

/**
 * API of Result component, called by GUI to get result of command and check if program has ended.
 */
public interface Result {

    String getResult();

    boolean isExit();
}
