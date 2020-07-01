package br.com.evaluation.david.augusto.moraes.lavor.movie.dto;

public class Error
{
    String errorMessage;

    public Error(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
