# Deploy to Heroku

[Getting Started on Heroku](https://devcenter.heroku.com/start "Getting Started on Heroku")

Make sure you have a systems.properties file in the application root directory with the following line:

    java.runtime.version=11

Then run:

    git subtree push --prefix VLOL heroku master
