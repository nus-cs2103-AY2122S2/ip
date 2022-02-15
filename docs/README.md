# User Guide
Duke is a task manager chatbot. Chat with Duke by keying in commands and view Duke's replies :handshake:

## Features 

### Task manager
Add tasks. List the added tasks. Delete the tasks. Mark them as done or undone. Find tasks containing a specific keyword.

### Set reminders
Can't remember tasks? Set a reminder for them at a specific date and time.

## Usage

### `todo [description]` - Add a `todo` task
Adds a `todo` task to the task manager. 

Example: `todo cs2103 iP`

```
added o.O:
  [T][ ] cs2103 iP
Now there are 1 tasks on the list x)
```

### `deadline [description] /by [date]` - Add a `deadline` task
Adds a `deadline` task to the task manager. `date` must be in `YYYY-MM-DD` format.

Example: `deadline cs2103 iP /by 2022-02-18`

```
added o.O:
  [D][ ] cs2103 iP (by: Feb 18 2022)
Now there are 2 tasks on the list x)
```

### `event [description] /at [date]` - Add an `event` task
Adds an `event` task to the task manager. `date` must be in `YYYY-MM-DD` format.

Example: `event cs2103 lecture /at 2022-02-18`

```
added o.O:
  [E][ ] cs2103 lecture (at: Feb 18 2022)
Now there are 3 tasks on the list x)
```

### `list` - List tasks
Lists all tasks in the task manager.

Example: `list`

```
Here are the tasks on your list :O
1. [T][ ] cs2103 iP
2. [D][ ] cs2103 iP (by: Feb 18 2022)
3. [E][ ] cs2103 lecture (at: Feb 18 2022)
```

### `delete [index]` - Delete a task
Deletes a task with the given `index` from the task manager. `index` must be a **positive integer** 1, 2, 3...

Example: `delete 1`

```
deleted this item O_O:
  [T][ ] cs2103 iP
Now there are 2 tasks on the list x)
```

### `mark [index]` - Marks a task as done
Marks a task with the given `index` as done. `index` must be a **positive integer** 1, 2, 3...

Example: `mark 1`

```
Task done! \(n_n)/
  [D][X] cs2103 iP (by: Feb 18 2022)
```

### `unmark [index]` - Marks a task as undone
Marks a task with the given `index` as undone. `index` must be a **positive integer** 1, 2, 3...

Example: `unmark 1`

```
Task not done =(
  [D][ ] cs2103 iP (by: Feb 18 2022)
```

### `find [keyword]` - Find tasks with a specific keyword
Lists all tasks with descriptions containining the specific keyword or phrase. 

Example: `find iP`

```
Here are the matching tasks on your list :O
1. [D][ ] cs2103 iP (by: Feb 18 2022)
```

### `remind [index] [date] [time]` - Set a reminder for a specific task
Sets a reminder for the task with the given `index` at the specified `date` and `time`.
- `index` must be a **positive integer** 1, 2, 3...
- `date` must be in `YYYY-MM-DD` format.
- `time` must be in HH:MM format.

Example: `remind 1 2022-02-15 13:16`

(After setting reminder)
```
:D Reminder set at 15 Feb 2022, 13:16 for task: cs2103 iP
```
(At 15 Feb 2022, 13:16)
```
!Reminder! cs2103 iP by Feb 18 2022
```

## Command summary
| Command | Format |
| --- | --- |
| todo | `todo [description]` |
| deadline | `deadline [description] /by [date]` |
| event | `event [description] /by [date]` |
| list | `list` |
| delete | `delete [index]` |
| mark | `mark [index]` |
| unmark | `unmark [index]` |
| find | `find [keyword]` |
| remind | `remind [index] [date] [time]` |
