# User Guide
Duke bot is a chat bot designed to be used via a command line interface(CLI) with the benefits of using a graphical user interface(GUI).

## Features
>list : prints the list of stored tasks<br>
bye : terminates the program<br>
todo [your task input] : adds a todo type task<br>
event [your task input] /at [YYYY-MM-DD hhmm]: adds an event type task<br>
deadline [your task input] /by [YYYY-MM-DD hhmm]: adds a deadline type task<br>
delete[number] : deletes the selected task<br>
mark[number] : mark the selected task as done<br>
unmark[number] : mark the selected task as not done<br>
find [search string] : finds tasks that match the string description<br>
undo : undo the previous command<br>

### `list` - prints the list of stored tasks


Example of usage:

`list`

Expected outcome:

>1.[T] [X] sample_todo  
2.[D] [ ] sample_deadline 2020-11-11 1200  
3.[T] [ ] sample_todo_2

### `bye` - terminates the program


Example of usage:

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`

### `todo` - adds a todo type task

Example of usage:

`todo sample_todo`

Expected outcome:

>Got it. I've added this task: <br>
[T][X] sample_todo<br>
You now have 1 tasks in your list

### `event` - adds an event type task

Example of usage:

`event sample_event /at 2020-01-01 1111`

Expected outcome:

>Got it. I've added this task:<br>
[E][X] sample_event 2020-11-11 1200  
You now have 1 tasks in your list

### `deadline` - adds a deadline type task

Example of usage:

`deadline wakeup /by 2020-01-01 1111`

Expected outcome:

>Got it. I've added this task:<br>
> [D][X] sample_deadline 2020-11-11 1200  
>You now have 1 tasks in your list

### `delete` - deletes the selected task

Example of usage:

`delete 1`

Expected outcome:

>Noted. I've removed this task:<br>
[T][X] sample_todo
You now have 0 tasks in your list

### `mark` - mark the selected task as done

Describe action and its outcome.

Example of usage:

`mark 1`

Expected outcome:

>Nice! I've marked this task as done:<br>
[T][X] sample

### `unmark` - mark the selected task as not done

Describe action and its outcome.

Example of usage:

`unmark 1`

Expected outcome:

>Nice! I've marked this task as not done:<br>
[T][ ] sample

### `find` - finds tasks that match the string description

Example of usage:

`find sample`

Expected outcome:

>Here are the matching tasks in your list:<br>
> 1. [T][X] sample_todo
> 2. [T][X] sample_todo_2

### `undo` - undo the previous command

Example of usage:

`undo`

Expected outcome:

>Undo task successfully!<br>
> 1. [T][X] sample_todo
> 2. [T][X] sample_todo_2
