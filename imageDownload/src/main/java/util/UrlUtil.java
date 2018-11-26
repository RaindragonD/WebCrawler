package util;

import java.io.IOException;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import model.ImgModel;
import util.BaiduImgParser;

@SuppressWarnings("unused")
public class UrlUtil {
	//public static List<ImgModel> parseUrl(CloseableHttpClient client, String url) throws Exception{
	public static String parseUrl(CloseableHttpClient client, String url) throws Exception{
		//List<ImgModel> imgs = new ArrayList<ImgModel>();
		String source = null;
        try {
            //执行get方法
        	 HttpResponse response = HTTPUtils.getRawHtml(client, url);
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
        return source;
	}
}
