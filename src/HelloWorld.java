

import java.io.*;
import java.util.*;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.UUID;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.security.SecureRandom;


import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;

public class HelloWorld {

	protected static CloudTableClient tableClient;
	protected static CloudTable table;
	protected final static String tableName = "sflog";
	
	static {
	    // Create a trust manager that does not validate certificate chains
	    TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
	        public X509Certificate[] getAcceptedIssuers(){return null;}
	        public void checkClientTrusted(X509Certificate[] certs, String authType){}
	        public void checkServerTrusted(X509Certificate[] certs, String authType){}
	    }};

	    // Install the all-trusting trust manager
	    try {
	        SSLContext sc = SSLContext.getInstance("TLS");
	        sc.init(null, trustAllCerts, new SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	    } catch (Exception e) {
	        ;
	    }
	}

	
	public static void main(String[] args) throws InvalidKeyException, URISyntaxException, InterruptedException, StorageException {
		// TODO Auto-generated method stub
		
//		while(true){
//			logger.debug(i);
//			i++;
//			try{
//				Thread.sleep(5000);
//			} catch (Exception e){
//				logger.error("error");
//			}
//			
//		}
		String connectionString = "BlobEndpoint=https://azbetlog.blob.core.chinacloudapi.cn/;TableEndpoint=https://azbetlog.table.core.chinacloudapi.cn/;DefaultEndpointsProtocol=http;AccountName=azbetlog;AccountKey=xIWuaEwM3/hDAE46Jq/gFUzBbvLe5w5UiQaUxP7NYqtTbBGIKn0KyK99aDhduwvLrQ8oKM56LnAJZ04EYjlAig==";
		CloudStorageAccount account = CloudStorageAccount.parse(connectionString);
		tableClient = account.createCloudTableClient();
		try{
			table = tableClient.getTableReference(tableName + UUID.randomUUID().toString().replace("-", ""));
			table.createIfNotExists();
		} catch (Throwable t) {
			
		}
		
		int n = 0;
		while(true){
			n++;
			Date d = new Date();
			LogEntity log = new LogEntity(String.valueOf(d.getTime()),"row"+n);
			log.setT(d.toGMTString());
			TableOperation oper = TableOperation.insert(log);
			table.execute(oper);
			System.out.println("done");
			Thread.sleep(5000);
		}
	}

}

