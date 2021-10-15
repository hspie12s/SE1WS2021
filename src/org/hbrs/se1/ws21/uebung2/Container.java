package org.hbrs.se1.ws21.uebung2;

import java.util.LinkedList;


public class Container{

    private int size = 0;
    LinkedList<Member> list;

    public Container(){
        list = new LinkedList<Member>();
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
    public void dump(){ // ausgabe der Memeber IDs aus Container
        for(Member member: list){
            System.out.println("Member (ID= " + member.getID()+ " )");
        }
    }
    public int size(){ // Anzahl aller Member in Container
        return list.size();
    }
}
