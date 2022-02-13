# Duke Task Manager User Guide
Duke is a task manager that keeps track of various types of tasks and compiles it into a list for your convenience.

##Quick Setup
1. Install Java 11 or above on your device.
2. Run ```java -jar duke.jar```

##Features
### 1. [Add Todo](#todo---adds-todo-task)

## Commands 
### `todo` - Adds Todo task
Adds a simple todo task.\
Format Syntax:```todo [task]```

Example:

```todo go home```

Expected Outcome:
```
Got it. I've added this task:
[T][] go home
Now you've got 1 task in the list.
```

### deadline task

Add a simple task with a deadline.\
Format Syntax:```deadline [task] /by [yyyy-dd-mm]```

Example:

```deadline homework assignment /by 2022-02-14```

Expected Outcome:
```
Got it. I've added this task:
[D][] homework assignment (by Feb 14 2022)
Now you've got 2 task in the list.
```

### event task

Add a simple task with a location.\
Format Syntax:```event [task] /at [location]```

Example:

```event LAN party /at Samuel's place```

Expected Outcome:
```
Got it. I've added this task:
[E][] LAN party (at Samuel's place)
Now you've got 3 task in the list.
```

### do_after task

Add a simple task to do after another task.\
Format Syntax:```do_after [task] /after [task]```

Example:

```do_after cry /after finals```

Expected Outcome:
```
Got it. I've added this task:
[A][] cry (after finals)
Now you've got 4 task in the list.
```

### list

Show all the current tasks you have in the list.\
Format Syntax:```list```

Expected Outcome:
```
Here are the tasks in your list:
1. [T][ ] go home
2. [D][ ] homework assignment (by Feb 14 2022)
3. [E][ ] LAN Party (at Sammy boyo's house)
4. [A][X] cry (after finals)
```

### mark

Marks a task at the given index as completed.\
Format Syntax:```mark [index]```

Example:

```mark 2```

Expected Outcome:
```
Nice! I've marked this task as done:
[D][X] homework assignment (by Feb 14 2022)
```

### unmark

Marks a task at the given index as in-complete.\
Format Syntax:```unmark [index]```

Example:

```unmark 2```

Expected Outcome:
```
Oof! I've marked this task as undone:
[D][ ] homework assignment (by Feb 14 2022)
```

### delete

Deletes a task at the given index in the task list.\
Format Syntax:```delete [index]```

Example:

```delete 2```

Expected Outcome:
```
Noted. I've removed this task:
[D][X] homework assignment (by Feb 14 2022)
Now you have 3 tasks in the list.
```

### find

Finds a task in the list.\
Format Syntax:```find [task]```

Example:

```find cry```

Expected Outcome:
```
Here are the matching tasks in your list:
1. [A][X] cry (after finals)
```

### bye

Closes the application.\
Format Syntax:```bye```

Expected Outcome:
```
Bye. Hope to see you again soon!
```