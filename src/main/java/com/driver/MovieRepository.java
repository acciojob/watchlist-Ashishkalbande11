package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

import javax.management.DescriptorRead;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        // your code here
        if(!movieMap.containsKey(movie)){
            movieMap.put(movie.getName(), movie);
        }

    }

    public void saveDirector(Director director){
        // your code here
        if(!directorMap.containsKey(director)){
            directorMap.put(director.getName(), director);
        }
    }

    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            if(!directorMovieMapping.containsKey(director)){
                List<String> movies = new ArrayList<>();
                movies.add(movie);
                directorMovieMapping.put(director, movies);
            }else{
                List<String> movies = directorMovieMapping.get(director);
                movies.add(movie);
            }
        }
    }

    public Movie findMovie(String movie){
        // your code here
        if (movieMap.containsKey(movie)) {

            return movieMap.get(movie);
        }
        return null;
    }

    public Director findDirector(String director){
        // your code here
        if(directorMap.containsKey(director)){
            return directorMap.get(director);
        }
        return null;
    }

    public List<String> findMoviesFromDirector(String director){
        // your code here
        if(directorMovieMapping.containsKey(director)){
            List<String> movies = directorMovieMapping.get(director);
            return movies;
        }
        return null;

    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        // your code here
        if(directorMovieMapping.containsKey(director)){
            List<String>movies = directorMovieMapping.get(director);
            for(String movieName : movies){
                if(movieMap.containsKey(movieName)){
                    movieMap.remove(movieName);
                }
            }
            directorMap.remove(director);
            directorMovieMapping.remove(director);
        }else if(directorMap.containsKey(director)){
            directorMap.remove(director);
        }
    }

    public void deleteAllDirector(){
        // your code here
        for(String director : directorMap.keySet()){
            if(directorMovieMapping.containsKey(director)){
                List<String> movies = directorMovieMapping.get(director);
                for(String movieName : movies){
                    if(movieMap.containsKey(movieName)){
                        movieMap.remove(movieName);
                    }
                }
                directorMap.remove(director);
                directorMovieMapping.remove(director);
            }
        }
    }
}