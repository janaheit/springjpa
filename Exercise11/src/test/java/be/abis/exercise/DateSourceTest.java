package be.abis.exercise;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class DateSourceTest {

    @Autowired
    DataSource dataSource;
    @Autowired
    Environment env;

    @Test
    void testConnectionViaDataScourse(){
        try{
            Connection c = dataSource.getConnection();
            System.out.println("connection succeeded via "
                    + c.getMetaData().getDatabaseProductName() + " in profile ");
                    //+ env.getProperty("spring.profiles.active"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
