package task1;

public class Laureate {
    // Member variables to store laureate information
    private int year;
    private String name;
    private int birthYear;
    private int deathYear;
    private String nationality;
    private String languages;
    private String citation;
    private String genres;

    // Constructor to initialize a Laureate object with provided information
    public Laureate(int year, String name, int birthYear, int deathYear, String nationality,
                    String languages, String citation, String genres) {
        this.year = year;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.nationality = nationality;
        this.languages = languages;
        this.citation = citation;
        this.genres = genres;
    }

    // Getter method to retrieve the year the laureate was awarded
    public int getYear() {
        return year;
    }

    // Getter method to retrieve the name of the laureate
    public String getName() {
        return name;
    }

    // Getter method to retrieve the birth year of the laureate
    public int getBirthYear() {
        return birthYear;
    }

    // Getter method to retrieve the death year of the laureate
    public int getDeathYear() {
        return deathYear;
    }

    // Getter method to retrieve the nationality of the laureate
    public String getNationality() {
        return nationality;
    }

    // Getter method to retrieve the languages known by the laureate
    public String getLanguages() {
        return languages;
    }

    // Getter method to retrieve the citation of the laureate
    public String getCitation() {
        return citation;
    }

    // Getter method to retrieve the genres associated with the laureate's work
    public String getGenres() {
        return genres;
    }

    // Override of toString() method to provide a string representation of the Laureate object
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Birth Year: ").append(birthYear).append("\n");
        sb.append("Death Year: ").append(deathYear).append("\n");
        sb.append("Nationality: ").append(nationality).append("\n");
        sb.append("Languages: ").append(languages).append("\n");
        sb.append("Citation: ").append(citation).append("\n");
        sb.append("Genres: ").append(genres).append("\n");
        return sb.toString();
    }
}

