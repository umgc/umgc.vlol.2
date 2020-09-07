# Deploy to UMGC GitHub

## DO THIS ONCE:

From the SWEN670 folder, run the following command:

    git remote add VLOL fire.department https://github.com/umgc/fire.department

Then run:

    git subtree push --prefix VLOL https://github.com/umgc/fire.department <your branch>

On Windows, if you get the following error:

    hint: Updates were rejected because the tip of your current branch is behind
    hint: its remote counterpart. Merge the remote changes (e.g. 'git pull')
    hint: before pushing again.
    hint: See the 'Note about fast-forwards' in 'git push --help' for details.

Run the following commands:

    git subtree split --prefix VLOL garciart

Once you receive the 40-character token, add it to the following command:

    git push https://github.com/umgc/fire.department abcdefghijklmnopqrstuvwxyz0123456789xxxx:garciart --force

On Linux, you can run:

    git push https://github.com/umgc/fire.department `git subtree split --prefix VLOL garciart`:garciart --force
