package sjsu.cs157a;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateOutput;
import org.flywaydb.core.api.output.MigrateResult;
import sjsu.cs157a.config.DatabaseConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application implements ServletContextListener {

    //tasks to run when the application starts
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Hi thje app just started");

        try {
            Properties dbProperties = initializeProperties();
            String dbURL = dbProperties.getProperty("dbURL");
            String dbName = dbProperties.getProperty("dbName");
            String miscSetting = dbProperties.getProperty("miscSetting");
            String dbUsername = dbProperties.getProperty("dbUsername");
            String dbPassword = dbProperties.getProperty("dbPassword");

            //do migrations with flyway
            Flyway flyway = Flyway.configure()
                    .dataSource(dbURL + dbName + miscSetting, dbUsername, dbPassword)
                    .locations("sjsu/cs157a/migrations")
                    .baselineOnMigrate(true)
                    .load();

            MigrateResult migrateResult = flyway.migrate();
//
            System.out.println("Migrations executed: " + migrateResult.migrationsExecuted);
            for(MigrateOutput migration : migrateResult.migrations)
                System.out.println(migration.filepath);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    Properties initializeProperties() throws IOException {
        Properties dbProperties = new Properties();
        InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties");

        dbProperties.load(inputStream);

        return dbProperties;
    }
}
