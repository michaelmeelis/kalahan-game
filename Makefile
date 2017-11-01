game: create-artifact build-docker run-game

create-artifact:
	docker run \
		--rm \
		-v `pwd`:/app \
		-w /app \
		maven:3.3.9-jdk-8-alpine \
			mvn package

build-docker:
	docker build \
	-t bol-game \
	`pwd`

run-game:
	docker run \
	-p 8080:8080 \
	-d \
	bol-game:latest