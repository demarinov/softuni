package barracksWars.core.commands;

import barracksWars.interfaces.Executable;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public abstract class Command implements Executable {
    private String[] data;

    protected Command(String[] data) {
        this.data = data;
    }

//    protected Command(String[] data, Repository repository, UnitFactory unitFactory) {
//        this.data = data;
//        this.repository = repository;
//        this.unitFactory = unitFactory;
//    }

    protected String[] getData() {
        return data;
    }

}
