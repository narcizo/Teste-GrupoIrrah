# Teste técnico Grupo Irrah

Java 20\
Spring-boot 3.1.2


## Docker

Baixar a imagem com o .jar do [DockerHub](https://hub.docker.com/repository/docker/narcizo/teste_irrah_backend_img/general).

Após baixar a imagem rodar o seguinte comando para subir os containers do PostgreSQL e do SpringBoot:
```
cd src/main/docker
docker-compose up
cd ..
```

Após isso é possivel acessar a API através do endereço ```localhost:8081/<caminho-endpoint>```

## Banco de Dados
Na pasta /pgdump tem um arquivo .sql que pode ser rodado para obter um 
backup do banco de dados que usei.

Primeiro temos que colocar esse arquivo para dentro do container. Para isso rode

```
docker cp <caminho<pgdump/backup.sql>> docker-db-1:/docker-pgdump/backup.sql
```

Depois use o seguinte comando para executar o sql:
```
docker exec docker-db-1 psql -U admin teste_irrah_db < /pgdump/backup.sql
```

IMPORTANTE: Para criar um cliente o campo ```paymentPlan``` é obrigatório. Se certifique que
```paymentPlan.planType``` e ```paymentPlan.id``` existam e estejam corretos. 

## IDE
Caso vá rodar numa IDE, eu usei o IntelliJ Community Edition e essas foram minhas configurações para 
rodar: 

![alt text](Images/RunDegub%20config.png "Run/Dedub Configuration")

## Swagger Doc
Quando rodar o projeto colocar a seguinte URL no navegador
http://localhost:8081/swagger-ui/index.html#/.

## Postman
Um arquivo JSON da collection do Postman que fiz para esse projeto está disponível na raiz do projeto com o nome 
```Teste Irrah.postman_collection.json```.

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

## Dúvidas e contato
Quaisquer dúvidas sobre o projeto por favor me mandar mensagem no [LinkedIn](https://www.linkedin.com/in/narcizog/)
ou pelo meu email narcizo.gabriel2@gmail.com.
