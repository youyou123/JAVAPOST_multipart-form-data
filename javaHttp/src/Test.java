

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/** 
 * @author  yanggb 
 * @date 创建时间：2017-6-7 下午3:15:41 
 * @version 1.0 
 * @parameter  
 * @return 
 */
public class Test {
	
	public static void main(String[] args) {
		  try {  
         String service_url="http://192.168.1.93:8080/philips/stitch/uploadStitchImage";
		 HttpClient httpclient = new DefaultHttpClient();  
	     HttpPost httppost = new HttpPost(service_url);  
	     String returnResult = "";  
	           //文件参数
	    	   File circle1 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193505_001.JPG");
	     	   File circle2 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193505_001.JPG");
	     	   File circle3 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193505_001.JPG");
	     	   File match1 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193505_001.JPG");
	     	   File match2 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193505_001.JPG");
	     	   File match3 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193505_001.JPG");
	     	   
	     	   //入参
	     	   MultipartEntity reqEntity = new MultipartEntity();
	     	  
	     	//   reqEntity.addPart("uuid",new StringBody("123456789", Charset.forName("utf-8")));
               reqEntity.addPart("circle[]", new FileBody(circle1));
               reqEntity.addPart("circle[]", new FileBody(circle2));
               reqEntity.addPart("circle[]", new FileBody(circle3));
               
               reqEntity.addPart("match[]", new FileBody(match1));
               reqEntity.addPart("match[]", new FileBody(match2));
               reqEntity.addPart("match[]", new FileBody(match3));
               reqEntity.addPart("uuid", new StringBody("123456789", Charset.forName("utf-8")));
               reqEntity.addPart("lens_version", new StringBody("1038", Charset.forName("utf-8")));
               String boundary ="123456789";//+ UUID.randomUUID().toString();
               
                 httppost.addHeader("Content-type", "multipart/form-data; boundary=123456789");  
	             httppost.setEntity(reqEntity);  
	             HttpResponse response = httpclient.execute(httppost);  
	           	 returnResult = EntityUtils.toString(response  
	                         .getEntity()); 
	            
	             System.out.println("接口返回值："+returnResult);
	     } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	          
  
	}
}
