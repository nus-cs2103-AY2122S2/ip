# User Guide
Duke is an application for **managing and running your tasks**. Structure of user guide adapted from [AB3 User Guide](https://github.com/se-edu/addressbook-level3/blob/master/docs/UserGuide.md).



## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `duke.jar` from [here](https://github.com/lawwm/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Duke.

4. Double-click the file to start the app.

5. Type the command in the command box and press Send to execute it. 
      Some example commands you can try:

    * **`list`** : Lists all contacts.

    * **`todo buy food`** : Adds a Task named `buy food` to Duke.

    * **`delete`**`3` : Deletes the 3rd task shown in the current list.

    * **`bye`** : Exits the app.

6. Have fun using Duke!


## Features 

### Listing all tasks : `list`

Shows a list of all Tasks in Duke.

Format: `list`

### Adding a Todo: `todo`

Adds a todo to duke. Todo has no date and only has a description.

Format: `todo d/DESCRIPTION`

Examples:
* `todo buy food`
* `todo go home`

### Adding a Deadline: `deadline`

Adds a deadline to duke. Deadline has a time and date to complete task by.

Format: `deadline d/DESCRIPTION /by d/DATE t/TIME`

Examples:
* `deadline have pancakes /by 2019-10-10 18:00:00`
* `deadline get salary /by 2023-11-11 11:04:20`

### Adding an Event: `event`

Adds an event to duke. Event has a date and a duration marked by starting time and ending time.

Format: `event d/DESCRIPTION /at d/DATE t/TIME t/TIME`

Examples:
* `event party /at 2019-10-10 18:00:00 20:00:00`
* `event mugging session /at 2021-10-10 08:00:00 20:00:00`

### Mark a task as completed : `mark`

Marks an existing task in Duke as completed.

Format: `mark INDEX`

* Marks the Task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `mark 1`

### Mark a task as uncompleted : `unmark`

Marks an existing task in Duke as uncompleted.

Format: `unmark INDEX`

* Unmarks the Task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `unmark 1`


### Locating tasks by description: `find`

Find tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

* The search is case-sensitive.
* Incomplete words will be matched e.g. `fami` will match `family`

Examples:
* `find family` 
* `find buy` 


### Locating tasks by date: `findDate`

Find tasks whose descriptions contain the given date.

Format: `find DATE`

* Format of date must be YYYY-MM-DD.

Examples:
* `findDate 2019-10-10`

### Sorting tasks by keyword: `sort`

Sort tasks by a given keyword and order.

Format: `sort KEYWORD [o/order] `

* Keyword must be either `date`, `mark` or `task`.
* Order must be either `asc` or `desc`.

Examples:
* `sort mark asc`
* `sort date desc`
* `sort task asc`

### Deleting a task : `delete`

Deletes the specified task from Duke.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in Duke.
* `find family` followed by `delete 1` deletes the 1st task in Duke and ignores result of find.
* `sort date` followed by `delete 1` deletes the 1st task in sorted list.

### Exiting Duke : `bye`

Exit Duke program immediately.

Format: `bye`