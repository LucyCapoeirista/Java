package adventofcode._2023._01;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        int finalSum = 0;

        //Read file of inputs
        List<String> lines = Collections.emptyList();
        lines = Files.readAllLines(Paths.get("C:\\Users\\Lucy\\IdeaProjects\\AdventOfCode2023\\src\\adventOfCode\\_2023\\_01\\dec01.txt"), StandardCharsets.UTF_8);

        //Work through the value for each line of input
        for (int currentLine = 0; currentLine < lines.size(); currentLine++) {

            String workingValue = lines.get(currentLine);

            System.out.println("workingValue [" + workingValue + "]");

            int startDigit = 0, finishDigit = 0;
            int finalDigit = 0;
            int min = 99, max = 0;

            int[] firstDigits = new int[18];

            firstDigits[0] = workingValue.indexOf("1");
            firstDigits[1] = workingValue.indexOf("2");
            firstDigits[2] = workingValue.indexOf("3");
            firstDigits[3] = workingValue.indexOf("4");
            firstDigits[4] = workingValue.indexOf("5");
            firstDigits[5] = workingValue.indexOf("6");
            firstDigits[6] = workingValue.indexOf("7");
            firstDigits[7] = workingValue.indexOf("8");
            firstDigits[8] = workingValue.indexOf("9");
            firstDigits[9] = workingValue.indexOf("one");
            firstDigits[10] = workingValue.indexOf("two");
            firstDigits[11] = workingValue.indexOf("three");
            firstDigits[12] = workingValue.indexOf("four");
            firstDigits[13] = workingValue.indexOf("five");
            firstDigits[14] = workingValue.indexOf("six");
            firstDigits[15] = workingValue.indexOf("seven");
            firstDigits[16] = workingValue.indexOf("eight");
            firstDigits[17] = workingValue.indexOf("nine");

            //for (int currentValue = 0; currentValue < 9; currentValue++) { // Part 1
            for (int currentValue = 0; currentValue < 18; currentValue++) {
                if (firstDigits[currentValue] > -1 && min > firstDigits[currentValue]) {
                    min = firstDigits[currentValue];
                    switch (currentValue) {
                        case 0:
                            startDigit = 1;
                            break;
                        case 1:
                            startDigit = 2;
                            break;
                        case 2:
                            startDigit = 3;
                            break;
                        case 3:
                            startDigit = 4;
                            break;
                        case 4:
                            startDigit = 5;
                            break;
                        case 5:
                            startDigit = 6;
                            break;
                        case 6:
                            startDigit = 7;
                            break;
                        case 7:
                            startDigit = 8;
                            break;
                        case 8:
                            startDigit = 9;
                            break;
                        case 9:
                            startDigit = 1;
                            break;
                        case 10:
                            startDigit = 2;
                            break;
                        case 11:
                            startDigit = 3;
                            break;
                        case 12:
                            startDigit = 4;
                            break;
                        case 13:
                            startDigit = 5;
                            break;
                        case 14:
                            startDigit = 6;
                            break;
                        case 15:
                            startDigit = 7;
                            break;
                        case 16:
                            startDigit = 8;
                            break;
                        case 17:
                            startDigit = 9;
                            break;
                    }
                }
            }

            System.out.println("startDigit [" + startDigit + "]");

            int[] lastDigits = new int[18];

            lastDigits[0] = workingValue.lastIndexOf("1");
            lastDigits[1] = workingValue.lastIndexOf("2");
            lastDigits[2] = workingValue.lastIndexOf("3");
            lastDigits[3] = workingValue.lastIndexOf("4");
            lastDigits[4] = workingValue.lastIndexOf("5");
            lastDigits[5] = workingValue.lastIndexOf("6");
            lastDigits[6] = workingValue.lastIndexOf("7");
            lastDigits[7] = workingValue.lastIndexOf("8");
            lastDigits[8] = workingValue.lastIndexOf("9");
            lastDigits[9] = workingValue.lastIndexOf("one");
            lastDigits[10] = workingValue.lastIndexOf("two");
            lastDigits[11] = workingValue.lastIndexOf("three");
            lastDigits[12] = workingValue.lastIndexOf("four");
            lastDigits[13] = workingValue.lastIndexOf("five");
            lastDigits[14] = workingValue.lastIndexOf("six");
            lastDigits[15] = workingValue.lastIndexOf("seven");
            lastDigits[16] = workingValue.lastIndexOf("eight");
            lastDigits[17] = workingValue.lastIndexOf("nine");

            //for (int currentValue = 0; currentValue < 9; currentValue++) { // Part 1
            for (int currentValue = 0; currentValue < 18; currentValue++) {
                if (lastDigits[currentValue] > -1 && max <= lastDigits[currentValue]) {
                    max = lastDigits[currentValue];
                    switch (currentValue) {
                        case 0:
                            finishDigit = 1;
                            break;
                        case 1:
                            finishDigit = 2;
                            break;
                        case 2:
                            finishDigit = 3;
                            break;
                        case 3:
                            finishDigit = 4;
                            break;
                        case 4:
                            finishDigit = 5;
                            break;
                        case 5:
                            finishDigit = 6;
                            break;
                        case 6:
                            finishDigit = 7;
                            break;
                        case 7:
                            finishDigit = 8;
                            break;
                        case 8:
                            finishDigit = 9;
                            break;
                        case 9:
                            finishDigit = 1;
                            break;
                        case 10:
                            finishDigit = 2;
                            break;
                        case 11:
                            finishDigit = 3;
                            break;
                        case 12:
                            finishDigit = 4;
                            break;
                        case 13:
                            finishDigit = 5;
                            break;
                        case 14:
                            finishDigit = 6;
                            break;
                        case 15:
                            finishDigit = 7;
                            break;
                        case 16:
                            finishDigit = 8;
                            break;
                        case 17:
                            finishDigit = 9;
                            break;
                    }
                }
            }

            System.out.println("finishDigit [" + finishDigit + "]");

            finalDigit = Integer.parseInt(Integer.toString(startDigit) + Integer.toString(finishDigit));

            System.out.println("finalDigit [" + finalDigit + "]");

            finalSum = finalSum + finalDigit;
        }
        System.out.println("finalSum [" + finalSum + "]");

        // Part 1 = 54159
        // Part 2 = 53866
    }
}
