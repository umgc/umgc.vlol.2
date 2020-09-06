##############################################################
# Makefile for building and testing
##############################################################

# Run make SKIP_TEST=y
SKIP_TESTS:=

# Git tag based versioning 
VERSION:=$(shell git describe --abbrev=0).$(shell git rev-list HEAD | wc -l)

# Docker app vars
APP_NAME=vlol
APP_TAG=$(VERSION)
APP_IMG=$(APP_NAME):$(APP_TAG)

# Docker build vars
BUILD_ENV_NAME=vlol-build-env
BUILD_ENV_TAG=latest
BUILD_IMG=$(BUILD_ENV_NAME):$(BUILD_ENV_TAG)

# Maven options
MAVEN_OPTS:=-Dversion=$(VERSION)

VLOL_APP=VLOL-$(VERSION)
VLOL_JAR=$(VLOL_APP).jar

# Skip test flag
# make all SKIP_TESTS=y <- doest not run unit tests
ifdef SKIP_TESTS
	MAVEN_OPTS:=$(MAVEN_OPTS) -Dmaven.test.skip=true
endif  

# PHONY 
.PHONY: all build build-vlol start-vlol build-env start-env clean 

	
##############################################################
#	make all:
#		This recipe starts the volo-build-env with repo volumed
#		mapped into the container can creates the vlol-app jar
#		then exits. This is useful so that developer do not 
#		need to modify environments.
##############################################################
all:
	docker run -v $(PWD)/:/repo --entrypoint '/bin/bash' $(BUILD_IMG) -c 'cd /repo && make target/$(VLOL_JAR) VERSION=$(VERSION)'


##############################################################
#	make all:
#		This recipe is like make all but without the Docker
#		context.
##############################################################
build: target/$(VLOL_JAR)
	

##############################################################
#	make vlol-app:
#		This recipe create the vlol java jar
##############################################################
target/$(VLOL_JAR):	
	@mvn versions:set -DnewVersion=$(VERSION)
	mvn $(MAVEN_OPTS) package -f pom.xml
	@mvn versions:commit


##############################################################
#	make build-vlol:
#		This recipe create the vlol Docker image
##############################################################
build-vlol: target/$(VLOL_JAR)
	cp target/$(VLOL_JAR) ./$(VLOL_JAR)
	docker build -f ./docker/Dockerfile.app --build-arg VERSION=$(VERSION) --build-arg VLOL_APP=$(VLOL_JAR) -t $(APP_IMG) .
	rm -rf ./$(VLOL_JAR)


##############################################################
#	make start-vlol:
#		This recipe start the vlol Docker app
##############################################################
start-vlol:
	docker run --rm --name $(VLOL_APP) --network=host $(APP_IMG)


##############################################################
#	make build-env:
#		This recipe create the Docker build env image capable 
#		of building the vlol-app
##############################################################
build-env:
	docker build -f ./docker/Dockerfile.build -t $(BUILD_IMG) .


##############################################################
#	make start-env:
#		This recipe starts the Docker build env image 
##############################################################
start-env:
	docker run -it -v $(PWD)/:/repo $(BUILD_IMG) bash


##############################################################
#	make clean:
#		This recipe cleans the user workspace
##############################################################
clean:
	@mvn $(MAVEN_OPTS) clean -f pom.xml
	@rm -rf ./tomcat


##############################################################
#	make help:
#		This recipe prints make commands
##############################################################
help:
	@$(info For new environment setup follow the below steps)
	@$(info Prerequisites: )
	@$(info 1. GNU Make version 3.82)
	@$(info 1. Docker version 19.03.12)
	@$(info -----------------------------------------------------------------------------------------------------------)
	@$(info 1. make build-env:    Creates a Docker image used for building the VLOL application.)
	@$(info 2. make all:          Creates the VLOL application using the Docker image created in the previous step.)
	@$(info 2. make build-vlol:   Creates a VLOL Docker image from the artifact created in the previous step.)
	@$(info 3. make start-vlol:   Starts the VLOL Docker image.)
	@$(info -----------------------------------------------------------------------------------------------------------)
	@$(info )
	@$(info Optional CLI)
	@$(info SKIP_TESTS=y/n        Skips running unit tests)
	@$(info )
	@$(info Available Make commands)
	@cat Makefile | sed -n -e '/####/,/#####/ p' | grep -v '###' | sed -e 's/#//g' | grep -v Makefile|grep -v help
