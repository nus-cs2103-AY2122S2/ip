# Duke v0.2

Duke v0.2 helps you manage a list of your tasks. Currently, 3 types of tasks are supported: tasks with/without a deadline, and events happening at a specifc time.  
Duke v0.2 uses command input. Simply enter your command and hit return.  
Currently, the name of the Duke in this program is "Enkel".

## Syntaxes of Commands

You can view this by typing `help` to Enkel. 

**To add a task:**
  1. `todo <description>`
  2. `deadline <description> /by <time>`
  3. `event <description> /at <time>`

Both `<description>` and `<time>` cannot be omitted.
Valid time formats: `yyyy-M-d` (e.g. `2022-1-24`) and `yyyy-M-d H:mm` (e.g. `2021-12-15 8:30`)

**To view tasks:**
  4. `list`: displays the whole list
  5. `find <keyword>`: displays tasks containing the keyword in the description

**To modify the list:**
  6. `mark <index>`: marks the task at the given index
  7. `unmark <index>`: unmarks the task at the given index
  8. `edit <index> <syntax of 1, 2, or 3>` (e.g. edit 3 deadline lab report /by 2022-1-24 17:00): edits the task at the given index. If the task type is omitted, the original type will be used (you still need to use syntax 1, 2, or 3. e.g. edit 3 lab report /by 2022-1-24 17:00)
  9. `delete <index>`: deletes the task of the given index

**To quit the program:**
  10. `bye` or cross it directly
