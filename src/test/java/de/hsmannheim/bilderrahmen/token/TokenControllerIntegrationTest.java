package de.hsmannheim.bilderrahmen.token;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import org.springframework.test.context.TestPropertySource;

/**
 * Created by mjando on 29.05.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:test.properties")
public class TokenControllerIntegrationTest {

    @LocalServerPort
    public int port;

    CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
    HttpPost httpPost;
    String HOST_URL;
    String ENDPOINT;

    @Before
    public void setUp() {
        HOST_URL = "http://localhost:" + port;
        ENDPOINT = HOST_URL + "/token";
        httpPost = new HttpPost(ENDPOINT);
    }


    @Test
    public void testTokenEndpointWithNoParameters() throws IOException {
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        assert response.getStatusLine().getStatusCode() == 400;
    }

    @Test
    public void testTokenEndpointWithCorrectParameters() throws IOException {
        httpPost.setEntity(new StringEntity("apfel@googlemail.com"));
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        assert response.getStatusLine().getStatusCode() == 200;
    }

    @Test
    public void testTokenEndpointWithNoCorrectParameters() throws IOException {
        httpPost.setEntity(new StringEntity(""));
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        assert response.getStatusLine().getStatusCode() == 400;
    }

}
