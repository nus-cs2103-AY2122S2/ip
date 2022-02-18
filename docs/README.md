# User Guide for Duke

## Features 

### Add a task

Adds a task to the task list. The task can be a todo, deadline or event task.

### Mark a task as done or not done

Mark (or unmark) a task as done (or not done).

### Delete a task

Delete a task from the task list.

### List tasks

Lists out all the tasks in the task list.

### Find tasks

Search for tasks in the task list using keywords or phrases.

### Add a place

Adds a place into the list of places you want to take note of.

### View your places

Lists out all the places in the list of places.

### View place description

View the description of the place you added previously.

### Delete a place

Delete a place from the list of places.

## Usage

### `todo` - Create a todo task

A todo task is created and added to the task list, and saved to the hard disk automatically.

Example of usage:

`todo User Guide`

Expected outcome:

```
Got it. I've added this task:
  [T][ ] User Guide
Now you have 1 tasks in the list.
```

### `deadline` - Create a deadline task

A deadline task is created and added to the task list, and saved to the hard disk automatically.
It takes in a date as the deadline (YYYY-MM-DD).

Example of usage:

`deadline Essay /by 2022-02-18`

Expected outcome:

```
Got it. I've added this task:
  [D][ ] Essay (by: Feb 18 2022)
Now you have 2 tasks in the list.
```

### `event` - Create an event task

An event task is created and added to the task list, and saved to the hard disk automatically.
It takes in a date as the event date (YYYY-MM-DD).

Example of usage:

`event Recess Week /at 2022-02-19`

Expected outcome:

```
Got it. I've added this task:
  [E][ ] Recess Week (at: Feb 19 2022)
Now you have 3 tasks in the list.
```

### `mark` - Marks the task as done

Updates the task to be marked as done, and saved to the hard disk automatically.

Example of usage:

`mark 2`

Expected outcome:

```
Nice! I've marked this task as done:
  [D][X] Essay (by: Feb 18 2022)
```

### `unmark` - Marks the task as undone

Updates the task to be marked as not done yet, and saved to the hard disk automatically.

Example of usage:

`unmark 2`

Expected outcome:

```
Nice! I've marked this task as not done yet:
  [D][ ] Essay (by: Feb 18 2022)
```

### `delete` - Deletes a task

A task is deleted and removed from the task list.
The updated list is automatically saved to the hard disk.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
  [T][ ] User Guide
Now you have 2 tasks in the list.
```

### `list` - Lists all the tasks

Shows all the tasks in the task list.

Example of usage:

`list`

Expected outcome:

```
1.[D][ ] Essay (by: Feb 18 2022)
2.[E][ ] Recess Week (at: Feb 19 2022)
```

### `find` - Search for tasks

Display tasks with descriptions containing the search word or phrase.

Example of usage:

`find Essay`

Expected outcome:

```
Here are the matching tasks in your list:
1.[D][ ] Essay (by: Feb 18 2022)
```

### `addplace` - Adds a place

Creates a place with an accompanying description and adds it to the list of places.
The list is then automatically saved to the hard disk.

Example of usage:

`addplace East Coast Park /desc A green, vibrant and beautiful park in the east. Perfect to go cycling with family and friends!`

Expected outcome:

Description of the outcome.

```
Got it. I've added this place:
  East Coast Park
Now you have 1 places in the list.
```

### `viewplaces` - Lists all the places

Shows all the places in the list of places.

Example of usage:

`viewplaces`

Expected outcome:

```
1. East Coast Park
```

### `view` - Shows the description of the place.

Shows the description of the selected place. 

Example of usage:

`view 1`

Expected outcome:

```
A green, vibrant and beautiful park in the east. Perfect to go cycling with family and friends!
```

### `delplace` - Deletes a place

A place is deleted and removed from the list of places.
The updated list is automatically saved to the hard disk.

Example of usage:

`delplace 1`

Expected outcome:

```
Noted. I've removed this place:
  East Coast Park
Now you have 0 places in the list.
```

### `bye` - Describe action

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
