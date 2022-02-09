# PikaBot project template

This is a project template for a greenfield Java project. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)


2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.


3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.


4. After that, locate the `src/main/java/pikabot/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
     _________________________________
     Hello! I'm PikaBot
     What can I do for you? シ
     _________________________________

   ```
5. Here is a list of commands that you can enter to use PikaBot:
   * `todo <your_task>` : adds a Todo task
   * `event <your_task> /at <YYYY-MM-DD>` : adds an Event task
   * `deadline <your_task> /by <YYYY-MM-DD>` : adds a Deadline task
   * `delete <task_number>` : deletes a task
   * `list` : view a numbered list of all your current tasks
   * `find <keyword>` : finds tasks containing a specific keyword
   * `mark <task_number>` : mark a task as done
   * `unmark <task_number>` : unmark a task   


6. If you are familiar with PikaBot, shorter aliases for key commands can also be used!
   * `t` for `todo`
   * `e` for `event`
   * `d` for `deadline`
   * `del` for `delete`
   * `m` for `mark`
   * `um` for `unmark`