# Juke User Guide

Juke is an interactive desktop application for task management with a chatbot interface.

## Features 

### Basic task management

Allows for adding, removing and editing tasks.
Tasks can be marked as done or not done.
Find tasks easily by searching for keywords.

### Supports three task types

- Todo: A basic task to be done.
- Deadlines: A task with a deadline.
- Events: A task happening at a specified time.

### Local storage of tasks

Tasks are saved automatically whenever the list is modified.
The data is automatically loaded on startup

## Quick Start

1. Ensure you have Java 11 installed.
   It can be downloaded from [here](https://www.oracle.com/java/technologies/downloads/).
   
2. Download the latest release of [Juke](https://github.com/Spyobird/ip/release).
3. Place it in the desired directory.
4. Either double click the `.jar` file or
   execute `java -jar <juke-jar-name>.jar` in your respective OS terminal.
   For example: `java -jar juke-0.3-all.jar`.
   
5. The application should run, and you can start managing tasks!

## Usage

### `todo` - Adds a todo task

Creates a todo task which has a basic description.

Format: `todo TASK_NAME`

Example of usage:

- `todo Fill up the gas`
- `todo Repair the lights`

### `deadline` - Adds a deadline task

Creates a deadline task which has a description, and a deadline which is a date and time component.
See [date and time component](#date-and-time-component) for more information about the date and time component.

Format: `deadline TASK_NAME -by DEADLINE`

Example of usage:

- `deadline Finish chemistry homework -by 22/02/2022 22:30`
- `deadline Sumbit tax invoices -by 01042022 0800`

### `event` - Adds an event task

Creates an event task which has a description, and a start date which is a date and time component.
See [date and time component](#date-and-time-component) for more information about the date and time component.

Format: `event TASK_NAME -at START_DATE`

Example of usage:

- `event Music festival -at 03/03/2022 19:00`
- `event Car expo -at 06072022 1000`

### `list` - Lists all tasks

Lists all tasks that have been stored in Juke.

Format: `list`

### `list` - Lists all tasks

Lists all tasks that have been stored in Juke.

Format: `list`

Expected outcome: List of tasks as the output
```
[T] [ ] The first box indicates the task type by initial
[T] [X] The second box indicates the task is done or not done
[T] [ ] Following that is the description
[D] [ ] And the time component (if any) (by: 10 Feb 2022 10:34)
```

### `mark` - Marks a task as done

Marks the task at a given index as done.
A valid index is a positive integer that starts at 1 and ends at the size of the task list,
where the value represents its position in the task list.

Format: `mark INDEX`

Example of usage:

- `mark 1` marks the first task as done.

### `unmark` - Marks a task as not done

Marks the task at a given index as not done.
A valid index is a positive integer that starts at 1 and ends at the size of the task list,
where the value represents its position in the task list.

Format: `unmark INDEX`

Example of usage:

- `unmark 3` marks the third task as not done.

### `delete` - Deletes a task

Deletes a task at a given index. Deleting is permanent and cannot be undone.
A valid index is a positive integer that starts at 1 and ends at the size of the task list,
where the value represents its position in the task list.

Format: `delete INDEX`

Example of usage:

- `delete 2` deletes the second task.

### `find` - Searches for a task

Searches for a task using a string query.
The command will return all tasks that have a matching sequence of
characters as the query string. The query is case-insensitive.

Format: `find QUERY`

Example of usage:

- `find buy` searchings for tasks with the keyword 'buy'.

Output:
```
Found task(s):
[T] [ ] buy fish
[T] [ ] buy books
[T] [ ] Buy milk (case insensitive)
[D] [ ] BUY TICKETS (by: 10 May 2022 23:00)
```

### `clone` - Clones a task

Creates a copy of a task at a given index. The task is added to the end of the task list.
A valid index is a positive integer that starts at 1 and ends at the size of the task list,
where the value represents its position in the task list.

Format: `clone INDEX`

Example of usage:

- `clone 4` clones the fourth task.

### `edit` - Edits a task

Edits a task at a given index. The command must contain at least one valid parameter to edit.
A valid index is a positive integer that starts at 1 and ends at the size of the task list,
where the value represents its position in the task list.

Format: `edit INDEX [-d DESCRIPTION] [-t DATE_TIME]`

Example of usage:

- `edit 1 -d Grab dinner` edits the description of the first task.
- `edit 2 -t 23102022 1400` edits the date and time of the second task.
- `edit 3 -d Finish readings -t 11032022 1900` edits the description, and the date and time of the third task.
- `edit 4` invalid as missing parameters.

### `bye` - Exits Juke

Closes the Juke application.

Format: `bye`

## Date and Time Component

The way date and time is parsed requires a string to be in the given format:

`DAY[D_SEPARATOR]MONTH[D_SEPARATOR]YEAR[ HOUR[T_SEPARATOR]MINUTE]`

Below is the table for the formatting of each element:

|Element|Description|Formats|
|:---:|---|---|
|`DAY`|Day of the month.|`02` (standard), `2`|
|`MONTH`|Month of the year.|`04` (standard), `Apr`, `April`|
|`YEAR`|Year.|`2022`|
|`HOUR`|Hour of the day (24 hr).|`19`|
|`MINUTE`|Minute of the hour. |`30`|
|`D_SEPARATOR`|Date separator. Optional.|space, `-`, `/` (`DAY` and `MONTH` as standard)|
|`T_SEPARATOR`|Time separator. Optional.|space, `-`, `:`|

The entire time component of any date and time is optional.
If time is not specified, it is defaulted to `0800`.

The output format of time is always in the form `02 Apr 2022 19:30`.
