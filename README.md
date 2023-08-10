# Teste técnico Grupo Irrah

Java 20\
Spring-boot 3.1.2


## Docker Hub repo
[Docker image](https://hub.docker.com/repository/docker/narcizo/teste_irrah_backend_img/general)

Rodar o seguinte comando para subir o container:

docker run -d 
--name teste-irrah-narcizo-container 
-e POSTGRES_PASSWORD=admin
-e POSTGRES_USER=admin
-e POSTGRES_DB=teste-irrah-db
-p 5432:5432 
my-postgres-image

## Swagger Doc
Quando rodar o projeto colocar a seguinte URL no navegador
http://localhost:8081/swagger-ui/index.html#/.

## Postman
Um arquivo JSON da collection do Postman que fiz para esse projeto está disponível na raiz do projeto.

## FrontEnd
Infelizmente não consegui implementar um Frontend para esse projeto no tempo 
especificado, porém se quiser avaliar as minhas habilidades no frontend tenho um projeto exclusivamente frontend nesse 
[repo aqui](https://github.com/narcizo/Teste-Be220) utilizando Ionic e Angular.

## Envio de SMS e Whatsapp
Para o envio das mensagens usei a api da [Twilio](https://mvnrepository.com/artifact/com.twilio.sdk/twilio/9.9.1),
tanto para SMS quanto para WhatsApp.

Infelizmente por questões de segurança essa feature não estará disponível, pois precisaria 
compartilhar informações sensíveis sobre a minha conta na Twilio, portanto irei disponibilizar alguns prints da feature
funcionando./

### Mandando mensagem por SMS via API do Twilio
![alt text](Images/mensagemTwilioSms.jpg "Mandando mensagem por SMS via API do Twilio")
### Mandando mensagem por WhatsApp via API do Twilio
![alt text](Images/mensagemTwilioWhats.jpg "Mandando mensagem por WhatsApp via API do Twilio")
