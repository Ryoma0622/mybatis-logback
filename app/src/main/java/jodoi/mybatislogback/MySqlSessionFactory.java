package jodoi.mybatislogback;

import jodoi.mybatislogback.mapper.UserMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;



import javax.sql.DataSource;
import java.util.Properties;

public class MySqlSessionFactory {

    SqlSessionFactory build() {
        Properties prop = new Properties();
        prop.setProperty("driver", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("url", "jdbc:mysql://127.0.0.1:3306/db");
        prop.setProperty("user", "root");
        prop.setProperty("password", "root");
        MyDataSourceFactory myDataSourceFactory = new MyDataSourceFactory();
        myDataSourceFactory.setProperties(prop);
        DataSource dataSource = myDataSourceFactory.getDataSource();
        TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        Environment environment =
                new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
//        configuration.addInterceptor(new CustomInterceptor());
        return new SqlSessionFactoryBuilder().build(configuration);

    }
}
