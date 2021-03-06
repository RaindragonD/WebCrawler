package store;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import model.ImgModel;

public class SQLStorage {

	static DataSource ds = getDataSource("jdbc:mysql://10.0.0.7:3306/JDcrawler");
	static QueryRunner qr = new QueryRunner(ds);

	public static DataSource getDataSource(String connectURI){
		BasicDataSource ds = new BasicDataSource();
		//MySQL的jdbc驱动
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUsername("root");              //所要连接的数据库名
		ds.setPassword("202520lyl");                //MySQL的登陆密码
		ds.setUrl(connectURI);
		return ds;
	}


	//第一类方法
	public static void executeUpdate(String sql){
		try {
			qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//第二类数据库操作方法
	public static void executeInsert(List<ImgModel> data){
		Object[][] params = new Object[data.size()][2];
		for ( int i=0; i<params.length; i++){
			params[i][0] = data.get(i).getImgURL();
			params[i][1] = data.get(i).getImgName();
		}
		try {
			qr.batch("insert into Baidu_images (imgURL,imgName)"
					+ "values (?,?)", params);
			System.out.println("Insert into SQL："+data.size());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}
}
