![Test/Code Coverage VLOL](https://github.com/umgc/fire.department/workflows/Test/Code%20Coverage%20VLOL/badge.svg?branch=master)
![Deploy Vlol](https://github.com/umgc/fire.department/workflows/Deploy%20Vlol/badge.svg?branch=master)
![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=umgc-city-vlol&metric=coverage)

![Sonarcloud Quality](https://sonarcloud.io/api/project_badges/quality_gate?project=umgc-city-vlol)

For Code Coverage Report view [Sonar Dashboard](https://sonarcloud.io/dashboard?id=umgc-city-vlol)

# Team 3 Capstone Project

![A collaboration between the SFD and the UMGC](/Images/README00.png "A collaboration between the SFD and the UMGC")

>***DISCLAIMER:***
>
>- ***THIS REPOSITORY IS FOR EDUCATION AND CLASSROOM PURPOSES ONLY.***
>
>- ***NEITHER THE CITY OF SALISBURY NOR THE SALISBURY FIRE DEPARTMENT HAS APPROVED THIS PROJECT FOR FUNDING OR PRODUCTION.***

## Table of Contents

- [Introduction](#introduction)
- [Requirement Analysis](#requirement-analysis)
- [System and Database Design](#system-and-database-design)
- [User Guide](#user-guide)
- [Meet The Team!](#meet-the-team)
- [References](#references)

## Introduction

On May 20, 2020, the Salisbury Fire Department published a Request For Proposal (RFP) through the City of Salisbury's GovQuote web portal. Team 3, SWEN 670 (Summer), at the University of Maryland, Global Campus (UMGC), responded to the proposal the next day.

The Salisbury Fire Department's **Business Problem** and **Desired Outcomes** are:

>*"We are searching for a system that will allow emergency medical services (EMS) to retrieve the medical information contained in a patient's ["Letter of Life"](https://salisbury.md/departments/fire/letter-of-life) at any location.*
>
>*"In an emergency, the Letter of Life provides responders with a list of pertinent medical conditions and illnesses when the patient cannot provide this information on their own. We recommend all residents, especially the elderly and those suffering from chronic medical conditions, to complete a "Letter of Life" and place it in a location easily accessible by EMS personnel, such as on a refrigerator or by the entry door to the residence. However, when an emergency occurs outside the home, EMS personnel cannot access this list, which is why we are searching for an easily accessible, cloud-based version of the letter.*
>
>*"Our desired outcomes are:*
>
>- *Provide EMS personnel access to a patient's Letter of Life information, from a laptop and mobile device, through a secure, but easily navigable interface.*
>- *Allow EMS personnel to scan a barcode or quick response (QR) code, found on a non-electronic token, such as a card, a bracelet, or necklace, and retrieve Letter of Life information for non-responsive patients.*
>- *Allow patients and authorized users to create, read, update, and delete records, from a desktop or laptop computer, through a secure, but easily navigable administration interface.*
>- *Allow patients and authorized users to print Letters of Life or save them in portable data format (PDF).*

This folder contains the code generated to satisfy the business problem and meet each desired outcome.

---

## Requirement Analysis

---

## System and Database Design

---

## User Guide
Prerequisites: 
GNU Make version 3.82
Docker version 19.03.12

This repo leverages Docker as a developer environment to bootstrap system requirements into a full application build. Under the docker folder is a Dockerfile Dockerfile.build that creates a Docker image that container all necessary packages and dependencies for building, testing, and deploying the Virtual Letter of Life application. 
>- Note: users may decide to use a local environment if they choose to install the required dependencies locally.

At any time user may run `$ make help` to display usefully make commands.
##### Development Build:
1) First build the Docker build environment image.
    ```bash
    $ make build-env
    ```
    This will produce the Docker image `vlol-build-env:latest`
    ```bash
    $ docker images
    REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
    vlol-build-env      latest              febd35d3c354        16 seconds ago      1.46GB
    ```
2) Next build the application.
    ```bash
    $ make all | make all SKIP_TESTS=y
    ```
    This will build the application Java jar by starting the Docker build-env image with the local repo volume mapping into the container context then running the make command to build the application. Optionally users may include the `SKIP_TESTS=y` CLI to skip unit tests.
    ```bash
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  37.400 s
    [INFO] Finished at: 2020-09-10T19:33:56Z
    [INFO] ------------------------------------------------------------------------
    ```
3) Next build the application Docker image.
    ``` bash
    $ make build-vlol
    ``` 
    This will build the application Docker image.
    ```bash
    $ docker images
    REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
    vlol.app            1.0.146             399d4e207440        4 seconds ago       712MB
    ```

##### Development Testing:
After building the application Docker image user can run a local instance of the application via `make` commands.
1) Start the application
    ```bash
    $ make start-vlol
    ```
    This start the Docker container application
    ```bash
    $ docker ps
    CONTAINER ID   IMAGE         COMMAND     CREATED      STATUS          PORTS             NAMES
    b4267ea10757  vlol.app:1.0.146  "..." 56 seconds ago Up 55 seconds  0.0.0.0:5000->5000/tcp   vlol.app
    ```
2) Open a browser and navigate to `localhost:5000` it may take a few seconds for the application to fully start.
    

##### Development Publish Docker image:
*** TBD ***
##### Development Deployment:
*** TBD ***

---

## Meet the Team

Team 2 is:

- Panhavorn Hok
- Kimberly Van Allen
- Michael Marcucci
- Andrew Coleman
- Heather Barnes
- Michael Shaw 

---

## References