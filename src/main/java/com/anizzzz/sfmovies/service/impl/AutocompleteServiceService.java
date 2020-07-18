package com.anizzzz.sfmovies.service.impl;

import com.anizzzz.sfmovies.dto.TrieNode;
import com.anizzzz.sfmovies.service.IAutocompleteService;
import com.anizzzz.sfmovies.service.IMovieDataService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AutocompleteServiceService implements IAutocompleteService {
    private TrieNode root;
    private final IMovieDataService movieService;

    public AutocompleteServiceService(IMovieDataService movieService){
        this.root = new TrieNode("");
        this.movieService = movieService;
    }

    @Override
    public void init() {
        movieService.findAll().forEach(movie -> {
            String title = movie.getTitle();
            insert(title.toLowerCase(), title);
            String[] words = title.split("\\s");
            for(String word : words)
                insert(word, title);
        });
    }

    protected void insert(String word, String line){
        TrieNode crawl = root;
        String[] charList = word.toLowerCase().split("");
        for(String aChar: charList){
            Map<String, TrieNode> child = crawl.getChildren();
            if(child.containsKey(aChar)){
                crawl = child.get(aChar);
                crawl.getWords().add(line);
            }
            else{
                TrieNode temp = new TrieNode(aChar);
                temp.getWords().add(line);
                child.put(aChar, temp);
                crawl = temp;
            }
        }
        crawl.setIsEnd(true);
    }

    @Override
    public Set<String> autoComplete(String text) {
        Set<String> words = new HashSet<>();
        String[] charList = text.toLowerCase().split("");
        TrieNode crawl = root;
        boolean contains = true;

        for(String aChar : charList){
            Map<String, TrieNode> child = crawl.getChildren();
            if(child.containsKey(aChar)){
                crawl = child.get(aChar);
            }
            else{
                contains=false;
                break;
            }
        }
        if(contains)
            words.addAll(crawl.getWords());
        crawl = null;
        return words;
    }
}
