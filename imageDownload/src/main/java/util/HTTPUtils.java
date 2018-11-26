package util;

import java.io.IOException;
import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.HttpVersion;
//import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.message.BasicHttpResponse;

public class HTTPUtils {
    public static HttpResponse getRawHtml(CloseableHttpClient client, String personalUrl) {
        //获取响应文件，即html，采用get方法获取响应数据
        HttpGet getMethod = new HttpGet(personalUrl);
        CloseableHttpResponse response = null;
        try {
            //执行get方法
            response = client.execute(getMethod);
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            // getMethod.abort();
        }
        return response;
    }

}