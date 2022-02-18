# User Guide
###DukeTime
## Features 
- `todo` : add todo
- `deadline` : add deadline to a task
- `event` : add event
- `delete` : delete a task
- `list` : view all the tasks
- `mark` : mark a task as done
- `unmark` : unmark a task as undone
- `search` : search a keyword
- `find` : find your existing tasks
- `bye` : quit the app


## Usage
### Feature-`todo`

Add a todo task

Outcome: Add a task to your task list

Format: `todo n/TASK`

Example of Usage: `todo revision`

Expected Outcome:
```
Okayy!! I've added this task:
T | [] revision
You have 2 tasks in the list.
```


### Feature-`deadline`
Add a deadline to a task 

Outcome: Add a deadline to your task list

Format : `deadline n/TASK /by [t/Date] [t/Time]`

Example of Usage: `deadline submission /by 12/03/2022`

Expected Outcome:
```
Deadline for this task:
D | [] submission (by:Mar 12 2022)
You have 3 tasks in the list.
```

### Feature-`event`
Add a event task

Outcome: Add an event to your task list

Format : `event n/TASK /at [t/Date] [t/Time]`

Example of Usage: `event party /at 12/03/2022`

Expected Outcome:
```
I have added this task and the event time is:
E | [] party (at:Mar 12 2022)
You have 4 tasks in the list.
```

### Feature-`delete`
Delete a task

Outcome: Delete a task from your task list

Format : `delete n/TASK_NUMBER`

Example of Usage: `delete 1`

Expected Outcome:
```
Noted.I've deleted this task:
 project submission (by:Feb 20 2022 1800)
You have 3 tasks in the list.
```

### Feature-`list`
List down the tasks in your task list

Outcome: Overview of your task list

Format : `list`

Example of Usage: `list`

Expected Outcome:
```
1. T | [ ] revision
2. D | [ ] submission (by:Mar 12 2022)
3. E | [] party (at:Mar 12 2022)
```

### Feature-`mark`
Mark your task as done

Outcome: task will be shown as completed

Format : `mark n/TASK_NUMBER`

Example of Usage: `mark 1`

Expected Outcome:
```
Good work!!! I have marked it done:
1. T | [X] revision
```

### Feature-`unmark`
Unmark your task as undone

Outcome: task will be shown as completed

Format : `unmark n/TASK_NUMBER`

Example of Usage: `unmark 1`

Expected Outcome:
```
Alright!!! I have marked it undone:
1. T | [ ] revision
```

### Feature-`search`
Search for a task using start with the keyword(s)

Outcome: task with keywords matched will be shown

Format : `search n/KEYWORD`

Example of Usage: `search t`

Expected Outcome:
```
T | [ ] this
```

### Feature-`find`
find for a task that contains the keyword(s)

Outcome: task that contain the keyword(s) will be shown

Format : `find n/KEYWORD`

Example of Usage: `find sion`

Expected Outcome:
```
D | [ ] submission (by:Mar 12 2022)
```

### Feature-`bye`
exit the app

Outcome: exit the app

Format : `bye`

Example of Usage: `bye`

Expected Outcome:
```
Bye. Hope to see you again soon!
```
