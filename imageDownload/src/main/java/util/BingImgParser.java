package util;

import java.util.List;

import model.ImgModel;

public class BingImgParser extends ImageParser{
	//async=content  
    //q=美女 搜索关键字  
    //first=118 开始条数  
    //count=35 显示数量  
    private static final String BING_IMAGE_SEARCH_URL = "http://www.bing.com/images/async?async=content&q="+
    		"%s&first=%d&count=%d";  
    private static final int PAGE_SIZE = 35;  
	private static final String REGURL="imgurl:&quot;(https?://[^,]+)&quot;";  ;	
	private static final String REGNAME="\"pt\":\"([^\"]+)";
	private static final String PREFIXURL="\"ou\":\"";	
	private static final String PREFIXNAME="\"pt\":\"";
	
	private String keyword;
	
	public BingImgParser(String keyword) {
		this.keyword=keyword;
	}
	
	@Override
	public String generateQuery(int page) {
		int begin = page * PAGE_SIZE; 
		return String.format(BING_IMAGE_SEARCH_URL, keyword, begin, PAGE_SIZE, Integer.toHexString(begin)); 
	}
	
	public List<ImgModel> getData(String source){
		return super.getSource(source,REGURL,REGNAME,PREFIXURL,PREFIXNAME);
	}
}
