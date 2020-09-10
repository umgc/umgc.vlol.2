##############################################################
# Makefile for building and testing
##############################################################

# Run make SKIP_TEST=y
SKIP_TESTS:=

# Version vars
VERSION:=1.0.$(shell git rev-list HEAD | wc -l)
VLOL_JAR=VLOL-1.0.0.jar

# Docker app vars
APP_NAME=vlol.app
APP_TAG=$(VERSION)
APP_IMG=$(APP_NAME):$(APP_TAG)

# Docker build vars
BUILD_ENV_NAME=vlol-build-env
BUILD_ENV_TAG=latest
BUILD_IMG=$(BUILD_ENV_NAME):$(BUILD_ENV_TAG)
REMOTE_IMG:=docker.io/umgccaps/$(APP_IMG)

# Maven options
MAVEN_OPTS:=-Dversion=$(VERSION)

# Unique ID used for devel Azure deployments
UUID_FILENAME:=user.uuid
UUID:=$(shell cat $(UUID_FILENAME) 2> /dev/null || (uuidgen | sed s/'-'/''/g | head -c 10 \
         | tr A-Z a-z > $(UUID_FILENAME) && cat $(UUID_FILENAME)))

# Skip test flag
# make all SKIP_TESTS=y <- doest not run unit tests
ifdef SKIP_TESTS
	MAVEN_OPTS:=$(MAVEN_OPTS) -Dmaven.test.skip=true
endif  


# PHONY 
.PHONY: all push build build-vlol start-vlol build-env start-env clean dev-deploy stop-deploy help

	
##############################################################
#	make all:
#		This recipe starts the vlol-build-env with repo volumed
#		mapped into the container can creates the vlol-app jar
#		then exits. This is useful so that developer do not 
#		need to modify environments.
#
##############################################################
all:
	docker run -v $(PWD)/:/repo --entrypoint '/bin/bash' $(BUILD_IMG) \
		-c 'cd /repo && make target/$(VLOL_JAR) VERSION=$(VERSION)'

##############################################################
#	make push:
#		This recipe pushes the Docker vlol application to the
#		Azure container registry 
#
##############################################################
push:
	docker tag $(APP_IMG) $(REMOTE_IMG)
	docker push $(REMOTE_IMG)


##############################################################
#	make build:
#		This recipe is like make all but without the Docker
#		context.
#
##############################################################
build: target/$(VLOL_JAR)
	
# This internal recipe is used by build to create the vlol versioned jar
target/$(VLOL_JAR):	
	mvn $(MAVEN_OPTS) package -f pom.xml


##############################################################
#	make build-vlol:
#		This recipe create the vlol Docker image
#
##############################################################
build-vlol: target/$(VLOL_JAR)
	cp target/$(VLOL_JAR) ./$(VLOL_JAR)
	docker build -f ./docker/Dockerfile.app --build-arg VERSION=$(VERSION) \
		--build-arg VLOL_APP=$(VLOL_JAR) -t $(APP_IMG) .
	rm -rf ./$(VLOL_JAR)


##############################################################
#	make start-vlol:
#		This recipe start the vlol Docker app
#
##############################################################
start-vlol:
	docker run --rm --name $(APP_NAME) -p 5000:5000 --mount source=h2data,target=/data $(APP_IMG)



##############################################################
#	make build-env:
#		This recipe create the Docker build env image capable 
#		of building the vlol-app
#
##############################################################
build-env:
	docker build -f ./docker/Dockerfile.build -t $(BUILD_IMG) .


##############################################################
#	make start-env:
#		This recipe starts the Docker build env image 
#
##############################################################
start-env:
	docker run -it -v $(PWD)/:/repo -v /var/run/docker.sock:/var/run/docker.sock \
		-v /sys/fs/cgroup:/sys/fs/cgroup --privileged $(BUILD_IMG) bash


##############################################################
#	make clean:
#		This recipe cleans the user workspace
#
##############################################################
clean:
	@mvn $(MAVEN_OPTS) clean -f pom.xml
	@rm -rf ./tomcat


##############################################################
#	make dev-deploy:
#		This deploys a given VLOL application to an Azure 
#		container instances. This need to be done within the
#		build-env Docker container unless user have azure-cli 
#		installed
#			Optional CLI: REMOTE_IMG=<docker-hub-image>
#
##############################################################
dev-deploy: 
	@az group create --name devTestGroup --location eastus
	@az deployment group create --resource-group devTestGroup \
			--template-file azure/deploy-template.json \
			--parameter azure/deploy-parameters.json \
			--parameter imageName=$(REMOTE_IMG) \
			--parameter dnsNameLabel=$(UUID) 
	@$(info $(REMOTE_IMG) deployed to $(UUID).eastus.azurecontainer.io)
	@$(info This may take a few minutes to respond)


##############################################################
#	make stop-deploy:
#		This stops the Azure container instances.
#
##############################################################
stop-deploy:
	@az group delete --name devTestGroup


# This prints make commands and usage
help:
	@$(info For new environment setup follow the below steps)
	@$(info Prerequisites: )
	@$(info 1. GNU Make version 3.82)
	@$(info 1. Docker version 19.03.12)
	@$(info -----------------------------------------------------------------------------------------------------------)
	@$(info 1. make build-env:    Creates a Docker image used for building the VLOL application.)
	@$(info                       Note: This only needs to be done once takes ~3m)
	@$(info 2. make all:          Creates the VLOL application using the Docker image created in the previous step.)
	@$(info 2. make build-vlol:   Creates a VLOL Docker image from the artifact created in the previous step.)
	@$(info 3. make start-vlol:   Starts the VLOL Docker image.)
	@$(info -----------------------------------------------------------------------------------------------------------)
	@$(info )
	@$(info Azure development deployment steps)
	@$(info 1. make build-env:     Only needed if user has not previously done so)
	@$(info 2. make start-env:     Starts the build-env Docker context)
	@$(info 1. az login            Navigate to the url and enter the code from the CLI output)
	@$(info 2. make dev-deploy     This creates a container instances with the VLOL application deployed)
	@$(info )
	@$(info Optional CLI)
	@$(info SKIP_TESTS=y/n        Skips running unit tests)
	@$(info REMOTE_IMG=y/n        Overrides Docker image to deploy to Azure container instances)
	@$(info )
	@$(info Available Make commands)
	@cat Makefile | sed -n -e '/####/,/#####/ p' | grep -v '###' | sed -e 's/#//g' | grep -v Makefile|grep -v help
