# User Guide

## Features

### Manage Tasks

1. Todo - Allows you to note the various tasks that you have to do.
2. Deadline - Allows you add a task that has a deadline to be executed before.
3. Event - Allows you to add an event that has a date to be executed on.

### Tagging functionality

The tagging feature allows you to tag tasks in a category of your desire.

## Usage

### `todo` - Adds a todo

Creates a task of type ToDos

Example of usage:

`todo drink water`

Expected outcome:

```
ok added alr bro: todo drink water
```

### `list` - lists out all tasks

Lists out the various tasks that are available.

Example of usage:

`list`

Expected outcome:

```
1. [T][ ] drink water
```

### `bye` - updated duke.txt

Updates duke.txt file with the latest tasks.

Example of usage:

`bye`

Expected outcome:

```
Byeeeee, come back again ah!
```

### `mark` - marks the specified task as done

Marks the specified task as done.

Example of usage:

`mark 1`

Expected outcome:

```
Power la Mr Bosssssss, mark alr bro!
1. [T][X] drink water
```

### `unmark` - marks the specified task as not done

Marks the specified task as not done.

Example of usage:

`unmark 1`

Expected outcome:

```
No probs bro, unmarked already!
1. [T][ ] drink water
```

### `delete` - deletes a specified task

Deletes a specified task from the task list.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
 todo drink water
Now you have 0 in the list.
```

### `tag` - tags a specified task

Tags the specified task.

Example of usage:

`tag 1 urgent`

Expected outcome:

```
Added a tag to: todo drink water

1. [T][ ] drink water [tag: urgent]
```

### `find` - finds a task

Returns tasks that contain the given keyword.

Example of usage:

`find drink`

Expected outcome:

```
1. [T][ ] drink water [tag: urgent]
```

### `event` - adds a task of type event

Creates a task of type event.

Example of usage:

`event project meeting /at Mon 2-4pm`

Expected outcome:

```
1. [E][ ] project meeting (at: Mon 2-4pm)
```

### `deadline` - adds a task of type deadline

Creates a task of type deadline.

Example of usage:

`deadline make some cups /by the day after`

Expected outcome:

```
1. [D][ ] make some cups (by: the day after)
```
