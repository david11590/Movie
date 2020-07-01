package br.com.evaluation.david.augusto.moraes.lavor.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Movie
{
    Long id;
    @JsonProperty("nome")
    String name;
    @JsonProperty("dataDeLancamento")
    String release;
    @JsonProperty("nivelDeCensura")
    String censorship;
    @JsonProperty("direcao")
    String direction;
    @JsonProperty("Elenco")
    List<String> cast;

    protected Movie(){}

    @Override
    public String toString()
    {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release='" + release + '\'' +
                ", censorship='" + censorship + '\'' +
                ", direction='" + direction + '\'' +
                ", cast=" + cast +
                '}';
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRelease()
    {
        return release;
    }

    public void setRelease(String release)
    {
        this.release = release;
    }

    public String getCensorship()
    {
        return censorship;
    }

    public void setCensorship(String censorship)
    {
        this.censorship = censorship;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    public List<String> getCast()
    {
        return cast;
    }

    public void setCast(List<String> cast)
    {
        this.cast = cast;
    }
}