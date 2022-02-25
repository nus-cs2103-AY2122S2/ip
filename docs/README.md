# User Guide

## Requirements
- [ ] Java 11 Runtime
- [ ] Comic Sans MS Bold font installed

## Features 

### Feature-Create a tasklist for multiple types of tasks

- [ ] Todo tasks
- [ ] Event tasks
- [ ] Deadline tasks

### Feature-Mark tasks that are done/ Unmark tasks that are not done

### Feature-Save tasks added into a file for viewing later

### Feature-Find tasks added to tasklist

Using a search function, you can find any tasks in the list

## Usage

### `help` - Brings you to a help page on the web (This page)

### `list` - Lists out all the items in the tasklist

All tasks in tasklist are listed out in index order based
on when they were added.

Due to limitations, only some tasks can be listed out.

To see all your tasks, make sure you have the following file
created in the directory: C:/repos/ip/data/tasks.txt. Then click
on the menubar and go to help -> my tasks. This will open a file
containing your added tasks.

### `bye` - Summons a goodbye message from Friendly Friend

After the message, the app automatically closes (almost immediately unfortunately).

### `todo` - Adds a normal task

Format: *todo the_task*

Example of usage: 

`todo pass CS2103T`

Expected outcome:

![pass cs2103](https://user-images.githubusercontent.com/77189033/153693340-37f3da52-1658-4560-ae95-942952d6f5e3.jpg)

### `deadline` - Adds a deadline task

A deadline task is a task with a deadline.

Format: *deadline the_task /by yyyy-mm-dd H:m*

Example of usage: 

`deadline finish ip /by 2193-12-19 23:59`

Expected outcome:

![deadline screenshot](https://user-images.githubusercontent.com/77189033/153693548-494de2d5-8a37-4dca-b8fc-145f19af1cdc.jpg)

### `event` - Adds a event task

A event task is a task with a time.

Format: *event the_task /at yyyy-mm-dd H:m*

Example of usage: 

`event survive the semester /at 2193-12-19 00:00`

Expected outcome:

![event screenshot](https://user-images.githubusercontent.com/77189033/153693682-7bb7747b-9683-4e1b-9b11-94d4808fccdb.jpg)

### `mark` - Marks a task as done

A task marked as done has a 'X' in one of its brackets.

Format: *mark the_index* (Note: 0 < the_index value <= tasklist size)

Example of usage: 

`mark 1`,`mark 3` 

### `unmark` - Unarks a marked task

Removes the 'X' from the brackets of the task.

Format: *unmark the_index* (Note: 0 < the_index value <= tasklist size)

Example of usage: 

`unmark 1`,`unmark 12`

### `delete` - Removes selected task from tasklist

Removes a task according to its index, from the tasklist.

Format: *delete the_index* (Note: 0 < the_index value <= tasklist size)

Example of usage: 

`delete 1`, `delete 3`

### `clear` - Clears all tasks from the list

### `find` - Finds a task on the list

Finds a task based on a keyword provided.

Format: *find the_keyword*

Example of usage: 

`find baboon`, `find bab`, `find baboo`

Expected outcome:

![baboon](https://user-images.githubusercontent.com/77189033/153694046-814cdb1a-183a-4502-a60a-d435d56884a6.jpg)


