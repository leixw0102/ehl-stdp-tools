
import com.ehl.stdp.phoenix.sql.Callback;
import com.ehl.stdp.phoenix.sql.PhoenixSqlUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Created by 雷晓武 on 2017/3/8.
 */
public class Example {

    public static void main(String []args) throws Exception {
            final long start = System.nanoTime();
        PhoenixSqlUtils phoenixSqlUtils = PhoenixSqlUtils.getInstance();
        phoenixSqlUtils.execute(new Callback<Object>() {
            @Override
            public Object call(Connection conn) throws Exception {
                System.out.println("=="+(System.nanoTime()-start)+"\t"+new Date().toLocaleString());
                ResultSet rs = null;
                try {
//                   rs= conn.createStatement().executeQuery("select * from \"mobile\"" + " LIMIT 5 OFFSET 10");
                    rs = conn.prepareStatement("select count(*) from \"mobile\"").executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getLong(1));
                    }
                }finally {
                    if( null != rs){
                        rs.close();
                    }
                }
                System.out.println("==111"+(System.nanoTime()-start)+"\t"+new Date());
                return null;
            }
        });
    }
}
