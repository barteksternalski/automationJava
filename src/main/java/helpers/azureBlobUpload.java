package helpers;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import org.json.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//https://mlinfdev.blob.core.windows.net/assets/304147/o.jpg?sv=2012-02-12&se=2016-04-05T09%3A19%3A47Z&sr=b&sp=w&sig=Gu6WbTo%2Bb1lKM6C4ez431DOpdCPZScukjWbn3A%2BvRvQ%3D

public class azureBlobUpload {

    public void uploadToAzure(JSONObject jobj, File file) throws URISyntaxException, StorageException, IOException {

        String azureFileUri = jobj.getJSONObject("Data").get("Uri").toString();
        String uriDate = azureFileUri.substring(azureFileUri.indexOf("&se") + 4, azureFileUri.indexOf("&se") + 14);
        String uriToken = azureFileUri.substring(azureFileUri.indexOf("&sig") + 5, azureFileUri.length());
        System.out.println(azureFileUri);

        URI uri = URI.create("https://mlinfdev.blob.core.windows.net/");
        CloudStorageAccount storageAccount = CloudStorageAccount.getDevelopmentStorageAccount(uri);
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
        CloudBlobContainer container = blobClient.getContainerReference("assets");
        CloudBlockBlob blob = container.getBlockBlobReference("o.jpg");
        blob.upload(new FileInputStream(file), file.length());
    }

}
