package models;

import java.util.List;

public abstract class SymbolicLocation {

    public abstract List<Guard> getInvariants();
}