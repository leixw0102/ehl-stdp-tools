package com.ehl.es4sql;

import com.ehl.Sql4Plugin;
import org.elasticsearch.plugin.nlpcn.QueryActionElasticExecutor;
import org.nlpcn.es4sql.SearchDao;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.jdbc.ObjectResult;
import org.nlpcn.es4sql.jdbc.ObjectResultsExtractException;
import org.nlpcn.es4sql.jdbc.ObjectResultsExtractor;
import org.nlpcn.es4sql.query.QueryAction;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by 雷晓武 on 2017/7/10.
 */
public class Es4sql implements Sql4Plugin {


    public static void main(String[] args) throws SQLException, SqlParseException, IOException, ObjectResultsExtractException {
        SearchDao searchDao = new SearchDao(ElasticSearchFactory.buildConnection());
        for(String sql:sqls) {
//        QueryAction qa = searchDao.explain("select * from ehlindex/pass_car limit 4,5");
            QueryAction qa = searchDao.explain(MessageFormat.format(sql, "ehlindex/pass_car"));
        System.out.println(qa.explain().explain());
            ObjectResult ore = new ObjectResultsExtractor(false, false, false).extractResults(QueryActionElasticExecutor.executeAnyAction(searchDao.getClient(), qa), true);
            for (String temp : ore.getHeaders()) {
                System.out.print(temp + "\t");
            }
            System.out.println("\r\n");
            for (List<Object> temp : ore.getLines()) {
                for (Object o : temp) {
                    System.out.print(o + "\t");

                }
                System.out.println("\r\n");
            }
        }
        searchDao.getClient().close();
    }

}
