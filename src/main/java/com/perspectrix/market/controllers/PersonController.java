package com.perspectrix.market.controllers;

import com.perspectrix.market.domain.Person;
import com.perspectrix.market.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Controller for retrieving Person objects from the read database
 *
 * @author William Stone
 * @since 2025-01-15
 */

@RestController
@RequestMapping("/api/read")
public class PersonController {
    @Autowired
    private PersonService personService;

    /**
     * Finds all people located within a given polygon boundary.
     *
     * @param polygonCoordinates List of coordinate pairs [longitude, latitude] defining the polygon vertices
     * @return ResponseEntity containing list of Person objects within the polygon
     * @throws IllegalArgumentException if polygon has fewer than 3 points or contains invalid coordinates
     * @see PersonService#findPeopleInPolygon
     */
    @PostMapping("/find-in-polygon")
    public ResponseEntity<List<Person>> findPeopleInPolygon(@RequestBody List<List<Double>> polygonCoordinates) {
        List<Person> people = personService.findPeopleInPolygon(polygonCoordinates);
        return ResponseEntity.ok(people);
    }
}
