package parse;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import model.jdModel;

/*
 * 解析方式，采用Jsoup解析，有不明白Jsoup的可以上网搜索API文档
 * Jsoup是一款很简单的html解析器
 */

public class jdParse {
    public static List<jdModel> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<jdModel> data = new ArrayList<jdModel>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        
        File file = new File("jdHtml");
        try{
            //file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            byte[] thing = doc.toString().getBytes();
            out.write(thing);
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //获取html标签中的内容
        Elements elements=doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        for (Element ele:elements) {
            String bookID=ele.attr("data-sku");
            String bookPrice=ele.select("div[class=p-price]").select("strong").select("i").text();
            String bookName=ele.select("div[class=p-name]").select("em").text();
            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            jdModel jdModel=new jdModel();
            //对象的值
            jdModel.setBookID(bookID);
            jdModel.setBookName(bookName);
            jdModel.setBookPrice(bookPrice);
            //将每一个对象的值，保存到List集合中
            data.add(jdModel);
        }
        //返回数据
        return data;
    }
}
