# User Guide

##

## Quick Start
1. Ensure you have Java ```11``` or above installed in your computer.
2. Download the latest ```ip.jar``` [here](https://github.com/DALIN-Prog/ip).
3. Copy the file to the folder you want to use as the home folder for your Dazz program.
4. Double-click the file to start the app. The GUI should appear in a few seconds.
   - Alternatively, open your Command Prompt (for Windows) or Terminal (for Mac) and navigate to the folder where ```ip.jar``` is located. Run the command ```java -jar ip.jar```.
5. Type the command in the command box and press Enter or click on ```Send``` to execute it. e.g. typing ```help``` and pressing Enter will open the help window.
   Some example commands you can try:

   - ```list``` : Lists all tasks.

   - ```exit``` : Exits the app.

6. Refer to the [Features]() below for details of each command.

##

## Features

### ℹ️ Notes about the command format:
- Words in UPPER_CASE are the parameters to be supplied by the user.  
e.g. in `mark NUM`, `NUM` is a parameter which can be used as `mark 1`.
- `TIME` is a date time format in dd/mm/yyyy HHmm  
e.g. 02/02/2022 2222
- You must follow the exact format stated in this guide.

### `help` - Viewing help

Shows a list of available commands and their functionality.

Example of usage: 

`help`

Expected outcome:

```
Here are the list of commands available:
Note that items inside <> are to be specified by you.

bye
Description: Terminates the program

...

...

...

unmark <index> 
Description: Unmarks your task at a particular index in your task list.
```
*Note that the expected outcome above is truncated*

### `todo` - Adding a todo

Adds a todo to your task list.

Format: `todo DESCRIPTION`

Example of usage:

`todo clean laptop`

Expected outcome:

```
Got it. I've added this task:
[T][] clean laptop
Now you have 4 tasks in the list.
```

### `deadline` - Adding a deadline

Adds a deadline to your task list.

Format:`deadline DESCRIPTION /by TIME`

Example of usage:

`deadline complete iP /by 13/02/2022 2359`

Expected outcome:

```
Got it. I've added this task:
[D][] complete iP (by: 13 Feb 2022, 11:59PM)
Now you have 4 tasks in the list.
```

### `event` - Adding an event

Adds an event to your task list.

Format: `event DESCRIPTION /at TIME`

Example of usage:

`event tP meeting /at 14/02/2022 2200`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
[E][] tP meeting (at: 14 Feb 2022, 10:00PM)
Now you have 4 tasks in the list.
```

### `list` - Listing tasks

Lists out all the tasks that are currently in the task list

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][] clean laptop
2. [D][] complete iP (by: 13 Feb 2022, 11:59PM)
3. [E][] tP meeting (at: 14 Feb 2022, 10:00PM)
```

### `mark` - Marking a task

Marks a task at the specified index in the task list.

Format: `mark NUM`

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've mark this task as done:
[T][X] clean laptop
```

### `unmark` - Unmarking a task

Unmarks a task at the specified index in the task list.

Format: `unmark NUM`

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've unmark this task as not done yet:
[T][] clean laptop
```

### `delete` - Deleting a task

Deletes a task at the specified index in the task list.

Format: `delete NUM`

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][] clean laptop
```

### `alias` - Creating an alias for the original command

Creates an alias for the initial commands that are available.

Format: `alias COMM /as SHORT`

Example of usage:

`alias help /as h`

Expected outcome:

```
Nice! Your alias for help is h.
```

### `bye` - Terminating the program

Terminates Dazz program.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
Click on 'X' to close the window
```

#

Command summary

##

|   Action   | Format, Examples                                                                    |
|:----------:|:------------------------------------------------------------------------------------|
|    todo    | `todo DESCRIPTION`<br/>e.g.`todo clean laptop`                                      |
|  deadline  | `deadline DESCRIPTION /by TIME`<br/>e.g.`deadline complete iP /by 13/02/2022 2359`  |
|   event    | `event DESCRIPTION /at TIME`<br/>e.g.`event tP meeting /at 14/02/2022 2200`         |
|    list    | `list`                                                                              |
|    mark    | `mark NUM`<br/>e.g. `mark 1`                                                        |
|   unmark   | `unmark NUM`<br/>e.g. `unmark 1`                                                    |
|   delete   | `delete NUM`<br/>e.g. `delete 1`                                                    |
|    help    | `help`                                                                              |
|   alias    | `alias COMM /as SHORT`<br/>e.g.`alias help /as h`                                   |
|    bye     | `bye`                                                                               |