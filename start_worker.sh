mvn clean package
docker stop apartments
docker rm apartments
docker build -t apartments .
docker run -t -p 8443:8443 --name apartments apartments

