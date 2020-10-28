package task2;

import java.util.Random;

public class TextGenerator {

    private static final int MIN_PARAGRAPH_AMOUNT = 1;
    private static final int MAX_PARAGRAPH_AMOUNT = 5;
    private static final int MIN_SENTENCE_IN_PARAGRAPH = 1;
    private static final int MAX_SENTENCE_IN_PARAGRAPH = 20;
    private static final int MIN_WORDS_IN_SENTENCE = 1;
    private static final int MAX_WORDS_IN_SENTENCE = 15;

    private final String[] dictionaryWithWords;
    private final String[] dictionaryWithProbabilities;
    private final float probability;

    /**
     * This is the constructor for TextGenerator class
     *
     * @param dictionaryWithWords    the dictionary with words
     * @param dictionaryWithProbabilities the dictionary with words
     *                               for insertion into next sentences
     * @param probability            the probability of any word from
     *                               the dictionaryWithProbabilities
     *                               of being inserted to the next sentence
     */
    public TextGenerator(String[] dictionaryWithWords, String[] dictionaryWithProbabilities, float probability) {
        this.dictionaryWithWords = dictionaryWithWords;
        this.dictionaryWithProbabilities = dictionaryWithProbabilities;
        this.probability = probability;
    }

    /**
     * This method allows to generate text for the file
     *
     * @return the generated text
     */
    public String generateText() {
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        int paragraphAmount = random.nextInt(MAX_PARAGRAPH_AMOUNT) + MIN_PARAGRAPH_AMOUNT;
        for (int paragraphNo = 0; paragraphNo < paragraphAmount; paragraphNo++) {
            text.append(generateParagraph(random));
        }
        return text.toString();
    }

    /**
     * This method allows to generate paragraph for the text
     *
     * @param random the instance of Random
     * @return the generated paragraph
     */
    private String generateParagraph(Random random) {
        StringBuilder paragraph = new StringBuilder();
        int sentenceAmount = random.nextInt(MAX_SENTENCE_IN_PARAGRAPH) + MIN_SENTENCE_IN_PARAGRAPH;
        for (int sentenceNo = 0; sentenceNo < sentenceAmount; sentenceNo++) {
            paragraph.append(generateSentence(random));
            paragraph.append(" ");
        }
        paragraph.append("\n\n");
        return paragraph.toString();
    }

    /**
     * This method allows to generate sentence for the paragraph
     *
     * @param random the instance of Random
     * @return the generated sentence
     */
    private String generateSentence(Random random) {
        StringBuilder sentence = new StringBuilder();
        int wordAmount = random.nextInt(MAX_WORDS_IN_SENTENCE) + MIN_WORDS_IN_SENTENCE;
        boolean includeProbabilityWord = random.nextFloat() <= probability;
        int probabilityWordIndex = 0;
        if (includeProbabilityWord) {
            probabilityWordIndex = random.nextInt(wordAmount);
        }
        for (int wordNo = 0; wordNo < wordAmount; wordNo++) {
            String word;
            if (!includeProbabilityWord) {
                word = generateWord(random, dictionaryWithWords);
            } else {
                if (wordNo != probabilityWordIndex) {
                    word = generateWord(random, dictionaryWithWords);
                } else {
                    word = generateWord(random, dictionaryWithProbabilities);
                }
            }
            if (wordNo == 0) {
                word = makeWordBeginWithCapitalLetter(word);
            }
            sentence.append(word);
            sentence.append(generateCommaAndSpaceAfterWord(random, wordAmount, wordNo));
        }
        sentence.append(generateSentenceEnding(random));
        return sentence.toString();
    }

    /**
     * This method allows to generate optional comma and
     * space after the word, if it is not the last in the
     * sentence
     *
     * @param random     the instance of Random
     * @param wordAmount the number of words in the sentence
     * @param wordNo     the index of a word in the sentence
     * @return           the string after the word
     */
    private String generateCommaAndSpaceAfterWord(Random random, int wordAmount, int wordNo) {
        StringBuilder str = new StringBuilder();
        if (wordNo != wordAmount - 1) {
            boolean commaPresence = random.nextBoolean();
            if (commaPresence) {
                str.append(",");
            }
            str.append(" ");
        }
        return str.toString();
    }

    /**
     * This method allows to generate the last symbol of
     * the sentence ("." || "!" || "?")
     *
     * @param random the instance of Random
     */
    private String generateSentenceEnding(Random random) {
        int ending = random.nextInt(3);
        switch (ending) {
            case 0:
                return ".";
            case 1:
                return "!";
            case 2:
                return "?";
            default:
                System.out.println("Impossible end of sentence");
                return "";
        }
    }

    /**
     * This method allows to make word begin with the capital letter
     *
     * @param word in input word
     * @return the updated word
     */
    private String makeWordBeginWithCapitalLetter(String word) {
        StringBuilder updatedWord = new StringBuilder();
        updatedWord.append(String.valueOf(word.charAt(0)).toUpperCase());
        updatedWord.append(word.substring(1));
        return updatedWord.toString();
    }

    /**
     * This method allows to generate the word
     *
     * @param random the instance of Random
     * @return the generated word
     */
    private String generateWord(Random random, String[] dictionary) {
        int randIndex = random.nextInt(dictionary.length);
        return dictionary[randIndex];
    }
}
