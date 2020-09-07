# Running SonarQube

To run SonarQube on this project, follow the directions at [Try Out SonarQube](https://docs.sonarqube.org/latest/setup/get-started-2-minutes/ "Try Out SonarQube").

To reanalyze a project:

1. Open a terminal window and start the SonarQube Server as a non-root user:

    # On Windows, execute:
    C:\sonarqube\bin\windows-x86-64\StartSonar.bat

    # On other operating systems, as a non-root user execute:
    /opt/sonarqube/bin/[OS]/sonar.sh console

2. Log in to http://localhost:9000 using System Administrator credentials (login=admin, password=admin).

3. Open another terminal window and run the following command:

    mvn sonar:sonar

|*Note* - You can restart the analysis process by running the following commands:

    mvn clean install
    mvn sonar:sonar