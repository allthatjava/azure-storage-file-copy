package brian.example.storage.file.copy.azurestoragefilecopy;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

public class AzureStorageFileCopyApplication {

    public static void main(String[] args) {

        String connectionString = "{{your_storage_connection_string}}";
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        BlobContainerClient sourceContainerClient = blobServiceClient.getBlobContainerClient("firstcontainer");
        BlobClient sourceBlobClient = sourceContainerClient.getBlobClient("test.txt");

        BlobContainerClient destContainerClient = blobServiceClient.getBlobContainerClient("secondcontainer");
        BlobClient destBlobClient = destContainerClient.getBlobClient("test-COPY.txt");

        destBlobClient.beginCopy(sourceBlobClient.getBlobUrl(), null);
        System.out.println("Copy operation completed successfully");
    }

}
