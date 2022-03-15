# Van User Guide

## Features 

### Manage tasks

The Van chatbot allows you to manage the various things in your life by 
allowing you to add various kinds of tasks to be tracked. The types of tasks
currently supported are the following:
1. Deadlines - Allows you to track the various deadlines that are upcoming 
2. Events - Allows you to track when are the various important events in your life
3. to-do - Allows you to track the various things that you have yet to do

### Search functionality

The search function allows you to easily find the specific task that you are looking for
from all the tasks that you have to make it easier to find tasks related to a certain subject.

### Tagging function

The tagging function allows you to add small notes to your tasks in order to add details or
help differentiate between similar types of tasks.

## Commands

#### `list` - Displays all the tasks
The `list` command displays all current tasks, the task type, completion status, timing and tags.

Syntax:
`list`

Example:
```
list

Pending Tasks:
1. [T][] eat lunch #yummy
2. [D][X] finish assignment (by:Oct 20 2022 1800)
3. [E][X] career fair (at:Dec 12 2022 1200)
```

#### `event` - adds a event task
Creates a task of type event with the description and time it occurs

Syntax:
`event <description> /at <date (DD-MM-YYYY HH:MM)>`

Example:
```
event career fair /at 20-01-2022 18:00

Task added
[E][] career fair (at:Jan 20 2022 1800)
1 tasks pending
```

#### `deadline` - adds a deadline task
creates a task of type deadline with description and deadline

Syntax:
`deadline <description> /by <date (DD-MM-YYYY HH:MM)>`

Example:
```
deadline assigment /by 12-03-2022 06:00

Task added
[D][] assignment (by:Mar 12 2022 0600)
1 tasks pending
```

#### `todo` - adds a todo task
creates a task of type todo with description

Syntax:
`todo <description>`

Example:
```
todo eat lunch

Task added
[T][] eat lunch
1 tasks pending
```

#### `mark` - marks a task as done
Marks a task as done by its tasks number

Syntax:
`mark <task number>`

Example:
```
list

Pending Tasks:
1. [T][] eat lunch #yummy
2. [D][X] finish assignment (by:Oct 20 2022 1800)
3. [E][X] career fair (at:Dec 12 2022 1200)

mark 1

Changed task status

list

Pending Tasks:
1. [T][X] eat lunch #yummy
2. [D][X] finish assignment (by:Oct 20 2022 1800)
3. [E][X] career fair (at:Dec 12 2022 1200)
```

#### `unmark` - marks a task as undone
Marks a task as undone by its task number

Syntax:
`unmark <task number>`

Example:
```
list

Pending Tasks:
1. [T][] eat lunch #yummy
2. [D][X] finish assignment (by:Oct 20 2022 1800)
3. [E][X] career fair (at:Dec 12 2022 1200)

unmark 2

Changed task status

list

Pending Tasks:
1. [T][] eat lunch #yummy
2. [D][] finish assignment (by:Oct 20 2022 1800)
3. [E][X] career fair (at:Dec 12 2022 1200)
```

#### `delete` - deletes a task
Deletes a task from the task list using its task number

Syntax:
`delete 1`

Example:
```
list

Pending Tasks:
1. [T][] eat lunch #yummy
2. [D][X] finish assignment (by:Oct 20 2022 1800)
3. [E][X] career fair (at:Dec 12 2022 1200)

delete 1

task deleted

list 

Pending Tasks:
1. [D][X] finish assignment (by:Oct 20 2022 1800)
2. [E][X] career fair (at:Dec 12 2022 1200)
```

#### `find` - find tasks containing a keyword
Find tasks that contain a certain keyword

Syntax:
`find <keyword>`

Example:
```
list

Pending Tasks:
1. [T][] eat lunch #yummy
2. [D][X] finish assignment (by:Oct 20 2022 1800)
3. [E][X] eat (at:Dec 12 2022 1200)

find eat

Tasks containing eat:
1. [T][] eat lunch #yummy
2. [E][X] eat (at:Dec 12 2022 1200)
```

#### `tag` - tags a task
Tags a task from its task number with a keyword

Syntax:
`tag <task number> #<keyword>`

Example:
```
list

Pending Tasks:
1. [T][] eat lunch #yummy
2. [D][X] finish assignment (by:Oct 20 2022 1800)
3. [E][X] eat (at:Dec 12 2022 1200)

tag 1 #NUS

tag added

Pending Tasks:
1. [T][] eat lunch #yummy #NUS
2. [D][X] finish assignment (by:Oct 20 2022 1800)
3. [E][X] eat (at:Dec 12 2022 1200)
```
#### `untag` - removes tag from task 
Removes a tags from a task based on its task number

Syntax:
`untag <task number> #<keyword>`

Example:
```
list

Pending Tasks:
1. [T][] eat lunch #yummy
2. [D][X] finish assignment (by:Oct 20 2022 1800)
3. [E][X] eat (at:Dec 12 2022 1200)

untag 1 #yummy

Pending Tasks:
1. [T][] eat lunch
2. [D][X] finish assignment (by:Oct 20 2022 1800)
3. [E][X] eat (at:Dec 12 2022 1200)
```

#### `bye` - saves all tasks and exits
saves all current tasks to an external file and closes the program

Syntax:
`bye`
