# User Guide
Sussus Dukus or Duke is a **desktop app for managing tasks**. It is optimised for use via a command line interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you are a fast typer, Sussus Dukus can manage and organise your tasks faster than traditional GUI apps. The chat interface also gives users a more ~~sussy~~ homely feeling.

- Starting the app
- Features
    - Adding a todo: `todo`
    - Adding a deadline: `deadline`
    - Adding an event: `event`
    - Listing all tasks: `list`
    - Searching for a task: `find`
    - Marking a task: `mark`
    - Unmarking a task: `unmark`
    - Deleting a task: `delete`
    - Tagging a task: `tag`
    - Untagging a task: `untag`
    - Exiting the program: `bye`
    - Saving the data

## Starting the app
1. Ensure that you have Java `11` or above installed on your computer.
2. Download the latest Duke Jar from [here](https://github.com/Ardentsoul/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Duke program.
4. Double click the file to start the app for Windows, or run `java -jar duke.jar` for Mac.
5. Type the command in the command box and press Enter to execute it.

Some example commands you can try:
  - `todo go for a run`: Adds a todo `go for a run` to the list of tasks.
  - `list`: Lists all tasks.
  - `mark 1`: Marks the first task in the list as done.
  - `delete 1`: Deletes the first task shown in the list.
  - `bye`: Exits the app

6. Refer to the **Features** below for details of each command.

## Features
- Notes about the command format:
  - Words in `UPPER_CASE` are the parameters to be supplied by the user. eg. in `todo DESCRIPTION`, where `DESCRIPTION` is a parameter which can be used as `todo read book`
  - Parameters must be in the order specified.
  - Command is case-insensitive.
  
### Adding a todo: `todo`
Adds a todo to the list of tasks.
Format: `todo DESCRIPTION`

Example of usage:
`todo go for a run`

Expected outcome:

`Task added!`

`[T][] go for a run`

`You currently have 1 task in your device.`

### Adding a deadline: `deadline`
Adds a deadline to the list of tasks.
Format: `deadline DESCRIPTION /by DATE TIME`

>⚠️For `DATE TIME` parameters in both deadline and event features, only the following format is accepted:
- Date format:
  - dd-mm-yyyy
    - eg. 13-02-2022 represents 13 February 2022
- Time format:
  - HHmm
    - eg. 1900 represents 7pm

Example of usage:
`deadline do CS2103T project /by 13-02-2022 1900`

Expected outcome:

`Task added!`

`[D][] do CS2103T project (by: 13 Feb 2022 07:00PM)`

`You currently have 2 tasks in your device.`

### Adding an event: `event`
Adds an event to the list of tasks.

Format: `event DESCRIPTION /by DATE STARTTIME-ENDTIME`

Example of usage:
`event valentine's dinner /at 14-02-2022 1900-2100`

Expected outcome:

`Task added!`

`[E][] valentine's dinner (at: 14 Feb 2022 07:00PM - 09:00PM)`

`You currently have 3 tasks in your device.`

### Listing all tasks: `list`
Shows a list of all tasks.

Format: `list`

Example of usage:
`list`

Expected outcome:

`Here are the tasks in your device:`

`1. [T][] go for a run`

`2. [D][] do CS2103T project (by: 13 Feb 2022 07:00PM)`

`3. [E][] valentine's dinner (at: 14 Feb 2022 07:00PM - 09:00PM)`

### Searching for a task: `find`

Finds tasks whose description matches all the keywords.

Format: `find KEYWORD`

- Keywords are case-sensitive.
- Only the task description is searched.
- Partial words can be matched (eg `ru` will match `run`)
- Only tasks matching all keywords will be returned.

Examples:

- `find go for a run`

Expected outcome:

`1. [T][X] go for a run`


### Marking a task: `mark`
Marks a task.
Format: `mark INDEX`

>ℹ️`INDEX` refers to the index of the task as displayed when executing `list`. 

Example of usage:
`mark 1`

Expected outcome:

`The bar on the top left of your screen just increased!`

`Keep going!`

`1. [T][X] go for a run`

`2. [D][] do CS2103T project (by: 13 Feb 2022 07:00PM)`

`3. [E][] valentine's dinner (at: 14 Feb 2022 07:00PM - 09:00PM)`

### Unmarking a task: `unmark`
Unmarks a task.
Format: `unmark INDEX`
>ℹ️`INDEX` refers to the index of the task as displayed when executing `list`.

Example of usage:
`unmark 1`

Expected outcome:

`Surely you aren't the imposter... right??`

`1. [T][] go for a run`

`2. [D][] do CS2103T project (by: 13 Feb 2022 07:00PM)`

`3. [E][] valentine's dinner (at: 14 Feb 2022 07:00PM - 09:00PM)`

### Deleting a task: `delete`
Deletes a task.
Format: `delete INDEX`
>ℹ️`INDEX` refers to the index of the task as displayed when executing `list`.

Example of usage:
`delete 1`

Expected outcome:

`Hmm... kinda sus you deleted this task...`

`[T][] go for a run`

`You currently have 2 tasks in your device.`

### Tagging a task: `tag`
Tags a task with a with `#`.

Format: `tag INDEX #DESCRIPTION`

>`INDEX` refers to the index of the task as displayed when executing `list`.
>
>`DESCRIPTION` refers to what the `INDEX` should be tagged as.

Example of usage:
`tag 1 #sus`

Expected outcome:

`Task number 1 has been tagged with #sus`

### Untagging a task
Untags a task.

Format: `untag INDEX`

>`INDEX` refers to the index of the task as displayed when executing `list`.

Example of usage:
`untag 1`

Expected outcome:

`Task number 1 has been untagged`

### Exiting the program: `bye`
Exits the program.

Format: `bye`

### Saving the data

All tasks are saved in the hard drive automatically after every command. There is no need to save manually.

> 💡Data is stored in /DukeSaveDirectory/DukeSaveFile.txt relative to the home folder.