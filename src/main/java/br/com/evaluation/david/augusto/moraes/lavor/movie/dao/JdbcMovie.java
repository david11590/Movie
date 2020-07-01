package br.com.evaluation.david.augusto.moraes.lavor.movie.dao;

import br.com.evaluation.david.augusto.moraes.lavor.movie.dto.Movie;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JdbcMovie
{
    final JdbcTemplate jdbcTemplate;

    public JdbcMovie(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean findMoovieNameExistsBoolean(String name) throws NullPointerException
    {
        return jdbcTemplate.queryForObject(
                                       "SELECT COUNT(*) FROM MOVIE WHERE NAME LIKE ?",
                                           new Object[] {name},
                                           Integer.class
                                          ) > 0 ? true : false;
    }

    public int insertMovie(Movie RequestMovie) throws NullPointerException
    {
        return jdbcTemplate.update(
                               "INSERT INTO MOVIE (NAME, RELEASE, CENSORSHIP, DIRECTION) VALUES (?, ?, ?, ?)",
                                   new Object[] {
                                                 RequestMovie.getName(),
                                                 RequestMovie.getRelease(),
                                                 RequestMovie.getCensorship(),
                                                 RequestMovie.getDirection()
                                                }
                                  );
    }

    public void insertMovieCast(Movie RequestMovie) throws NullPointerException
    {
        RequestMovie
        .getCast()
        .forEach(
                 ACTOR -> {
                           jdbcTemplate.update(
                                           "INSERT INTO CAST (ID, ACTOR) VALUES ((SELECT ID FROM MOVIE WHERE NAME like ?), ?)",
                                               new Object[] {
                                                             RequestMovie.getName(),
                                                             ACTOR
                                                            }
                                              );
                          }
                );
    }

    public List<Movie> findMovie(String censorship)
    {
        if(censorship==null)
        {
            return jdbcTemplate.query(
                                  "SELECT * FROM MOVIE",
                                      new BeanPropertyRowMapper(Movie.class)
                                     );
        }
        else
        {
            return jdbcTemplate.query(
                                  "SELECT * FROM MOVIE WHERE censorship=?",
                                      new Object[]{censorship},
                                      new BeanPropertyRowMapper(Movie.class)
                                     );
        }
    }

    public List<String> findMovieCast(Long id)
    {
        return jdbcTemplate.queryForList(
                                     "SELECT ACTOR FROM CAST WHERE id=?",
                                         new Object[]{id},
                                         String.class
                                        );
    }
}