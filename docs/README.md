# User Guide

## Features 

### Todo List

Adds a task that you intend to do for Jarvis to record down

### Event

Adds an Event that you intend to attend, at a given date/ time for Jarvis to record down

### Deadline

Adds a Deadline that you need to meet, at a given date/ time for Jarvis to record down

### Mark/ Unmark

Marks(Unmarks) a task as completed(incomplete) in the list

### Delete

Deletes a specific task in the list

### Find

Finds a task based on keywords that you keyed in for Jarvis

### Statistics

Jarvis will source out completed tasks within enquiry period

### List

Jarvis will list out the tasks that you have currently

## Usage

### `todo`, `deadline`, `event` - adds tasks to the list

Jarvis adds a generic task to the list and returns a completed message

Example of usage: 

`todo run 10km`, `deadline Assignment 1 /by 2022-02-20`, `event Dance party /at 2022-03-01 9:00PM`

Expected outcome:

Jarvis will return a message indiciating that task is added to the list for all three types of commands
```
    ____________________________________________________________
    Got it. I've added this task:
    [T][ ] run 10 km
    Now you have 3 tasks in the list.
    ____________________________________________________________
```
```
    ____________________________________________________________
    Got it. I've added this task:
    [D][ ] Assignment 1 (by: 20 Feb 2022)
    Now you have 4 tasks in the list.
    ____________________________________________________________
```
```
    ____________________________________________________________
    Got it. I've added this task:
    [E][ ] Dance party (at: 01 Mar 2022, 21:00)
    Now you have 5 tasks in the list.
    ____________________________________________________________

```
### `list` - lists out all the tasks you have in the list

Jarvis returns all the tasks that you have in the list.

Example of usage: 

`list`

Expected outcome:

Jarvis will return a message showing you all the tasks you have
```
    ____________________________________________________________
    Here are the tasks in your list:
    1.[T][ ] run 10km
    2.[D][ ] Assignment 1 (by: 20 Feb 2022)
    3.[E][ ] Dance party (at: 01 Mar 2022, 21:00)
    ____________________________________________________________
 ```

### `mark`, `unmark` - marks or unmarks a task in the list

Jarvis marks(unmarks) a specific task to the list and returns a completed message

Example of usage: 

`mark 1`, `mark 3`, `unmark 3`

Expected outcome:

Jarvis will return a message indiciating that task is marked or unmarked
```
    ____________________________________________________________
    Nice! I've marked this task as done:
    [T][X] run 10km
    ____________________________________________________________
```
```
    ____________________________________________________________
    Nice! I've marked this task as done:
    [E][X] Dance party (at: 01 Mar 2022, 21:00)
    ____________________________________________________________
```
```
    ____________________________________________________________
    OK, I've marked this task as not done yet:
    [E][ ] Dance party (at: 01 Mar 2022, 21:00)
    ____________________________________________________________

```
### `delete` - deletes a specific task in the list

Jarvis deletes a specific task to the list and returns a completed message

Example of usage: 

`delete 1`

Expected outcome:
Jarvis deletes the task in the list and returns a completed message
```
    ____________________________________________________________
    Noted. I've removed this task:
    [T][X] run 10km
    Now you have 2 tasks in the list.
    ____________________________________________________________
```

### `find` - finds tasks in the list using keyword

Jarvis finds tasks to the list matching the keyword provided

Example of usage: 

`find Dance`

Expected outcome:
Jarvis finds tasks in the list matching the keyword and returns them as a list
```
    ____________________________________________________________
    Here are the tasks in your list:
    1.[E][ ] Dance party (at: 01 Mar 2022, 21:00)
    ____________________________________________________________
```

### `stats` - shows completed tasks within the enquiry period provided

Jarvis finds completed tasks in the list within enquiry period and returns them as a list if there are any

Example of usage: 

`stats today`

Expected outcome:
Jarvis finds completed tasks within enquiry period and returns them as a list
```
    ____________________________________________________________
    Here are the tasks completed during enquiry period:
    1. [D][X] Assignment 1 (by: 20 Feb 2022)
    ____________________________________________________________
```
