package bernie.enums;

/**
 * Different Type of tasks: notable types are EMPTY, when the user inputs nothing: "".
 * LIST, BYE, EMPTY are inputs that won't result in creation of tasks.
 * MARK, UNMARK, DELETE are action tasks that changes the state of the tasks.
 * ADD: refers to when Type is to be added are: TODO, DEADLINE, EVENT.
 * They are the 3 different types of tasks that can be created.
 */
public enum Type {
    LIST,
    BYE,
    EMPTY,
    ADD,
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    DELETE,
    FIND
}
