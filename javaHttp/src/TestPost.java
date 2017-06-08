import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by mowi on 17/6/7.
 */
public class TestPost {

    public static void main(String[] args) {
        try {
            String service_url="http://192.168.1.93:8080/philips/stitch/uploadStitchImage";//8080
            HttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(service_url);
            String returnResult = "";


            File circle1 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193505_001.JPG");
            File circle2 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193509_002.JPG");
            File circle3 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193510_003.JPG");
            File match1 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193510_004.JPG");
            File match2 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193510_005.JPG");
            File match3 = new File("D:/360Downloads/Software/qqtextdoc/3162481706/FileRecv/PHOTO/2017_0607_193510_006.JPG");

//            MultipartEntity reqEntity = new MultipartEntity();
//            reqEntity.addPart("circle[1]", new FileBody(circle1));
//            reqEntity.addPart("circle[2]", new FileBody(circle2));
//            reqEntity.addPart("circle[3]", new FileBody(circle3));
//
//            reqEntity.addPart("match[4]", new FileBody(match1));
//            reqEntity.addPart("match[5]", new FileBody(match2));
//            reqEntity.addPart("match[6]", new FileBody(match3));
//
//            reqEntity.addPart("uuid",new StringBody("123456789", Charset.forName("utf-8")));

            MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
            mEntityBuilder.addBinaryBody("circle[]", circle1);
            mEntityBuilder.addBinaryBody("circle[]", circle2);
            mEntityBuilder.addBinaryBody("circle[]", circle3);
            mEntityBuilder.addBinaryBody("match[]", match1);
            mEntityBuilder.addBinaryBody("match[]", match2);
            mEntityBuilder.addBinaryBody("match[]", match3);
            mEntityBuilder.addPart("uuid", new StringBody("123456789", Charset.forName("utf-8")));
            mEntityBuilder.addPart("lens_version", new StringBody("1038", Charset.forName("utf-8")));

            String boundary ="123456789";//+ UUID.randomUUID().toString();
            mEntityBuilder.setBoundary(boundary);
            httppost.addHeader("Content-Type", "multipart/form-data; boundary="+boundary);
            
            httppost.setEntity(mEntityBuilder.build());
            HttpResponse response = httpclient.execute(httppost);
            returnResult = EntityUtils.toString(response
                    .getEntity());

            System.out.println("result:  "+returnResult);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
//result:  {"code":0,"timeInfo":{"timezone":8,"rawOffset":28800000,"timezoneID":"GMT+08:00"},"data":{"device_model":{"code":30,"name":"philips"},"info":{"match_count":0,"offset":"2_709.200_730.800_730.800_0.000_0.000_90.000_693.800_2188.800_720.000_0.000_0.000_90.000_2880_1440_1038","image_path":"/data/storage/philips/20170608143551036","last_modify_time":1496905904000,"id":18,"state":4,"upload_cnt":4,"uuid":"123456789"}},"requestId":"d3d2017060815393533f"}

