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
public class PhilipsStitchFailure {

	public static void main(String[] args) {
		try {
			String service_url = "http://192.168.1.93:8080/philips/stitch/setBadStitch";// 8080
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(service_url);
			String returnResult = "";

			MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder
					.create();

			mEntityBuilder.addPart("uuid",
					new StringBody("123456789", Charset.forName("utf-8")));
			mEntityBuilder.addPart("lens_version", new StringBody("1038",
					Charset.forName("utf-8")));

			String boundary = "123456789";// + UUID.randomUUID().toString();
			mEntityBuilder.setBoundary(boundary);
			httppost.addHeader("Content-Type", "multipart/form-data; boundary="
					+ boundary);

			httppost.setEntity(mEntityBuilder.build());
			HttpResponse response = httpclient.execute(httppost);
			returnResult = EntityUtils.toString(response.getEntity());

			System.out.println("result:  " + returnResult);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
// result:
// {"code":0,"timeInfo":{"timezone":8,"rawOffset":28800000,"timezoneID":"GMT+08:00"},"data":{"device_model":{"code":30,"name":"philips"}},"requestId":"f3b20170608154302adb"}

