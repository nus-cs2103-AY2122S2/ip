TaskJamie is a desktop app used to keep track of tasks. It provides a Graphical User Interface (GUI) for users to input their commands.
# User Guide (Set up)

1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from  [here](https://github.com/ChanWeiJie/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the _home folder_ for your TaskJamie.
4. Double-click the file to start the app. The GUI should not appear similar to the one found [here](https://chanweijie.github.io/ip/Ui.png). This is because
the app does not contain any sample data. 

## Features

## Adding a task : `todo` , `event`, `deadline`

Adds a task into TaskJame.

Format for `todo`: `todo DESCRIPTION`
Format for `deadline`: `deadline DESCRIPTION /by DATE TIME`
Format for `event`: `event DESCRIPTION /at DATE START_TIME END_TIME`


**NOTE** the following are the format for the date and any of the mentioned time
- `TIME`: `hhmm`
- `DATE`: `yyyy-mm-dd`

Example of usage: 
- `todo run`
- `deadline return book /by 2022-05-28 2000`
- `event book sale /at 2022-05-27 0800 2000`		  

Expected outcome:  
- After running `todo run`
```
"Got it. I've added this task:
[T][] run
Now you have 1 tasks in the list."
```
- After running `deadline return book /by 2022-05-28 2000`
```
"Got it. I've added this task:
[D][] return book (by: May 28 2022 8:00PM)
Now you have 2 tasks in the list."
```
- After running `event book sale /at 2022-05-27 0800 2000`
```
"Got it. I've added this task:
[D][] book sale (at: May 27 2022 8:00AM to 8:00PM)
Now you have 3 tasks in the list."
```

## Deleting a task : `delete`

Deletes a task from the list in TaskJamie.

Format : `delete INDEX`

- `INDEX` would be the index of the task to be deleted from the list.

Example of usage: `delete 1`

Expected outcome:  
**Assuming the first task is `todo run`**
```
"Noted. I've removed this task:
[T][] run
Now you have 0 tasks in the list."
```

## Listing all tasks: `list`

Shows a list of all tasks recorded by TaskJamie.

Format : `list`  

Expected outcome:
```
"Here are the tasks in your list:
1. task 1
2. deadline 1
3. event 1 "
```

## Checking/Unchecking a task on the list : `unmark`, `mark`

Marks/Unmarks the specified task in the list as done or not done.

Format for `mark` : `mark INDEX`

Format for `unmark` : `unmark INDEX`

- `INDEX` would be the index of the task to be deleted from the list.

Example usage : 
- `mark 1`
- `unmark 1` 

Expected outcome:
**Assuming the first task is `todo run`**
```
If its mark 1 : 
"Nice I've marked this task as done:
[T][X] run"

If its unmark 1 :
"Nice I've marked this task as not done yet:
[T][] run"
```

## Listing all `events` and `deadlines` from a particular `date`: `schedule`

Shows a list of all tasks that have the same date provided.

Format : `schedule DATE`

- As stated above, the format for `DATE` would be **`yyyy-mm-dd`**

Example usage : `schedule 2022-05-28`

Expected outcome:  
**Assuming only task is `deadline return book /by 2022-05-28 2000`**
```
"Here is your Schedule for: 2022-05-28
1.[D][] return book (by: May 28 2022 8:00PM)
```

## Exiting the program : `bye`

Exits the program.

Format: `bye`

## Saving the data

Tasks stored in TaskJamie are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

