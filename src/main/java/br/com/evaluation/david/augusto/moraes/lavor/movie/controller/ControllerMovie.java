package br.com.evaluation.david.augusto.moraes.lavor.movie.controller;

import br.com.evaluation.david.augusto.moraes.lavor.movie.dto.Error;
import br.com.evaluation.david.augusto.moraes.lavor.movie.dto.Movie;
import br.com.evaluation.david.augusto.moraes.lavor.movie.util.UtilMovie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.InvalidParameterException;

@RestController
@RequestMapping("/movie")
public class ControllerMovie
{
    UtilMovie utilMovie;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ControllerMovie(UtilMovie utilMovie)
    {
        this.utilMovie = utilMovie;
    }

    @PostMapping
    public ResponseEntity<?> postMovie(@RequestBody Movie requestMovie)
    {
        try
        {
            utilMovie.utilMovieProcess(requestMovie);
        }
        catch (InvalidParameterException e)
        {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(
                                        new Error(e.getMessage()),
                                        HttpStatus.BAD_REQUEST
                                       );
        }
        catch (DuplicateKeyException e)
        {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(
                                        new Error(e.getMessage()),
                                        HttpStatus.CONFLICT
                                       );
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(
                                        new Error("Please contact a Support team to report this issue."),
                                        HttpStatus.INTERNAL_SERVER_ERROR
                                       );
        }
        logger.info("Success "+requestMovie.toString());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/{censorship}")
    public ResponseEntity<?> getMovie(@PathVariable(value ="censorship") String censorship)
    {
        try
        {
            return new ResponseEntity<>(
                                        utilMovie.utilMovieProcessGet(censorship),
                                        HttpStatus.ACCEPTED
                                       );
        }
        catch (InvalidParameterException e)
        {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(
                                        new Error(e.getMessage()),
                                        HttpStatus.BAD_REQUEST
                                       );
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(
                                        new Error("Please contact a Support team to report this issue."),
                                        HttpStatus.INTERNAL_SERVER_ERROR
                                       );
        }
    }

    @GetMapping
    public ResponseEntity<?> getMovieAll()
    {
        try
        {
            return new ResponseEntity<>(
                                        utilMovie.utilMovieProcessGet(null),
                                        HttpStatus.ACCEPTED
                                       );
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(
                                        new Error("Please contact a Support team to report this issue."),
                                        HttpStatus.INTERNAL_SERVER_ERROR
                                       );
        }
    }
}
