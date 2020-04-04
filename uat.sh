./gradlew bootJar
cp build/libs/leo-0.0.1-SNAPSHOT.jar ~/Desktop/docker/universe/leo/ser.jar
mkdir ~/Desktop/docker/universe/leo/config/
cp -r src/main/resources/leo-bd12a-firebase-adminsdk-nna1r-246e2e1f84.json ~/Desktop/docker/universe/leo/config/
cp src/main/resources/application-uat.yml ~/Desktop/docker/universe/leo/config/
cp src/main/resources/log4j2.xml ~/Desktop/docker/universe/leo/config/
cd ~/Desktop/docker/universe/leo
docker stop leo
docker run --rm -it -d --name leo -p 60005:8906 --network net -v /Users/chengweiou/Desktop/docker/universe/leo:/proj/ -w /proj/ openjdk java -jar ser.jar