package org.hbrs.se1.ws21.uebung4;

import java.util.HashMap;
import java.util.Map;

public class Mitarbeiter {

    private Integer id;
    private HashMap<String, Integer> expertise;
    private boolean verfuegbar;
    private String vorname;
    private String lastname;
    private String role;
    private String abteilung;

    public Mitarbeiter(){

    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setExpertise(HashMap<String, Integer> expertise) {this.expertise = expertise;}
    public String getExpertise() {
        String expertisen = "";
        for(Map.Entry<String,Integer> expertise: expertise.entrySet()){
            expertisen += expertise.getKey() + ":" + expertise.getValue() + "|";
        }
        return expertisen;
    }

    public void setVerfuegbar(boolean verfuegbar) { this.verfuegbar = verfuegbar;}
    public boolean isVerfuegbar() { return verfuegbar;}

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    public String getVorname() {
        return vorname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getLastname() {
        return lastname;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }

    public void setAbteilung(String abteilung) {
        this.abteilung = abteilung;
    }
    public String getAbteilung() {
        return abteilung;
    }
}
