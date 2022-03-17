package duke.controller;

import duke.exception.DukeException;

/**
 * Parser that parses information into type T.
 */
public interface Parser<T> {
    T parse() throws DukeException;
}