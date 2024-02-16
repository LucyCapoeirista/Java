package adventofcode._2023._02;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {

        boolean isPossible = false;
        int gameN = 0;
        int possibleGamesTotal = 0;
        int minCubePower = 0;

        //Read file of inputs
        List < String > lines = Collections.emptyList();
        lines = Files.readAllLines(Paths.get("C:\\Users\\Lucy\\IdeaProjects\\AdventOfCode2023\\src\\adventofcode\\_2023\\_02\\dec02.txt"), StandardCharsets.UTF_8);

        Map<String, Integer> colours
                = new HashMap<String, Integer>();

        //Work through the value for each line of input (game)
        for (int currentLine = 0; currentLine < lines.size(); currentLine++) {

            gameN++;
            int redMin = 0, greenMin = 0, blueMin = 0;

            String workingValue = lines.get(currentLine);

            System.out.println("workingValue [" + workingValue + "]");

            int find = workingValue.indexOf(": ") + 2;
            workingValue = workingValue.substring(find);

            String[] draws = workingValue.split("; ");

            System.out.println("draws [");
            for (String draw : draws)
                System.out.println(draw);
            System.out.println("]");

            for (int currentDraw = 0; currentDraw < Arrays.stream(draws).count(); currentDraw++) {

                int redCheck = 0, greenCheck = 0, blueCheck = 0;
                colours.clear();

                String[] cubes = draws[currentDraw].split(", ");
                /*System.out.println("cubes [");
                for (String cube : cubes)
                    System.out.println(cube);
                System.out.println("]");*/

                for (String cube : cubes) {
                    String[] keyValue = cube.split(" ");
                    colours.put(keyValue[1], Integer.parseInt(keyValue[0]));
                }

                System.out.println("colours [" + colours + "]");

                for (int currentColour = 0; currentColour < colours.size(); currentColour++) {
                    redCheck = 0;
                    greenCheck = 0;
                    blueCheck = 0;

                    if (colours.containsKey("red")) {
                        redCheck = colours.get("red");
                        if (redCheck > redMin) {
                            redMin = redCheck;
                        }
                      }

                    if (colours.containsKey("green")) {
                        greenCheck = colours.get("green");
                        if (greenCheck > greenMin){
                            greenMin = greenCheck;
                        }
                    }

                    if (colours.containsKey("blue")) {
                        blueCheck = colours.get("blue");
                        if (blueCheck > blueMin){
                            blueMin = blueCheck;
                        }
                    }
                }

                if (redCheck <= 12 && greenCheck <= 13 && blueCheck <= 14) {
                    isPossible = true;
                } else {
                    isPossible = false;
                    //break; // Part 1
                }
            } //End of each draw

            System.out.println("redMin [" + redMin + "] greenMin [" + greenMin + "] blueMin [" + blueMin + "]");

            minCubePower = minCubePower + (redMin * greenMin * blueMin);

            if (isPossible) {
                possibleGamesTotal = possibleGamesTotal + (gameN);

                System.out.println("possibleGamesTotal [" + possibleGamesTotal + "]");
            }

        } // End of each line (game)
        System.out.println("minCubePower [" + minCubePower + "]");

        // Part 1 = 2207
        // Part 2 = 62241
    }
}