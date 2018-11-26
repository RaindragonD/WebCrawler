package crawler.imageDownload;

import java.util.List;
import java.util.ArrayList;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import model.ImgModel;
import util.UrlUtil;
import util.ImageParser;
import util.BaiduImgParser;
import util.GoogleImgParser;
import store.LocalStorage;
import store.PreProcess;
import store.SQLStorage;

@SuppressWarnings("unused")
public class App {
	private static String searchEngine = "google";
	private static String keyword = "梅根福克斯";
	private static int pageNumber = 2;		
	private static String storageAdr = "/Users/RaindragonD/Desktop/Java/Crawler/imageDownload/database/"+keyword;
	
	public static void main(String[] args) throws Exception { 
	    // Create a new client and a list
		CloseableHttpClient client = HttpClients.createDefault();
		List<ImgModel> imgs = new ArrayList<ImgModel>();
		ImageParser parser = null;
		
		switch (searchEngine) {
		default:
		case "google":
			parser = new GoogleImgParser(keyword);
			break;		
		case "baidu":
			parser = new BaiduImgParser(keyword);
			break;
		}
		
		List<ImgModel> imgs_temp = null;
		for (int i=1;i<=pageNumber;i++) {
			String query = parser.generateQuery(i);
			String source = UrlUtil.parseUrl(client,query);
			imgs_temp = parser.getData(source);   
	    	for (int j=0;j<imgs_temp.size();j++) {imgs.add(imgs_temp.get(j));}
		}
		
		
		// DownloadImages and save to SQL; package storage
		PreProcess.modName(PreProcess.delRptImg(imgs));
		LocalStorage.download(imgs,storageAdr);
		//SQLStorage.executeInsert(imgs);
	}
}
