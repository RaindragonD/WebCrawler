package test;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PreAnalyze {
	public static void main(String[] args) {
		String url="http://www.sina.com.cn/";
		getSource(url);
	}
	
	public static String getSource(String url) {
		CloseableHttpClient client = HttpClients.createDefault();
		String source = null;
        try {
            //执行get方法
        	HttpGet getMethod = new HttpGet(url);
            CloseableHttpResponse response = null;
            try {
                //执行get方法
                response = client.execute(getMethod);
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                // getMethod.abort();
            }
            int StatusCode = response.getStatusLine().getStatusCode();
            //get entity if status code is 200
            if(StatusCode == 200){
                source = EntityUtils.toString (response.getEntity(),"utf-8");     
                EntityUtils.consume(response.getEntity());
            }else {
                //else consume the entity
                EntityUtils.consume(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();}
		Document doc = Jsoup.parse(source);
		return doc.toString();
		//System.out.println(doc); //check source file
	}
}
