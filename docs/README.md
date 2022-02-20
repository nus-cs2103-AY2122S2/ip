# User Guide

DukeLCH is a personal task tracker that works best for users who love CMI!
It also doubles as a contact book if you need to remember contacts on the fly!

## Features 

### Track your tasks 

Have things Todo, Deadlines to remember or Events to attend?

Fret not! DukeLCH will remember them for you!

### Storing Contacts - `bye`

DukeLCH can remember contact information of your friends and family!

But be warned, DukeLCH is bad with names and numbers  and will not remember your contacts once you say goodbye.

## Usage

### `bye` - Closes DukeLCH

Ends the current session with DukeLCH and closes the window.

Example of usage: 

`bye`

Expected outcome:

DukeLCH says goodbye

```
Goodbye. I hope to be of service to you again soon!
```
### `list` - Lists all tasks

Gets DukeLCH to print all the tasks that it currently remembers.

Example of usage:

`list`

Expected outcome:

DukeLCH prints the list of tasks that it remembers
```
This are your tasks that we have in our records:
1. [T][] borrow book
2. [D][] return book (by: Sunday, 10:00AM)
3. [E][] Project Meeting (at: UTown, 12:00PM-02:00PM)
```

### `todo` - Add a task ToDo

DukeLCH will remember this todo task for you.

Example of usage:

`todo [description]`

Expected outcome:

DukeLCH remembers the todo task
```
Understood, adding this task now:
    [T][] borrow book
Currently you have 1 task in our records.
```

### `deadline` - Add a Deadline to a task

DukeLCH will remember this deadline for you so that you finish your task on time.

Example of usage:

`deadline [description] /by [date] [time]`

Expected outcome:

DukeLCH remembers the deadline of the task
```
Understood, adding this task now:
    [D][] return book (by: Sunday, 10:00AM)
Currently you have 2 task in our records.
```

### `event` - Add an Event

DukeLCH will remember this event for you so that you do not forget to attend it.

Example of usage:

`event [description] /at [date] [time]`

Expected outcome:

DukeLCH remembers the event
```
Understood, adding this task now:
    [E][] Project Meeting (at: UTown, 12:00PM-02:00PM)
Currently you have 3 task in our records.
```

### `mark` - Mark a task as done

Let DukeLCH know which tasks you have finished.

Example of usage:

`mark [index]`

Expected outcome:

DukeLCH will mark the task as done
```
Well done! You have completed the task:
    [T][] borrow book
```

### `unmark` - Unmark a task that you have not done

Let DukeLCH know which tasks you have yet to finish.

Example of usage:

`unmark [index]`

Expected outcome:

DukeLCH will unmark the task that you have not done
```
A reminder that the following task has not been done:
    [T][] borrow book
```

### `delete` - Delete a task from DukeLCH's memory

Let DukeLCH know which tasks you no longer want to track.

Example of usage:

`delete [index]`

Expected outcome:

DukeLCH will 'forget' the task
```
Understood, removing this task now:
    [T][] borrow book
Now you have 2 tasks in our records.
```

### `update` - Update DukeLCH's memory on the local device

Get DukeLCH to update the memory on the local device.

Example of usage:

`update`

Expected outcome:

DukeLCH will update the duke.txt file
```
Duke.txt updated successfully!
```

### `addcontacts` - Add a contact to DukeLCH's temporary contact book

Get DukeLCH to remember a contact temporarily

Example of usage:

`addcontacts [name] [contactNumber]`

Expected outcome:

DukeLCH will temporarily remember that contact
```
Welcome a new friend:
    ChokHoe [12345678]
    
Currently you have 1 contacts in our records.
```

### `listcontacts` - List all contacts in DukeLCH's temporary contact book

Get DukeLCH to list all contacts in the contact book

Example of usage:

`listcontacts`

Expected outcome:

DukeLCH will list all contacts that it remembers
```
These are your Contacts in our records:
1. ChokHoe [12345678]
Currently you have 1 contacts in our records.
```

### `deletecontacts` - Delete a contact in DukeLCH's temporary contact book

Get DukeLCH to delete a contact in the contact book

Example of usage:

`deletecontacts [index]`

Expected outcome:

DukeLCH will 'forget' the contact
```
Goodbye for now:
    ChokHoe [12345678]
Now you have 0 contacts in our records.
```
