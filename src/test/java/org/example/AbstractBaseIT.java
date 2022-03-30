package org.example;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("it")
@SpringBootTest(classes = ExampleApp.class)
abstract class AbstractBaseIT {

    private static final AzureSQLEdgeContainer MSSQL_SERVER_CONTAINER = new AzureSQLEdgeContainer("mcr.microsoft.com/azure-sql-edge:latest");

    static {
        MSSQL_SERVER_CONTAINER
                .withPassword("rootAsdfQwer1234!#+-")
                .withInitScript("init.sql")
                .withUrlParam("secure", "false")
                .withUrlParam("trustServerCertificate", "true")
                .start();
        MSSQL_SERVER_CONTAINER.followOutput(outputFrame -> {
            System.out.println("MSSQL_SERVER_CONTAINER: " + outputFrame.getUtf8String().trim());
        });
        final String jdbc = MSSQL_SERVER_CONTAINER.getJdbcUrl() + ";database=example";
        System.out.println(jdbc);
        System.setProperty("JDBC_URL", jdbc);
    }
}
