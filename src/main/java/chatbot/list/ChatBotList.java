package chatbot.list;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import chatbot.exception.ChatBotException;

/**
 * The type Chat bot list.
 *
 * @param <T> the type parameter
 */
public abstract class ChatBotList<T> {

    private final List<T> list;

    /**
     * Instantiates a new Chat bot list.
     */
    public ChatBotList() {
        this.list = new ArrayList<>();
    }

    /**
     * Instantiates a new Chat bot list.
     *
     * @param list the list
     */
    public ChatBotList(List<T> list) {
        this.list = list;
    }

    /**
     * Insert.
     *
     * @param t the t
     */
    public void insert(T t) {
        list.add(t);
    }

    /**
     * Remove t.
     *
     * @param index the index
     * @return the t
     */
    public T remove(int index) {
        return list.remove(index);
    }

    /**
     * Get t.
     *
     * @param index the index
     * @return the t
     */
    public T get(int index) {
        return list.get(index);
    }

    /**
     * Gets num items.
     *
     * @return the num items
     */
    public int getNumItems() {
        return list.size();
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Is valid index boolean.
     *
     * @param index the index
     * @return the boolean
     */
    public boolean isInvalidIndex(int index) {
        return index < 0 || index > list.size();
    }

    /**
     * Filter list.
     *
     * @param condition the condition
     * @return the list
     */
    public List<T> filter(Predicate<T> condition) {
        return list
                .stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    public abstract void save(File saveFile) throws ChatBotException;

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
