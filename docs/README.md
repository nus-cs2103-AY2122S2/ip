# Mickey User Guide

## Features 

### Task Management

- Add ToDos, Deadlines and Events
- Mark tasks as done
- Show list of tasks
- Delete tasks

### Search for tasks

- Find tasks by name

### Sort Tasks

- Sort tasks by alphabetical order or deadline

## Usage

### `todo` - Create a new ToDo

Adds a new ToDo. Todos have no deadline.

Example of usage: 

`todo Bake a cake for Mickey`

Expected outcome:
- Creates a new ToDo 'Bake a cake for Mickey'

```
Aw, gee! New todo:
    [T][] Bake a cake for Minnie
Hooray! You now have 1 task
```
<br/>

### `deadline` - Create a new Deadline

Adds a new Deadline. Deadlines have a `by` date.

Example of usage:

`deadline Clean the Clubhouse /by 2022-02-16 1020`

Expected outcome:
- Creates a new Deadline 'Clean the Clubhouse' due on Feb 16 2022, 10:20am

```
Aw, gee! New deadline:
    [D][] Clean the Clubhouse (by: Feb 16 2022 10:20 AM)
Hooray! You now have 2 tasks
```
<br/>

### `event` - Create a new Event

Adds a new Event. Deadlines have an `at` date.

Example of usage:

`event Pluto's Birthday /at 2022-03-18 0000`

Expected outcome:
- Creates a new Event 'Pluto's Birthday' that falls on Mar 18 2022, 12:00am

```
Aw, gee! New event:
    [E][] Pluto's Birthday (at: Mar 18 2022 12:00 AM)
Hooray! You now have 3 tasks
```
<br/>

### `list` - List all tasks

Displays all current saved tasks.

Example of usage:

`list`

Expected outcome:
- Lists all current tasks

```
Oh boy! You have 3 tasks:
1. [T][] Bake a cake for Mickey
2. [D][] Clean the Clubhouse (by: Feb 16 2022 10:20 AM)
3. [E][] Pluto's Birthday (at: Mar 18 2022 12:00 AM)
```

<br/>

### `mark` - Mark task as done

Marks corresponding numbered task in the list as done.

Example of usage:

`mark 1`

Expected outcome:
- Marks task 1 as done. In the example above, Mickey will mark Bake a Cake as done.

```
That is swell! You have completed this task:
[T][X] Bake a cake for Mickey
```
<br/>

### `delete` - Delete task

Deletes corresponding numbered task in the list.

Example of usage:

`delete 1`

Expected outcome:
- Deletes task 1. In the example above, Mickey will delete the Bake a Cake ToDo.

```
Alrighty. I've removed this task.
    [T][X] Bake a cake for Mickey
You now have 2 tasks
```
<br/>

### `find` - Finds a specific task

Searches the list for task matching text entered.

Example of usage:

`find Pluto`

Expected outcome:
- Displays tasks containing the word 'Pluto'.

```
Oh boy! I found 1 matching tasks:
1. [E][] Pluto's Birthday (at: Mar 18 2022 12:00 AM)
```
<br/>

### `sort` - Sorts tasks

Sorts list of tasks by a custom order.
The following sorting orders are currently supported:
- alphabetical
- deadline

Example of usage:

`sort alphabetical`

Expected outcome:
- Display tasks in alphabetical order.

```
Oh boy! You have 3 tasks:
1. [T][X] Bake a cake for Mickey
2. [D][] Clean the Clubhouse (by: Feb 16 2022 10:20 AM)
3. [E][] Pluto's Birthday (at: Mar 18 2022 12:00 AM)
```
