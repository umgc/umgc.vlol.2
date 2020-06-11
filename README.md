# Application Development Notes

Using Visual Studio:

- Cloned repo into local repo storage (C:\Users\Rob\source\repos)

        git clone https://github.com/garciart/SWEN670.git

- Added VLOL solution and project in SWEN670 folder (File->New Project) and used [Introduction to Identity on ASP.NET Core](https://docs.microsoft.com/en-us/aspnet/core/security/authentication/identity?view=aspnetcore-3.1&tabs=visual-studio "Introduction to Identity on ASP.NET Core") as the foundation.
- Created and checked out to a new branch named "garciart" through Team Explorer->Branches.
- Added .gitattributes, .gitignore, LICENSE, and this README.MD file to solution.

The last two action above above can also be completed by opening a command window in the SWEN670 directory and entering the following commands:

    cd C:\Users\Rob\source\repos\SWEN670
    git branch garciart
    git checkout garciart
    cd VLOL
    touch .gitattributes # Add a placeholder for an attributes file
    touch .gitignore # Add a placeholder for a list of files to ignore
    touch README.MD # Add a placeholder for the description of the application
    touch LICENSE # Add a placeholder for the appropriate license (e.g., MIT, BSD, etc.)
    git add --all
    git commit -m "Added .gitattributes, .gitignore, LICENSE, and README.MD."

- Populated README.md and LICENSE (MIT) files.
- Published to vlol.rgcoding.com thorugh FTP:

Right click on the solution name (i.e., VLOL) and selecting "Publish..."

When asked to pick a publish target, select "IIS, FTP, etc" and click on create profile.

    Connection:
    Publish Method: FTP
    Server: ftp://vlol.rgcoding.com
    Site path: [Leave blank. We're not placing code in a sub-folder]
    User name: vlol
    Password: [Sent via separate email]
    Destination URL: https:/vlol.rgcoding.com

Click on Validate Connection. Correct any issues that arise or click next if the connection was validated.

    Settings:
    Configuration: Release
    Target Framework: netcoreapp3.1
    Deployment Mode: Self-Contained
    Target Runtime: Portable

You can also transfer files via FTP through WinSCP.