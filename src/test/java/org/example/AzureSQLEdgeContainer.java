package org.example;

import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.shaded.com.google.common.collect.Sets;
import org.testcontainers.utility.DockerImageName;

import java.util.Set;
import java.util.regex.Pattern;

public class AzureSQLEdgeContainer extends JdbcDatabaseContainer<AzureSQLEdgeContainer> {

    private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse("mcr.microsoft.com/azure-sql-edge");
    private static final Pattern[] PASSWORD_CATEGORY_VALIDATION_PATTERNS = new Pattern[]{Pattern.compile("[A-Z]+"), Pattern.compile("[a-z]+"), Pattern.compile("[0-9]+"), Pattern.compile("[^a-zA-Z0-9]+", 2)};
    private static final int DEFAULT_STARTUP_TIMEOUT_SECONDS = 240;
    private static final int DEFAULT_CONNECT_TIMEOUT_SECONDS = 240;

    public static final String DEFAULT_USER = "SA";
    public static final String DEFAULT_PASSWORD = "A_Str0ng_Required_Password";
    public static final String NAME = "sqlserver";
    public static final String IMAGE = DEFAULT_IMAGE_NAME.getUnversionedPart();
    public static final Integer MS_SQL_SERVER_PORT = 1433;

    private String password;

    public AzureSQLEdgeContainer(String dockerImageName) {
        this(DockerImageName.parse(dockerImageName));
    }

    private AzureSQLEdgeContainer(DockerImageName dockerImageName) {
        super(dockerImageName);
        this.password = "A_Str0ng_Required_Password";
        if (!dockerImageName.getUnversionedPart().equals("mcr.microsoft.com/azure-sql-edge")) {
            throw new IllegalArgumentException("");
        }
        this.withStartupTimeoutSeconds(240);
        this.withConnectTimeoutSeconds(240);
        this.addEnv("ACCEPT_EULA", "Y");
        this.addExposedPort(MS_SQL_SERVER_PORT);
    }

    public Set<Integer> getLivenessCheckPortNumbers() {
        return Sets.newHashSet(new Integer[]{MS_SQL_SERVER_PORT});
    }

    protected void configure() {
        this.addEnv("SA_PASSWORD", this.password);
    }

    public String getDriverClassName() {
        return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }

    public String getJdbcUrl() {
        String additionalUrlParams = this.constructUrlParameters(";", ";");
        return "jdbc:sqlserver://" + this.getHost() + ":" + this.getMappedPort(MS_SQL_SERVER_PORT) + additionalUrlParams;
    }

    public String getUsername() {
        return "SA";
    }

    public String getPassword() {
        return this.password;
    }

    public String getTestQueryString() {
        return "SELECT 1";
    }

    public AzureSQLEdgeContainer withPassword(String password) {
        this.password = password;
        return this;
    }
}
