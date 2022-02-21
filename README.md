# Athena

---

Athena is a command-line desktop app to help you keep track of different types of tasks in your life.
All changes are **automatically saved** to the hard drive after each command for 100% peace of mind!

<img src="docs/Ui.png" alt="A screenshot of Athena's GUI" width="350"/>

## Quick Start
1. Ensure that you have Java `11` or above installed in your computer.
2. Download the latest version of `athena.jar` from the repository [here](https://github.com/limweiliang/ip).
3. Copy the jar file to the folder you would like to use as the home folder for Athena.
4. Double-click on the jar file to run it!
5. Execute any of the following commands:
   * `list` : List all tasks currently stored by athena
   * `todo task_name`: Add a new todo task with the given `task_name`
   * `event event_name /at mm/dd/yyyy hhmm` : Add a new event
   * `deadline deadline_name /by mm/dd/yyyy hhmm` : Add a new deadline
   * `mark task_number`/`unmark task_number`: Mark/Unmark a task as complete
   * `delete task_number`: Delete a task
   * `find search_phrase` : Find tasks using a search phrase
   * `remind search_key` : Get reminders within a time period
   * `bye`: Quit the app

**Note**: A `data` folder will be created in the same directory to store your task data. If no data is found,
Athena will create a new task list.

Check out the User Guide [here](docs/README.md) for more details!