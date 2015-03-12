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
    
    /**
     * Check if this state has equals position with state arg.
     * Equals position means same positionX and positionY but not necessary same orientation.
     * 
     * @param state To compare equality position.
     * @return True if position equals, else false.
     * */
    public boolean equalsPosition(State state){
    	if(this.positionX == state.getPositionX() && this.positionY == state.getPositionY()){
    		return true;
    	}
    	return false;
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

	@Override
	public String toString() {
		return "State [orientation=" + orientation + ", positionX=" + positionX
				+ ", positionY=" + positionY + "]";
	}
    
    
    
    
}
