package util;

import java.util.ArrayList;

public class InputParser {
    public static String getOperator(String userInput) {
        return userInput.split(" ")[0];
    }

    public static String getRemainingInput(String userInput, String operator) {
        if (userInput.length() > operator.length()) {
            return userInput.substring(operator.length() + 1);
        } else {
            return "";
        }
    }

    public static String[] parseTaskBody(String task) {
        ArrayList<String> parameters = new ArrayList<>(0);
        String[] parameterList = task.split(" ", -1);
        StringBuilder curParameter = new StringBuilder();
        for (String str : parameterList) {
            if (str.length() == 0) {
                curParameter.append(" ");
            } else if (str.charAt(0) == '/') {
                if (curParameter.length() > 0) {
                    parameters.add(curParameter.toString());
                    curParameter = new StringBuilder();
                }
                parameters.add(str.substring(1));
            } else {
                if (curParameter.length() > 0) {
                    curParameter.append(" ");
                }
                curParameter.append(str);
            }
        }
        if (curParameter.length() > 0) {
            parameters.add(curParameter.toString());
        }
        return parameters.toArray(new String[0]);
    }
}
