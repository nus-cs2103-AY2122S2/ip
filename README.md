#  Nikki Project

This is a project for a greenfield Java project. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/nikki/Nikki.java` file, right-click it, and choose `Run Nikki.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
```
███╗░░██╗██╗██╗░░██╗██╗░░██╗██╗　　　　 　∧_∧
████╗░██║██║██║░██╔╝██║░██╔╝██║　　　　 ( ﾟωﾟ)
██╔██╗██║██║█████═╝░█████═╝░██║　　　　 /ｕ ｕ
██║╚████║██║██╔═██╗░██╔═██╗░██║　　　　/ / /
██║░╚███║██║██║░╚██╗██║░╚██╗██║　　　 (ノノ
╚═╝░░╚══╝╚═╝╚═╝░░╚═╝╚═╝░░╚═╝╚═╝　　　彡

Your Personal Assistant Chatbot that helps you keep track of the important things in life
```

## Acknowledgements
Credit list of package(s) used:
1. [JavaWuzzy](https://github.com/xdrop/fuzzywuzzy): fuzzy search for task finding
