package controller;

import hyperGraphs.HLH;

public interface HyperGraphBrowser
{

    public abstract void setGraph(HLH hlh);
    
    public abstract void setCurrentFloor(int floorNr);

    public abstract void clear();
}
