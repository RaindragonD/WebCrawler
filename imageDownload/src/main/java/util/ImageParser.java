package util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;

import model.ImgModel;

public class ImageParser {
	
	public String generateQuery(int page) {
		return null;
	}
	
	public List<ImgModel> getData(String source){
		return null;
	}
	
	public List<ImgModel> getSource(String source,String REGURL, String REGNAME,String PREFIXURL, String PREFIXNAME){
		List<ImgModel> imgs = new ArrayList<>();
		Document doc = Jsoup.parse(source);
		String stringSource = doc.toString();
		//System.out.println(doc); //check source file
					
		Pattern pattern = Pattern.compile(REGURL);
        Matcher m = pattern.matcher(stringSource);
        ArrayList<String> urls = new ArrayList<String>();
        while (m.find()) {
        	String url = m.group().replaceAll(PREFIXURL,"").trim();
        	urls.add(url);
        } 
        
        pattern = Pattern.compile(REGNAME);
        m = pattern.matcher(stringSource);
        ArrayList<String> names = new ArrayList<String>();
        while (m.find()) {
        	String name = m.group().replaceAll(PREFIXNAME,"").trim();
        	names.add(name);
        } 
		
        for (int i=0;i<urls.size();i++) {
        	ImgModel img = new ImgModel();
        	String url = urls.get(i);
        	img.setImgURL(url);
        	String name = names.get(i)+url.substring(url.lastIndexOf("."));
        	img.setImgName(name);
        	imgs.add(img);
        }
        
		return imgs;
	}
}
