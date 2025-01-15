package com.perspectrix.market.repository;

import com.perspectrix.market.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Implementation of custom Person repository queries using MongoTemplate.
 *
 * @author William Stone
 * @since 2025-01-15
 */
public class PersonReadRepositoryImpl implements CustomPersonReadRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> findDistinctCities() {
        Query query = new Query();
        query.fields().include("city");
        return mongoTemplate.findDistinct(query, "city", Person.class, String.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Person> findByCity(String city){
        // Return all persons with the matching city
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is(city));
        return mongoTemplate.find(query, Person.class);
    }
}
