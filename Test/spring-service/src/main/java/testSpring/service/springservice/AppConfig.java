package testSpring.service.springservice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@PropertySource("classpath:/application.properties")
public class AppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private org.springframework.core.env.Environment env;

    private final static Logger lg = Logger.getLogger(AppConfig.class);


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        super.configureDefaultServletHandling(configurer);
        lg.info("start at " + dataSource());
        try {
//            createTable("derivative","CREATE TABLE derivative(" +
//                    "  deri_composite_code VARCHAR(20) NOT NULL," +
//                    "  derivative_code VARCHAR(20) NOT NULL," +
//                    "  effective_date DATE," +
//                    "  expiration_date DATE," +
//                    "  created_date DATE," +
//                    "  created_by VARCHAR(10)," +
//                    "  modified_date DATE," +
//                    "  modified_by VARCHAR(10)," +
//                    "  underlying_type VARCHAR(10))");

        } catch (Exception ex) {
            lg.error(ex, ex);
        }
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(env.getProperty("jdbc.url"));
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("testSpring/service/model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties pro = new Properties();
        pro.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        pro.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        return pro;

    }

    private void createTable(String name, String script) throws SQLException {
        DatabaseMetaData dbmd = dataSource().getConnection().getMetaData();
        ResultSet rs = dbmd.getTables(null, null, name, null);
        if (rs.next()) {
            lg.info("Table " + rs.getString("TABLE_NAME") + " already exists!");
            return;
        }
        dataSource().getConnection().createStatement().executeUpdate(script);
    }
}
