package brian.example.storage.file.copy.azurestoragefilecopy;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobProperties;

import java.util.HashMap;
import java.util.Map;

public class AzureStorageFileCopyApplication {

    public static void main(String[] args) {

        String connectionString = "DefaultEndpointsProtocol=https;AccountName=teststorageaccount123123;AccountKey=3fjj7aNCvbv62MLQoG1i96CRZLivjMbcyD5mN9G4wN6k9p4ub3/eekUQj35Uq03XFeGLx1uLbTVr+AStnagsaQ==;EndpointSuffix=core.windows.net";
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        BlobContainerClient sourceContainerClient = blobServiceClient.getBlobContainerClient("firstcontainer");
        BlobClient sourceBlobClient = sourceContainerClient.getBlobClient("test.txt");

        BlobContainerClient destContainerClient = blobServiceClient.getBlobContainerClient("secondcontainer");
        BlobClient destBlobClient = destContainerClient.getBlobClient("test-COPY.txt");

        // Optional Meta data manipulation
        Map<String, String> metaData = new HashMap<>();
        metaData.put("copier","Brian");
        metaData.put("environment", "development");
        sourceBlobClient.setMetadata(metaData);   // Adding metaData while copying the file.

        destBlobClient.beginCopy(sourceBlobClient.getBlobUrl(), null);
        System.out.println("File copy completed successfully");
    }

}
