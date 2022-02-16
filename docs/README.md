# Duke User Guide

## Features 

### Feature-Task Types

Tasks in the application are classified under three different categories as listed below:
    
- Todo
- Deadline
- Event

This application also prevents users from adding duplicate tasks into the list, and will alert the user if they try to.

### Feature-Task State

Each task has a state, to mark whether it has been completed.

Completed tasks are marked with a cross, as follows: `[X]`\
Incomplete tasks are marked as follows: `[ ]`

Each task is also associated with a specific number, detailing its position in the entire list. 

To learn how to obtain the number and use the application, read on to the `Usage` section.

### Feature-Task Date & Time

Certain types of tasks allow for users to add dates and time for increased specificity.

To find out which tasks are capable of doing so, read on to the `Usage` section.

### Feature-Saving Tasks

Tasks added while using the app will automatically be saved.

Upon start-up, the tasks will be retrieved and thus the user can continue using the app. 

## Usage

### `list` - View current tasks in the list

Calling this command returns a list of tasks that have been added.

If the user is a first time user, the list will be empty, and the text returned will reflect it as so.

Command: 

`list`

<br />

### `todo` - Adds a task of type todo

Calling this command adds a task of type todo to the list of tasks.

Todo tasks have no date and time functionality, and are automatically marked as incomplete by default.

Specify the task name after a single space following `todo`.

Command:

`todo <taskName>`

Example of usage :heavy_check_mark::

`todo walk`

`todo go for a run`

Task name can be made up of multiple words.

Negative examples of usage :x::

```
todo
- No task name is specified
```

<br />

### `deadline` - Adds a task of type deadline into the list

Calling this command adds a task of type deadline to the list of tasks.

Deadline tasks have date and time functionality, and are automatically marked as uncompleted by default.

Time is optional, while specifying the date is compulsory.

Ensure the keyword `/by` is used, and a whitespace follows after the command, task name and date.

Command:

`deadline <taskName> /by <DD/MM/YYYY> <time>`

Example of usage :heavy_check_mark::

```
deadline report /by 22/02/2022 2pm

deadline submit cheque /by 13/02/2021 midnight

deadline deliverables /by 16/01/2020
```

Negative examples of usage :x::

```
deadline report \by 22/02/2022 2pm
- Incorrect use of keyword '/by'

deadline report/by 22/02/2022 2pm
- Incorrect use of whitespace

deadline report /by 22/2/2022 2pm
- Incorrect use of date format
```

<br />

### `event` - Adds a task of type event into the list

Calling this command adds a task of type event to the list of tasks.

Event tasks have date and time functionality, and are automatically marked as uncompleted by default.

Time is optional, while specifying the date is compulsory.

Ensure the keyword `/at` is used, and a whitespace follows after the command, task name and date.

Example of usage :heavy_check_mark::

```
event carnival /at 22/02/2022 2pm

event swim comp /at 13/02/2021 noon

event birthday /at 16/01/2020
```

Negative examples of usage :x::

```
event carnival \at 22/02/2022 2pm
- Incorrect use of keyword '/at'

event carnival/at 22/02/2022 2pm
- Incorrect use of whitespace

event carnival /at 22/2/2022 2pm
- Incorrect use of date format
```

<br />

### `mark` - Marks a task as completed

Calling this command marks the task specified as completed.

Specify the task number (as a digit) after a single space following `mark`.

To get the task number, one can use the `list` command. The task number will be beside the task name.

Command:

`mark <taskNumber>`

Example of usage :heavy_check_mark::

`mark 1`

Negative examples of usage :x::

```
mark1
- Incorrect use of whitespace

mark 
- No task number specified

mark one
- A digit isn't used
```

<br />

### `unmark` - Marks a task as incomplete

Calling this command marks the task specified as incomplete.

Specify the task number after a single space following `unmark`.

To get the task number, one can use the `list` command. The task number will be beside the task name.

Command:

`unmark <taskNumber>`

Example of usage :heavy_check_mark::

`unmark 1`

Negative examples of usage :x::

```
unmark1
- Incorrect use of whitespace

unmark 
- No task number specified

unmark one
- A digit isn't used
```

<br />

### `find` - Finds a specific task

Calling this command marks returns the specified task.

Specify the task number after a single space following `find`.

To get the task number, one can use the `list` command. The task number will be beside the task name.

Command:

`unmark <taskNumber>`

Example of usage :heavy_check_mark::

`find 1`

Negative examples of usage :x::

```
find1
- Incorrect use of whitespace

find 
- No task number specified

find one
- A digit isn't used
```

<br />

### `delete` - Deletes a specific task

Calling this command deletes the specified task.

Specify the task number after a single space following `delete`.

To get the task number, one can use the `list` command. The task number will be beside the task name.

Command:

`delete <taskNumber>`

Example of usage :heavy_check_mark::

`find 1`

Negative examples of usage :x::

```
delete1
- Incorrect use of whitespace

delete
- No task number specified

delete one
- A digit isn't used
```

<br />

### `bye` - Closes Duke

Calling this command closes Duke and does a save one last time.

Duke will automatically close after waiting a second, there is no need to manually close it.

Command:

`bye`

<br />

### Unrecognized Command

Duke will notify the user if an unrecognized command is inputted with a message in regard to the issue.