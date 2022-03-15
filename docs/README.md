# User Guide for Duke
### List
To list out all the task

**Example of usage:**
'list'

**Expected Outcome:**
```
1. [T][ ] buy bread
2. [E][X] project meeting (at: 02/12/2019)
3. [D][X]return book (by: 05/12/2019 1300)
4. [T][ ]enroll cooking class
5. [T][ ]apply internship
6. [T][ ] borrow book
```
### Todo <task name>

Adds a task that you want to do in Duke.

**Example of usage:**
'todo read book'

**Expected Outcome:**
```
Got it. I've added this task: 
[T][ ] buy book
Now you have 1 tasks in the list.
```

### Event <taskname> </at> <date> <time>

Adds an event you want to attend into Duke. Include the venue, date and time in the syntax as shown.

**Example of usage:**
'event project meeting /at 01/01/2022 2200'

**Expected outcome:**
```
Got it. I've added this task:
[E][ ] project meeting (at: 01/01/2022 2200)
Now you have 1 tasks in the list.
```


### Deadline <taskname> </by> <date> <time>

Adds a task with a deadline to duke.

**Example of usage:**
'deadline chinese project submission /by 01/01/2022 2200'

**Expected outcome:**
```
Got it. I've added this task:
[D][ ] chinese project submission (ny: 01/01/2022 2200)
Now you have 8 tasks in the list.
```

### Mark <task index>

Marks the task you have completed.

**Example of usage:**
'mark 1'

**Expected outcome:**
```
Nice! I've marked this task as done:
[T][X] buy bread
```
### 'delete <task index>'

Deletes a task in Duke.

**Example of usage:**
'delete 1'

**Expected outcome:**
```
Noted. I've removed this task
[T][ ] buy bread
Now you have 7 tasks in the list.
```
###Find <task key word>

Finds all tasks with a keyword in Duke.

**Example of usage:**
'find book'

**Expected outcome:**
```
Here are the matching tasks in your list:
[D][X] return book
[T][ ] borrow book
[T][ ] buy book
```
### Bye

Exits Duke.

**Example of usage:**
'bye'

**Expected outcome:**
Program closes

### Help
Shows a list of commands.

**Example of usage:**
'help'

**Expected outcome:**
```
todo <taskname> : Adds a todo task to the tasklist
event <taskname> /at dd/mm/yyyy HHmm : Adds an event task to the task list
deadline <taskname> /by dd/mm/yyyy HHmm : Adds a deadline task to the task list
list : Displays the task list
mark <index> : Marks a task at <index> as done
delete <index> : Deletes a task at <index>
find <keyword> : Displays all tasks with <keyword>
bye : Exits the program
```