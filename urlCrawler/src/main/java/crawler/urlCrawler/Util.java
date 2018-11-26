package crawler.urlCrawler;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Util {
	public static void downloadFile() {

	}

	public static void saveUrl(String url,String dir) {
		PrintWriter pw = null;
		//File fileDir = new File(dir);
		//if(!fileDir.exists()) {fileDir.mkdirs();}

		try {
			pw = new PrintWriter(new FileWriter(dir),true);
			pw.println(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void saveUrls(ArrayList<String> urls, String dir) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(dir),true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(int i=0;i<urls.size();i++) {
			String url = urls.get(i);
			pw.println(url);
		}
	}

	public static boolean getResponse(String url) {
		boolean haveResponse = false;
		CloseableHttpClient client = HttpClients.createDefault();
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
		if (response!=null) {haveResponse=true;}
		return haveResponse;
		
		
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

	public static ArrayList<String> getURLs(String source) {
		String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";  
		Pattern p = Pattern.compile(regex);  
		Matcher m =  p.matcher(source);

		ArrayList<String> urls = new ArrayList<>();
		while (m.find()) {
			urls.add(m.group());
		}     
		return urls;
	}
}
