# User Guide

## Features

### Ask for help

Gene recognises the help command that prints all available commands and the specific syntax 
that gene recognises.

### Add tasks

Three different type of tasks are available: todo, event and deadline tasks. 

For event and deadline tasks, the specific date and time
 for each task can be saved and will then be displayed in a user friendly manner.

### Add locations

Locations include the name of the location, it's associated postal code, and the type of location. e.g School, Restaruant, etc...

### Mark tasks and locations

Tasks and locations can be marked as done or visited.

### Unmark tasks and locations

Tasks and locations can be unmarked too.

### Delete tasks and locations

Delete tasks and locations separately 

### Find tasks or locations

By typing in a keyword, users can now search for tasks and locations that contains the entered keyword

### Exit

exit the program

## Usage

### `todo ___` - creates a todo task

Creates and adds a todo task to the list of tasks


Example of usage: 

`todo task`

Expected outcome:

A todo task is created and added into the list of existing tasks.

```
--------------------------------------------------------
Got it. I've added this task:
  [T][ ]  task
Now you have 1 tasks in the list.
--------------------------------------------------------
```

-------------------------------------------------------------------------------
### `event ___ /at ____` - creates an event task

Creates and adds an event task to the list of tasks, following "at/ " date and time must be input according to the format:
d/MM/yyyy HHmm.


Example of usage:

`event hello /at 2/12/2222 1900`

Expected outcome:

An event task is created and added into the list of existing tasks.

```
--------------------------------------------------------
Got it. I've added this task:
  [E][ ]  hello (at: Monday, Dec 02, 2222 19:00 pm)
Now you have 2 tasks in the list.
--------------------------------------------------------
```
-------------------------------------------------------------------------------
### `deadline ___ /by ____` - creates a deadline task

Creates and adds a deadline task to the list of tasks, following "by/ " date and time must be input according to the format:
d/MM/yyyy HHmm.


Example of usage:

`deadline byebye /by 2/12/2222 1901`

Expected outcome:

An event task is created and added into the list of existing tasks.

```
--------------------------------------------------------
Got it. I've added this task:
  [D][ ]  byebye (by: Monday, Dec 02, 2222 19:01 pm)
Now you have 3 tasks in the list.
--------------------------------------------------------
```
-------------------------------------------------------------------------------
### `location ___ /at ____, ______` - creates a location

Creates and adds a location to the list of locations, following "at/ " is the postal code of the location.
Following ", " is the type of location.

Example of usage:

`location Ann Siang Hill /at 128598, Park`

Expected outcome:

A location is created and added into the list of existing locations.

```
--------------------------------------------------------
Got it. I've added this location:
  [L][ ] Ann Siang Hill: 128598, Park
Now you have 1 locations in the list.
--------------------------------------------------------
```
-------------------------------------------------------------------------------
### `mark task _` - marks a task

Marks an existing task in the list of tasks, _ is the index of the task to mark.


Example of usage:

`mark task 0`

Expected outcome:

The first task in the list is marked.

```
--------------------------------------------------------
Nice! I've marked this task as done:
  [T][X]  task
--------------------------------------------------------
```
-------------------------------------------------------------------------------
### `unmark task _` - unmarks a task

Unmarks an existing task in the list of tasks, _ is the index of the task to mark.


Example of usage:

`unmark task 0`

Expected outcome:

The first task in the list is unmarked.

```
--------------------------------------------------------
OK, I've marked this task as not done yet:
  [T][ ]  task
--------------------------------------------------------
```
-------------------------------------------------------------------------------
### `mark location _` - marks a location

Marks an existing location in the list of locations, _ is the index of the locations to mark.


Example of usage:

`mark location 0`

Expected outcome:

The first location in the list is marked.

```
--------------------------------------------------------
Nice! I've marked this location as visited:
  [L][X] Ann Siang Hill: 128598, Park
--------------------------------------------------------
```
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
### `unmark location _` - unmarks a location

Unmarks an existing location in the list of locations, _ is the index of the locations to unmark.


Example of usage:

`unmark location 0`

Expected outcome:

The first location in the list is unmarked.

```
--------------------------------------------------------
OK, I've marked this location as not visited yet:
  [L][ ] Ann Siang Hill: 128598, Park
--------------------------------------------------------
```
-------------------------------------------------------------------------------
### `find ___` - Finds tasks and locations

Finds all tasks and locations containing the given keyword

Example of usage:

`find a`

Expected outcome:

All task and location title containing "a" will be displayed.

```
--------------------------------------------------------
Here are the matching tasks in your list:
1.[T][ ] task

And here are the matching locations:
1.[L][ ] Ann Siang Hill: 128598, Park
--------------------------------------------------------
```
-------------------------------------------------------------------------------
### `delete task _` - Deletes a task

Deletes an existing task in the list of tasks, _ is the index of the task to delete.

Example of usage:

`delete task 0`

Expected outcome:

The first task in the list will be deleted
```
--------------------------------------------------------
Noted. I've removed this task:
  [T][ ] task
Now you have 2 tasks in the list.
--------------------------------------------------------
```
-------------------------------------------------------------------------------
### `delete location _` - Deletes a location

Deletes an existing location in the list of locations, _ is the index of the location to delete.

Example of usage:

`delete location 0`

Expected outcome:

The first location in the list will be deleted
```
--------------------------------------------------------
Noted. I've removed this location:
  [L][ ] Ann Siang Hill
Now you have 0 locations in the list.
--------------------------------------------------------
```
-------------------------------------------------------------------------------
### `list tasks` - Lists all existing tasks

Lists all tasks in the list of tasks. If the list is empty, a message will indicate so.

Example of usage:

`list tasks`

Expected outcome:

All existing tasks will be listed
```
--------------------------------------------------------
1.[D][ ] byebye (by: Monday, Dec 02, 2222 19:01 pm)
--------------------------------------------------------
```
-------------------------------------------------------------------------------
### `list locations` - Lists all existing locations

Lists all locations in the list of locations. If the list is empty, a message will indicate so.

Example of usage:

`list locations`

Expected outcome:

All existing locations will be listed usually, but since the list is empty, a message will indicate so.
```
The file and list is empty
```
-------------------------------------------------------------------------------
### `bye` - Exits the program

bye

Example of usage:

`bye`

Expected outcome:

bye
```
The file and list is empty
```
