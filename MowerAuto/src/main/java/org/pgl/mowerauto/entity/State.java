package org.pgl.mowerauto.entity;


/**
 * This class encapsulate a state for a mower. 
 * */
public class State {
    
    private Orientation orientation;
    
    private int positionX;
    
    private int positionY;
    
    public State(Orientation orientation, int positionX, int positionY) {
        super();
        this.orientation = orientation;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Orientation getOrientation() {
        return orientation;
    }
    
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
    
    
}
