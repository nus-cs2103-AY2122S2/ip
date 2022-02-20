# J.A.R.V.I.S project template

This is a project template for a greenfield Java project. It's named after the Tony Stark's A.I _J.A.R.V.I.S_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Main.java` file, right-click it, and choose `Run Main.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   <img width="403" alt="Screen Shot 2022-02-20 at 2 55 54 PM" src="https://user-images.githubusercontent.com/92976979/154832107-c7b7fd25-0bcb-44a9-9017-c49f10ba0182.png">
