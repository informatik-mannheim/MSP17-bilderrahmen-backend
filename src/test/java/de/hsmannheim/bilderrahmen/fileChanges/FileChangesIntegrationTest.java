package de.hsmannheim.bilderrahmen.fileChanges;

import de.hsmannheim.bilderrahmen.AbstractIntegrationTest;
import de.hsmannheim.bilderrahmen.client.FileChangesStompFrameHandlerClient;
import de.hsmannheim.bilderrahmen.filesync.FileChanges;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by mjando on 19.06.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileChangesIntegrationTest extends AbstractIntegrationTest<FileChanges>{

    private FileChanges objectToTest;

    @Override
    protected Object getObjectToTest() {
        return objectToTest;
    }

    @Before
    public void setup() throws Exception {
        ArrayList<String> deletedFiles = new ArrayList<>();
        ArrayList<String> createdFiles = new ArrayList<>();

        deletedFiles.add("sample.jpg");
        createdFiles.add("sample2.jpg");
        objectToTest = new FileChanges().build(deletedFiles, createdFiles);
        generalSetup(new FileChangesStompFrameHandlerClient());
    }

    @Test
    public void testFileChanges() throws Exception {
        Assert.assertEquals(objectToTest.getCreatedFiles(),  blockingQueueClient1.poll(5, SECONDS).getCreatedFiles());
        Assert.assertEquals(objectToTest.getDeletedFiles(),  blockingQueueClient2.poll(5, SECONDS).getDeletedFiles());
    }

    @Override
    protected String getSessionString() {
        return "/queue/filesync/testtoken";
    }

    @Override
    protected String getSendString() {
        return "/bilderrahmen/filesync/testtoken";
    }
}
