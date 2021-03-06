package db;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

import model.jdModel;

public class mySQLControl {

    //根据自己的数据库地址修改
    static DataSource ds = myDataSource.getDataSource("jdbc:mysql://10.0.0.7:3306/JDcrawler");
    static QueryRunner qr = new QueryRunner(ds);
    //第一类方法
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //第二类数据库操作方法
    public static void executeInsert(List<jdModel> jingdongdata){
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[jingdongdata.size()][3];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = jingdongdata.get(i).getBookID();
            params[i][1] = jingdongdata.get(i).getBookName();
            params[i][2] = jingdongdata.get(i).getBookPrice();
        }
        try {
        	qr.batch("insert into JD_detective (bookID, bookName, bookPrice)"
                + "values (?,?,?)", params);
        	System.out.println("执行数据库完毕！"+"成功插入数据："+jingdongdata.size()+"条");
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        
    }
}