.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean checkstyleMain checkstyleTest build

install:
	./gradlew clean install

# I used this article to pass arguments to the make-run command.
# https://stackoverflow.com/questions/2214575/passing-arguments-to-make-run/45003119#45003119:~:text=to%20make%20run%22.-,Method%201%3A,-run%3A%20prog%0A%20%20%20%20./prog

ARGS = $(filter-out $@, $(MAKECMDGOALS))
%:
	@true

run-stylish:
	./build/install/app/bin/app $(ARGS)

run-plain:
	./build/install/app/bin/app -f 'plain' $(ARGS)

run-json:
	./build/install/app/bin/app -f 'json' $(ARGS)

run-help:
	./build/install/app/bin/app -h

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions

build-run: build run

.PHONY: build
