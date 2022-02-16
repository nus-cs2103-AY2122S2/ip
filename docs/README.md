# User Guide
________________________________________________________________________________________________________________________
## Features 

### Feature-Add

walle.Walle is able to add 3 types of tasks: walle.ToDo, walle.Event and walle.Deadline.

### Feature-Mark/Unmark

walle.Walle is able to mark and unmark tasks.

### Feature-list

walle.Walle is able to list out all currently tracked tasks.

### Feature-Bye

walle.Walle is able to exit.
________________________________________________________________________________________________________________________
##Keywords

### `walle.ToDo` - Adds tasks of type: todo

Adds one task of type 'todo' with following input as task name.

Example of usage: 

    `todo borrow book`

Expected outcome:

     Got it. I've added this task: 
       [T][ ] borrow book
     Now you have 5 tasks in the list.

### `walle.Deadline` - Adds tasks of type: deadline

Adds one task of type 'deadline' with following input as task name and input after /by as deadline.

Example of usage:

    `deadline return book /by Sunday`

Expected outcome:

     Got it. I've added this task: 
       [D][ ] return book (by: Sunday)
     Now you have 6 tasks in the list.

### `walle.Event` - Adds tasks of type: event

Adds one task of type 'event' with following input as task name and input after /at as deadline.

Example of usage:

    `event project meeting /at Mon 2-4pm`

Expected outcome:

     Got it. I've added this task: 
       [E][ ] project meeting (at: Mon 2-4pm)
     Now you have 7 tasks in the list.

### 'mark' - Marks tasks as done

Expects <mark int> where int is the task number to be marked as done.

Example of usage:

    'mark 2'

Expected outcome:

     Nice! I've marked this task as done: 
       [X] return book

### 'unmark' - Marks tasks as not done

Expects <unmark int> where int is the task number to be marked as not done.

Example of usage:

    'unmark 2'

Expected outcome:

     OK, I've marked this task as not done yet:
       [ ] return book

### 'list' - List all current tasks

List all current tasks

Example of usage:

    'list'

Expected outcome:

     Here are the tasks in your list:
     1.[X] read book
     2.[ ] return book
     3.[ ] buy bread

________________________________________________________________________________________________________________________