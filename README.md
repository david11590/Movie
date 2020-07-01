# API MOVIE

Iniciando a aplicação: A aplicação é baseada em Spring Boot, pode ser iniciada atravez do jar com o comendo "java -jar nome_do_jar",
 ou executando a  classe ApplicationMovie. A aplicação esta com Dockerfile, caso queiram rodar ela no Docker.

Gerenciador de dependencias:Gradle
JDK utilizado no Desnvolvimento: jdk-8u251-windows-x64
Nome do Jar Da aplicação: movie-0.0.1-SNAPSHOT.jar
Banco de dados: H2
IDE:Intellij
Linguagem da Implementação:Ingles

Exemplos de Request(pode usar curl, postman, SoapUI ou aplicação de sua preferencia)
____________________________________________________________________________________
Cadastrar Filme

URL Post: http://localhost:8080/movie

Json Post Exemplo: 
{
	"nome":"Corrida Mortal", 
	"dataDeLancamento":"22 de agosto de 2008", 
	"nivelDeCensura":"CENSURADO", 
	"direcao":"Paul W. S. Anderson",
	"Elenco":["Jason Statham","Frederick Koehler", "Joan Allen", "Natalie Martinez"]
}
____________________________________________________________________________________
Buscar todos os filmes cadastrados

URL Get: http://localhost:8080/movie
____________________________________________________________________________________
Buscar os filmes com sensura igual a "SEM_CENSURA"(Só acrescentar uma barra seguido pela Censura)

URL Get: http://localhost:8080/movie/SEM_CENSURA
____________________________________________________________________________________
Buscar os filmes com sensura igual a "CENSURADO"(Só acrescentar uma barra seguido pela Censura)

URL Get: http://localhost:8080/movie/CENSURADO
____________________________________________________________________________________

