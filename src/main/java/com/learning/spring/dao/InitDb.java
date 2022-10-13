package com.learning.spring.dao;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class InitDb {

    private final Logger LOGGER = Logger.getLogger(InitDb.class);

    @Autowired
    public InitDb() throws IOException, SQLException {
        ClassPathResource classPathResource = new ClassPathResource("dataBase.sql");
        Statement statement = JDBC.getInstance().getConnection().createStatement();
        ScriptRunner runner;
        runner = new ScriptRunner(JDBC.getInstance().getConnection());
        runner.setAutoCommit(true);
        runner.setDelimiter("/");
        try {
            statement.execute("SELECT ID from Iogin_info");
            ResultSet resultSet = statement.getResultSet();
        } catch (SQLException e) {
            runner.runScript(new InputStreamReader(classPathResource.getInputStream()));
            LOGGER.debug("Created scheme in db");
        }
    }
}
