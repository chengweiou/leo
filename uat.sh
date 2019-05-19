./gradlew bootJar
cp build/libs/leonids-0.0.1-SNAPSHOT.jar ~/Desktop/docker/leonids/ser.jar
mkdir ~/Desktop/docker/leonids/config/
cp -r src/main/resources/leonids-bd12a-firebase-adminsdk-nna1r-246e2e1f84.json ~/Desktop/docker/leonids/config/
cp src/main/resources/application-uat.yml ~/Desktop/docker/leonids/config/
cp src/main/resources/log4j2.xml ~/Desktop/docker/leonids/config/
cd ~/Desktop/docker/leonids
docker stop leonids
docker run --rm -it -d --name leonids -p 60004:8906 --network net -v /Users/chengweiou/Desktop/docker/leonids:/proj/ -w /proj/ openjdk java -jar ser.jar