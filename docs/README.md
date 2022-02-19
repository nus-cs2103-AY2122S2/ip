# User Guide

## Features 

### `todo`
Adds a todo task into the list

### `event`
Add an event task into the list, along with the event date & time

### `deadline`
Add a deadline task into the list, along with the date & time it has to be done by

### `list`
Displays the current list of tasks

### `delete`
Delete an existing task in the list

### `mark`
Mark a task in the list as done

### `unmark`
Mark a task in the list as undone

### `find`
Search the list for a specific task

Description of the feature.

## Usage

### Adds a todo task into the list: `todo`

Format:
`todo TASK_NAME`

Example of usage:
`todo moo`

Expected outcome:
```
Got it. I've added this task:
[T][ ] moo
Now you have 1 task(s) in the list.
```

### Add an event task into the list, along with the event date & time: `event`

Format:
`event EVENT_NAME /at YYYY-MM-DD HHMM`

Example of usage:
`event eat grass /at 2020-02-20 1600`

Expected outcome:
```
Got it. I've added this task:
[E][] eat grass (at: Feb-20-2022 1600)
Now you have 2 task(s) in the list.
```

### Add a deadline task into the list, along with the date & time it has to be done by: `deadline`

Format:
`deadline DEADLINE_NAME /by YYYY-MM-DD HHMM`

Example of usage:
`deadline pretend to be a cow /by 2020-02-21 1200`

Expected outcome:
```
Got it. I've added this task:
[D][] pretend to be a cow (by: Feb-21-2022 1200)
Now you have 3 task(s) in the list.
```

### Displays the current list of tasks: `list`

Example of usage:
`list`

Expected outcome:
```
1.[T][] moo
2.[E][] eat grass (at: Feb-20-2022 1600)
3.[D][] pretend to be a cow (by: Feb-21-2022 1200)
```

### Delete an existing task in the list: `delete`

Format:
`delete TASK_INDEX`

Example of usage:
`delete 1`

Expected outcome:
```
Noted. I've removed this task:
1.[T][] moo
Now you have 2 task(s) in the list.
```

### Mark a task in the list as done: `mark`

Format:
`mark TASK_INDEX`

Example of usage:
`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
[E][X] eat grass (at: Feb-20-2022 1600)
```

### Mark a task in the list as undone: `unmark`

Format:
`unmark TASK_INDEX`

Example of usage:
`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
[E][] eat grass (at: Feb-20-2022 1600)
```

### Search the list for a specific task: `find`

Format:
`find TASK_NAME`

Example of usage:
`find eat grass`

Expected outcome:
```
Here are the matching tasks in your list:
[E][] eat grass (at: Feb-20-2022 1600)
```
