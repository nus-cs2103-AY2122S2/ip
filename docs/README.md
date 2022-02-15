# User Guide

## Features

### `todo`

Adds a to-do to your list of tasks.

#### Format

```
todo {description}
```

#### Sample input

`todo read book`

#### Expected output

```
Got it. I've added this task:
[T] [P2] [] read book
You now have {} tasks in the list.
```

### `deadline`

Adds a deadline to your list of tasks.

#### Format

`deadline {description) /by (datetime in YYYY-MM-DD HHHH)`

#### Sample input

`deadline return book /by 2022-02-20 2220`

#### Expected output

```
Got it. I've added this task:
[D] [P2] [] return book (by Feb 20 2220H)
You now have {} tasks in the list.
```

### `event`

Adds an event to your list of tasks.

#### Format

`deadline {description) /by (datetime in YYYY-MM-DD HHHH)`

#### Sample input

`event book sharing /by 2022-02-18 1400`

#### Expected output

```
Got it. I've added this task:
[D] [P2] [] book sharing (by Feb 20 2220H)
You now have {} tasks in the list.
```

### `delete`

Deletes a task

#### Format

`delete {task number}`

#### Sample input

`delete 1`

#### Expected output

```
Noted. I've removed this task:
...
You now have ... task(s) in the list.
```

### `mark`

Marks a task as done.

#### Format

`mark {task number}`

#### Sample input

`mark 1`

#### Expected output

```
Nice! I've marked this task as done:
...
```

### `unmark`

Unmark a completed task.

#### Format

`unmark {task number}`

#### Sample input

`unmark 1`

#### Expected output

```
OK, I've marked this task as not done yet:
...
```

### `find`

Find tasks that contain a keyword.

#### Format

`find {description}`

#### Sample input

`find read book`

#### Expected output

```
Here are the matching tasks in your list:
[T] [P1] [] read book
```

### `prioritise`

Changes a task's priority level.

#### Format

`prioritise {task number} {priority level}`

where `priority level` can be `low`, `medium`, or `high`

#### Sample input

`prioritise 1 high`

#### Expected output

```
Got it, I've changed the priority of this task:
1. [T1] [P1] [] read book
```