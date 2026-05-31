package fr.pantheonsorbonne.ufr27.miashs.poo;

import java.lang.String;

public class Item {
    private String nomEquipe;

    private int points;

    private int victoires;

    private int nuls;

    private int defaites;

    public String getNomEquipe() {
        return this.nomEquipe;
    }
    
    public void setNomEquipe(String NomEquipe) {
        this.nomEquipe = NomEquipe;
    }
    
    public Integer getPoints() {
        return this.points;
    }
    
    public void setPoints(Integer Points) {
        this.points = Points;
    }
    
    public Integer getVictoires() {
        return this.victoires;
    }
    
    public void setVictoires(Integer Victoires) {
        this.victoires = Victoires;
    }
    
    public Integer getNuls() {
        return this.nuls;
    }
    
    public void setNuls(Integer Nuls) {
        this.nuls = Nuls;
    }
    
    public Integer getDefaites() {
        return this.defaites;
    }
    
    public void setDefaites(Integer Defaites) {
        this.defaites = Defaites;
    }   
}

