# User Guide
Duke is a task manager chatbot. Chat with Duke by keying in commands and view Duke's replies!

## Features 

### 1. Add tasks
Duke supports three task types: `Todo`, `Deadline` and `Event`.

### 2. List tasks
List all tasks that have been added to Duke!

### 3. Delete tasks
Don't want to see a task anymore? Delete it!

### 4. Mark and unmark tasks
Mark tasks as 'done'. Unmark them. Mark them again.

### 5. Find tasks
Find tasks containing a specific keyword!

### 6. Set reminders
Can't remember your tasks? Let Duke do it for you!

## Usage
Note: items in [square brackets] indicate compulsory user-entered parameters.

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

### `find [keyword]` - Find tasks containing a specific keyword
Lists all tasks containing the specific keyword or phrase. 

Example: `find iP`

```
Here are the matching tasks on your list :O
1. [D][ ] cs2103 iP (by: Feb 18 2022)
```

### `remind [index] [date] [time]` - Set a reminder for a specific task
Sets a reminder for the task with the given `index` at the specified `date` and `time`.
Duke must not be closed after setting the reminder in order for the reminder to be successfully sent.
- `index` must be a **positive integer** 1, 2, 3...
- `date` must be in `YYYY-MM-DD` format.
- `time` must be in `HH:MM` format.

Example: `remind 1 2022-02-15 13:16`

(After setting reminder)
```
:D Reminder set at 15 Feb 2022, 13:16 for task: cs2103 iP
```
(At 15 Feb 2022, 13:16)
```
!Reminder! cs2103 iP by Feb 18 2022
```

### `bye` - Exit the program
Exits the program.

Example: `bye`

## Command summary
<table>
  <tr>
    <th>Command</th>
    <th>Format</th>
  </tr>
  <tr>
    <td>todo</td>
    <td><code>todo [description]</code></td>
  </tr>
  <tr>
    <td>deadline</td>
    <td><code>deadline [description] /by [date]</code></td>
  </tr>
  <tr>
    <td>event</td>
    <td><code>event [description] /at [date]</code></td>
  </tr>
  <tr>
    <td>list</td>
    <td><code>list</code></td>
  </tr>
  <tr>
    <td>delete</td>
    <td><code>delete [index]</code></td>
  </tr>
  <tr>
    <td>mark</td>
    <td><code>mark [index]</code></td>
  </tr>
  <tr>
    <td>unmark</td>
    <td><code>unmark [index]</code></td>
  </tr>
  <tr>
    <td>find</td>
    <td><code>find [keyword]</code></td>
  </tr>
  <tr>
    <td>remind</td>
    <td><code>remind [index] [date] [time]</code></td>
  </tr>
  <tr>
    <td>bye</td>
    <td><code>bye</code></td>
  </tr>
</table>