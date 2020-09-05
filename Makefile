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
MAVEN_OPTS="-Dversion=$(VERSION)"

VLOL_APP=VLOL-$(VERSION)
VLOL_JAR=$(VLOL_APP).jar

# Skip test flag
# make all SKIP_TESTS=y <- doest not run unit tests
ifdef SKIP_TESTS
	MAVEN_OPTS=$(MAVEN_OPTS) -Dmaven.test.skip=true
endif  

# PHONY 
.PHONY: all build build-vlol start-vlol build-env start-env clean 

	
##############################################################
#	make all:
#		This recipe starts the volo-build-env with repo volumed
#		mapped into the container can creates the vlol-app jar
#		then exits. This is useful so that developer do not 
#		need to modify enviorments.
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
#		This recipe creats the vlol java jar
##############################################################
target/$(VLOL_JAR):	
	mvn $(MAVEN_OPTS) package -f pom.xml


##############################################################
#	make build-vlol:
#		This recipe creats the vlol Docker Images
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
#		This recipe create the Docker image capable of building
#		the vlol-app
##############################################################
build-env:
	docker build -f ./docker/Dockerfile.build -t $(BUILD_IMG) .


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
	@mvn $(MAVEN_OPTS) clean -f pom.xml
	@rm -rf ./tomcat


##############################################################
#	make help:
#		This recipe prints make commands
##############################################################
help:
	@cat Makefile | sed -n -e '/####/,/#####/ p' | grep -v '###' | sed -e 's/#//g' | grep -v Makefile|grep -v help
