package org.hbrs.se1.ws21.uebung3.persistence;

import java.io.*;

import java.util.List;

public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";

    private ObjectInputStream ins;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private FileOutputStream fos;
    private String fpl;
    private String fps;
    private boolean read;
    private boolean hasRead;
    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    /*public PersistenceStrategyStream(String filepathLoad, String filepathStore) {
        fpl = filepathLoad;
        fps = filepathStore;
    }*/
    public void setLocation(String location){
        this.location = location;
    }
    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */
    public void openConnection() throws PersistenceException {
        if(read){
            try{
                fis = new FileInputStream(location);
                ins = new ObjectInputStream(fis);

            } catch (Exception e) {
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "No Connetion");
            }
        }
        else{
            try{
                fos = new FileOutputStream(location);
                oos = new ObjectOutputStream(fos);
            } catch (Exception e) {
                throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "No Connection");

            }
        }
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
        if(hasRead){
            try{
                ins.close();
            } catch (Exception e) {
                throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "s");
            }
        }
        else{
            try{
                ins.close();
            } catch (IOException e) {
                throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "s");
            }
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException  {
        read = false;
        openConnection();

        try{
            oos.writeObject(member);

        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,"SaveError");
        }
        finally {
            hasRead = false;
            closeConnection();
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<Member> load() throws PersistenceException  {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        List<Member> newListe =  null;
        Object obj = null;

        try {
            fis = new FileInputStream(location);
            ins = new ObjectInputStream(fis);

        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "");
        }
        try {
            obj = ins.readObject();
        }
        catch (Exception e){
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,"Load Failure");
        }
        if (obj instanceof List<?>) {
            newListe = (List) obj;
            return newListe;
        }
        return newListe;
    }
}
