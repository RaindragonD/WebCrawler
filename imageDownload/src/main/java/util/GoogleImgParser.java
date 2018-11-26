package util;

import java.util.List;

import model.ImgModel;

public class GoogleImgParser extends ImageParser{
	//tbm=isch  
	//q=美女 搜索关键字  
	//ijn=0 页码(***Google只提供到0到8页数据！)  
	//start=0 开始条数  
	//tbs=isz:l 搜索条件  
	//  尺寸  
	//  tbs=isz:l 大  
	//  tbs=isz:m 中  
	//  颜色  
	//  tbs=ic:color 彩色  
	//  tbs=ic:gray 黑白  
	//  tbs=ic:trans 透明  
	//  类型  
	//  tbs=itp:face 脸部特写  
	//  tbs=itp:photo 照片  
	//  tbs=itp:clipart 剪贴画  
	//  tbs=itp:lineart 素描画  
	//  tbs=itp:animated 动画  
	//  条件组合  
	//  tbs=isz:l,ic:color,itp:face  
	private static final String GOOGLE_IMAGE_SEARCH_URL = "https://www.google.com/search?tbm=isch&q="+
			"%s&ijn=%d&start=%d&tbs=isz:l";  
	private static final int PAGE_SIZE = 100;  
	
	private static final String REGURL="\"ou\":\"(https?://[^\"]+)\\.jpg";	
	private static final String REGNAME="\"pt\":\"([^\"]+)";
	private static final String PREFIXURL="\"ou\":\"";	
	private static final String PREFIXNAME="\"pt\":\"";
	
	private String keyword;
	
	public GoogleImgParser(String keyword) {
		this.keyword=keyword;
	}
	
	@Override
	public String generateQuery(int page) {
		int begin = page * PAGE_SIZE; 
		return String.format(GOOGLE_IMAGE_SEARCH_URL, keyword, begin, PAGE_SIZE, Integer.toHexString(begin)); 
	}
	
	public List<ImgModel> getData(String source){
		return super.getSource(source,REGURL,REGNAME,PREFIXURL,PREFIXNAME);
	}
}
