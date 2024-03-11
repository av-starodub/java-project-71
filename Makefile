.DEFAULT_GOAL := build-run

clean:
	make -C app clean

build:
	make -C app build

install:
	make -C app install

# I used this article to pass arguments to the make-run command.
# https://stackoverflow.com/questions/2214575/passing-arguments-to-make-run/45003119#45003119:~:text=to%20make%20run%22.-,Method%201%3A,-run%3A%20prog%0A%20%20%20%20./prog

ARGS = $(filter-out $@, $(MAKECMDGOALS))
%:
	@true

stylish:
	make -C app run-stylish $(ARGS)

plain:
	make -C app run-plain $(ARGS)

json:
	make -C app run-json $(ARGS)

help:
	make -C app run-help

test:
	make -C app test

report:
	make -C app report

lint:
	make -C app lint

update-deps:
	make -C app update-deps


build-run: build run

.PHONY: build
