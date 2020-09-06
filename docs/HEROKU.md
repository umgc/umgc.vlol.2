# Deploy to Heroku

[Getting Started on Heroku](https://devcenter.heroku.com/start "Getting Started on Heroku")

Make sure you have a **systems.properties** file in the application root directory with the following line:

    java.runtime.version=11

Also make sure that the server port is set to 5000 in the **application.properties** file:

    server.port=5000

Then run:

    git subtree push --prefix VLOL heroku master

The website is https://warm-hamlet-22515.herokuapp.com/

If you receive the following error:

    fatal: 'heroku' does not appear to be a git repository
    fatal: Could not read from remote repository.

    Please make sure you have the correct access rights
    and the repository exists.

Run the following command first:

    heroku git:remote -a warm-hamlet-22515

On Windows, if you get the following error:

    hint: Updates were rejected because the tip of your current branch is behind
    hint: its remote counterpart. Merge the remote changes (e.g. 'git pull')
    hint: before pushing again.
    hint: See the 'Note about fast-forwards' in 'git push --help' for details.

Run the following commands:

    git subtree split --prefix VLOL master

Once you receive the 40-character token, add it to the following command:

    git push heroku abcdefghijklmnopqrstuvwxyz0123456789xxxx:master --force

On Linux, you can run:

    git push heroku `git subtree split --prefix VLOL master`:master --force
