./gradlew bootJar
cp build/libs/leob-0.0.1-SNAPSHOT.jar ~/Desktop/docker/universe/leob/ser.jar
mkdir ~/Desktop/docker/universe/leob/config/
cp -r src/main/resources/leob-bd12a-firebase-adminsdk-nna1r-246e2e1f84.json ~/Desktop/docker/universe/leob/config/
cp src/main/resources/application-uat.yml ~/Desktop/docker/universe/leob/config/
cp src/main/resources/log4j2.xml ~/Desktop/docker/universe/leob/config/
cd ~/Desktop/docker/universe/leob
docker stop leob
docker run --rm -it -d --name leob -p 60005:8906 --network net -v /Users/chengweiou/Desktop/docker/universe/leob:/proj/ -w /proj/ openjdk java -jar ser.jar