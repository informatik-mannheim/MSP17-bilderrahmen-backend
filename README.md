# MSP17-bilderrahmen-backend

## General
This repository is part of the ['Ich-Zeig-Dir-Was'-Bilderrahmen project](https://github.com/informatik-mannheim/bilderrahmen-msp17). The backend of the bilderrahmen-project is written with spring-boot and uses an embedded tomcat as webserver.

There are two interfaces for communication. The first one is a REST-Interface which is used to create unique tokens for families. The second interface are websockets. These are used to synchronize the swipes, file changes and master requests across all mobile devices via Publish-Subscribe. 

## Overview


![Components](https://felixhefner.de/pics/Synchronizer.png)

## Deployment

Checkout the repository
```git clone https://github.com/informatik-mannheim/MSP17-bilderrahmen-backend.git```

Change directory:

``` cd MSP17-bilderrahmen-backend```

Optional: If you want to checkout the webapp aswell use:

```git submodule update --init --remote```

Then build with maven:

``` mvn install ```

Start tomcat:

```java -jar target/webserver-1.0.jar ```

Access under:

[http://localhost:8090/websocket](http://localhost:8090/websocket)


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


## Performance-Test
If you want to execute the performance-test you need to configure this file to your needings:
[TestConfig](src/test/java/de/hsmannheim/bilderrahmen/stresstest/config/TestConfig.java).

Make sure you have a running version of the backend!

Then run the following command on the project root

```mvn test -P performance-test```

## Troubleshooting

If the `openssl` from the *SSL*-section does not work for you since you do not have a SSL certificate, try the following command to create a self-signed certificate. Note that `keytool` only works if your JDK installation is in your $PATH - variable.

```bash
$ keytool -genkey -alias hs -storetype PKCS12 \
-keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
```

More information can be found on [this Stackoverflow-Page](https://stackoverflow.com/questions/40137773/self-signed-certificates-in-spring-boot) .


## License
This project is licensed under the Apache-2.0 License - see the [LICENSE](LICENSE) file for details.
