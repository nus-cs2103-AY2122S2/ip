# Pyke

## Introduction

Pyke is a smart chat bot that is embedded with a task manager.

## Quick start

### Installation

1. Ensure you have Java `11` or above installed. You can download from [here](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html).
   - You can run commands `java -verison` and `javac -version` on the terminal to check
2. Download `Pyke.jar` of the latest release version from [here](https://github.com/Rye-Catcher/ip/releases).
3. Move `Pyke.jar` to the directory where you want to use as the *home folder* of the application.
4. Double click `Pyke.jar` file to run the application.
   - Alternatively, you can open the terminal on that folder and run `java -jar Pyke.jar`.
   - If you wish to run in CLI, you can open the terminal on that folder and run `java -cp Pyke.jar pyke.Pyke`.

### A quick example

Enter `deadline CS4215 lab /by 2022-02-20` to add your first task into the list!

And enter `list` to view all the task in the list. Try `mark 1` and view the list again.

You can view the list of command guide by entering `help`.

Below it's a picture of what it should roughly look like on Windows 10.

![exmaple](https://github.com/Rye-Catcher/ip/blob/master/docs/ug_pics/quick_example.png)

And this is a demo for **CLI** (Command-Line Interface) in case you are a *GIGA* turbo-geek.

Note that you can enter `bye` to exit from CLI (you can try this on GUI).

![CLI](https://github.com/Rye-Catcher/ip/blob/master/docs/ug_pics/quick_example_CLI.png)

### Source code

You can check out the code in this [GitHub repository](https://github.com/Rye-Catcher/ip).

## Documentation

Check out this [GitHub page](https://rye-catcher.github.io/ip/)!

## FAQ

**Q**: Why my computer shows JNI error message when I try to open the application  
**A**: This is caused by wrong version of `jdk` (Java Development Kit) on your environment. You can read [this post](https://stackoverflow.com/questions/22381202/a-jni-error-has-occurred-please-check-your-installation-and-try-again-in-eclips?page=1&tab=votes#tab-top) for a solution.

## About

This is the [Xiaoteng](https://github.com/Rye-Catcher) 's project homework for [CS2103T Software Engineering](https://nus-cs2103-ay2122s2.github.io/website/admin/index.html) module at NUS.
