// Elaine Hsu
// May 27 2022
// CS 145
// Assignment 3: Anagrams
// This program finds all anagram phrases that matches a given word or phrase by the user.

import java.util.*; // imports the Scanner object

public class Anagrams { // Creates the anagram object
    private Set<String> dictionaryCopy; // Creates a copy of the dictionary and turns it into a set of strings  
    public Set<String> returnWords; // Creates a set of words that would be returned from the getWords method

    // Constructor for the anagram object, initializes the dictionaryCopy set and returnWords set, throws an exception if there is nothing in the set
    public Anagrams(Set<String> dictionary) { 
        if(dictionary == null) {
            throw new IllegalArgumentException();
        }
        this.dictionaryCopy = dictionary;
        returnWords = new HashSet<String>();
    }
    
    // Splits the user inputed String into char using the letter inventory and adds into the returnWords set then returns it.
    public Set<String> getWords(String phrase) {
        if(phrase == null) {
            throw new IllegalArgumentException("Input cannot be null");
        } 
            LetterInventory letter = new LetterInventory(phrase);
            Set<String> returnWords = new TreeSet<>();
            for(String word:dictionaryCopy) {
                if(letter.contains(word)) {
                    returnWords.add(word);
                }
            }
        
        return returnWords;
        
    }

    // Prints the array containing the words when the user clicks ENTER key since there is no max.
    public void print(String phrase) {
        if(phrase == null) {
            throw new IllegalArgumentException("No output");
        } else {
            LetterInventory letter = new LetterInventory(phrase);
            ArrayList<String> list = new ArrayList<String>(); 
            returnWords = getWords(phrase);
            print(letter, list, 0);     
        }
    }

    // Private method for the above print method to do recursion
    private void print(LetterInventory letter, ArrayList<String> list, int max) {
        if(letter.isEmpty() && max == 0) {
            System.out.println(list);
        } else if (letter.isEmpty() && list.size() == max){
            System.out.println(list);
        }
         else {
            for(String wordCheck : returnWords) {
                if(letter.contains(wordCheck)) {
                    LetterInventory tempCopy = new LetterInventory("");
                    ArrayList<String> temporary = new ArrayList<>(list);
                    tempCopy.add(letter);
                    temporary.add(wordCheck);
                    tempCopy.subtract(wordCheck);
                    print(tempCopy, temporary, max);
                }
            }
        }
    }

    // Print method when the user types in the input.
    public void print(String phrase, int max) {
        if( max < 0 ) {
            throw new IllegalArgumentException("max cannot be negative");
        } else {
            LetterInventory letter = new LetterInventory(phrase);
            ArrayList<String> list = new ArrayList<String>(); 
            returnWords = getWords(phrase);
            print(letter, list, max);
        }
    }

}
