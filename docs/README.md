# Prince - User Guide

- Prince can track 3 types of tasks
- [x] Todo
- [x] Deadline
- [x] Event

## Features
- Add tasks `todo` or `deadline` or `event`
- Delete tasks `delete`
- Search for tasks `find`
- Mark tasks as done `mark` and `unmark`

## Usage

### Adding an Todo Task - `todo`

A Todo needs only a task description.

Example of usage:

`todo Attend EC1101E Lecture`

Expected outcome:
Creates a new Event task

```
Got it. I've added:
[T][ ] Attend EC1101E Lecture
Sheesh you've now got 4 tasks in the list
```

### Adding an Event Task - `event`

An Event needs a description as well as an event time.

Example of usage:

`event Attend EC1101E Lecture /at Thursday 2-4pm`

Expected outcome:
Creates a new Event task

```
Got it. I've added:
[E][ ] Attend EC1101E Lecture (at: Thursday 2-4pm)
Sheesh you've now got 4 tasks in the list
```


### Adding a deadline task - `deadline`

A deadline must have a description and a deadline date.
The date must be provided in the format yyy-mm-dd.

Example of usage: 

`deadline Finish CS3243 project /by 2022-02-20`

Expected outcome:
Creates a new Deadline task

```
Got it. I've added:
[D][ ] Finish CS3243 project (by: 20-Feb-2022)
Sheesh you've now got 4 tasks in the list
```


