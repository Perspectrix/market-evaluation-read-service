package com.perspectrix.market.repository;

import com.perspectrix.market.domain.Person;

import java.util.List;

/**
 * Custom repository interface for Person-specific MongoDB queries.
 *
 * @author William Stone
 * @since 2025-01-15
 */
public interface CustomPersonReadRepository {

    /**
     * Retrieves list of unique cities from all Person documents.
     * @return List of distinct city names
     */
    List<String> findDistinctCities();

    /**
     * Finds all persons living in specified city.
     * @param city City name to search for
     * @return List of Person objects matching the city
     */
    List<Person> findByCity(String city);
}
