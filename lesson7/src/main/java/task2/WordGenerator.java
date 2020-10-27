package task2;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class WordGenerator {

    private static final int MIN_DICTIONARY_SIZE = 1;
    private static final int MAX_DICTIONARY_SIZE = 1000;
    private static final int MIN_WORD_LENGTH = 1;
    private static final int MAX_WORD_LENGTH = 15;
    private static final int ENGLISH_ALPHABET_SIZE = 26;

    /**
     * This method allows to generate dictionary of words
     *
     * @return the dictionary with all generated words
     */
    public String[] generateDictionary() {
        Set<String> wordSet = new TreeSet<>();
        Random random = new Random();
        int dictionarySize = random.nextInt(MAX_DICTIONARY_SIZE) + MIN_DICTIONARY_SIZE;
        while (dictionarySize != wordSet.size()) {
            int wordLength = random.nextInt(MAX_WORD_LENGTH) + MIN_WORD_LENGTH;
            String generatedWord = generateWord(wordLength, random);
            wordSet.add(generatedWord);
        }
        return wordSet.toArray(new String[0]);
    }

    /**
     * This method allows to generate word containing
     * only English alphabet letters of given length
     *
     * @param wordLength the length of the word
     * @return the generated word in lowercase
     */
    private String generateWord(int wordLength, Random random) {
        StringBuilder word = new StringBuilder();
        char firstAlphabetLetter = 'a';
        for (int letterNo = 0; letterNo < wordLength; letterNo++) {
            word.append((char) ((int) firstAlphabetLetter + random.nextInt(ENGLISH_ALPHABET_SIZE)));
        }
        return word.toString();
    }
}
