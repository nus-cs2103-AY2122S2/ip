# The all new Bob!

Bob is a personal desktop task manager.

# User Guide

## Quick Start

1. Ensure that you have Java 11 installed on your computer.
2. Download the latest `bob.jar` from [here](https://github.com/justinekoh/ip/releases/download/v1.2/bob.jar).
3. Copy the file to a folder that you want to use as the _home folder_ for Bob.
4. Double-click the file to start the app.
5. Type the command in the command box and press Enter or the `Send` button on the bottom right to execute it.
6. Type the "Commands" command to see a list of all available commands and what they do!
7. Refer to the [Features](https://github.com/justinekoh/ip/blob/master/docs/README.md#features) below for details of each command.

---

## Command summary

| Action    | Format                              |
|-----------|-------------------------------------|
| Commands  | `commands`                          |
| List      | `list`                              |
| Todo      | `todo [DESCRIPTION]`                |
| Event     | `event [DESCRIPTION] /at [TIME]`    |
| Deadline  | `deadline [DESCRIPTION] /by [TIME]` |
| Mark      | `mark [INDEX]`                      |
| Unmark    | `unmark [INDEX]`                    |
| Find      | `find [KEYWORD] `                   |
| Delete    | `delete [INDEX]`                    |
| Archive   | `archive`                           |

---

## Features

Note: all commands are **case insensitive**.

### Viewing all commands - `Commands`

Displays a list of all commands and what they do.  
Format: `Commands`

### Listing all entries - `List`

Displays a list of all entries currently in the task, in the order that they were added.

Format: `List`

### Adding a task to be done - `Todo`

Adds a task to the task list to be done.

Format: `todo [DESCRIPTION]`

Examples:

- `todo Buy groceries`
- `todo Read Pride and Prejudice`

### Adding a task to happen at a specified time - `Event`

Adds a task to the task list to happen at a specified time.

Format: `event [DESCRIPTION] /at [TIME]`

Examples:

- `event John's wedding /at 2022-03-12T18:00:00`
- `event CS2103T finals /at 2022=05-02T12:00:00`

### Adding a task with a deadline - `Deadline`

Adds a task to the task list to be done by a deadline.

Format: `deadline [DESCRIPTION] /by [TIME]`

Examples:

- `deadline Do CS2105 Assignment 1 /by 2022-02-25T23:59:59`
- `todo Finish CS2103T tutorial /by 2022-02027T13:00:00`

### Mark a task as done - `Mark`

Marks an existing task in the task list as done. Index provided should exist in the task list. The index must be a **
positive integer**.  
Marking an already marked entry is allowed.

Format: `mark [INDEX]`

Examples:

- `mark 1`

### Mark a task as not done - `Unmark`

Marks an existing task in the task list as not done. Index provided should exist in the task list. The index must be
a **positive integer**.  
Unmarking an already unmarked entry is allowed.

Format: `unmark [INDEX]`

Examples:

- `unmark 3`

### Finding a task by description - `Find`

Searches the task list for entries with descriptions that match the given keyword.

Format: `find [KEYWORD]`

Examples:

- `find tutorial`

### Removing a task - `Delete`

Deletes the entry specified by given index from the task list. Index provided should exist in the task list. The index
must be a **positive integer**.  
The indices of all subsequent entries will be modified after deletion of an entry before it. For example, the index of
the 3rd entry will be 2 after deletion of the first entry.

Format: `delete [INDEX]`

Examples:

- `delete 2`

### Archiving the current task list - `Archive`

Saves all current entries in the task list in the file `bob_archive.txt` in the same directory that `bob.jar` is saved in.  
Removes all entries from the list afterwards.

Format: `archive`

--- 

## Acknowledgements

### Third party libraries

- JUnit
- JavaFX
