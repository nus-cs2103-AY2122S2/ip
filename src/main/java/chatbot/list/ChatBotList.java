package chatbot.list;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import chatbot.exception.ChatBotException;


/**
 * A generic wrapper for lists used by ChatBot.
 *
 * @param <T> The type parameter.
 */
public abstract class ChatBotList<T> {

    private final String type;
    private final List<T> list;

    /**
     * Instantiates a new ChatBot list.
     */
    public ChatBotList(String type) {
        this.type = type;
        this.list = new ArrayList<>();
    }

    /**
     * Instantiates a new ChatBot list given an existing list of items.
     *
     * @param list The list of items.
     */
    public ChatBotList(String type, List<T> list) {
        this.type = type;
        this.list = list;
    }

    /**
     * Inserts an item into the list.
     *
     * @param t The item to insert.
     */
    public void insert(T t) throws ChatBotException {
        assert !list.contains(t) : "List should not already contain the item being inserted";
        if (list.contains(t)) {
            throw new ChatBotException(String.format("This %s is already in your %s list traveller!", type, type));
        }
        list.add(t);
    }

    /**
     * Deletes an item from the list.
     *
     * @param index The index of the task to be deleted.
     * @return The response to be outputted by the UI.
     * @throws ChatBotException If the task index is invalid.
     */
    public String delete(int index) throws ChatBotException {
        if (isInvalidIndex(index)) {
            throw new ChatBotException(
                    "This is an invalid task index traveller! You can type list to check all task indexes!"
            );
        }

        T removed = list.remove(index);
        return String.format(
                "This %s has successfully been removed from your %s list!%n             %d. %s",
                type,
                type,
                index + 1,
                removed
        );
    }

    /**
     * Gets an item from the list.
     *
     * @param index The index of the desired item.
     * @return The desired item.
     */
    public T get(int index) {
        return list.get(index);
    }

    /**
     * Gets the size of the list.
     *
     * @return The number of items in the list.
     */
    public int getNumItems() {
        return list.size();
    }

    /**
     * Gets the type of the list.
     *
     * @return "task" if TaskList, else "contact" if ContactList.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets whether the list is empty or not.
     *
     * @return True if list is empty, else false.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Gets the list of items.
     *
     * @return The list.
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Prints all items in the list.
     *
     * @return The string format of the list to be outputted by the UI.
     */
    public String summary() {
        if (isEmpty()) {
            return String.format("Your %s list is empty traveller! Add some %ss first!", type, type);
        }
        return toString();
    }

    /**
     * Checks if the input index is a valid one or not.
     *
     * @param index The index to verify.
     * @return True if the index is invalid, else false.
     */
    public boolean isInvalidIndex(int index) {
        assert index >= 0 && index < list.size() : "valid index should be nonnegative and smaller than list size";
        return index < 0 || index >= list.size();
    }

    /**
     * Filters the list based on a given condition.
     *
     * @param condition The filter condition.
     * @return The filtered list.
     */
    public List<T> filter(Predicate<T> condition) {
        return list
                .stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    /**
     * Saves items in the list to the save file.
     *
     * @param saveFile The save file.
     * @throws ChatBotException If I/O error occurs while writing to the save file.
     */
    public abstract void save(File saveFile) throws ChatBotException;

    /**
     * Loads the items saved in the save file into the list.
     *
     * @param saveFile The save file.
     * @throws ChatBotException If the save file cannot be found, or the data in it is formatted incorrectly.
     */
    public abstract void load(File saveFile) throws ChatBotException;

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            String item = String.format(
                    "%d. %s%s",
                    i + 1,
                    list.get(i),
                    i < list.size() - 1 ? "\n" : ""
            );
            res = res.concat(item);
        }
        return res;
    }

}
