package fr.pantheonsorbonne.ufr27.miashs.poo;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class ItemsScrapper {
    public ArrayList<Item> parseSource(String content) {
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<ArrayList<String>> rows = extractRows(content); 

        int teamCount = 0; 
        for (ArrayList<String> row : rows) {
            try {
                if (row.size() < 10) continue; 
                if (teamCount >= 20) break;    

                Item item = new Item();
                item.setNomEquipe(extractTeamName(row.get(1)));         
                item.setPoints(parseInteger(row.get(9)));          
                item.setVictoires(parseInteger(row.get(3)));               
                item.setNuls(parseInteger(row.get(4)));                    
                item.setDefaites(parseInteger(row.get(5)));               

                items.add(item);
                teamCount++; 
            } catch (Exception e) {
                System.out.println("Erreur ");
            }
        }
        return items;
    }

    private ArrayList<ArrayList<String>> extractRows(String content) {
        ArrayList<ArrayList<String>> rows = new ArrayList<>();
        int startIndex = 0;

        while ((startIndex = content.indexOf("<tr", startIndex)) != -1) {
            int endIndex = content.indexOf("</tr>", startIndex);
            if (endIndex == -1) break;

            String rowContent = content.substring(startIndex, endIndex);
            ArrayList<String> row = new ArrayList<>();

            int colIndex = 0;
            while ((colIndex = rowContent.indexOf("<td", colIndex)) != -1) {
                int colEnd = rowContent.indexOf("</td>", colIndex);
                if (colEnd == -1) break;

                String col = rowContent.substring(rowContent.indexOf(">", colIndex) + 1, colEnd).trim();
                row.add(col);
                colIndex = colEnd + 5; 
            }
            if (!row.isEmpty()) rows.add(row);
            startIndex = endIndex + 5;
        }
        return rows;
    }

    private String extractTeamName(String html) {
        if (html.contains("league-table__team-name--short")) {
            int start = html.indexOf("league-table__team-name--short short\">") + "league-table__team-name--short short\">".length();
            int end = html.indexOf("</span>", start);
            return html.substring(start, end).trim();
        }
        return html.trim();
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0; 
        }
    }

    public static String readFileContent(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Erreur");
        }
        return content.toString();
    }
}
