package org.pgl.mowerauto.entity;


/**
 * Abstract representation of A mower.
 * */
public class Mower {
    
    private static int nbMower = 0;
    
    /**
     * The id of mower.
     * */
    private int id;

    /**
     * Actual state of mower.
     * */
    private State state;
    
    public Mower(State initialState){
        this.state = initialState;
        //Attribute a new id to new Mower :
        this.id = ++nbMower;
    }
    
    /**
     * Attempt a presume forward to mower base on its actual state.
     * Does not move the mower.
     * 
     * @return Provides a presumed state after forward.
     * */
    public State presumeForward(){
        State result = null;
        
        Orientation orientation = state.getOrientation();
        int actualX = state.getPositionX();
        int actualY = state.getPositionY();
        
        switch(orientation){
            case NORTH:
                actualY++;
                break;
            case EAST:
                actualX++;
                break;
            case WEST:
                actualX--;
                break;
            case SOUTH:
                actualY--;
                break;
            default:
                throw new RuntimeException("Unknown orientation");//TODO creer ses propres exceptions
        }
        
        result = new State(orientation, actualX, actualY);
        
        return result;
    }
    
    /**
     * To rotate 90° on itself to left.
     * */
    public void turnLeft(){
        Orientation orientation = state.getOrientation();
        switch(orientation){
            case NORTH:
                state.setOrientation(Orientation.WEST);
                break;
            case EAST:
                state.setOrientation(Orientation.NORTH);
                break;
            case WEST:
                state.setOrientation(Orientation.SOUTH);
                break;
            case SOUTH:
                state.setOrientation(Orientation.EAST);
                break;
            default:
                throw new RuntimeException("Unknown orientation");//TODO creer ses propres exceptions
        }
    }
    
    /**
     * To rotate 90° on itself to right.
     * */
    public void turnRight(){
        Orientation orientation = state.getOrientation();
        switch(orientation){
            case NORTH:
                state.setOrientation(Orientation.EAST);
                break;
            case EAST:
                state.setOrientation(Orientation.SOUTH);
                break;
            case WEST:
                state.setOrientation(Orientation.NORTH);
                break;
            case SOUTH:
                state.setOrientation(Orientation.WEST);
                break;
            default:
                throw new RuntimeException("Unknown orientation");//TODO creer ses propres exceptions
        }
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public State getState() {
        return state;
    }
    
    public int getId() {
        return id;
    }
}