# User Guide
Welcome! Spark is a friendly chatbot that helps you keep track of your todos, deadlines and events!

Here's how you can interact with him 😁

## Features
- keep track of your `todos`, `deadlines` and `events`
- avoid adding duplicate tasks by mistake

### Keep track of your `todos`, `deadlines` and `events`
Spark helps remember important tasks and dates!

### Avoid adding duplicate tasks by mistake
Whenever you add a task with the same name as one that you've previously added, Spark will show you a helpful reminder that you've done so!

## Usage

### `list` - list all your tasks
To get an overview of all yours `todos`, `deadlines`, and `events`, use the `list` command.

Example of usage:

`list`

Expected outcome:

```
Here are your tasks:
1.[T][] buy milk
2.[D][] submit assignment (by: 28 Feb 2022, 11:59pm)
3.[E][] Sam's birthday party (at: 20 Feb 2022, 7:00pm)
```

### `todo` - Add a task
Got a task that you want to do, but that is non-urgent? Use the `todo` command to record it.

Example of usage: 

`todo task name`

note that you always have to give your task a name! (or you'll make Spark angry 😡)

Expected outcome:

```
Okay! I've added this task:
   [T][] task name
```

Once you've added a valid `todo`, Spark will show you a confirmation message with the name of the todo you've just added!

### `deadline` - Add a task with a deadline

Something urgent? Use the `deadline` command to record it.

Example of usage: 

`deadline task name /by 02-28-2022 2359`

note that you always have to give your deadline a name **and a date** with the following format `/by dd-mm-yyyy hhmm`

Expected outcome:

```
Okay! I've added this task:
   [D][] task name (by: 28 Feb 2022, 11:59 pm)
```

Likewise, once you've added a valid `deadline`, Spark will show you a confirmation message with the name of the deadline you've just added!

### `event` - Add an event

Need to remind yourself of an important upcoming event? Use the `event` command to record it.

Example of usage: 

`event event name /at 02-28-2022 2359`

note that you always have to give your deadline a name **and a date** with the following format `/at dd-mm-yyyy hhmm`

Expected outcome:

```
Okay! I've added this task:
   [E][] event name (at: 28 Feb 2022, 11:59 pm)
```

As usual, once you've added a valid `event`, Spark will show you a confirmation message with the name of the event you've just added!


### `mark` - Mark a completed task
Once you've finished that assignment, or attended that birthday party, use the `mark` command to recorded it as completed!

Example of usage:

`mark 2`

Expected outcome:

```
Okay! I've marked this task:
   [D][X] submit assignment (by: 28 Feb 2022, 11:59pm)
```

Once you've marked a task as done, a cross `[X]` will be filled in, and the task would appear like so whenever you list your tasks, like so:

```
Here are your tasks:
1.[T][] buy milk
2.[D][X] submit assignment (by: 28 Feb 2022, 11:59pm)      <---- done! 😃
3.[E][] Sam's birthday party (at: 20 Feb 2022, 7:00pm)
```

### `unmark` - Restore a task
Oops! Maybe that assignment needed some corrections! No worries, simply use the `unmark` command!

Example of usage:

`unmark 2`

Expected outcome:

```
Okay! I've unmarked this task:
   [D][] submit assignment (by: 28 Feb 2022, 11:59pm)
```

### `delete` - Delete a task
Finally done with that pesky assignment? Awesome! Let's get it out of the way with the `delete` command 😂

Example of usage:

`delete 2`

Expected outcome:

```
Okay! I've removed this task:
   [D][X] submit assignment (by: 28 Feb 2022, 11:59pm)
```

This would ***permanently remove*** the task from your list:
```
Here are your tasks:
1.[T][] buy milk
2.[E][] Sam's birthday party (at: 20 Feb 2022, 7:00pm)       <--- 💨 assignment is gone!
```

### `bye` - Exit

Ready to get back to work? Say `bye` to Spark!

Example of usage:

`bye`

Expected outcome:

```
Okay bye!
```




