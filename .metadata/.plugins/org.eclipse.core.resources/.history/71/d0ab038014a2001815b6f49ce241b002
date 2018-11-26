package test;

import java.io.FileWriter;  
import java.io.IOException;  
import java.io.PrintWriter;  
import java.net.MalformedURLException;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;

public class WebSpiderDemo {  
    public static void main(String[] args) {  
        String url = "http://www.sina.com.cn/";   
        String source = PreAnalyze.getSource(url);;  
        PrintWriter pw = null;  
        String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";  
        Pattern p = Pattern.compile(regex);  
        try {  
            pw = new PrintWriter(new FileWriter("/Users/RaindragonD/Desktop/Java/Crawler/urlCrawler/url.txt"), true);            
            Matcher m =  p.matcher(source);
            int count = 0;
            while (m.find()) {
            	pw.println(m.group()); 
            	count++;
            }
            System.out.println("Get "+count+" URL");  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
    }  
} 
