package com.perspectrix.market.services;

import com.perspectrix.market.domain.Person;
import com.perspectrix.market.repository.PersonReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages reading logic when reading the person read database
 *
 * @author William Stone
 * @since 2025-01-15
 */
@Service
public class PersonService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PersonReadRepository personRepository;

    /**
     * Retrieves a list of people from the read database that are within the polygon created by the coordinates.
     *
     * @param polygonCoordinates
     * @return a list of Person objects
     */
    public List<Person> findPeopleInPolygon(List<List<Double>> polygonCoordinates) {
        // Convert the polygon coordinates into GeoJsonPoint
        List<Point> points = new ArrayList<>();
        for (List<Double> coord : polygonCoordinates) {
            // Create a new Point from each coordinate and add it to the points list
            points.add(new Point(coord.get(0), coord.get(1)));
        }
        points.add(new Point(polygonCoordinates.get(0).get(0), polygonCoordinates.get(0).get(1)));
        GeoJsonPolygon polygon = new GeoJsonPolygon(List.of(points.toArray(new Point[0])));
        // Query for people within the polygon using
        Query query = new Query();
        query.addCriteria(Criteria.where("location").within(polygon));
        return mongoTemplate.find(query, Person.class);
    }

    /**
     * Returns a comprehensive list of Person objects in the read database
     *
     * @return people in the database
     */
    public List<Person> getPeople() {
        List<Person> people= null;
        try{
            people= mongoTemplate.findAll(Person.class);
            if(people.size()>0){
                return people;
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return people;
    }
}
