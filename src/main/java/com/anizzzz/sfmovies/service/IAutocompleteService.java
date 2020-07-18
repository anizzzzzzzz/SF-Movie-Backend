package com.anizzzz.sfmovies.service;

import java.util.Set;

public interface IAutocompleteService {
    /**
     * Initializing the trie structure data.
     * */
    void init();

    /**
     * @param text : String
     *             - Prefix based on which the autocompletion is carried out.
     *             - In order to make the autocompletion case insensitive, the user specified prefix is changed into
     *                  lower case, upper case and title case based on which it searches the tree.
     *
     * @return : List<String>
     *     - List of words
     * */
    Set<String> autoComplete(String text);
}
