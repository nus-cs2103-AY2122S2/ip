# Interface Screenshot

<img width="600" alt="Ui" src="https://github.com/zihaowrez/ip/blob/master/docs/Ui.png">


# Enkel v0.2

Latest release: [here](https://github.com/zihaowrez/CS2103_iP/releases/tag/Enkel-v0.2)

Enkel v0.2 helps you manage a list of your tasks. Currently, 3 types of tasks are supported: tasks with/without a deadline, and events happening at a specifc time.  
Enkel v0.2 uses command input. Simply enter your command and hit return.  

## Syntaxes of Commands

You can view this by typing `help` to Enkel.  
All inputs except for `<description>` and `<keyword>` must be in lowercase.

**To add a task:**
- `todo <description>`
- `deadline <description> /by <time>`
- `event <description> /at <time>`

Both `<description>` and `<time>` cannot be omitted.
Valid time formats: `yyyy-M-d` (e.g. `2022-1-24`) and `yyyy-M-d H:mm` (e.g. `2021-12-15 8:30`)

**To view tasks:**  
- `list`: displays the whole list  
- `find <keyword>`: displays tasks containing the keyword in the description

**To modify the list:**  
- `mark <index>`: marks the task at the given index  
- `unmark <index>`: unmarks the task at the given index  
- `edit <index> <syntax of adding a task>` (e.g. `edit 3 deadline lab report /by 2022-1-24 17:00`): edits the task at the given index. If the task type is omitted, the original type will be used (you still need to use the syntax of adding a task. e.g. `edit 3 lab report /by 2022-1-24 17:00`)  
- `delete <index>`: deletes the task of the given index

**To quit the program:**  
- `bye` or cross it directly
