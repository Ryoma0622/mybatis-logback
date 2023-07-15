package jodoi.mybatislogback;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class MyDataSourceFactory implements DataSourceFactory {
    private Properties prop;

    @Override
    public void setProperties(Properties props) {
        this.prop = props;
    }

    @Override
    public DataSource getDataSource() {

        PooledDataSource ds = new PooledDataSource();

        ds.setDriver(prop.getProperty("driver"));
        ds.setUrl(prop.getProperty("url"));
        ds.setUsername(prop.getProperty("user"));
        ds.setPassword(prop.getProperty("password"));

        return ds;
    }

}
