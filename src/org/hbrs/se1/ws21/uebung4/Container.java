package org.hbrs.se1.ws21.uebung4;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Container {

    private static Container container = null;
    private LinkedList<Mitarbeiter> list;
    private PersistenceStrategyStream <Mitarbeiter> pss = null;
    private static final Object lock = new Object();

   private Container(){
        list = new LinkedList<Mitarbeiter>();
    }

    public void setPersistanceStratgy(PersistenceStrategyStream strat){
        this.pss = strat;
    }
    public PersistenceStrategy getPersistanceStrategy(){
        return pss;
    }
    public void addMember(Mitarbeiter member) { //fügt ein Member in Container ein
        for(Mitarbeiter member1: list){
            if(member1.getId() == member.getId()){
                //throw new containerException(member.getId());
            }
        }
        list.add(member);
    }
    public String deleteMember(Integer id){ // Entfernt ein Member aus Container
        int res = 0;
        Mitarbeiter n;
        for(Mitarbeiter member: list){
            if(member.getId() == id){
                n = member;
                list.remove(n);
                return "" + id; // fehlermeldung
            }
        }
        return "Der Member mit der ID: " + id + " ist nicht enthalten.";
    }
    /* public void dump(){ // ausgabe der Memeber IDs aus Container
        for(Member member: list){
            System.out.println("Member (ID= " + member.getID()+ " )");
        }
    } */
    public int size(){ // Anzahl aller Member in Container
        return list.size();
    }
    public void store() throws PersistenceException { // speichert Member-Objekt persistent ab
        if(pss == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"No Strategy is set");
        }
        pss.save(list);
    }
    public void load() throws PersistenceException { // lädt Memeber-Objekt
        if(pss == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No Strategy is set");
        }
        list = (LinkedList<Mitarbeiter>) pss.load();
    }
    public static synchronized Container getContainerInstance(){
        synchronized (lock) {
            if (container == null) {
                container = new Container();
            }
        }
        return container;
    }
    public LinkedList<Mitarbeiter> getCurrentList(){
        return list;
    }
    public void enter(int id, String v, String n, String r, String a, HashMap<String,Integer> h){
        Mitarbeiter employee = new Mitarbeiter();

        employee.setId(id);
        employee.setVorname(v);
        employee.setLastname(n);
        employee.setRole(r);
        employee.setAbteilung(a);
        employee.setExpertise(h);

        container.addMember(employee);

    }
    public void dump(){
        OutputDialog od = new OutputDialog();
        od.dump(container);
    }
    public void search(String kriterium){
        LinkedList<Mitarbeiter> mitarbeiterMitKrit = new LinkedList<>();
        for(Mitarbeiter emp: getCurrentList()){
            if(emp.getExpertise().contains(kriterium)){
                mitarbeiterMitKrit.add(emp);
            }
        }
        list = mitarbeiterMitKrit;
        this.dump(); // ausgabe der Mitarbeiter in einer Tabelle
    }
    public void exit() {
        try{
            pss.closeConnection();
        } catch (org.hbrs.se1.ws21.uebung4.PersistenceException e){
           System.out.println((e.getMessage()));
        }

    }
    public void help(){
        HashMap<String, String> hilfe= new HashMap<>(7);
        hilfe.put("enter", "Erstellung eines Mitarbeiters");
        hilfe.put("store", "Abspeichern der Mitarbeiter");
        hilfe.put("load", "Laden von Mitarbeiter vom Datenträger");
        hilfe.put("dump", "Ausgabe der Mitarbeiter nach der ID");
        hilfe.put("search","Suche nach expertise, somit Ausgabe der Mitarbeiter");
        hilfe.put("exit","Verlassen der Anwendung");
        hilfe.put("help", "Ausgabe aller möglichen Eingaben");
        for(Map.Entry<String, String> e: hilfe.entrySet()){
            System.out.println(e.getKey() + ":" + e.getValue());
        }
    }
}
