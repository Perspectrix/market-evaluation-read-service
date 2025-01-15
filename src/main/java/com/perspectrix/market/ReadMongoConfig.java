package com.perspectrix.market;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Objects;

/**
 * MongoDB configuration for read operations.
 * Configures repository scanning and read-only MongoDB template.
 *
 * @author William Stone
 * @since 2025-01-15
 */
@Configuration
@EnableMongoRepositories(
        basePackages = "com.perspectrix.market.repository",
        mongoTemplateRef = "readMongoTemplate"
)
public class ReadMongoConfig {
    @Value("${spring.data.mongodb.uri}")
    private String connectionString;

    /**
     * Creates primary MongoDB template for read operations.
     *
     * @return Configured MongoTemplate instance
     * @throws Exception if MongoDB connection fails
     */
    @Primary
    @Bean(name = "readMongoTemplate")
    public MongoTemplate readMongoTemplate() throws Exception {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();

        return new MongoTemplate(MongoClients.create(settings),
                Objects.requireNonNull(new ConnectionString(connectionString).getDatabase()));
    }
}
