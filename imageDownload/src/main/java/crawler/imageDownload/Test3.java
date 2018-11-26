package crawler.imageDownload;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Test3 {

	public static void main(String[] args) {
		CloseableHttpClient client = HttpClients.createDefault();
		
		 //Get responsed html, use httpUtil
		HttpGet getMethod = new HttpGet("http://image.baidu.com/search/avatarjson?tn=resultjsonavatarnew&ie=utf-8&word=凯莉·布鲁克&cg=star&pn=30&rn=30&itg=0&z=0&fr=&width=&height=&lm=-1&ic=0&s=0&st=-1&gsm=1e");
        CloseableHttpResponse response = null;
        try {
            //执行get方法
            response = client.execute(getMethod);
            int StatusCode = response.getStatusLine().getStatusCode();
            //get html/json entity if status code is 200
            if(StatusCode == 200){
                String entity = EntityUtils.toString (response.getEntity(),"utf-8");    
                Document doc = Jsoup.parse(entity);
                
                File file = new File("baiduXML");
                try{
                    //file.createNewFile();
                    FileOutputStream out = new FileOutputStream(file);
                    byte[] thing = doc.toString().getBytes();
                    out.write(thing);
                    out.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                
                EntityUtils.consume(response.getEntity());
            }else {
                //else comuse the entity
                EntityUtils.consume(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            // getMethod.abort();
        }      
        //get status code
        
		

	}

}
