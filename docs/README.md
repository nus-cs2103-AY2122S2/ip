# User Guide
#### FunBox is an application that helps a person keep track of various tasks.

### Table of content
1. [Features](#features)
2. [Usage](#usage)

## Features 

### Feature-todo
Allows you to add a todo task.
### Feature-deadline
Allows you to add a deadline task.
### Feature-event
Allows you to add an event task.
### Feature-delete
Allows you to delete a task.
### Feature-filter
Allows you to filter tasks by date.
### Feature-find
Allows you to find a tasks by keyword.
### Feature-list
Allows you to look at every tasks available.
### Feature-mark
Allows you to mark a task as done.
### Feature-unmark
Allows you to mark a task as not done.
### Feature-tag
Allows you to tag a task.
### Feature-bye
Allows you to exit the program.


Description of the feature.

## Usage

### `todo` - Adds a todo task to FunBox.

Adds a todo task to be stored in FunBox.

Format: `todo <description>`
* Description of what the task should store.

Example of usage: 
`todo CS2107 CTF`

Expected outcome:
```
Got it. I've added this task:
1. [T][ ] CS2107  CTF
Tags: No tags found!
```

### `deadline` - Adds a deadline task to FunBox.

Adds a deadline task to be stored in FunBox.

Format: `deadline <description> /by <date & time>`
* Description of what the task should store.
* Date and time the tasks should be completed by. Date should be formatted as `yyyy-MM-dd`.

Example of usage:
`deadline CS2107 CTF /by 2022-02-20 2359`

Expected outcome:
```
Got it. I've added this task:
1. [D][ ] CS2107  CTF (by: FEB 20 2022 2359)
Tags: No tags found!
```

### `event` - Adds an event task to FunBox.

Adds an event task to be stored in FunBox.

Format: `event <description> /at <date & time>`
* Description of what the task should store.
* Date and time the tasks should be completed by. Date should be formatted as `yyyy-MM-dd`.


Example of usage:
`event CS2107 CTF Competition /at 2022-02-20 2359`

Expected outcome:
```
Got it. I've added this task:
1. [E][ ] CS2107  CTF Competition (at: FEB 20 2022 2359)
Tags: No tags found!
```

### `delete` - Deletes a todo task stored in FunBox.

Deletes a task stored in FunBox.

Format: `delete <index>`
* Index of the task to be deleted.

Example of usage:
`delete 1`

Expected outcome:
```
Noted! I've removed this task:
[D][ ] CS2107  CTF (by: FEB 20 2022 2359)
Tags: No tags found!
Now you have 0 tasks in the list.
```

### `filter` - Filters tasks by their date.

Filter tasks by their date and display it to the user.

Format: `filter <date>`
* Date to filter the tasks by. Date should be formatted as `yyyy-MM-dd`.

Example of usage:
1. `event CS2107 CTF Competition /at 2022-02-20 2359`
2. `deadline CS2107 CTF /by 2022-02-21 2359`
3. `filter 2022-02-20`

Expected outcome:
```
1.[E][ ] CS2107 CTF Competition (at: Feb 20 2022 2359)
Tags: No tags found!
```

### `find` - Finds a task by keyword.

Find tasks by keyword and display it to the user.

Format: `find <keyword>`
* Keyword to find the tasks by.

Example of usage:
1. `event CS2107 CTF Competition /at 2022-02-20 2359`
2. `deadline CS2107 CTF /by 2022-02-20 2359`
3. `filter 2022-02-20`

Expected outcome:
```
1.[E][ ] CS2107 CTF Competition (at: Feb 20 2022 2359)
Tags: No tags found!

2.[D][ ] CS2107 CTF (by: Feb 20 2022 2359)
Tags: No tags found!
```

### `list` - Retrieve all tasks stored in FunBox.

Retrieve all tasks stored in FunBox and displays it to user.

Format: `list`

Example of usage:
1. `event CS2107 CTF Competition /at 2022-02-20 2359`
2. `deadline CS2107 CTF /by 2022-02-20 2359`
3. `list`

Expected outcome:
```
Here are the tasks in your list:
1.[E][ ] CS2107 CTF Competition (at: Feb 20 2022 2359)
Tags: No tags found!

2.[D][ ] CS2107 CTF (by: Feb 20 2022 2359)
Tags: No tags found!
```

### `mark` - Marks a task in FunBox as done.

Marks a task in FunBox as done and display marked task with a cross.

Format: `mark <index>`
* Index of the task to be marked.

Example of usage:
1. `event CS2107 CTF Competition /at 2022-02-20 2359`
2. `mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
1.[E][X] CS2107 CTF Competition (at: Feb 20 2022 2359)
Tags: No tags found!
```

### `unmark` - Marks a task in FunBox as not done.

Marks a task in FunBox as not done and remove cross from task.

Format: `unmark <index>`
* Index of the task to be un-marked.

Example of usage:
1. `event CS2107 CTF Competition /at 2022-02-20 2359`
2. `mark 1`
3. `unmark 1`

Expected outcome:
```
Ok, I've marked this task as not done yet:
1.[E][ ] CS2107 CTF Competition (at: Feb 20 2022 2359)
Tags: No tags found!
```

### `tag` - Tags a task with a description.

Tags a task with a description.

Format: `tag <index> <description>`
* Index of the task to be tagged.
* Description of what the task should be tagged with.

Example of usage:
1. `event CS2107 CTF Competition /at 2022-02-20 2359`
2. `tag 1 finals`

Expected outcome:
```
1.[E][ ] CS2107 CTF Competition (at: Feb 20 2022 2359)
Tags: #finals
```

### `bye` - Exits the application.

Exits the application and closes FunBox.

Format: `bye`

Example of usage:
`bye`

Expected outcome:
```
B-b-bbye. Hope to see you again soon [0 n 0]
```