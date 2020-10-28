package task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WordSorter {

    private static final Logger log = LogManager.getLogger(WordSorter.class);
    private static final String INPUT_FILE_PATH = "lesson7/src/main/resources/task1/files/file.txt";
    private static final String[] NON_DICTIONARY_SYMBOLS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "<", ">", "=", "+", "-", "|", "±", "@", "%", "*", "/", "\""};
    private static final String[] REMOVABLE_SYMBOLS = {".", ",", ":", ";", "(", ")", "{", "}", "[", "]"};

    /**
     * This is the entry point of the WordSorter program.
     * It allows to read words from the file and prepare
     * the dictionary with all words containing in the
     * file
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        Set<String> wordSet = getDictionary(INPUT_FILE_PATH);
        displayDictionary(wordSet);
    }

    /**
     * This method allows to print all words contained in
     * the file
     *
     * @param wordSet the set of words in the dictionary
     */
    private static void displayDictionary(Set<String> wordSet) {
        if (!wordSet.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("Dictionary contains:");
            Iterator<String> iterator = wordSet.iterator();
            int wordNo = 1;
            while (iterator.hasNext()) {
                stringBuilder.append("\n");
                stringBuilder.append(wordNo++);
                stringBuilder.append(". ");
                stringBuilder.append(iterator.next());
                wordNo++;
            }
            log.info(stringBuilder);
        } else {
            log.warn("File is empty");
        }
    }

    /**
     * This method allows to read the file and form
     * the dictionary with all words
     *
     * @param inputFilePath the path to the input file
     * @return the dictionary will all file words
     */
    private static Set<String> getDictionary(String inputFilePath) {
        Set<String> wordSet = new TreeSet<>();
        File inputFile = new File(inputFilePath);
        if (inputFile.exists()) {
            if (inputFile.canRead()) {
                try (Scanner scanner = new Scanner(inputFile)) {
                    while (scanner.hasNext()) {
                        String word = scan(scanner);
                        if (word != null) {
                            wordSet.add(word);
                        }
                    }
                } catch (FileNotFoundException e) {
                    log.error("File \"{}\" cannot be found", inputFile, new FileNotFoundException());
                }
            } else {
                log.error("File \"{}\" cannot be read", inputFile, new SecurityException());
            }
        } else {
            log.error("File \"{}\" cannot be found", inputFile, new FileNotFoundException());
        }
        return wordSet;
    }

    /**
     * This method allows to scan the String
     *
     * @param scanner the object of Scanner class needed for
     *                scanning the number
     * @return the obtained value received via scanning
     */
    private static String scan(Scanner scanner) {
        String s = null;
        try {
            s = scanner.next();
            s = s.toLowerCase();

            for (String nonDictionarySymbol : NON_DICTIONARY_SYMBOLS) {
                if (s.contains(nonDictionarySymbol)) {
                    s = null;
                    return s;
                }
            }

            for (String removableSymbol : REMOVABLE_SYMBOLS) {
                if (s != null) {
                    if (s.contains(removableSymbol) && !s.contains("т.д.") && !s.contains("т.e.")) {
                        s = s.replace(removableSymbol, "");
                    }
                    if (s.equals("")) {
                        s = null;
                    }
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return s;
    }
}
