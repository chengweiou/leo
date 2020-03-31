./gradlew bootJar
cp build/libs/leonids-0.0.1-SNAPSHOT.jar ~/Desktop/docker/universe/leonids/ser.jar
mkdir ~/Desktop/docker/universe/leonids/config/
cp -r src/main/resources/leonids-bd12a-firebase-adminsdk-nna1r-246e2e1f84.json ~/Desktop/docker/universe/leonids/config/
cp src/main/resources/application-uat.yml ~/Desktop/docker/universe/leonids/config/
cp src/main/resources/log4j2.xml ~/Desktop/docker/universe/leonids/config/
cd ~/Desktop/docker/universe/leonids
docker stop leonids
docker run --rm -it -d --name leonids -p 60005:8906 --network net -v /Users/chengweiou/Desktop/docker/universe/leonids:/proj/ -w /proj/ openjdk java -jar ser.jar