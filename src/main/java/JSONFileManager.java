import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONFileManager {
    static private final String BASE_PATH = System.getProperty("user.dir");
    static private final Path SAVE_FOLDER_PATH = Paths.get(BASE_PATH, "src", "main", "data");
    static private final String FILE_NAME = "tasks.json";
    static private final Path SAVE_FILE_PATH = SAVE_FOLDER_PATH.resolve(FILE_NAME);

    static private boolean isExistsSaveFolder() {
        return Files.exists(SAVE_FOLDER_PATH);
    }

    static private void createSaveFolder() {
        try {
            Files.createDirectories(SAVE_FOLDER_PATH);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static private boolean isExistsSaveFile() {
        return Files.exists(SAVE_FILE_PATH);
    }

    static private void createSaveFile() {
        try {
            Files.createFile(SAVE_FILE_PATH);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static public void setUpSaveSystem() {
        if (!isExistsSaveFolder()) {
            createSaveFolder();
        }

        if (!isExistsSaveFile()) {
            createSaveFile();
        }
    }

    static public String readSavedString() {
        try {
            String savedString = Files.readString(SAVE_FILE_PATH);
            return savedString;
        } catch (IOException e) {
            System.out.println(e);
            return "";
        }
    }

    static public void writeSavedString(String savedString) {
        try {
            Files.writeString(SAVE_FILE_PATH, savedString);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static public String parseListToJSONString(WordList wordList) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonWrapper = new JSONObject();

        wordList.forEach(wordListItem -> {
            JSONObject itemJSON = new JSONObject();
            String symbol = wordListItem instanceof Todo
                    ? Todo.getSymbol()
                    : wordListItem instanceof Deadline
                        ? Deadline.getSymbol()
                        : wordListItem instanceof Event
                            ? Event.getSymbol()
                            : null;
            itemJSON.put("symbol", symbol);
            itemJSON.put("description", wordListItem.getDescription());
            itemJSON.put("isDone", wordListItem.getIsDone());
//            System.out.println(itemJSON);
            if (wordListItem instanceof Deadline) {
                Deadline deadline = (Deadline) wordListItem;
                itemJSON.put("datetime", deadline.getDatetime());
            }
            if (wordListItem instanceof  Event) {
                Event deadline = (Event) wordListItem;
                itemJSON.put("datetime", deadline.getDatetime());
            }
            jsonArray.put(itemJSON);
        });

        return jsonArray.toString();
    }

    static public WordList parseJSONStringToList(String jsonString) {
        JSONArray jsonArray = new JSONArray(jsonString);
        WordList wordList = new WordList();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String symbol = jsonObject.getString("symbol");
            if (symbol.equals(Todo.getSymbol())) {
                String description = jsonObject.getString("description");
                boolean isDone = jsonObject.getBoolean("isDone");
                wordList.storeTodo(description, isDone,false);
            } else if (symbol.equals(Deadline.getSymbol())) {
                String description = jsonObject.getString("description");
                boolean isDone = jsonObject.getBoolean("isDone");
                String datetime = jsonObject.getString("datetime");
                wordList.storeDeadline(description, datetime, isDone,false);
            } else if (symbol.equals(Event.getSymbol())) {
                String description = jsonObject.getString("description");
                boolean isDone = jsonObject.getBoolean("isDone");
                String datetime = jsonObject.getString("datetime");
                wordList.storeEvent(description, datetime, isDone,false);
            }
        }
        return wordList;
    }

    static public WordList loadListFromJSONFile() {
        return parseJSONStringToList(readSavedString());
    }

    static public void saveListToJSONFile(WordList wordList) {
        writeSavedString(parseListToJSONString(wordList));
    }
}
