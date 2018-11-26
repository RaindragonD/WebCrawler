package crawler.jdCrawler;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import db.mySQLControl;
import model.jdModel;
import util.URLFector;

public class App {
    static final Log logger = LogFactory.getLog(App.class);
    public static void main(String[] args) throws Exception {
        //初始化一个httpclient
        CloseableHttpClient client = HttpClients.createDefault();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String url="https://search.jd.com/Search?keyword=侦探小说&enc=utf-8&wq=侦探小说&pvid=4a23520fcb1e4251a4f7664acd434800";
        //抓取的数据
        List<jdModel> bookdatas=URLFector.URLParser(client, url);
        //循环输出抓取的数据
        for (jdModel jd:bookdatas) {
            logger.info("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
        }
        //将抓取的数据插入数据库
        mySQLControl.executeInsert(bookdatas);
    }
}