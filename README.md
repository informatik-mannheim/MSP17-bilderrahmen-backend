# MSP17-bilderrahmen-backend

## General
The backend of the bilderrahmen-project is written with spring-boot and uses an embedded tomcat as webserver.

There are two interfaces for communication. The first one is a REST-Interface which is used to create unique tokens for families. The second interface are websockets. These are used to synchronize the swipes, file changes and master requests across all mobile devices via Publish-Subscribe. 

## Overview

TODO BILD 

## Deployment

Checkout the repository
```git clone https://github.com/informatik-mannheim/MSP17-bilderrahmen-backend.git```

Then build with maven:

``` mvn install ```

Change directory:

``` cd MSP17-bilderrahmen-backend```

Optional: If you want to checkout the webapp aswell use:

```git submodule update --init --remote```

Start tomcat:

```java -jar target/webserver-1.0.jar ```

Access under:

[http://localhost:8090](http://localhost:8090)


## SSL

Spring-Boot offers some simple settings to enable SSl. But be aware there can be problems with self-signed certificates.

add to src/main/resources/application.properties
```
server.port: 8443
security.require-ssl=true
server.ssl.key-store:/path/to/keystore.p12
server.ssl.key-store-password: <your-password>
server.ssl.keyStoreType: PKCS12
server.ssl.keyAlias: tomcat`
```

You can create the keystore for example like this:

```openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem -out keystore.p12 -name tomcat -CAfile chain.pem -caname root```

Then build and run the project again

Access under:

[https://localhost:8443](https://localhost:8443)





