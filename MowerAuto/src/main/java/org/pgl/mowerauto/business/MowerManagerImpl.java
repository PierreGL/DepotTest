package org.pgl.mowerauto.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.pgl.mowerauto.dao.Dao;
import org.pgl.mowerauto.dao.DaoFileImpl;
import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.entity.Grass;
import org.pgl.mowerauto.entity.Instruction;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.Operation;
import org.pgl.mowerauto.entity.Sequence;
import org.pgl.mowerauto.entity.State;

//TODO comment
public class MowerManagerImpl implements MowerManager {

    private Dao dao;
    
    private Grass grass;
    
    /**
     * The set of waiting mowers, located on the grass.
     * */
    private Queue<Sequence> waitingSequences;
    
    private List<State> occupiedStates;
    
    public MowerManagerImpl(){
        dao = new DaoFileImpl();//TODO use factory or injection
    }
    
    @Override
    public void loadOperation(DataSource source) {
    	this.dao.loadDataSource(source);
        Operation operation = this.dao.getOperation();
        this.grass = operation.getGrass();
        this.occupiedStates = new ArrayList<>();
        List<Sequence> lstSequence = operation.getSequences();
        this.waitingSequences = new ArrayBlockingQueue<>(lstSequence.size());//new ArrayList<>();
        
        for (Sequence sequence : lstSequence) {
        	Mower newMower = sequence.getMower();
        	State initialState = newMower.getState();
        	if(isOccupiedState(initialState, this.occupiedStates)){//New mower cannot be located on other mower.
        		//TODO display pb
        	}else if(isStateOutsideGrass(initialState, operation.getGrass())){//New mower cannot be located outside the grass
        		//TODO display pb
        	}else{//The mower can be added on grass.
    			this.waitingSequences.add(sequence);
    			this.occupiedStates.add(initialState);		
        	}
		}
    }

    @Override
    public void execute() { 	
    	if(this.grass == null || this.occupiedStates == null || this.waitingSequences == null){
    		throw new IllegalStateException("Probably the execute method has been called before loadOperation.");
    	}
    	
        for (Sequence sequence : this.waitingSequences) {
            executeSequence(this.grass, sequence);
        }       
    }
    
    private void executeSequence(Grass grass, Sequence sequence){
        Mower mower = sequence.getMower();
        
        List<Instruction> instructions = sequence.getInstructions();
        
        for (Instruction instruction : instructions) {
            switch(instruction){
                case FORWARD:
                    State state = mower.presumeForward();
                    //Two business test : future state must not be out of grass or on occupied state.
                    if(!isStateOutsideGrass(state, grass) && !isOccupiedState(state, this.occupiedStates)){
                        mower.setState(state);
                    }
                    break;
                case RIGHT:
                    mower.turnRight();
                    break;
                case LEFT:
                    mower.turnLeft();
                    break;
                default:
                    throw new RuntimeException("Unknown instruction.");//TODO creer ses propres exceptions
            }
        }
        //TODO update waiting mower
        displayState(mower);
    }
    
    private void displayState(Mower mower) {
    	// TODO Auto-generated method stub
    	System.out.println(mower.getState());
    	
    }
    
    /**
     * Check if the state of a mower is outside the defined grass.
     * 
     * @param state The presumed state.
     * @param grass The defined grass.
     * @return If the state is outside return true, else return false.
     * */
    private boolean isStateOutsideGrass(State state, Grass grass){
        
        if(state.getPositionY() > grass.getLength()){
            return true;
        }else if(state.getPositionY() < 0){
            return true;
        }else if(state.getPositionX() > grass.getWidth() ){
            return true;
        }else if(state.getPositionX() < 0){
            return true;
        }
        
        return false;
    }
    
    /**
     * Check if a defined presumed state matches at case already occupied by other mower.
     * 
     * @param presumedState The presumed state.
     * @param states List of occupied state on the grass.
     * @return If the presumed state matches at least one state, then return true.
     * */
    private boolean isOccupiedState(State presumedState, List<State> states){
    	
    	for (State state : states) {
			if(presumedState.equalsPosition(state)){
				return true;
			}
		}
    	
    	return false;
    }

}
