package com.learning.spring.dao;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class InitDb {

    private final Logger LOGGER = Logger.getLogger(InitDb.class);

    @Autowired
    public InitDb() throws IOException, SQLException {
        Resource resource = new ClassPathResource("dataBase.sql");
        Statement statement = JDBC.getInstance().getConnection().createStatement();
        File script = resource.getFile();
        ScriptRunner runner;
        runner = new ScriptRunner(JDBC.getInstance().getConnection());

        try {
            statement.execute("SELECT ID from Iogin_info");
            ResultSet resultSet  = statement.getResultSet();
        } catch (SQLException e) {
            runner.runScript(new FileReader(script));
            LOGGER.debug("Created scheme in db");
        }
    }
}
