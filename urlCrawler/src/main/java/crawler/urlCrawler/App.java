package crawler.urlCrawler;


/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		String dir = "/Users/RaindragonD/Desktop/Java/Crawler/urlCrawler/url.txt";
		int depth = 3;
		String url = "https://www.yangqq.com";        
		
		MyCrawler c = new MyCrawler(url,depth,dir);
		c.craw();
	}
}
