# User Guide

## Quick Start

1. Ensure you have Java `11` installed on your computer.
2. Download latest `duke.jar` from [here.](https://github.com/zzhikai/ip/releases/tag/A-Release)
3. Copy the jar file into an empty folder.
4. Open a command/terminal window in that folder.
5. Run the command `java -jar duke.jar`.
6. Type any command in the command box, and press Enter to execute it.
7.  Refer to the [Features](#Features) below for details of each command.


## Features 

#### `List` - Displays a list of all task.
    Format: `list`

#### `todo` - Add a Todo Task to list.
    Format: `todo DESCRIPTION`

- Provide Task `DESCRIPTION`. The description cannot be empty.
- List will be updated with todo Task with description given.

#### `event` - Add an Event Task to list.
    Format: `event DESCRIPTION /at DATE`

- Provide Task `DESCRIPTION`. The description cannot be empty.
- `DATE` refers to the date the event is on.

#### `deadline` - Add a Deadline Task to list.
    Format: `event DESCRIPTION /by DATE`

- Provide Task `DESCRIPTION`. The description cannot be empty.
- `DATE` refers to the date the event is on, formats: "yyyy-MM-dd", "dd-MM-yyyy", "dd/MM/yyyy", "dd:MM:yyyy".

#### `mark` - Marks a Task in the list as done.
    Format: `mark INDEX`

- Marks Task at the specified `INDEX` as done. The index refers to the index number shown in the displayed list. The index must be a positive integer 1, 2, 3, ...
  
    Example:
    ```
    Nice! I've marked this task as done:
    [T][X] fun
    ```

#### `unmark` - Marks a Task in the list as not done.
    Format: `mark INDEX`

- Marks Task at the specified `INDEX` as done. The index refers to the index number shown in the displayed list. The index must be a positive integer 1, 2, 3, ...
    
    Example:
    
    ```
    OK, I've marked this task as not done yet:
    [T][ ] fun
    ```

#### `delete` - Delete a Task from the list.
    Format: `delete INDEX`

- Delete Task at the specified `INDEX` The index refers to the index number shown in the displayed list. The index must be a positive integer 1, 2, 3, ...

#### `find` - Find all the Tasks in the list containing the keyword.
    Format: `find KEYWORD`

- A list of task that contains the `KEYWORD` will be displayed. Keyword cannot be empty.

#### `update` - Update the description of the Task in the list.
    Format: `update INDEX DESCRIPTION`

- Update the `DESCRIPTION` of Task at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index must be a positive integer 1, 2, 3, ...
- The description cannot be empty.


#### `bye` - Exits duke
    Format: `bye`

