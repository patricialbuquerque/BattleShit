package client;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap <String, String> grid = new HashMap<>();

        String alphabet = "ABCDEFGHIJ";

        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++) {

                grid.put(alphabet.substring(i , i + 1) + j, "≈");

            }
        }
        for (String j : grid.keySet()) {
            System.out.println(j);
        }
    }
}
