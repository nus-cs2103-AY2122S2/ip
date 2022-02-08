# Lily
> “Why is your list so full of nonsense” – Lily

Lily is a CLI-based task manager. It attemps to embody the personality of a character in Duolingo of the same name. She is a disinterested teen with purple hair.


![Lily from Duolingo](https://c.tenor.com/8WRjkoq7QF0AAAAC/duolingo-lily.gif)

- On an emotional scale of 1-10, Lily is *always at a -4*
- Lily's perpetually unamused facial expression has one clear message: stop bothering me
- If you check her Spotify, she *might* have [Girl in Red](https://open.spotify.com/artist/3uwAm6vQy7kWPS2bciKWx9?si=J5xVJ9WNRwGRc0Xv8WBHjg) in her playlist. Who needs therapy when you can just dye your hair? 🥀

## Features
Example of what you can track with Lily:
- [ ] Todos, without a date
- [ ] Events which has a date (at: Evening of 2 Feb 2022)
- [x] Deadlines which has a due date (by: Wednesday 4 Feb 2022)
---

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)

1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

This is what you should see when launching her for the first time:
```

       ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼ 
       ██╗     ██╗██╗     ██╗   ██╗
       ██║     ██║██║     ╚██╗ ██╔╝
       ██║     ██║██║      ╚████╔╝ 
       ██║     ██║██║       ╚██╔╝  
       ███████╗██║███████╗   ██║   
       ╚══════╝╚═╝╚══════╝   ╚═╝   
       hey.
       ▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼
```