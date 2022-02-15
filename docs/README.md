# User Guide

## Features

### Tracking Tasks

3 types of tasks can be tracked:

* To Do: A task without any time or duration.
* Deadline: A task with a time.
* Event: A task with a time and duration.

### Adding Tasks

To add a task,

1. Select the task type from the dropdown menu at the bottom of the window.
2. The relevant input fields will be displayed. Refer to the table below for the required input formats.
   1. To Do: Description
   2. Deadline: Description, Time
   3. Event: Description, Time, Duration
3. Fill in the required fields and press the enter key.
4. The new task will be displayed in the task window.

|    Field    |       Format       |                                                                   Remarks                                                                    |                   Example Inputs                   |
|:-----------:|:------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------:|
| Description |        N/A         |                                                                     N/A                                                                      |                    Return book                     |
|    Time     |   yyyy-m-d hhmm    |                                                   Time is optional. <br/>Defaults to 0000.                                                   | 2022-11-25 <br/>2022-5-17 1430 <br/>2022-12-3 0530 |
|  Duration   | [Hours]h[Minutes]m | Replace [Hours] and [Minutes] <br/> with the desired values.<br/>Duration is valid if at least 1 of <br/> [Hours] or [Minutes] is specified. |       1h<br/>5m<br/>1h15m<br/>12h<br/>12h30m       |

### Deleting Tasks

To delete a task, click the delete button on the right of the task's card.

### Marking Tasks

To mark a task as done or not done, click the radio button on the left of the task's card.

If the task is marked as done, it's description will be struck-through. Otherwise, it's description will be displayed
normally.

### Editing Tasks

To edit a task,

1. Right-click on the task's card.
2. A dropdown menu will appear.
3. Select the field which you want to edit from the dropdown menu.
4. A pop-up window will appear.
5. In the pop-up window, enter the new value (following the same format when adding the task initially) and click OK.

### Searching Tasks

To search for a task,

1. Enter the search keyword into the search bar at the top left of the window.
2. The search results will be displayed in the task window.

### Saving Tasks

Tasks are automatically saved whenever a change is made.
