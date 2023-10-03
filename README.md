# Lily
> “Why is your list so full of nonsense” – Lily

Lily is a CLI-based task manager. It attemps to embody the personality of a character in Duolingo of the same name. She is a disinterested teen with purple hair.


![Lily from Duolingo](docs/intro.gif)

- On an emotional scale of 1-10, Lily is *always at a -4*
- Lily's perpetually unamused facial expression has one clear message: stop bothering me
- If you check her Spotify, she *might* have [Girl in Red](https://open.spotify.com/artist/3uwAm6vQy7kWPS2bciKWx9?si=J5xVJ9WNRwGRc0Xv8WBHjg) in her playlist. Who needs therapy when you can just dye your hair? 🥀

## Features
Example of what you can track with Lily:
- [ ] Todos, without a date
- [ ] Events which has a date (at: Evening of 2 Feb 2022)
- [x] Deadlines which has a due date (by: Wednesday 4 Feb 2022)
- [x] Jobs which have a duration (needs: 2.5h)
---
## Installation

1. [Download and Install Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) if you do not have it
2. Download the latest `Lily.jar` from [here](https://github.com/ian-from-dover/ip/releases)
3. Place it where it can make a `data` folder beside the .jar
4. Double click `Lily.jar` to start the app.


This is what you should see when launching her for the first time:
![Start up screen](docs/start.png)


---
## Setting up for development in Intellij with Gradle

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
2. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, open a terminal in the project directory and type `./gradlew run` on Mac or `gradlew run` on Windows.
If the setup is correct, you should see something like the below as the output:

Do open a pull request if you wish to contribute to this project.