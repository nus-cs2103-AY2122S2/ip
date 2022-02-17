# User Guide

## Features 

### Feature-Store to-do list permanently

Store the to-do list in a file even after the bot has exited.

### Feature-Priority

Assign priorities to certain tasks.

## Usage

### `deadline` - Add tasks with a deadline to your to-do list.

deadline ... /by ... adds a task with a deadline to your to-do list.
The task will be written to the data/todolist.txt file.
Priority can optionally be specified by adding :p* where * is between 1 and 4.
You must specify a description of the task and the deadline time in YYYY-MM-DD format.

Example of usage: 

`deadline return book /by 2002-02-02 :p2`

Expected outcome: 
A task is added to your to-do list, and the data/todolist.txt file also stores the new task.
The following will be output on the screen-
```
[D][ ] (Priority 2) return book (by Feb 2 2002)
```

### `delete` - Deletes specific task from to-do list.

delete ... removes the task with the specified serial number from your to-do list.
The task will also be removed from the data/todolist.txt file.
You must specify the serial number of the task to be deleted.

Example of usage:

Existing to-do list is
```
1. [T][X] borrow book
2. [D][X] return book (by Feb 2 2002)
3. [E][ ] attend birthday party (at May 5 2005)
```
`delete 3`

Expected outcome:
The third task is removed from the to-do list and data/todolist.txt.
```
I've removed this task:
attend birthday party
Now you have 2 tasks in the list.
```

### `event` - Add tasks with a specific time to your to-do list.

event ... /by ... adds a task occurring at a specific time to your to-do list.
The task will be written to the data/todolist.txt file.
Priority can optionally be specified by adding :p* where * is between 1 and 4.
You must specify a description of the task and the event time in YYYY-MM-DD format.

Example of usage:

`event attend birthday party /at 2005-05-05 :p3`

Expected outcome:
A task is added to your to-do list, and the data/todolist.txt file also stores the new task.
The following will be output on the screen-
```
[E][ ] (Priority 3) attend birthday party (at May 5 2005)
```

### `find` - Find tasks with certain keywords in to-do list.

find ... outputs tasks in the to-do list with certain words in the title.
You must specify the set of characters/word//phrase you want to search for

Example of usage:

Existing to-do list is
```
1. [T][X] borrow book
2. [D][X] (Priority 2) return book (by Feb 2 2002)
3. [E][ ] (Priority 3) attend birthday party (at May 5 2005)
```
`find book`

Expected outcome:
The following is output onto the screen:
```
1. [T][X] (Priority 2) borrow book
2. [D][X] (Priority 3) return book (by Feb 2 2002)
```

### `list` - Shows user to-do list

list command outputs the entire existing to-do list.

Example of usage:

`list`

Expected outcome:
The following is output onto the screen:
```
1. [T][X] borrow book
2. [D][X] (Priority 2) return book (by Feb 2 2002)
3. [E][ ] (Priority 3) attend birthday party (at May 5 2005)
```

### `mark` - Marks an action as finished

mark ... marks the task with the specified serial number as done.
The change will also be reflected in the data/todolist.txt file.
You must specify the serial number of the task to be marked as done.

Example of usage:

`keyword (optional arguments)`

Expected outcome:
Existing to-do list is
```
1. [T][X] borrow book
2. [D][ ] (Priority 2) return book (by Feb 2 2002)
3. [E][ ] (Priority 3) attend birthday party (at May 5 2005)
```
`mark 2`

Expected outcome:
The second task is marked as done, and the data/todolist.txt file is also updated accordingly.
The following will be output on the screen-
```
[D][X] (Priority 3) return book (by Feb 2 2002)
```

### `todo` - Add generic task to your to-do list.

todo ... adds a task to your to-do list.
The task will be written to the data/todolist.txt file.
Priority can optionally be specified by adding :p* where * is between 1 and 4.

Example of usage:

`todo borrow book`

Expected outcome:
A task is added to your to-do list, and the data/todolist.txt file also stores the new task.
The following will be output on the screen-
```
[T][ ] borrow book
```

### `unmark` - Marks an action as finished

unmark ... marks the task with the specified serial number as *not* done.
The change will also be reflected in the data/todolist.txt file.
You must specify the serial number of the task to be marked as not done.

Example of usage:

`keyword (optional arguments)`

Expected outcome:
Existing to-do list is
```
1. [T][X] borrow book
2. [D][ ] (Priority 2) return book (by Feb 2 2002)
3. [E][ ] (Priority 3) attend birthday party (at May 5 2005)
```
`unmark 1`

Expected outcome:
The first task is marked as *not* done, and the data/todolist.txt file is also updated accordingly.
The following will be output on the screen-
```
[T][ ] borrow book
```

## Download Release

1. Go to https://github.com/MontyPython28/ip/releases/tag/A-Release
2. Download `aceattorney.jar`
3. Go to the Command Line, navigate to the correct directory and type `java -jar aceattorney.jar`