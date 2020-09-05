##############################################################
# Makefile for building and testing
##############################################################

# Run make SKIP_TEST=y
SKIP_TESTS:=

BUILD_ENV_NAME=vlol-build-env
BUILD_ENV_TAG=latest
BUILD_IMG=$(BUILD_ENV_NAME):$(BUILD_ENV_TAG)

# Skip test flag
# make all SKIP_TESTS=y <- doest not run unit tests
ifdef SKIP_TESTS
	MAVEN_OPTS=-Dmaven.test.skip=true
endif  

# PHONY 
.PHONY: all vlol-app build-env start-env clean all

	
##############################################################
#	make all:
#		This recipe starts the volo-build-env with repo volumed
#		mapped into the container can creates the vlol-app jar
#		then exits. This is useful so that developer do not 
#		need to modify enviorments.
##############################################################
all:
	docker run -v $(PWD)/:/repo --entrypoint '/bin/bash' $(BUILD_IMG) -c 'cd /repo && make vlol-app'

##############################################################
#	make vlol-app:
#		This recipe creats the vlol java jar
##############################################################
vlol-app:	
	@mvn $(MAVEN_OPTS) package -f pom.xml

##############################################################
#	make build-env:
#		This recipe create the Docker image capable of building
#		the vlol-app
##############################################################
build-env:
	docker build -f ./docker/Dockerfile.build.env -t $(BUILD_IMG) .

##############################################################
#	make start-env:
#		This recipe start the Docker image 
##############################################################
start-env:
	docker run -it -v $(PWD)/:/repo $(BUILD_IMG) bash

##############################################################
#	make clean:
#		This recipe cleans the user workspace
##############################################################
clean:
	@mvn package -f pom.xml


##############################################################
#	make help:
#		This recipe prints make commands
##############################################################
help:
	@cat Makefile | sed -n -e '/####/,/#####/ p' | grep -v '###' | sed -e 's/#//g' | grep -v Makefile|grep -v help
