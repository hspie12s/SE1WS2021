package org.hbrs.se1.ws21.uebung2;

import org.hbrs.se1.ws21.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceStrategyStream;

import java.util.LinkedList;
import java.util.List;


public class Container{

    private static Container container = null;
    private LinkedList<Member> list;
    private PersistenceStrategy pss;
    private static final Object lock = new Object();

   private Container(){
        list = new LinkedList<Member>();
    }

    public void setPersistanceStratgy(PersistenceStrategy strat){
        this.pss = strat;
    }
    public PersistenceStrategy getPersistanceStrategy(){
        return pss;
    }

    public void addMember(Member member)throws containerException{ //fügt ein Member in Container ein
        for(Member member1: list){
            if(member1.getID() == member.getID()){
                throw new containerException(member.getID());
            }
        }
        list.add(member);
    }
    public String deleteMember(Integer id){ // Entfernt ein Member aus Container
        int res = 0;
        Member n;
        for(Member member: list){
            if(member.getID() == id){
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
        list = (LinkedList<Member>) pss.load();
    }

    public static synchronized Container getContainerInstance(){
        synchronized (lock) {
            if (container == null) {
                container = new Container();
            }
        }
        return container;
    }
    public List<Member> getCurrentList(){
        return list;
    }
}
