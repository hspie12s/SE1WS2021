package org.hbrs.se1.ws21.uebung3.persistence;

import org.hbrs.se1.ws21.uebung2.Member;

import java.util.List;

public class MemberView {
    public void dump(List<Member> liste){
        for(Member member: liste){
            System.out.println(member.toString());
        }
    }
}
