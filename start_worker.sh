mvn clean package
docker stop apartments
docker rm apartments
docker build -t apartments .
docker run -t -p 8443:8443 --volume ~/Repos/apartment-notifications/saved_information:/saved_information --name apartments apartments

