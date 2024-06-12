package adventofcode._2023._03;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) throws Exception {

        List < String > lines = Collections.emptyList();
        lines = Files.readAllLines(Paths.get("C:\\Users\\Lucy\\IdeaProjects\\AdventOfCode2023\\src\\adventofcode\\_2023\\_03\\dec03.txt"), StandardCharsets.UTF_8);

        Integer linesSize = lines.size();
        Integer partTotal = 0;

        Map < Integer, String > ln1 = new HashMap < > ();
        Map < Integer, String > typ1 = new HashMap < > ();
        Map < Integer, String > ln2 = new HashMap < > ();
        Map < Integer, String > typ2 = new HashMap < > ();
        Map < Integer, String > ln3 = new HashMap < > ();
        Map < Integer, String > typ3 = new HashMap < > ();
        Map < Integer, Integer > parts = new HashMap < > ();

        String storeValueMiddle = "";
        String storeValueTop = "";

        //Work through the value for each line of input
        for (int currentLine = 0; currentLine < linesSize; currentLine++) {

            String workingValue = lines.get(currentLine);

            //if (workingValue.equals(".008...008")) {
            if (storeValueMiddle.equals(".....*..#................506..143........375......77.....155...........400.518...64....773...718..797........694....972.603.....*...........")) {
                String pauseHere = "";
            }

            //System.out.println("storeValueTop [" + storeValueTop + "]");
            //System.out.println("storeValueMiddle [" + storeValueMiddle + "]");
            //System.out.println("storeValueBottom (workingValue) [" + workingValue + "]");

            if (currentLine > 0) {
                populateData(ln1, storeValueTop);
                populateType(typ1, ln1);
            }

            populateData(ln2, storeValueMiddle);
            populateType(typ2, ln2);

            if (currentLine < lines.size()) {
                populateData(ln3, workingValue);
                populateType(typ3, ln3);
            }

            //First line
            if (currentLine == 1) {
                List < String > numbers = Collections.emptyList();
                numbers = checkLn(ln2, typ2);
                //System.out.println("checkLn numbers [" + numbers + "]");

                for (String number: numbers) {
                    if (number != "")
                        parts.put(parts.size(), Integer.parseInt(number));
                }

                numbers = checkLnBelow(ln2, typ2, ln3, typ3);
                //System.out.println("checkLnBelow numbers [" + numbers + "]");
                for (String number: numbers) {
                    if (number != "")
                        parts.put(parts.size(), Integer.parseInt(number));
                }

            }

            //Last line
            else if (currentLine == lines.size() - 1) {
                List < String > numbers = Collections.emptyList();
                numbers = checkLn(ln2, typ2);
                //System.out.println("checkLn numbers [" + numbers + "]");

                for (String number: numbers) {
                    if (number != "")
                        parts.put(parts.size(), Integer.parseInt(number));
                }

                numbers = checkLnAbove(ln2, typ2, ln3, typ3);
                //System.out.println("checkLnAbove numbers [" + numbers + "]");
                for (String number: numbers) {
                    if (number != "")
                        parts.put(parts.size(), Integer.parseInt(number));
                }
            }

            //All other lines
            else {
                List < String > numbers = Collections.emptyList();
                numbers = checkLn(ln2, typ2);
                //System.out.println("checkLn numbers [" + numbers + "]");

                for (String number: numbers) {
                    if (number != "")
                        parts.put(parts.size(), Integer.parseInt(number));
                }

                numbers = checkLnBelow(ln2, typ2, ln3, typ3);
                //System.out.println("checkLnBelow numbers [" + numbers + "]");
                for (String number: numbers) {
                    if (number != "")
                        parts.put(parts.size(), Integer.parseInt(number));
                }

                numbers = checkLnAbove(ln1, typ1, ln2, typ2);
                //System.out.println("checkLnAbove numbers [" + numbers + "]");
                for (String number: numbers) {
                    if (number != "")
                        parts.put(parts.size(), Integer.parseInt(number));
                }
            }

            //System.out.println("ln1 [" + ln1 + "]");
            //System.out.println("ln2 [" + ln2 + "]");
            //System.out.println("ln3 [" + ln3 + "]");
            System.out.println("parts [" + parts + "]");

            storeValueTop = storeValueMiddle;
            storeValueMiddle = workingValue;
        } //End of for loop

        for (Integer currentPart = 0; currentPart < parts.size(); currentPart++) {
            partTotal = partTotal + Integer.parseInt(Integer.toString(parts.get(currentPart)));
        }

        System.out.println("partTotal [" + partTotal + "]");

    } //End of main

    public static Map < Integer, String > populateData(Map < Integer, String > line, String substring) throws Exception {
        for (int currentChar = 0; currentChar < substring.length(); currentChar++) {
            line.put(currentChar, substring.substring(currentChar, currentChar + 1));
        }
        return line;
    } //End of populateData

    public static Map < Integer, String > populateType(Map < Integer, String > types, Map < Integer, String > line) throws Exception {
        for (int currentChar = 0; currentChar < line.size(); currentChar++) {
            String type = "symbol";
            String value = line.get(currentChar);
            boolean checkNumber = Pattern.matches("[0-9]", value);
            boolean checkDot = value.equals(".");
            if (checkNumber) type = "number";
            if (checkDot) type = "dot";
            types.put(currentChar, type);
        }
        return types;
    } //End of populateType

    public static List < String > checkLn(Map < Integer, String > ln2, Map < Integer, String > typ2) throws Exception {
        Boolean validPart = false;
        List < String > numberList;
        String number = "";
        String numbers = "";

        for (int currentChar = 0; currentChar < typ2.size(); currentChar++) {
            if (typ2.get(currentChar) == "number")
                number = number + ln2.get(currentChar);

            //Scenario 1: Number, followed by symbol
            if (typ2.get(currentChar) == "symbol" && number != "") {
                numbers = numbers + number + " ";
                number = "";
            }

            //Scenario 2: Number, preceeded by symbol
            if (currentChar > 0) {
                if (typ2.get(currentChar - 1) == "symbol" && number != "")
                    validPart = true;
            }

            if (checkNumber(typ2, currentChar, validPart, number)) {
                numbers = numbers + number + " ";
                number = "";
                validPart = false;
            }

            if (number != "" && typ2.get(currentChar) != "number" && validPart == false) {
                number = "";
            }
        }
        numberList = Arrays.stream((numbers.split(" "))).toList();
        return numberList;
    } //End of checkLn

    public static List < String > checkLnBelow(Map < Integer, String > ln2, Map < Integer, String > typ2, Map < Integer, String > ln3, Map < Integer, String > typ3) throws Exception {
        Boolean validPart = false;
        List < String > numberList;
        String number = "";
        String numbers = "";

        for (int currentChar = 0; currentChar < typ2.size(); currentChar++) {
            if (typ2.get(currentChar) == "number")
                number = number + ln2.get(currentChar);

            //Scenario 3: Number with symbol below
            if (typ2.get(currentChar) == "number" && typ3.get(currentChar) == "symbol")
                validPart = true;

            //Scenario 4: Number with symbol forward and below
            if (currentChar < typ2.size() - 1) {
                if (typ2.get(currentChar) == "number" && typ3.get(currentChar + 1) == "symbol")
                    validPart = true;
            }

            //Scenario 5: Number with symbol backward and below
            if (currentChar > 0) {
                if (typ2.get(currentChar) == "number" && typ3.get(currentChar - 1) == "symbol")
                    validPart = true;
            }

            if (checkNumber(typ2, currentChar, validPart, number)) {
                numbers = numbers + number + " ";
                number = "";
                validPart = false;
            }

            if (number != "" && typ2.get(currentChar) != "number" && validPart == false) {
                number = "";
            }
        }
        numberList = Arrays.stream((numbers.split(" "))).toList();
        return numberList;
    } //End of checkLnBelow

    public static List < String > checkLnAbove(Map < Integer, String > ln1, Map < Integer, String > typ1, Map < Integer, String > ln2, Map < Integer, String > typ2) throws Exception {
        Boolean validPart = false;
        List < String > numberList;
        String number = "";
        String numbers = "";

        for (int currentChar = 0; currentChar < typ2.size(); currentChar++) {
            if (typ2.get(currentChar) == "number")
                number = number + ln2.get(currentChar);

            //Scenario 6: Number with symbol above
            if (typ2.get(currentChar) == "number" && typ1.get(currentChar) == "symbol")
                validPart = true;

            //Scenario 7: Number with symbol forward and above
            if (currentChar < typ2.size() - 1) {
                if (typ2.get(currentChar) == "number" && typ1.get(currentChar + 1) == "symbol")
                    validPart = true;
            }

            //Scenario 8: Number with symbol backward and above
            if (currentChar > 0) {
                if (typ2.get(currentChar) == "number" && typ1.get(currentChar - 1) == "symbol")
                    validPart = true;
            }

            if (checkNumber(typ2, currentChar, validPart, number)) {
                numbers = numbers + number + " ";
                number = "";
                validPart = false;
            }

            if (number != "" && typ2.get(currentChar) != "number" && validPart == false) {
                number = "";
            }
        }
        numberList = Arrays.stream((numbers.split(" "))).toList();
        return numberList;
    } //End of checkLnAbove

    public static Boolean checkNumber(Map < Integer, String > typ2, Integer currentChar, Boolean validPart, String number) throws Exception {
        //                Exited by dot                         Exited by line end
        if (validPart && (typ2.get(currentChar) != "number" || (currentChar == typ2.size() - 1))) {
            return true;
        }
        return false;
    } //End of checkNumber

} //End of Main

// 538886 too low