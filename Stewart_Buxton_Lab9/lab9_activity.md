﻿Git Intro Activity
==================

A. Form teams
-------------

Form a 2-person team. Try to find someone who uses the same platform as you (e.g., Windows, Linux, etc.). If you can't, that's fine. If you are the odd-person-out, join a team of 2.

Complete the exercises below, and fill in your answers in the spaces provided below each question.
Once you have completed all the exercises and filled in this file with your answers, 
add this file to your Lab 9 directory (which you will create in section G) and push it to your team repo.
This file is the deliverable for this lab, so make sure you do not forget to push it at the end!

1.  List your team members below.

    ```
	
	Joseph Buxton
	Jordan Stewart

    ```


D. Getting help
---------------

Run the following commands.

    git help
    git help -ag
    git help init

1.  What does `git help` do?

    ```
	
	git help shows common Git commands used in various situations.  It categorizes them into each situation and states what each command does.
	
    ```

2.  What does `-ag` cause `git help` to do?

    ```
	
	-ag causes git help to list all the available git commands, subcommands, and concept guides.  It lists them alphabetically instead of by category.
	
    ```

G. Basic commands
-----------------

Open the terminal. Navigate to your team’s repository directory.

Create a directory inside of your team repository which will act as your submission directory for this lab.
The directory should be named according to the last names of your team members following the format below.

    <member1LastName>_<member2LastName>_Lab9

Use a plain text editor to create `names.txt` inside the directory you just created.
Put the names of your team in the file. Save and exit.

Run `git status` before and after each of these commands.

    git add names.txt
    git commit –m “Add our names.”
    git log

1.  What kind of information does `git status` report?

    ```
	
	git status reports untracked files and changes to be committed.  It also gives hints as to how to handle the status.
	
    ```

2.  What does `git add names.txt` do?

    ```
	
	It adds names.txt to the list of files that git tracks, so git can follow changes made to it.
	
    ```

3.  What does `git commit -m "Add our names."` do?

    ```
	
	It gives a name to and records the change that you made by adding a file to the directory.  It creates a stage that you can revert back to if needed.
	
    ```

Use a plain text editor to create the following files:

-   `birthdays.txt` - Put your birthdays in this file. (you are not obligated to use real information here)

-   `movies.txt` - Put the last movie each of you watched in alphabetical order.

Run `git status` before and after each of these commands.

    git add .
    git commit		Note:  Commit will open the vim editor; write a multi-line commit
						   message, save and quit (press esc and then type :wq).
    git log

4.  What does `git add .` do? What do you think `.` means?

    ```
	
	git add . adds all files to be tracked instead of just one.
	
    ```

5.  What does `git commit` (without -m) do?

    ```
	
	git commit opens up a text editor so you can write multi-line commits instead of just one statement.
	
    ```

6.  If you want to write a more detailed commit message (which is
    good practice) what command would you use?

    ```
	
	git commit
	
    ```

7.  What does `git log do`?

    ```
	
	git log opens up a log of all the commits made.  You can scroll through this.  
	
    ```


H. Stage/Cache/Index
--------------------

Do the following:

-   Modify `names.txt` so that names are listed in *Last, First* format,
    one per line.

-   Modify `movies.txt` so they are in reverse alphabetical order
    by title.

-   Create a new file `foods.txt` that contains your favorite foods (one
    for each team member).

Run the following commands:

    git add names.txt
    git status

1.  Below write each file name under the state that its changes are
    currently in. Compose a definition for each state.

    **Staged**

    ```
	
	"names.txt"
	
	These files have been changed and they are in the list of files to be committed.  
	
    ```

    **Unstaged**

    ```
	
	"lab9_activity.md"
	"movies.txt"
	
	These files are tracked by git and have been changed, but are not in the list of files to be committed.  
	
    ```

    **Untracked**

    ```
	
	"food.txt"
	
	These files are not tracked by git.  They do not get committed unless first tracked.  
	
    ```

1.  If you run `git commit` what changes will be committed (***DON’T DO IT***)?

    ```
	
	Just the staged changes, in this case "names.txt"
	
    ```

2.  What command do you run to stage changes?

    ```
	
	git add <file>
	
    ```

3.  What command do you run to unstage changes?

    ```
	
	git reset HEAD <file>
	
    ```

Run the following commands:

    git diff
    git diff --cached

1.  What does `git diff` display?

    ```
	
	git diff displays the differences between the current version of unstaged (but tracked) files and the last version that was committed
	
    ```

2.  What does `git diff --cached` display?

    ```
	
	git diff --cached displays the changes between staged files and the last version that was committed
	
    ```

3.  Formulate a sequence of commands to unstage changes to `names.txt`,
    and stage the changes to `movies.txt`. Execute your commands and
    confirm they worked.

    ```
	
	git reset HEAD "names.txt"
	git add "movies.txt"
	git status confirms this works
	
    ```

4.  Edit `movies.txt`, change any one of the movies, and save it. Then
    run `git status`. What do you observe? Explain what you think is
    going on.

    ```
	
	movies.txt is listed under both "Changes to be committed" and "Changes not staged for commit".  This is because our original changes to the file are still prepared for commit, but our new changes have caused the file to be updated away from this state.
	
    ```

5.  Delete `names.txt`. Then run `git status`. What do you observe?
    Explain what you think is going on.

    ```
	
	Under "Changes not staged for commit", "names.txt" is listed as having been deleted.  This is because deletion is considered as a change, just like any other and we will have to stage/commit this.  
	
    ```

6.  Rename `movies.txt` to `last-movies`. Run `git status`. Observe
    and explain.

    ```
	
	Like "names.txt" before, "movies.txt" is listed for deletion.  "last-movies.txt" is now an untracked file.  Git considers the two files to be entirely separate since each has a different name.  Thus, we are basically adding and deleting two different files.  
	
    ```

7.  Formulate a sequence of commands to stage all changes including the
    untracked file and commit (with any reasonable message you like).
    Execute them.

    ```
	
	git add .
	git commit -m "Update all files"
	
    ```

8.  In git vernacular, `index`, `cache`, and `stage` all refer to the
    same thing. What does it hold?

    ```
		
	The "index", "cache", or "stage" holds changes that we have finalized for commit.  
	
    ```

9.  Why have a `stage`? Why not just commit all changes since the last
    commit?

    ```
	
	The "stage" is useful since it is entirely under the control of the user.  It also allows us to selectively push files, if we have made other changes that are not ready to be deployed.  
	
    ```

I. Undo
-------

Run the following commands:

    git log
    git status
    git reset --soft "HEAD^"
    git log
    git status

1.  What does `git reset --soft ``"HEAD^" `do?

    ```
	
	This reverts the last commit.  The files themselves are not changed, and all changes are still staged.  
	
    ```

Run the following commands:

    git commit –m "Redo."
    git log
    git status
    git reset --hard "HEAD^"
    git log
    git status

1.  What does `git reset --hard ``"HEAD^"`` `do?

    ```
	
	This reverts the last commit and changes your files to what they were at the time of the last commit.  
	
    ```

2.  What is the difference between `--hard` and `--soft`?

    ```
	
	Hard actually modifies your files to return them to their previous verison.  Soft just removes the last commit without actually changing your files.  
	
    ```

3.  What do you think `HEAD` means?

    ```
	
	HEAD is the current working branch.  
	
    ```

4.  What do you think `HEAD^` means?

    ```
	
	HEAD^ refers to the branch that is one commit behind the current working branch.  
	
    ```

J. Helpful resources
--------------------

-   <https://git-scm.com/doc>

-   <https://www.atlassian.com/git/tutorials/>

-   <https://training.github.com/kit/downloads/github-git-cheat-sheet.pdf>

K. Copyright and Licensing
--------------------------

Copyright 2016, Darci Burdge and Stoney Jackson SOME RIGHTS RESERVED

This work is licensed under the Creative Commons Attribution-ShareAlike
4.0 International License. To view a copy of this license, visit
<http://creativecommons.org/licenses/by-sa/4.0/> .

