package org.hbrs.se1.ws21.uebung4;

public class Main {
    public static void main(String[] args) {

        Container container = Container.getContainerInstance();

        container.setPersistanceStratgy(new PersistenceStrategyStream<Mitarbeiter>());
        InputDialog dia = new InputDialog();
        dia.input();
    }
}
