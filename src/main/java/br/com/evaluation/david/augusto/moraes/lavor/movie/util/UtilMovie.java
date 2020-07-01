package br.com.evaluation.david.augusto.moraes.lavor.movie.util;

import br.com.evaluation.david.augusto.moraes.lavor.movie.dao.JdbcMovie;
import br.com.evaluation.david.augusto.moraes.lavor.movie.dto.Movie;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import java.security.InvalidParameterException;
import java.util.List;

@Component
public class UtilMovie
{
    ValidateMovie validateMovie;
    final JdbcMovie jdbcMovie;
    List<Movie> listMovie=null;

    public UtilMovie(ValidateMovie validateMovie, JdbcMovie jdbcMovie)
    {
        this.validateMovie = validateMovie;
        this.jdbcMovie = jdbcMovie;
    }

    public void utilMovieProcess(Movie RequestMovie)
            throws DuplicateKeyException, InvalidParameterException, NullPointerException
    {
        validateMovie.validateRequestMovie(RequestMovie);
        jdbcMovie.insertMovie(RequestMovie);
        jdbcMovie.insertMovieCast(RequestMovie);
    }

    public List<Movie> utilMovieProcessGet(String censorship)
            throws InvalidParameterException, NullPointerException
    {
        if(censorship==null)
        {
            listMovie = jdbcMovie.findMovie(null);
            listMovie
            .forEach(
                     MOVIE -> {
                               MOVIE.setCast(jdbcMovie.findMovieCast(MOVIE.getId()));
                              }
                    );
            return listMovie;
        }
        validateMovie.validateGetMovie(censorship);
        listMovie = jdbcMovie.findMovie(censorship);
        listMovie
        .forEach(
                 MOVIE -> {
                           MOVIE.setCast(jdbcMovie.findMovieCast(MOVIE.getId()));
                          }
                );
        return listMovie;
    }
}