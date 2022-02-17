@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac -cp ..\src\main\java\connor -Xlint:none -d ..\bin ..\src\main\java\connor\*.java ..\src\main\java\connor\command\*.java ..\src\main\java\connor\exception\*.java ..\src\main\java\connor\task\*.java ..\src\main\java\connor\gui\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
	pause
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin connor.Launcher < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
pause
