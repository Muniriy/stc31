package task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MainCalculator {

    private static final Logger log = LogManager.getLogger(MainCalculator.class);
    private static final int NUMBER_AMOUNT = 10;
    private static final int UPPER_BOUNDARY = 200;
    private static final double DIVISOR = 2;
    private static final Integer INTEGER_TO_DELETE = 77;

    /**
     * The entry point of MainCalculator program.
     * It creates array of unique Number elements and
     * allows to make operations on them by calling
     * several methods: summation(), deleteInteger(),
     * splitter(), toString(), equals(), hashCode()
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        // this method runs task1
        runPreviousCode();

        // this method shows exception while putting Object to MathBox
        runNewCode();
    }

    /**
     * This method throws an Exception when not Number
     * element is put into MathBox
     */
    private static void runNewCode() {
        List<Object> objects = new ArrayList<>();
        objects.add(-7.4);
        objects.add("invalid type");
        objects.add(43);

        try {
            MathBox mathBox3 = new MathBox((ArrayList) objects);
            log.info(mathBox3.getNumbers());
        } catch (InputMismatchException e) {
            log.error("All array members have to have type Number, not {}", e.getMessage());
        }
    }

    /**
     * The method creates array of unique Number elements
     * and allows to make operations on them by calling
     * several methods: summation(), deleteInteger(),
     * splitter(), toString(), equals(), hashCode()
     */
    private static void runPreviousCode() {
        List<Number> numbers = generateNumbers(NUMBER_AMOUNT);
        try {
            MathBox mathBox1 = new MathBox(numbers);
            log.info("Generated numbers: {}", mathBox1.getNumbers());

            MathBox mathBox2 = new MathBox(numbers);
            log.info("Generated numbers: {}", mathBox2.getNumbers());

            // demonstration that identical arrays are equal
            String equalityMsg = mathBox1.equals(mathBox2)
                    ? "MathBoxes mathBox1 and mathBox2 are equal"
                    : "MathBoxes mathBox1 and mathBox2 are not equal";
            log.info(equalityMsg);

            // demonstration that identical arrays have equal hash codes
            String hashCodeEqualityMsg = mathBox1.equals(mathBox2)
                    ? "MathBoxes mathBox1 and mathBox2 have equal hash codes"
                    : "MathBoxes mathBox1 and mathBox2 have different hash codes";
            log.info(hashCodeEqualityMsg);


            // the sum of number array elements
            log.info("Sum of all elements is {}", mathBox1.summation());

            // deletion of an Integer value
            if (mathBox1.deleteInteger(INTEGER_TO_DELETE)) {
                log.info("Integer {} was deleted", INTEGER_TO_DELETE);
                log.info("Updated numbers: {}", mathBox1.getNumbers());
            } else {
                log.warn("Integer {} was not found in the array", INTEGER_TO_DELETE);
            }

            // division of each element by divisor
            mathBox1.splitter(DIVISOR);
            log.info("All elements were divided by {}", DIVISOR);

            // call of toString()
            log.info(mathBox1);
        } catch (InputMismatchException e) {
            log.error("All array members have to have type Number, not {}", e.getMessage());
        }
    }


    /**
     * This method allows to generate random array of numbers
     * with different types which are inherited from Number class
     *
     * @param numberAmount the size of the array
     * @return the generated array of numbers
     */
    private static ArrayList<Number> generateNumbers(int numberAmount) {
        ArrayList<Number> numbers = new ArrayList<>();
        Random random = new Random(3);
        StringBuilder stringBuilder = new StringBuilder("Generated array types [ ");
        Set<Number> set = new HashSet<>();
        for (int i = 0; i < numberAmount; i++) {
            NumberTypes chosenType;
            Number number;
            do {
                chosenType = NumberTypes.values()[random.nextInt(NumberTypes.values().length)];
                switch (chosenType) {
                    case BYTE:
                        number = (byte) random.nextInt(UPPER_BOUNDARY);
                        break;
                    case LONG:
                        number = ThreadLocalRandom.current().nextLong(UPPER_BOUNDARY);
                        break;
                    case FLOAT:
                        number = random.nextFloat() * UPPER_BOUNDARY;
                        break;
                    case DOUBLE:
                        number = random.nextDouble() * UPPER_BOUNDARY;
                        break;
                    case INTEGER:
                        number = random.nextInt(UPPER_BOUNDARY);
                        break;
                    case SHORT:
                        number = (short) random.nextInt(UPPER_BOUNDARY);
                        break;
                    case BIG_DECIMAL:
                        number = BigDecimal.valueOf(random.nextDouble())
                                .multiply(BigDecimal.valueOf(UPPER_BOUNDARY));
                        break;
                    case BIG_INTEGER:
                        number = BigInteger.valueOf(random.nextInt(UPPER_BOUNDARY));
                        break;
                    default:
                        log.error("Wrong input type");
                        number = null;
                }
                set.add(number);
            }
            while (set.size() < i + 1);
            numbers.add(number);
            stringBuilder.append(chosenType);
            stringBuilder.append(" ");
        }
        stringBuilder.append("]");
        log.info(stringBuilder);
        return numbers;
    }
}
