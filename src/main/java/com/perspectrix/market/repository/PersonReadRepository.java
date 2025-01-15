package com.perspectrix.market.repository;

import com.perspectrix.market.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Main repository interface for Person document operations.
 *
 * @author William Stone
 * @since 2025-01-15
 */
@Repository
public interface PersonReadRepository extends MongoRepository<Person, String>, CustomPersonReadRepository{
}
