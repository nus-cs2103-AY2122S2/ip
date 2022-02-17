# User Guide

## Quick Start
1. Ensure you have Java `11` or newer installed in your Computer.
2. Download the latest 'Daphne.jar' from [here](https://github.com/TypeDefinition/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your duke.
4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds.  
   ![](Ui.png)
5. Type the command in the text box and press `Enter` or click on the `Send` button to execute it.
6. Type `help` for the list of commands.

## Features
| Feature   | Description                                                                      | Command (Case Sensitive)                                                  |  
|-----------|----------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| Help      | Lists all commands.                                                              | `help`                                                                    |
| Hello     | Greets Daphne.                                                                   | `hello`                                                                   |
| ToDo      | Add a task without any data/time attached to it.                                 | `todo <DESCRIPTION>`                                                      |
| Deadline  | Add a task that needds to be done before a specific date/time.                   | `deadline <DESCRIPTION> /by <DATE> <TIME>`                                |
| Event     | Add a task that starts at a specific date/time and ends at a specific date/time. | `event <DESCRIPTION> /at <START DATE> <START TIME> <END DATE> <END TIME>` |
| Mark      | Mark a task as complete.                                                         | `mark <INDEX>`                                                            |
| Unmark    | Mark a task as incomplete.                                                       | `unmark <INDEX>`                                                          |
| Delete    | Delete a task.                                                                   | `delete <INDEX> `                                                         |
| Reset     | Delete all tasks.                                                                | `reset`                                                                   |
| Find      | Find a task based on a keyword.                                                  | `find <KEYWORD>`                                                          |
| List      | List all current tasks.                                                          | `list`                                                                    |
| UwU       | (⁄˘⁄ ⁄ ω⁄ ⁄ ˘⁄)♡                                                                 | `uwu`                                                                     |
| OwO       | (。O⁄ ⁄ω⁄ ⁄ O。)                                                                  | `owo`                                                                     |
| Terminate | Terminate the application.                                                       | `bye`                                                                     |

*Dates are required to be in the `YYYY-MM-DD` format.*  
*Times are required to be in the `HH:MM` or `HH:MM:SS` format.*

## Saving
Data is automatically saved in the directory Daphne is run from.
### [Linux](https://pop.system76.com/)
It is most likely located in `~/daphne/tasks.save`.
### [Windows](https://www.microsoft.com/en-sg/windows/windows-11)
It is most likely located in `[Daphne JAR file location]/daphne/tasks.save`.