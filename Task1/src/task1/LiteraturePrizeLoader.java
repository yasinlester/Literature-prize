package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiteraturePrizeLoader {

    /**
     * Loads literature prizes from a file.
     * @param filePath The path to the file containing literature prizes data.
     * @return A list of LiteraturePrize objects.
     */
    public List<LiteraturePrize> loadPrizes(String filePath) {
        List<LiteraturePrize> literaturePrizes = new ArrayList<>();
        List<Laureate> laureates = readNobelPrizeFile(filePath);
        int currentYear = -1;
        List<Laureate> currentLaureates = new ArrayList<>();

        // Iterate through laureates and group them by year into LiteraturePrize objects
        for (Laureate laureate : laureates) {
            if (currentYear != -1 && laureate.getYear() != currentYear) {
                // If a new year is encountered, create a new LiteraturePrize object
                literaturePrizes.add(new LiteraturePrize(currentYear, currentLaureates));
                currentLaureates = new ArrayList<>();
            }
            currentYear = laureate.getYear();
            currentLaureates.add(laureate);
        }

        // Add the last batch of laureates to the last LiteraturePrize object
        if (!currentLaureates.isEmpty()) {
            literaturePrizes.add(new LiteraturePrize(currentYear, currentLaureates));
        }

        return literaturePrizes;
    }

    /**
     * Reads Nobel Prize data from a file and creates Laureate objects.
     * @param filePath The path to the file containing Nobel Prize data.
     * @return A list of Laureate objects.
     */
    private List<Laureate> readNobelPrizeFile(String filePath) {
        List<Laureate> laureates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    if (Character.isDigit(line.charAt(0))) {
                        // If the line starts with a digit, it represents a year
                        int year = Integer.parseInt(line.trim());
                        String laureateLine;
                        while ((laureateLine = br.readLine()) != null && !laureateLine.isEmpty() && !laureateLine.equals("-----")) {
                            // Read laureate data until an empty line or "-----" is encountered
                            if (laureateLine.equals("Not awarded")) {
                                break; // Skip "Not awarded" lines
                            }
                            // regex used to parse laureate information
                            Matcher matcher = Pattern.compile("(.+)\\((?:b\\. )?(\\d{4})(?:-(\\d{1,4}))?\\)\\|([^|]+)\\|([^|]+)").matcher(laureateLine);

                            if (matcher.matches()) {
                                String name = matcher.group(1).trim();
                                int birthYear = Integer.parseInt(matcher.group(2));
                                int deathYear = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0;
                                String nationality = matcher.group(4).trim();
                                String languages = matcher.group(5).trim();
                                String citation = br.readLine().trim();
                                String genres = br.readLine().trim();
                                laureates.add(new Laureate(year, name, birthYear, deathYear, nationality, languages, citation, genres));
                            } else {
                                System.err.println("Invalid data format in line: " + laureateLine);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return laureates;
    }
}
