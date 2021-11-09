package org.hbrs.se1.ws21.uebung4;

import org.hbrs.se1.ws21.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws21.uebung3.persistence.PersistenceStrategy;

import java.util.List;

public class PersistenceStrategyMongoDB<Mitarbeiter> implements PersistenceStrategy<Mitarbeiter> {

    @Override
    public void openConnection() throws PersistenceException {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public void closeConnection() throws PersistenceException {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public void save(List<Mitarbeiter> member) {
        throw new UnsupportedOperationException("Not implemented!");

    }

    @Override
    public List<Mitarbeiter> load() {
        throw new UnsupportedOperationException("Not implemented!");
    }
}
