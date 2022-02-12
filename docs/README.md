TaskJamie is a desktop app used to keep track of tasks. It provides a Graphical User Interface (GUI) for users to input their commands.
# User Guide (Set up)

1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from  [here](https://github.com/ChanWeiJie/ip/releases/tag/A-Jar2)
3. Copy the file to the folder you want to use as the _home folder_ for your TaskJamie.
4. Double-click the file to start the app. The GUI should not appear similar to the one found [here](https://chanweijie.github.io/ip/Ui.png). This is because
the app does not contain any sample data. 

## Features

## Adding a task : `todo` , `event`, `deadline`

Adds a task into TaskJame.

Format for `todo`: `todo DESCRIPTION`

Format for `event`: `event DESCRIPTION /at DATE START_TIME END_TIME`

Format for `deadline`: `deadline DESCRIPTION /by DATE TIME`

**NOTES** the following are the format for the date and any of the mentioned time
- `TIME`: `hhmm`
- `DATE`: `yyyy-mm-dd`

Example of usage: 
- `todo run`
- `deadline return book /by 2022-05-28 2000`
- `event book sale /at 2022-05-27 0800`		  

Expected outcome: 

TaskJamie would record that task and save it. 
TaskJamie should show a message `Got it. I've added this task: task_description` 
and TaskJamie would tell you how many tasks are in the list. Eg: `Now you have x tasks in your list.`


## Deleting a task : `delete`

Deletes a task from the list in TaskJamie.

Format : `delete INDEX`

- `INDEX` would be the index of the task to be deleted from the list.

Example of usage: `delete 1`

Expected outcome: 
TaskJamie will remove the task at index 1.
TaskJamie should show a message `Got it. I've removed this task: task_description`and 
TaskJamie would tell you how many tasks are left in the list. Eg: `Now you have x tasks in your list.`


## Listing all tasks: `list`

Shows a list of all tasks in the address book.

Format : `list`


## Checking/Unchecking a task on the list : `unmark`, `mark`

Marks/Unmarks the specified task in the list as done or not done.

Format for `mark` : `mark INDEX`

Format for `unmark` : `unmark INDEX`

- `INDEX` would be the index of the task to be deleted from the list.

Example usage : 
- `mark 1`
- `unmark 1` 

Expected outcome:
 
TaskJamie will mark the task at index 1.
TaskJamie should show a message `Nice! I've marked this task as done: task_description`
and TaskJamie would Mark and `X` on that task to signify that the task is done. The negation is true as well (for unmark).


## Listing all `events` and `deadlines` from a particular `date`: `schedule`

Shows a list of all tasks that have the same date provided.

Format : `schedule DATE`

- As stated above, the format for `DATE` would be **`yyyy-mm-dd`**

Example usage : `schedule 2022-05-29`

Expected outcome: 
TaskJamie will list all the task that are associated with the given date and 
should show a message `Here is your Schedule for DATE`.

## Exiting the program : `bye`

Exits the program.

Format: `bye`

## Saving the data

Tasks stored in TaskJamie are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

