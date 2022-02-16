# User Guide
Duke is a personal assistant chatbot that helps you to keep track of your daily tasks and schedules.


## Quick Start
1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest Duke.jar
3. Copy the file to the folder you want to use as the home folder for your application.
4. Open a command window in that folder.
5. Run the command `java -jar Duke.jar`. A folder named 'data' containing a file 'duke.txt' will be created in the same 
folder to store data from the application.


## Features 

### Adding Task: `todo`

Adds a to-do task to a list.

Format: `todo DESCRIPTION`

Example of usage: `todo return book`

Expected outcome:
```
Got it! I've added this task:
    [T][] return book
There is 1 task in the list now.    
```

###
### Adding Task: `deadline`

Adds a task that needs to be done by a specific date and time to a list.

Format: `deadline DESCRIPTION /by DATE TIME`
- Date Format: `yyyy-mm-dd`
- Time Format: `HH:mm`

Example of usage: `deadline submit assignment /by 2022-02-19 13:00`

Expected outcome:
```
Got it! I've added this task:
    [D][] submit assignment (by: 19 Feb 2022, 1:00 PM)
There are 2 tasks in the list now.    
```

###
### Adding Task: `event`

Adds a task that takes place at a specific date and time to a list.

Format: `event DESCRIPTION /at DATE TIME`
- Date Format: `yyyy-mm-dd`
- Time Format: `HH:mm`

Example of usage: `event interview /at 2022-02-21 10:00`

Expected outcome:
```
Got it! I've added this task:
    [E][] interview (at: 21 Feb 2022, 10:00 AM)
There are 3 tasks in the list now.    
```

###
### Listing Tasks: `list`

Displays list of all added tasks.

Format: `list`

Example of usage: `list`

Expected outcome:
```
Here are the tasks in your list:
    1. [T][] return book
    2. [D][] submit assignment (by: 19 Feb 2022, 1:00 PM)
    3. [E][] interview (at: 21 Feb 2022, 10:00 AM)
```

###
### Deleting Task: `delete`

Deletes the specified task from the list.

Format: `delete INDEX`
- `INDEX` refers to the index number in the task list

Example of usage: `delete 2`

Expected outcome:
```
Okay, I've deleted this task:
    [D][] submit assignment (by: 19 Feb 2022, 1:00 PM)
There are 2 tasks in the list now.    
```

###
### Marking Task: `mark`

Marks a specified task as complete.

Format: `mark INDEX`
- `INDEX` refers to the index number in the task list

Example of usage: `mark 1`

Expected outcome:
```
Nice! You've completed this task:
    [T][X] return book        
```

###
### Unmarking Task: `unmark`

Marks a specified task as complete.

Format: `unmark INDEX`
- `INDEX` refers to the index number in the task list.

Example of usage: `mark 1`

Expected outcome:
```
Okay, I've marked this task as undone:
    [T][] return book        
```

###
### Finding Task: `find`

Find tasks which descriptions contain the specified keyword.

Format: `find KEYWORD`
- `KEYWORD` is case-sensitive
- Tasks with words partially matching keyword will also be returned 
(e.g. proj will match project)

Example of usage: `find assignment`

Expected outcome:
```
Here are the matching tasks in your list:
    1. [D][] submit assignment (by: 19 Feb 2022, 1:00 PM)
```

###
### Updating Task: `update`

Updates a task with a given new description.

Format: 
- For todo task: `update INDEX DESCRIPTION`
- For deadline task: `update INDEX DESCRIPTION /by DATE TIME`
- For event task: `update INDEX DESCRIPTION /at DATE TIME`

Example of usage: `update 2 interview /at 2022-02-21 12:00`

Expected outcome:
```
Done! This is the updated task:
    [E][] interview (at: 21 Feb 2022, 12:00 PM)
```

###
### Exiting Application: `bye`

Closes the app after goodbye message.

Format: `bye`

Example of usage: `bye`

Expected outcome:
```
"Bye! Hope to see you again soon!"
```
###
## Command Summary
| Action | Format                                                                                                                   |
|--------|--------------------------------------------------------------------------------------------------------------------------|
| Add    | `todo DESCRIPTION`<br/> `deadline DESCRIPTION /by DATE TIME`<br/> `event DESCRIPTION /at DATE TIME`                      |
| List   | `list`                                                                                                                   | 
| Delete | `delete INDEX`                                                                                                           |
| Mark   | `mark INDEX`                                                                                                             |
| Unmark | `unmark INDEX`                                                                                                           |
| Update | `update INDEX DESCRIPTION` <br/> `update INDEX DESCRIPTION /by DATE TIME` <br/> `update INDEX DESCRIPTION /at DATE TIME` |
| Find   | `find KEYWORD`                                                                                                           |
| Exit   | `bye`                                                                                                                    |


