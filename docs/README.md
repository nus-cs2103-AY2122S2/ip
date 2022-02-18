# Michael
### Organise your life, Free up your mind!
> "Your mind is for having ideas, not holding them." – David Allen [(source)](https://dansilvestre.com/productivity-quotes/)

Michael is a Desktop Application that manages your tasks for you.
It is designed with JavaFX giving Michael a fun and unique Graphic User Interface (GUI). 

Organising your tasks will never be easier!

<hr>

## Features
### Add your Tasks

- `todo`
- `event`
- `deadline`

### List your Tasks

- `list`

### Find a Task

- `find`

### Save all Tasks

- `save`

### Delete a Task

- `delete`

### Mark or Un-mark a Task

- `mark`
- `unmark`

### Close Michael

- `bye`

### In-Application Manual on how to use Michael

- `help`
<hr>


## Usage
### `todo` - Adds a Todo Task into Michael

Adds a Todo task into the TaskList

Format: `todo (task name)`

Example: `todo Meet Jim`

Expected outcome:
```
OMG IT'S HAPPENING! EVERYBODY STAY CALM!

Added to your tasks: 
	[T][ ] Meet Jim
You now have 4 tasks in your list
```

### `event` - Adds an Event Task into Michael

Adds an Event task into the TaskList.

* Note: Time should be in a YYYY-MM-DD HH:MM format. 
  * Single digit numbers should be appended with `0` at the front
  * For example: January should be `01` not just `1`

Format: `event (task name) /at YYYY-MM-DD HH:MM`

Example: `event Jim's Birthday Party /at 2022-11-12 12:20`

Expected outcome:
```
OMG IT'S HAPPENING! EVERYBODY STAY CALM!

Added to your tasks: 
	[E][ ] Jim's Birthday Party  (at: Nov 12 2022, 12:20)
You now have 5 tasks in your list
```

### `deadline` - Adds a Deadline Task into Michael

Adds a Deadline task into the TaskList.

* Note: Time should be in a YYYY-MM-DD HH:MM format.
  * Single digit numbers should be appended with `0` at the front
  * For example: January should be `01` not just `1`

Format: `deadline (task name) /by YYYY-MM-DD HH:MM`

Example: `deadline Buy a Gift for Jim's Birthday /by 2022-11-12 12:20`

Expected outcome:
```
OMG IT'S HAPPENING! EVERYBODY STAY CALM!

Added to your tasks: 
	[D][ ] Buy a Gift for Jim's Birthday  (by: Nov 12 2022, 12:20)
You now have 3 tasks in your list
```

### `list` - List out all tasks that are saved to Michael

List out all tasks that are saved to Michael.

Format: `list`

Expected outcome:
```
OMG IT'S HAPPENING! EVERYBODY STAY CALM!

1: [E][ ] Jim's Birthday Party  (at: Nov 13 2022, 12:20)
2: [D][ ] Buy Jim's Birthday Gift  (by: Nov 11 2022, 23:59)
3: [T][ ] Meet Jim
```

### `find` - Find a Task in Michael

Find a Task stored in Michael.

Note: The keyword is Case Sensitive. Ensure that the right case of the keyword is inputted.

Format: `find (keyword)`

Example: `find Birthday`

Expected outcome:
```
OMG IT'S HAPPENING! EVERYBODY STAY CALM!

1: [E][ ] Jim's Birthday Party  (at: Nov 13 2022, 12:20)
2: [D][ ] Buy Jim's Birthday Gift  (by: Nov 11 2022, 23:59)
```

### `save` - Save all tasks that are added to Michael

Writes all tasks that are added to Michal to a Text file named `michael.txt`

Format: `save`

Expected outcome:
```
OMG IT'S HAPPENING! EVERYBODY STAY CALM!

Your Tasks has been saved into your device!
```

### `delete` - Delete a Task in Michael

Delete a task with the corresponding task number

Format: `delete (task number)`

Example: `delete 1`

Expected outcome:
```
OMG IT'S HAPPENING! EVERYBODY STAY CALM!

Removed from your tasks: 
	[E][ ] Jim's Birthday Party  (at: Nov 13 2022, 12:20)
You now have 2 tasks in your list
```


### `mark` - Mark a Task in Michael

Marks a task with the corresponding task number

Format: `mark (task number)`

Example: `mark 1`

Expected outcome:
```
OMG IT'S HAPPENING! EVERYBODY STAY CALM!

Nice! I've marked this task as done: 
	[D][X] Buy Jim's Birthday Gift  (by: Nov 11 2022, 23:59)
```

### `unmark` - Un-mark a Task in Michael

Un-marks a task with the corresponding task number

Format: `unmark (task number)`

Example: `unmark 1`

Expected outcome:
```
OMG IT'S HAPPENING! EVERYBODY STAY CALM!

Alright, I've marked this task as not done yet: 
	[D][ ] Buy Jim's Birthday Gift  (by: Nov 11 2022, 23:59)
```

### `bye` - Closes Michael

Closes the program Michael. 

Note: 
- Tasks that are added are also automatically saved to `michael.txt` upon this command.
- The Window will take about 2 seconds before closing.

Format: `bye`

Expected outcome:
```
OMG IT'S HAPPENING! EVERYBODY STAY CALM!

GoodBye! I hope to see you again!

Please wait as Michael closes!
```

### `help` - In-Application Manual to help navigate

Manual of all the commands that can be used in Michael.

Format: `help`

Expected outcome:
```
OMG IT'S HAPPENING! EVERYBODY STAY CALM!

Here is a list of commands that you can use!

==> Utility commands that you can use!
- 'save' : 
	=> Save all tasks in Michael into a local file.
- 'bye' : 
	=> Exit Michael. All will be saved upon this command.
- 'list' : 
	=> List out all the tasks added into Michael.
- 'find *keyword*' : 
	=> Find all tasks with the *keyword*
	=> Eg. find Jim

==> Commands to add a task!
- 'todo *todo name*' : 
	=> Add a todo task into Michael.
	=> Eg. todo Meet Jim
- 'event *event name* /at *YYYY-MM-DD HH:MM*' : 
	=> Add an event task into Michael.
	=> Eg. event Jim's Birthday Party /at 2022-11-13 12:20
- 'deadline *deadline name* /by *YYYY-MM-DD HH:MM*' : 
	=> Add a deadline task into Michael.
	=> Eg. deadline Buy Jim's Birthday Gift /by 2022-11-11 23:59

==> Commands to edit a task on Michael.
- 'mark *task#*' : 
	=> Mark a task as completed.
	=> Eg. mark 1
- 'unmark *task#*' : 
	=> Un-mark a task as incomplete.
	=> Eg. unmark 1
- 'delete *task#*' : 
	=> Delete a task from Michael.
	=> Eg. delete 1
```

