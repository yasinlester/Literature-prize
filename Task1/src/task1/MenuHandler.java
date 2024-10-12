package task1;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuHandler {
    private List<LiteraturePrize> literaturePrizes;

    public MenuHandler(List<LiteraturePrize> literaturePrizes) {
        this.literaturePrizes = literaturePrizes;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------");
            System.out.println("Literature prize menu");
            System.out.println("----------------------");
            System.out.println("List ................1");
            System.out.println("Select ..............2");
            System.out.println("Search ..............3");
            System.out.println("Exit.................0");
            System.out.println("----------------------");
            System.out.print("Enter choice > ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter start year: ");
                    int startYear = scanner.nextInt();
                    System.out.print("Enter end year: ");
                    int endYear = scanner.nextInt();
                    listPrizes(startYear, endYear);
                    break;
                case 2:
                    selectPrize();
                    break;
                case 3:
                    searchPrizes();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

//listPrizes method
    private void listPrizes(int startYear, int endYear) {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("| Year | Prize winners (and associated nations)                                 |");
        System.out.println("--------------------------------------------------------------------------------");
        for (int year = startYear; year <= endYear; year++) {
            StringBuilder winners = new StringBuilder();
            boolean hasWinner = false;
            for (LiteraturePrize prize : literaturePrizes) {
                if (prize.getYear() == year) {
                    winners.append(prize.getLaureatesString()); // This line appends laureates' names
                    hasWinner = true;
                    break;
                }
            }
            if (hasWinner) {
                System.out.printf("| %-4d | %-70s |\n", year, winners);
            } else {
                System.out.printf("| %-4d | %-70s |\n", year, "NOT AWARDED");
            }
        }
        System.out.println("---------------------------------------------------------------------------------");
    }

//selectPrize method
    private void selectPrize() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the year to select: ");
        int selectedYear = scanner.nextInt();

        boolean found = false;

        for (LiteraturePrize prize : literaturePrizes) {
            if (prize.getYear() == selectedYear) {
                System.out.println("----------------------------------------------------------------------------------------------");
                System.out.println("| Winner(s)             | Born | Died | Language(s)    | Genre(s)                             |");
                System.out.println("---------------------------------------------------------------------------------------------");

                for (Laureate laureate : prize.getLaureates()) {
                    String winnerLine = String.format("| %-22s", laureate.getName());

                    String languages = laureate.getLanguages();
                    String deathYear = laureate.getDeathYear() == 0 ? "----" : String.valueOf(laureate.getDeathYear());

                    // Split genres into separate lines
                    String[] genres = laureate.getGenres().split(",\\s*");

                    System.out.printf("%s | %-4d | %-4s | %-14s | %-32s |\n", winnerLine, laureate.getBirthYear(), deathYear, languages, genres[0]);
                    for (int i = 1; i < genres.length; i++) {
                        System.out.printf("| %-22s |      |      |                | %-32s |\n", "", genres[i]);
                    }
                    System.out.println("----------------------------------------------------------------------------------------------");

                    // Citation formatting
                    String citationTitle = "Citation:";
                    int titlePadding = (80 - citationTitle.length()) / 2;
                    System.out.printf("|%" + titlePadding + "s%s%" + (80 - titlePadding - citationTitle.length()) + "s|\n", "", citationTitle, "");

                    String citationText = laureate.getCitation();
                    int citationLength = citationText.length();
                    int numLines = (citationLength + 84) / 85;
                    int textPadding = (80 - 85) / 2;
                    for (int i = 0; i < numLines; i++) {
                        int start = i * 85;
                        int end = Math.min(start + 85, citationLength);
                        String line = citationText.substring(start, end);
                        System.out.printf("| %" + textPadding + "s%-85s%" + (80 - textPadding - 85) + "s |\n", "", line, "");
                    }
                    System.out.println("------------------------------------------------------------------------------------------");
                }
                found = true;
            }
        }

        if (!found) {
            System.out.println("No prize found for the selected year.");
        }
    }
//searchPrizes method
    private void searchPrizes() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search term for writing genre: ");
        String searchTerm = scanner.nextLine().trim().toLowerCase();

        boolean found = false;

        // Creates a list to store laureates matching the search term
        List<Laureate> matchingLaureates = new ArrayList<>();

        for (LiteraturePrize prize : literaturePrizes) {
            for (Laureate laureate : prize.getLaureates()) {
                for (String genre : laureate.getGenres().split(",\\s*")) {
                    if (genre.trim().toLowerCase().contains(searchTerm)) {
                        matchingLaureates.add(laureate);
                        found = true;
                        break; // No need to continue checking genres once a match is found
                    }
                }
            }
        }

        // Sorts the matching laureates alphabetically by name
        matchingLaureates.sort((l1, l2) -> l1.getName().compareTo(l2.getName()));

        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("| Name                           | Genres                                                       | Year   |");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        for (Laureate laureate : matchingLaureates) {
            String name = laureate.getName().length() > 30 ? laureate.getName().substring(0, 27) + "..." : laureate.getName();
            String genres = laureate.getGenres().length() > 60 ? laureate.getGenres().substring(0, 57) + "..." : laureate.getGenres();
            String year = String.valueOf(laureate.getYear());
            year = String.format("%1$4s", year); // Right-align the year column and pad to 4 characters

            System.out.printf("| %-30s | %-60s | %-6s |\n", name, genres, year);
        }

        if (!found) {
            System.out.println("| No matching result found for the search keyword.                                                       |");
        }

        System.out.println("----------------------------------------------------------------------------------------------------------");
    }
}

