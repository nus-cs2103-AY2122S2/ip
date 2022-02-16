# User Guide

<img src="https://wxliong.github.io/ip/Ui.png" width="310" height="500">

## Features 

### Adding Todo task: `todo` 

> Adds a todo task.  

Format: `todo <DESCRIPTION>`  
Example: `todo kill joker` 

### Adding Event task: `event` 

> Adds an event task which includes date and time of event.   

Format: `event DESCRIPTION /at DATE TIME`  
Example: 
* `event charity ball /at 10/11/2022 19:00` 
* `event charity ball /at 2022-10-11 19:00` 

### Adding Deadline task: `deadline` 

> Adds a deadline task which includes date and time of deadline.   

Format: `deadline DESCRIPTION /by DATE TIME`  
Example: 
* `deadline repair batmobile /by 18/2/2022 23:59` 
* `deadline repair batmobile /by 2022-2-18 23:59` 

### Listing All Tasks: `list`

> Displays all existing tasks.  

Format: `list`

_Note:_  
_- List is sorted in chronological order._  
_- Todo tasks are by default at the top of the list._

### Deleting a task: `delete` 

> Deletes a task according to the index in the list.  

Format: `delete INDEX`  
Example: `delete 3`  

_Note: Deletes only if the index exists_


### Finding keyword within task: `find` 

> Displays tasks that matches the keyword given.

Format: `find`  
Example: 
* `find joker` 
* `find 18 Feb`

### Marking a task: `mark` 

> Marks a task as done. Shows X if done, nothing otherwise.

Format: `mark INDEX`  
Example: `mark 3` 

_Note: Marks only if the index exists_


### Unmarking a task: `unmark` 

> Unmarks a task as undone. Shows X if done, nothing otherwise. 

Format: `unmark INDEX`  
Example: `unmark 3` 

_Note: Unmarks only if the index exists_

### Exiting the program: `bye`

> Program displays exit message and closes after 3 seconds.

Format: `bye`  