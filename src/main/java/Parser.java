import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static Command parse(String fullCommand) {
        String[] tokens = fullCommand.split(" ", 2);
        CommandType commandType = CommandType.fromString(tokens[0]);
        Map<String, String> paramMap = new HashMap<>();

        for (String param : commandType.getParams()) {
            String regex = "(?<=/" + param + "\\s)([^/].*?)(?=\\s*/|$)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(fullCommand);

            if (matcher.find()) {
                String arg = matcher.group();
                paramMap.put(param, arg);
            } else {
                throw new DukeException("Missing parameter: " + param);
            }
        }

        return new Command(commandType, paramMap);
    }
}

