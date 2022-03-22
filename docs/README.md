# User Guide
___

## Features

**Notes about the command format:**<br>
* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
e.g. In `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo buy groceries`.

* Parameters cannot be in any order.<br>
 e.g. `reschedule 1 /r 2022-10-25` is accepted but `reschedule /r 2022-10-25 1` is invaild command format.</pre>

* Extraneous parameters for commands that do not take in parameters will be ignored.<br>
e.g. `list XYZ` will be interpreted as `list`.
  
___
### Add a ToDo task : `todo`

Adds a ToDo task to the list with the given task description.<br>
**Format:** `todo TASK_DESCRIPTION`<br>
**e.g.** `todo buy groceries`
___
### Add an Event task : `event`

Adds an Event task to the list with the given task description and date.<br>
**Format:** `event TASK_DESCRIPTION /at DATE`<br>
**e.g.** `event attend meeting /at 2022-02-15`

* `DATE`should be in the format **YYYY-MM-DD** e.g. 2022-02-15
___
### Add a Deadline task : `deadline`

Adds an Deadline task to the list with the given task description and date.<br>
**Format:** `deadline TASK_DESCRIPTION /by DATE`<br>
**e.g.** `deadline submit project report /by 2022-02-15`

* `DATE`should be in the format **YYYY-MM-DD** e.g. 2022-02-15
___
### Display all the tasks : `list`

Displays all the tasks in the tasks list.<br>
**Format:** `list`<br>
**e.g.** `list`
___
### Mark a task as done : `mark`
Marks the task with the given task index as done.<br>
**Format:** `mark TASK_INDEX`<br>
**e.g.** `mark 2`
___
### Mark a task as not done : `unmark`
Marks the task with the given task index as not done.<br>
**Format:** `unmark TASK_INDEX`<br>
**e.g.** `unmark 2`
___
### Delete a task : `delete`
Deletes the task with the given task index.<br>
**Format:** `delete TASK_INDEX`<br>
**e.g.** `delete 2`
___
### Reschedule a task : `reschedule`
Reschedule the date of a `event` task or a `deadline` task with the given task index and date.<br>
**Format** `reschedule TASK_INDEX /r DATE`<br>
**e.g.** `reschedule 2 /r 2022-02-18`

* `reschedule` cannot be called for a `todo` task
* `DATE`should be in the format **YYYY-MM-DD** e.g. 2022-02-15
___
### Search the list of tasks : `find`

Finds all the tasks containing the given keyword.<br>
**Format:** `find KEYWORD`<br>
**e.g.** `find XYZ`
___


