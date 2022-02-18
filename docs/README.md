# User Guide

> "I can help!" - MNSKY, *Fargo* S3E3 "The Law of Non-Contradiction"

## Features 

1) Add tasks to MNSKY's task list

- Tasks can be added to MNSKY's task list and MNSKY will remember them for you. MNSKY
can also remember events and deadlines.

2) Mark and unmark tasks

- Tasks in MNSKY's task list can be marked or unmarked to indicate whether they have
been completed or not.

3) Delete tasks

- Tasks can be deleted from MNSKY's memory.

4) Find tasks given a keyword

- MNSKY can search for and print out all tasks which have the keyword in their name.

5) Automatic saving of task list across sessions

- MNSKY automatically saves the task list to a data file every time a change is made
to the task list. On startup, MNSKY will load the task list stored from the data
file if it exists.

6) Undo and redo and changes you've made

- MNSKY can undo and redo any changes made to the task list, but only within that
session.

## Usage

### `hi` - Prints greeting message

Causes MNSKY to print its greeting message.

Format: `hi`

Example of usage: `hi`

Expected outcome: MNSKY will print out the following greeting message in response.

```
Hi, I'm MNSKY!
I can help!
```

### `list` - Prints the task list

Causes MNSKY to print out the entire task list.

Format: `list`

Example of usage: `list`

Expected outcome: MNSKY will print out the entire task list. An example is shown below.

```
1. [T][X] Buy eggs
2. [D][?] Homework /by thursday
3. [E][?] Exam /at 2022-10-14 15:00
4. [T][?] lol
I can help!
```

### `bye` - Quits MNSKY

Closes the MNSKY program.

Format: `bye`

Example of usage: `bye`

Expected outcome: The MNSKY program will close.

### `todo` - Adds task to the task list

Adds a task to MNSKY's task list.

Format: `todo [name]`

Example of usage: `todo calculus homework`

Expected outcome: MNSKY will add the task with the given name
to its task list and print out the following message in response.

```
[MNSKY added task calculus homework to the list]
I can help!
```

### `event` - Adds event to the task list

Adds an event to MNSKY's task list. Note that if the time is given
in the proper format (YYYY-MM-DD HH:SS) then MNSKY will display
the date and time properly.

Format: `event [name] /at [time]`

Example of usage: `event Graduation Ceremony /at 2022-05-19`

Expected outcome: MNSKY will add the event with the given name
to its task list and print out the following message in response.

```
[MNSKY added task Graduation Ceremony to the list]
I can help!
```

### `deadline` - Adds deadline to the task list

Adds a deadline to MNSKY's task list. Note that if the time is given
in the proper format (YYYY-MM-DD HH:SS) then MNSKY will display
the date and time properly.

Format: `deadline [name] /by [time]`

Example of usage: `deadline cs2103 project /by 2022-03-10 23:59`

Expected outcome: MNSKY will add the deadline with the given name
to its task list and print out the following message in response.

```
[MNSKY added task cs2103 project to the list]
I can help!
```

### `mark` - Marks a task

Marks the task at the index passed to the mark command. Note that
tasks in the task list are 1-indexed, and the index must both be a
valid number and within the bounds of the task list. Also note that
a marked task can be marked, but it will not change anything.

Format: `mark [index]`

Example of usage: `mark 1`

Expected outcome: MNSKY will mark the task and print it, showing
that it has been marked. An example is shown below, with 'X' indicating
that the task has been marked.

```
[T][X] calculuse homework
I can help!
```

### `unmark` - Unmarks a task

Unmarks the task at the index passed to the unmark command. Note that
tasks in the task list are 1-indexed, and the index must both be a
valid number and within the bounds of the task list. Also note that 
an unmarked task can be unmarked, but it will not change anything.

Format: `unmark [index]`

Example of usage: `unmark 1`

Expected outcome: MNSKY will unmark the task and print it, showing
that it has been unmarked. An example is shown below, with '?' indicating
that the task has been unmarked.

```
[T][?] calculus homework
I can help!
```

### `delete` - Deletes a task

Deletes the task at the index passed to the delete command. Note that
tasks in the task list are 1-indexed, and the index must both be a
valid number and within the bounds of the task list. Also note that
tasks after the deleted task will have their indices decremented by 1.

Format: `delete [index]`

Example of usage: `delete 1`

Expected outcome: MNSKY will delete the task and print the name of the task
it has deleted as shown below.

```
[MNSKY has deleted task calculus homework from the list.]
I can help!
```

### `find` - Deletes a task

Finds and prints all the tasks which has the search term in their name.

Format: `find [search_term]`

Example of usage: `find cul`

Expected outcome: MNSKY will print out all the tasks which have the search
term in their name. Two examples are shown below, the first being if at least
one matching task is found, and the second being otherwise.

```
[T][?] calculus homework
I can help!
```

```
[MNSKY couldn't find any tasks matching the search term.]
I can help!
```

### `undo` - Undoes a change to the task list

Undoes the most recent change to the task list. Note that failed edits to the
task list (such as inputting todo command without any name) do not count as
changes.

Format: `undo`

Example of usage: `undo`

Expected outcome: MNSKY will undo the most recent change and print out the
state of the task list after undoing, such as in the example below.

```
1. [T][X] Buy eggs
2. [D][?] Homework /by thursday
3. [E][?] Exam /at 2022-10-14 15:00
I can help!
```

### `redo` - Redoes an undone change to the task list

Redoes the most recent undone change to the task list.

Format: `redo`

Example of usage: `redo`

Expected outcome: MNSKY will redo the most recent undone change and print out 
the state of the task list after redoing, such as in the example below.

```
1. [T][X] Buy eggs
2. [D][?] Homework /by thursday
3. [E][?] Exam /at 2022-10-14 15:00
4. [T][?] lol
I can help!
```

### `bye` - Shut down MNSKY

Causes MNSKY to shut itself down. At this point, it will only give
blank responses.

Format: `bye`

Example of usage: `bye`

Expected outcome: MNSKY will shut itself down and will only respond with `...`
if the user sends any input.
