package task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.RandomAccessFile;

public class TextFileGenerator {

    private static final Logger log = LogManager.getLogger(TextFileGenerator.class);
    private static final String GENERATED_FILES_PATH = "lesson7/src/main/resources/task2/files";
    private static final int FILE_AMOUNT = 10;
    private static final float PROBABILITY = 0.1F;
    private static final int MAX_FILE_SIZE = 2;


    /**
     * This is the entry point of the TextFileGenerator program.
     * It allows to generate text files based on given patterns
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        WordGenerator wordGenerator = new WordGenerator();
        String[] wordsWithProbabilities = wordGenerator.generateDictionary();
        getFiles(GENERATED_FILES_PATH, FILE_AMOUNT, MAX_FILE_SIZE, wordsWithProbabilities, PROBABILITY);
    }

    /**
     * This method allows to generate text files
     *
     * @param path        the path to the directory with the
     *                    generated files
     * @param filesAmount the number of files
     * @param size        the size of each file
     * @param words       the dictionary
     * @param probability the probability of occurrence of
     *                    words from dictionary in the next
     *                    sentence
     */
    private static void getFiles(String path, int filesAmount, int size, String[] words, float probability) {
        WordGenerator wordGenerator = new WordGenerator();
        String[] dictionaryWithWords = wordGenerator.generateDictionary();
        for (int fileNo = 0; fileNo < filesAmount; fileNo++) {
            TextGenerator textGenerator = new TextGenerator(dictionaryWithWords, words, probability);
            String text = textGenerator.generateText();
            log.info("File {}: \n{}", fileNo + 1, text);
            String fileName = path + "/" + (fileNo + 1) + ".txt";
            try (RandomAccessFile f = new RandomAccessFile(fileName, "rw")) {
                f.writeBytes(text);
                f.setLength(size);
            } catch (IOException e) {
                log.error(e);
            }
        }
    }
}
