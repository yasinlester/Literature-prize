package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        String fileLocation = System.getProperty("user.dir");
        String dataPath = fileLocation + java.io.File.separator + "literature-prizes.txt";
        LiteraturePrizeLoader loader = new LiteraturePrizeLoader();
        List<LiteraturePrize> literaturePrizes = loader.loadPrizes(dataPath);
        MenuHandler menuHandler = new MenuHandler(literaturePrizes);
        menuHandler.displayMenu();
    }
}
