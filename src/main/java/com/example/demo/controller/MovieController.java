package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
@CrossOrigin		
public class MovieController {
	@Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies")
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @PostMapping("/movies")
    public Mono<Movie> createMoview(@Valid @RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping("/movies/{id}")
    public Mono<ResponseEntity<Movie>> getMovieById(@PathVariable(value = "id") String movieId) {
        return movieRepository.findById(movieId)
                .map(savedMovie -> ResponseEntity.ok(savedMovie))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/movies/{id}")
    public Mono<ResponseEntity<Movie>> updateMovie(@PathVariable(value = "id") String movieId,
                                                   @Valid @RequestBody Movie movie) {
        return movieRepository.findById(movieId)
                .flatMap(existingMovie -> {
                    existingMovie.setName(movie.getName());
                    existingMovie.setYear(movie.getYear());
                    return movieRepository.save(existingMovie);
                })
                .map(updatedMovie -> new ResponseEntity<>(updatedMovie, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/movies/{id}")
    public Mono<ResponseEntity<Void>> deleteMovie(@PathVariable(value = "id") String movieId) {

        return movieRepository.findById(movieId)
                .flatMap(existingMovie ->
                			movieRepository.delete(existingMovie)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Movies are Sent to the client as Server Sent Events
    @CrossOrigin
    @GetMapping(value = "/stream/movies", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Movie> streamAllTweets() {
        return movieRepository.findAll();
    }



}
