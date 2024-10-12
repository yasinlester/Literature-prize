package task1;

import java.util.List;

public class LiteraturePrize {
    private int year;
    private List<Laureate> laureates;

    // Constructor to initialize a LiteraturePrize with a year and list of laureates
    public LiteraturePrize(int year, List<Laureate> laureates) {
        this.year = year;
        this.laureates = laureates;
    }

    // Getter method to retrieve the year of the LiteraturePrize
    public int getYear() {
        return year;
    }

    // Getter method to retrieve the list of laureates associated with the LiteraturePrize
    public List<Laureate> getLaureates() {
        return laureates;
    }

    // Method to generate a string representing the names of laureates for display purposes
    public String getLaureatesString() {
        StringBuilder sb = new StringBuilder();
        for (Laureate laureate : laureates) {
            sb.append(laureate.getName());
            if (!laureate.getNationality().isEmpty()) {
                sb.append(" [").append(laureate.getNationality()).append("]");
            }
            sb.append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
    }

    // Override of toString() method to provide a string representation of the LiteraturePrize
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Year: ").append(year).append("\n");

        // Iterate through laureates and append their details to the string
        for (Laureate laureate : laureates) {
            sb.append("------------------------------------------------------------------------------------------\n");
            sb.append("| Winner(s)             | Born       | Died       | Nationality  | Language(s) | Genre(s)           |\n");
            sb.append("------------------------------------------------------------------------------------------\n");
            sb.append(String.format("| %-23s | %-10s | %-10s | %-12s | %-11s | %-18s |\n",
                    laureate.getName(), formatYear(laureate.getBirthYear()), formatYear(laureate.getDeathYear()),
                    laureate.getNationality(), laureate.getLanguages(), laureate.getGenres()));
            sb.append("------------------------------------------------------------------------------------------\n");
            sb.append("| Citation:                                                                           |\n");
            sb.append("|                                                                                    |\n");
            sb.append("| ").append(laureate.getCitation()).append("\n");
            sb.append("------------------------------------------------------------------------------------------\n");
        }

        return sb.toString();
    }

    // Helper method to format a year as a string
    private static String formatYear(int year) {
        return year != 0 ? String.valueOf(year) : "-";
    }
}
