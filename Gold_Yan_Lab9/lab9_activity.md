Git Intro Activity
==================

A. Form teams
-------------

Form a 2-person team. Try to find someone who uses the same platform as you (e.g., Windows, Linux, etc.). If you can't, that's fine. If you are the odd-person-out, join a team of 2.

Complete the exercises below, and fill in your answers in the spaces provided below each question.
Once you have completed all the exercises and filled in this file with your answers, add this file to your Lab 9 directory (which you will create in section G) and push it to your team repo.
This file is the deliverable for this lab, so make sure you do not forget to push it at the end!

1.  List your team members below.

    ```
	Ashley Gold
	Ziyao Yan

    ```


D. Getting help
---------------

Run the following commands.

    git help
    git help -ag
    git help init

1.  What does `git help` do?

    ```
	Gives a list of common git commands that can be used in various situations.

    ```

2.  What does `-ag` cause `git help` to do?

    ```
	Gives a list of available git commands in whatever directory you're in.


    ```

G. Basic commands
-----------------

Open the terminal. Navigate to your team's repository directory.

Create a directory inside of your team repository which will act as your submission directory for this lab.
The directory should be named according to the last names of your team members following the format below.

    <member1LastName>_<member2LastName>_Lab9

Use a plain text editor to create `names.txt` inside the directory you just created.
Put the names of your team in the file. Save and exit.

Run `git status` before and after each of these commands.

    git add names.txt
    git commit -m "Add our names."ù
    git log

1.  What kind of information does `git status` report?

    ```

	Git status reports if you're up to date with the master branch, if you have any uncommitted changes, etc.

    ```

2.  What does `git add names.txt` do?

    ```

	This adds names.txt to the directory you are in. 

    ```

3.  What does `git commit -m "Add our names."` do?

    ```

	This commits the changes you made to the directory and tells you how many changes you made.

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

	This adds all new files and modifies files. '.' means 'all'.

    ```

5.  What does `git commit` (without -m) do?

    ```

	Opens up vim editor and prompts for a commit message, then commits all changes.

    ```

6.  If you want to write a more detailed commit message (which is good practice) what command would you use?

    ```

	You would use 'git commit' instead of 'git commit -m' in order to write multi-line, more detailed commit messages.

    ```

7.  What does `git log do`?

    ```
	'git log' shows you who has committed and at what times within the directory.


    ```


H. Stage/Cache/Index
--------------------

Do the following:

-   Modify `names.txt` so that names are listed in *Last, First* format, one per line.

-   Modify `movies.txt` so they are in reverse alphabetical order by title.

-   Create a new file `foods.txt` that contains your favorite foods (one for each team member).

Run the following commands:

    git add names.txt
    git status

1.  Below write each file name under the state that its changes are currently in. Compose a definition for each state.

    **Staged**

    ```
	names.txt
	Staged files are things that have been added, are up to date and are ready to commit.

    ```

    **Unstaged**

    ```
	movies.txt
	Unstaged files are things that are in the directory but have been modified since last commit and have not been added since modification.

    ```

    **Untracked**

    ```
	foods.txt
	Untracked files are new files that have not been added into directory and are not ready to commit.
    ```

1.  If you run `git commit` what changes will be committed (***DON'T DO IT***)?

    ```

	Only the modifications to names.txt will be committed.

    ```

2.  What command do you run to stage changes?

    ```
	'git add <file>'


    ```

3.  What command do you run to unstage changes?

    ```

	'git reset HEAD <file>...'

    ```

Run the following commands:

    git diff
    git diff --cached

1.  What does `git diff` display?

    ```

	'git diff' displays the differences between the last commit and the present with modifications for unstaged files.

    ```

2.  What does `git diff --cached` display?

    ```

	'git diff' displays the differences between the last commit and the present with modifications for staged files.

    ```

3.  Formulate a sequence of commands to unstage changes to `names.txt`, and stage the changes to `movies.txt`. Execute your commands and confirm they worked.

    ```
	git reset HEAD names.txt
	git add movies.txt
	git status

	The commands worked because when git status was run, movies.txt was listed as staged and names.txt was listed as unstaged.

    ```

4.  Edit `movies.txt`, change any one of the movies, and save it. Then run `git status`. What do you observe? Explain what you think is going on.

    ```
	After changing one of the movies, git status shows that movies.txt is appearing under staged and unstaged. We think that if committed, the changes that are staged would commit, but the new change (changing one movie, which is unstaged) would not commit.


    ```

5.  Delete `names.txt`. Then run `git status`. What do you observe? Explain what you think is going on.

    ```

	After deleting names.txt, git status shows that names.txt is deleted, but this shows up under unstaged. We think what is going on is that we have made a change that is unstaged and if we want this file deleted permanently, we have to run 'git rm names.txt'.

    ```

6.  Rename `movies.txt` to `last-movies`. Run `git status`. Observe and explain.

    ```

	'last-movies' now shows up as untracked and 'movies' has been deleted (and it is unstaged). Because last-movies is a new file name, the operating system doesn't realize it is the same movies file, so it registers that we deleted the movies file and added a new file, last-movies.

    ```

7.  Formulate a sequence of commands to stage all changes including the untracked file and commit (with any reasonable message you like). Execute them.

    ```
	git add .
	git status
	git commit -m "deleted names, renamed movies, added foods"
	git status


    ```

8.  In git vernacular, `index`, `cache`, and `stage` all refer to the same thing. What does it hold?

    ```
	
	Cache/index/stage holds files you want committed to the git repository.

    ```

9.  Why have a `stage`? Why not just commit all changes since the last commit?

    ```
	The stage is useful because you can which files you want to commit and when. This helps you split up one large change into multiple commits, helps to review changes, and helps resolve merge conflicts.


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
	This undoes the latest commit. 

    ```

Run the following commands:

    git commit -m "Redo."
    git log
    git status
    git reset --hard "HEAD^"
    git log
    git status

1.  What does `git reset --hard ``"HEAD^"`` `do?

    ```
	This undoes the latest commit and gets rid of all changes.


    ```

2.  What is the difference between `--hard` and `--soft`?

    ```
	--soft unstages the commit only, whereas --hard unstages the commit and undoes all the changes, AKA returns the files to how they were before you made any changes. 


    ```

3.  What do you think `HEAD` means?

    ```
	HEAD is your current working branch. 

    ```

4.  What do you think `HEAD^` means?

    ```
	HEAD^ means the current branch back one commit. As opposed to HEAD^2 etc. which would be going back 2 commits, and so on. 

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
