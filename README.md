# StrayPets-Backend

Backend do meu aplicativo feito em React Native para ajudar animais de rua

# Demo
[Demo do Stray Pets](https://stray-pets.s3.sa-east-1.amazonaws.com/stray-pets-backend/screen-20230724-113711.mp4)

# Como executar?
-> <code>mvn clean package</code><br>
-> <code>java -jar -DGOOGLE_MAPS_KEY={chave-do-google-maps} -DJWT_SECRET={chave-do-token} .\target\StrayPets-0.0.1-SNAPSHOT.jar</code>

## Exemplo:
<code>java -jar -DGOOGLE_MAPS_KEY=chave-do-maps -DJWT_SECRET=chave-do-jwt .\target\StrayPets-0.0.1-SNAPSHOT.jar</code>

# Tecnologias utilizadas:
<ul>
  <li>Spring Boot</li>
  <li>JPA</li>
  <li>MySQL</li>
  <li>Redis</li>
  <li>AWS S3</li>
  <li>Google Maps Api</li>
</ul>

# Arquitetura
![StrayPets arquitetura](https://stray-pets.s3.sa-east-1.amazonaws.com/stray-pets-backend/StrayPets+(1).png)

