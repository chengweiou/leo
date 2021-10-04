./gradlew bootJar
cp build/libs/leob-0.0.1-SNAPSHOT.jar ~/Desktop/docker/universe/leob/ser.jar
mkdir ~/Desktop/docker/universe/leob/config/
cp -r src/main/resources/fcm/leob-key.json ~/Desktop/docker/universe/leob/config/
#cp src/main/resources/application.yml ~/Desktop/docker/universe/leob/config/
cp src/main/resources/application-uat.yml ~/Desktop/docker/universe/leob/config/
cp src/main/resources/log4j2.xml ~/Desktop/docker/universe/leob/config/
cp docker-compose.yml ~/Desktop/docker/universe/leob/docker-compose.yml
cd ~/Desktop/docker/universe/leob

docker compose down
docker compose rm -f
docker compose up -d
