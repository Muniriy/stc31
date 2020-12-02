package task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class MethodInserter {

    private static final Logger log = LogManager.getLogger(MethodInserter.class);
    private static final String BEFORE_METHOD_CODE_FILE_PATH = "lesson10/src/main/resources/task1/beforeMethod.txt";
    private static final String AFTER_METHOD_CODE_FILE_PATH = "lesson10/src/main/resources/task1/afterMethod.txt";
    private static final String JAVA_FILE_PATH = "lesson10/src/main/java/task1/SomeClass.java";
    private static final String JAVA_CLASS_PATH = "lesson10/target/classes";

    /**
     * The entry point of MethodInserter program.
     * It reads the code from the console, inserts it
     * to the doWork() method of SomeClass, compiles
     * code at runtime and calls this method
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        String methodCode = readMethodCode();
        insertCodeAndRun(methodCode);
    }

    /**
     * This method allows to insert the code into the
     * doWork() method of SomeClass
     *
     * @param methodCode the code for insertion
     */
    private static void insertCodeAndRun(String methodCode) {
        File beforeMethodFile = new File(BEFORE_METHOD_CODE_FILE_PATH);
        File afterMethodFile = new File(AFTER_METHOD_CODE_FILE_PATH);
        File javaFile = new File(JAVA_FILE_PATH);
        byte[] code = methodCode.getBytes();
        try (BufferedInputStream bis1 = new BufferedInputStream(new FileInputStream(beforeMethodFile));
             BufferedInputStream bis2 = new BufferedInputStream(new FileInputStream(afterMethodFile));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(javaFile))) {
            byte[] beforeMethodCode = new byte[(int) beforeMethodFile.length()];
            bis1.read(beforeMethodCode, 0, beforeMethodCode.length);

            byte[] afterMethodCode = new byte[(int) afterMethodFile.length()];
            bis2.read(afterMethodCode, 0, afterMethodCode.length);

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            outStream.write(beforeMethodCode);
            outStream.write(code);
            outStream.write(afterMethodCode);
            byte[] classCode = outStream.toByteArray();

            bos.write(classCode, 0, classCode.length);
            bos.close();

            // compile in runtime
            compileClassInRuntime(JAVA_FILE_PATH);

            MyClassLoader myClassLoader = new MyClassLoader();
            final Class<?> someClass = Class.forName("task1.SomeClass", true, myClassLoader);
            Worker worker = (Worker) someClass.getDeclaredConstructor().newInstance();
            worker.doWork();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | InstantiationException | IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * This method allows to compile the Java file
     * and get class
     *
     * @param filePath the path to a Java file to
     *                 compile
     */
    private static void compileClassInRuntime(String filePath)  {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = compiler
                .getStandardFileManager(null, null, null);

        File output = new File(JAVA_CLASS_PATH);
        try {
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(
                    output));

            Iterable<? extends JavaFileObject> compilationUnits = fileManager
                    .getJavaFileObjectsFromStrings(Collections.singletonList(filePath));

            compiler.getTask(null, fileManager, null, null, null,
                    compilationUnits).call();
            fileManager.close();
        } catch (IOException e) {
            log.error(e);
        }
    }

    /**
     * This method allows to read the code text from the
     * console
     *
     * @return the scanned code text
     */
    private static String readMethodCode() {
        StringBuilder code = new StringBuilder();
        String nextLine;
        try (Scanner scanner = new Scanner(System.in).useLocale(Locale.ENGLISH)) {
            do {
                nextLine = scanString(scanner);
                if (!nextLine.isEmpty()) {
                    code.append("\t\t");
                    code.append(nextLine);
                    code.append("\n");
                }
            } while (!nextLine.isEmpty());
        }
        return code.toString();
    }

    /**
     * This method allows to read valid input String values,
     * which contains only symbols applicable for Java code.
     * Only English alphabet letters are applicable
     *
     * @return The obtained value received via scanning
     */
    private static String scanString(Scanner scanner) {
        String value;
        boolean validityFlag = false;
        log.info("Type the next code line: ");
        do {
            value = scanner.nextLine();
            if (!value.contains("ж")) {
                validityFlag = true;
            } else {
                log.warn("\nUse strings containing only English alphabet symbols, numbers," +
                        "\nand other symbols, which can be used in code. Try one more time: ");
            }
        } while (!validityFlag);
        return value;
    }
}
