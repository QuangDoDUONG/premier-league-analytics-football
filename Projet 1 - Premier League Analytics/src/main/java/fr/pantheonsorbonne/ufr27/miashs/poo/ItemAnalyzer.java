package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.util.ArrayList;

public class ItemAnalyzer {
    private ArrayList<Item> items;

    public ItemAnalyzer(ArrayList<Item> items) {
        this.items = items;
    }

    public String getEquipeAvecPlusDePoints() {
        Item meilleureEquipe = null;
        int maxPoints = Integer.MIN_VALUE;

        for (Item item : items) {
            if (item.getPoints() > maxPoints) {
                maxPoints = item.getPoints();
                meilleureEquipe = item;
            }
        }
        return meilleureEquipe.getNomEquipe() + "(" + meilleureEquipe.getPoints() + ")";
    }

    public String getEquipeAvecPlusDeVictoires() {
        Item meilleureEquipe = null;
        int maxVictoires = Integer.MIN_VALUE;

        for (Item item : items) {
            if (item.getVictoires() > maxVictoires) {
                maxVictoires = item.getVictoires();
                meilleureEquipe = item;
            }
        }
        return meilleureEquipe.getNomEquipe() + "(" + meilleureEquipe.getVictoires() + ")";
    }

    public String getEquipeAvecPlusDeNuls() {
        Item meilleureEquipe = null;
        int maxNuls = Integer.MIN_VALUE;

        for (Item item : items) {
            if (item.getNuls() > maxNuls) {
                maxNuls = item.getNuls();
                meilleureEquipe = item;
            }
        }
        return meilleureEquipe.getNomEquipe() + "(" + meilleureEquipe.getNuls() + ")";
    }

    public String getEquipeAvecPlusDeDefaites() {
        Item meilleureEquipe = null;
        int maxDefaites = Integer.MIN_VALUE;

        for (Item item : items) {
            if (item.getDefaites() > maxDefaites) {
                maxDefaites = item.getDefaites();
                meilleureEquipe = item;
            }
        }
        return meilleureEquipe.getNomEquipe() + "(" + meilleureEquipe.getDefaites() + ")";
    }

    public double getMoyenneDesPoints() {
        if (items.isEmpty()) {
            return 0.0; 
        }

        int totalPoints = 0; 

        for (Item item : items) {
            totalPoints += item.getPoints();
        }

        return Math.round((double) totalPoints / items.size() * 100.0) / 100.0;
    }

    public String getEquipe_susceptible_etre_Championne() {
        Item equipeChampionne = null;
        int maxVictoires = Integer.MIN_VALUE;
        int minDefaites = Integer.MAX_VALUE;

        for (Item item : items) {
            if (item.getVictoires() > maxVictoires || 
                (item.getVictoires() == maxVictoires && item.getDefaites() < minDefaites)) {
                maxVictoires = item.getVictoires();
                minDefaites = item.getDefaites();
                equipeChampionne = item;
            }
        }
        return equipeChampionne.getNomEquipe() ;
    }

    public ArrayList<String> getTrois_EquipesRisqueRelegation() {
        ArrayList<Item> sortedItems = new ArrayList<>(items);
        for (int i = 0; i < sortedItems.size() - 1; i++) {
            for (int j = i + 1; j < sortedItems.size(); j++) {
                if (sortedItems.get(i).getPoints() > sortedItems.get(j).getPoints() || 
                    (sortedItems.get(i).getPoints() == sortedItems.get(j).getPoints() && 
                    sortedItems.get(i).getDefaites() < sortedItems.get(j).getDefaites())) {
                    Item temp = sortedItems.get(i);
                    sortedItems.set(i, sortedItems.get(j));
                    sortedItems.set(j, temp);
                }
            }
        }

        ArrayList<String> equipesRisque = new ArrayList<>();
        for (int i = 0; i < 3 && i < sortedItems.size(); i++) {
            Item equipe = sortedItems.get(i);
            equipesRisque.add(equipe.getNomEquipe());
        }
    
        return equipesRisque;
    }

    public String getEquipeAvecMeilleurRendement() {
        Item meilleureEquipe = null;
        double meilleurRendement = 0.0;
    
        for (Item item : items) {
            int totalMatches = item.getVictoires() + item.getNuls() + item.getDefaites();
            if (totalMatches > 0) {
                double rendement = (double) item.getVictoires() / totalMatches * 100;
                if (rendement > meilleurRendement) {
                    meilleurRendement = rendement;
                    meilleureEquipe = item;
                }
            }
        }
    
        return meilleureEquipe.getNomEquipe() + "(" + String.format("%.2f", meilleurRendement) + ")";
    }
} 