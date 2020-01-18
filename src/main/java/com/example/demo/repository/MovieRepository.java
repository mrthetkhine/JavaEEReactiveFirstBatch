package com.example.demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Movie;


@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

}
