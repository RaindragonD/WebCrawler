package db;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class myDataSource {
    public static DataSource getDataSource(String connectURI){
        BasicDataSource ds = new BasicDataSource();
         //MySQL的jdbc驱动
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");              //所要连接的数据库名
        ds.setPassword("202520lyl");                //MySQL的登陆密码
        ds.setUrl(connectURI);
        return ds;
    }
}