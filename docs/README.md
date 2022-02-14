# User Guide
Duke is your personal assistant to keep track of tasks.

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `duke.jar` from [here](https://github.com/Gnoznaug/ip/releases).

3. Copy the file to the folder you want to use as the home folder for Duke.

4. Open a command line terminal in the same folder as Duke and run `java -jar duke.jar`. The GUI similar to the below should appear in a few seconds.

5. Type the command in the input box and press Enter to execute it. e.g. typing list and pressing Enter will display the task list. Some example commands you can try:
- `list`: Lists all tasks.
- `todo read book`: Adds a todo task to the task list.
- `delete 2`: Deletes the 2nd task shown in the current list.
- `bye`: Exits the app.

## Features

> Notes about the command format:
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
> - If a parameter is expected only once in the command, but if you specified it multiple times, only the first occurrence of the parameter will be taken. The `update` command is the exception where the syntax has to be adhered to strictly.
> - All date inputs has to be in the yyyy-mm-dd format, e.g. 2022-10-21.
> - Extraneous parameters for commands that do not take in parameters (such as list and exit) will be ignored.
    e.g. if the command specifies `bye 123`, it will be interpreted as `bye`.
### Listing all tasks: `list`
Shows a list of all the tasks in the task list.

There are 3 types of tasks, namely `todo`, `event` and `deadline`.
They are represented by their short forms T, E and D respectively.

Format: `list`

<br>

### Adding a todo task: `todo`
Adds a todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Example: `todo read book`

<br>

### Adding an event task: `event`
Adds an event task to the task list.

Format: `event TASK_DESCRIPTION /at DATE`

Examples:
- `event concert /at 2023-05-12`
- `event midterms /at 2022-03-03`

<br>

### Adding a deadline task: `deadline`
Adds a deadline task to the task list.

Format: `deadline TASK_DESCRIPTION /by DATE`

Examples:
- `deadline return book /by 2022-03-01`
- `deadline cs2105 assignment 1 /by 2022-02-20`

<br>

### Deleting a task: `delete`
Deletes the specified task from the task list.

Format: `delete INDEX`
- Deletes the person at the specified `INDEX`.
- The `INDEX` refers to the index number shown when listing all tasks.

Example: `delete 1` deletes the very first task in the list.

<br>

### Marking a task as done: `mark`
Marks the specified task from the task list as done.

Format: `mark INDEX`
- Marks the person at the specified `INDEX` as done.
- The `INDEX` refers to the index number shown when listing all tasks.

Example: `mark 1` marks the very first task in the list as done.

<br>

### Marking a task as not done: `unmark`
Marks the specified task from the task list as not done.

Format: `unmark INDEX`
- Marks the person at the specified `INDEX` as not done.
- The `INDEX` refers to the index number shown when listing all tasks.

Example: `mark 1` marks the very first task in the list as not done.

<br>

### Finding tasks that matches the specified search term: `find`
Find tasks where its task description contains the specified search term.

Format: `find SEARCH_TERM`

Example: `find eat` will show all the tasks the contains the word eat in their description.

<br>

### Update a task's description and/or date: `update`
Updates a task with a new task description and/or new date description.

Format: `update TASK_TYPE INDEX /details DESCRIPTION /date DATE`
- The `TASK_TYPE` can be `todo`, `event`, `deadline` depending on the task you want to update.
- The `INDEX` refers to the index number shown when listing all tasks.
- Both details and date can be included, either can be excluded, but not both excluded.
- Date field is only applicable for `event` and `deadline` tasks.

Examples:
- `update event 2 /details concert then drinks /date 2023-04-04` updates the 2nd task of the task list which is an event task with the appropriate details and date.
- `update deadline 3 /date 2022-03-08` updates the 3rd task which is a deadline to another date.
- `update event 4 /details visit museum` updates the 4th task which is an event with another task description.

<br>

### Exit the program: `bye`
Exits the program.

Format: `bye`

## Acknowledgements
- Third party libraries used: JavaFX, JUnit 5, johnrengelman shadow.
- Followed AB-3's user guide format for this user guide.