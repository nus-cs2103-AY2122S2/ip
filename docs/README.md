# Ask@Jamie User Guide

Ask@Jamie is chatbot-style task management app that allows users to track upcoming deadlines, tasks, and events in an organised manner.

## Features

### Feature-Task Types

Tasks in the application are classified under three different types as listed below:

- Todo
- Deadline
- Event

This application also reminds the user what are the upcoming tasks given the daate.

### Feature-Task State

Each task has two states, to mark whether it has been completed or incomplete.

Completed tasks are marked with a cross, as follows: `[X]`\
Incomplete tasks are marked as follows: `[ ]`

Each task is also associated with a specific index number, detailing its position in the entire list.

To obtain the list and the task index number, refer to the `Usage` section.

### Feature-Task Date 

Certain types of tasks allow for users to add dates for increased specificity.

To find out which tasks are capable of doing so, read on to the `Usage` section.

### Feature-Saving Tasks

Tasks added by the user while using the app will automatically be saved.

When the user uses the application again, the latest tasks will be retrieved. 

## Usage

### `list` - Displays all tasks in the list

Command: `list`

Display the list with the title, type and state [Incomplete/Complete] of all task(s).

Example of usage ✅: 

`list`


The first box represents the type of task:

- `[T]` for todo
- `[D]` for deadline
- `[E]` for event

Expected outcome: Application will reply with the list of all tasks that have been added

<br />

### `todo` - Add a todo task

Command: `todo <description>`

Adds a task of type todo into the task list. 

Todo tasks contain a title and state [incomplete/complete], and are marked as incomplete by default.

Example of usage ✅:

`todo walk`

`todo go for a run`

Negative examples of usage ❌ :

```
todo
- The description of todo cannot be empty
```

Expected outcome: Application will reply if the task has been successfully added

<br />

### `deadline` - Adds a task of type deadline into the list

Command: `deadline <description> /by <YYYY-MM-DD>`

Adds a task of type deadline into the task list.

Deadline tasks contain a title, state [incomplete/complete] and date functionality, and are marked as incomplete by default.

Specifying the date is required.

Ensure the keyword `/by` is used, and a whitespace follows after the command, task name and date.

Example of usage ✅:

```
deadline Finish CS2103T iP Project /by 2022-02-18

deadline Submit CS2105 Assignment /by 2022-02-25
```

Negative examples of usage ❌:

```
deadline Finish CS2103T iP Project \by 2022-02-18 
- Incorrect use of keyword '/by'

deadline Finish CS2103T iP Project/by 2022-02-18  
- Incorrect use of whitespace

deadline Finish CS2103T iP Project /by 2022-13-18 
- Incorrect use of date format
```

Expected outcome: Application will reply if the task has been successfully added

<br />

### `event` - Adds a task of type deadline into the list

Command: `event <description> /by <YYYY-MM-DD>`

Adds a task of type event into the task list.

Event tasks contain a title, state [incomplete/complete] and date functionality, and are marked as incomplete by default.

Specifying the date is required.

Ensure the keyword `/by` is used, and a whitespace follows after the command, task name and date.

Example of usage ✅:

```
event CS2105 Midterms /at 2022-03-03

event Sports Club Day /at 2022-04-15

event Bob's Birthday /at 2022-03-21
```

Negative examples of usage ❌:

```
event  CS2105 Midterms \at 2022-03-03
- Incorrect use of keyword '/at'

event  CS2105 Midterms/at 2022-03-03
- Incorrect use of whitespace

event  CS2105 Midterms /at 2022-13-03
- Incorrect use of date format
```

Expected outcome: Application will reply if the task has been successfully added

<br />

### `mark` - Mark a task as completed

Command: `mark <index>`

Marks the task specified as complete based on the index on the list.

Specify the task number (as a digit) after a single space following `mark`.

To get the task number, one can use the `list` command. The task number will be beside the task name.

Example of usage ✅:

`mark 1`

Negative examples of usage ❌:

```
mark1
- Incorrect use of whitespace

mark 
- No task number specified

mark one
- A digit isn't used
```

Expected outcome: Application will reply if the task has been successfully marked as complete

<br />

### `unmark` - Marks a task as incomplete

Command: `unmark <index>`

Marks the task specified as incomplete based on the index on the list.

Specify the task number (as a digit) after a single space following `unmark`.

To get the task number, one can use the `list` command. The task number will be beside the task name.

Example of usage ✅:

`unmark 1`

Negative examples of usage ❌:

```
unmark1
- Incorrect use of whitespace

unmark 
- No task number specified

unmark one
- A digit isn't used
```

Expected outcome: Application will reply if the task has been successfully marked as incomplete

<br />

### `delete` - Deletes a specific task

Command: `delete <index>`

Deletes the specified task based on the index on the list.

Specify the task number after a single space following `delete`.

To get the task number, one can use the `list` command. The task number will be beside the task name.


Example of usage ✅:

`delete 1`

Negative examples of usage ❌:

```
delete1
- Incorrect use of whitespace

delete
- No task number specified

delete one
- A digit isn't used
```

Expected outcome: Bot will reply if the task has been successfully deleted

<br />

### `find` - Find tasks with keyword

Command: `find <keyword>`

Finds all tasks with the description that corresponds with the keyword the user has entered.

Specify the keyword after a single space following `find`.

Example of usage ✅:

`find CS2103T`

Negative examples of usage ❌:

```
findCS2103T
- Incorrect use of whitespace

find 
- No task number specified
```
Expected outcome: Application will reply with the list of task(s) that corresponds with the keyword

<br />

### `reminder` - List upcoming tasks 

Command: `reminder <date>`

Finds all upcoming tasks that corresponds with the date the user has entered.

Specify the keyword after a single space following `reminder`.

Example of usage ✅:

`reminder 2022-02-10`

Negative examples of usage ❌:

```
reminder2022-02-10
- Incorrect use of whitespace

reminder 
- No task number specified
```
Expected outcome: Application will reply with the list of upcoming task(s) that corresponds with the date

<br />

### `bye` - Closes Duke

Command: `bye`

Closes Duke and does a save before exiting.

Application will automatically close after waiting a second, there is no need to manually close it.

<br />

### Unrecognized Command

Application will notify the user if an unrecognized command is used with a message of the list of commands.