package org.pgl.mowerauto.entity;

/**
 * Abstract representation of a grass.
 * */
public class Grass {

    /**
     * The width of grass. Matches with abscissa of surface.
     * */
    private int width;

    /**
     * The length of grass. Matches with ordinate of surface.
     * */
    private int length;

    /**
     * This matrix represents the grass as a set of case occupied or not occupied.
     * */
    private boolean[][] occupiedCases;

    public Grass(int width, int length) {
        super();
        this.width = width;
        this.length = length;
        this.occupiedCases = new boolean[width+1][length+1];
    }

    /**
     * Make the case matching with defined state occupied.
     * @param The defined state.
     * */
    public void addOccupiedState(State state){
        this.occupiedCases[state.getPositionX()][state.getPositionY()] = true;
    }

    /**
     * Make the case matching with defined state not occupied.
     * @param The defined state.
     * */
    public void removeOccupiedState(State state){
        this.occupiedCases[state.getPositionX()][state.getPositionY()] = false;
    }

    /**
     * Check if a defined presumed state matches at case already occupied by other mower.
     * 
     * @param state The state to check.
     * @return If the state matches with occupied case then return true.
     * */
    public boolean isOccupiedState(State state){

        if(this.occupiedCases[state.getPositionX()][state.getPositionY()]){
            return true;
        }
        return false;
    }

    /**
     * To check if a presumed state is inside or outside of grass.
     * 
     * @param state The defined state to check.
     * @param True if state outside, false if inside.
     * */
    public boolean isOutsideState(State state){

        if(state.getPositionY() > this.length){
            return true;
        }else if(state.getPositionY() < 0){
            return true;
        }else if(state.getPositionX() > this.width ){
            return true;
        }else if(state.getPositionX() < 0){
            return true;
        }

        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public boolean[][] getOccupiedCases() {
        return occupiedCases;
    }

    public void setOccupiedCases(boolean[][] occupiedCases) {
        this.occupiedCases = occupiedCases;
    }

}
