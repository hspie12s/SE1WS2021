package org.hbrs.se1.ws21.uebung3;

import org.hbrs.se1.ws21.uebung2.Container;
import org.hbrs.se1.ws21.uebung2.Factory;
import org.hbrs.se1.ws21.uebung2.Member;
import org.hbrs.se1.ws21.uebung2.containerException;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    Container c;

    @BeforeEach
    void setUp(){
        c = Container.getContainerInstance();
    }
    @Test
    void noStrategySetTest(){
        try{

            c.setPersistanceStratgy(null);
            c.store();
        }
        catch (PersistenceException pe){
            assertEquals("No Strategy is set", pe.getMessage());
            assertEquals(pe.getExceptionTypeType(), PersistenceException.ExceptionType.NoStrategyIsSet);
        }
    }
    @Test
    void strategieMongoDBTest(){
        try{
            c.setPersistanceStratgy(new PersistenceStrategyMongoDB<Member>());
            c.store();

        } catch (Exception pe) {
            assertEquals(pe.getMessage(), "Not implemented!");
            //assertEquals(pe.getExceptionTypeType(), PersistenceException.ExceptionType.ImplementationNotAvailable);
        }
    }
    @Test
    void wrongLocationTest(){
        try {
            PersistenceStrategyStream<Member> strategy = new PersistenceStrategyStream<>();
            strategy.setLocation("/User/benutzer/tmp");
            c.setPersistanceStratgy(strategy);
            c.store();

        } catch (PersistenceException e) {
            assertEquals("No Connection", e.getMessage());
            assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, e.getExceptionTypeType());
        }

    }
    @Test
    void functionTest(){
        Member m1 = new Factory(1);
        assertEquals(0, c.size());

        try{
            c.setPersistanceStratgy(new PersistenceStrategyStream<Member>());

            c.addMember(m1);
            assertEquals(1, c.size());

            c.deleteMember(1);
            assertEquals(0,c.size());

            c.addMember(m1);
            assertEquals(1, c.size());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}