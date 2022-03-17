package duke.command;

/**
 * enum for type of command
 */
public enum Command {

    TODO("^(t|T)(o|O)(d|D)(o|O)$"),
    DEADLINE("^(d|D)(e|E)(a|A)(d|D)(l|L)(i|I)(n|N)(e|E)$"),
    EVENT("^(e|E)(v|V)(e|E)(n|N)(t|T)$"),
    DELETE("^(d|D)(e|E)(l|L)(e|E)(t|T)(e|E)$"),
    FIND("^(f|F)(i|I)(n|N)(d|D)$"),
    EXIT("^(b|B)(y|Y)(e|E)$"),
    LIST("^(l|L)(i|I)?(s|S)(t|T)?$"),
    DONE("^(d|D)(o|O)(n|N)(e|E)$"),
    HELP("^(h|H)(e|E)(l|L)(p|P)$");

    public String type;

    Command(String type) {
        this.type = type;
    }

    public boolean isMatchPattern(String input) {
        return input.matches(type);
    }
}

