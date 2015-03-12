package org.pgl.mowerauto.business;

import java.util.List;

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
    
    public MowerManagerImpl(){
        dao = new DaoFileImpl();//TODO use factory or injection
    }
    
    @Override
    public Operation loadOperation(DataSource source) {
        Operation operation = dao.getOperation();
        return operation;
    }

    @Override
    public void executeOperation(Operation operation) {
        
        Grass grass = operation.getGrass();
        List<Sequence> sequences = operation.getSequences();
        
        for (Sequence sequence : sequences) {
            executeSequence(grass, sequence);
        }       
    }
    
    @Override
    public void executeSequence(Grass grass, Sequence sequence){
        Mower mower = sequence.getMower();
        
        List<Instruction> instructions = sequence.getInstructions();
        
//        State lastState = mower.getState();
        for (Instruction instruction : instructions) {
            switch(instruction){
                case FORWARD:
                    State state = mower.presumeForward();
                    if(!isPossibleState(state, grass)){
                        //TODO WARN CASE OUT
                        mower.setState(state);
                    }else if(false){
                        //TODO WARN CASE CROSS MOWER (one mower go to other mower)
                    }else{
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
        
        displayState(mower);
    }
    
    @Override
    public void displayState(Mower mower) {
    	// TODO Auto-generated method stub
    	
    }
    
    /**
     * Check if the state is possible to a defined grass. A possible state has to be inside the grass.
     * 
     * @param state The presumed state.
     * @param grass The defined grass.
     * @return If the state is possible return true, else return false.
     * */
    private boolean isPossibleState(State state, Grass grass){
        
        if(state.getPositionY() > grass.getLength()){
            return false;
        }else if(state.getPositionY() < 0){
            return false;
        }else if(state.getPositionX() > grass.getWidth() ){
            return false;
        }else if(state.getPositionX() < 0){
            return false;
        }
        
        return true;
    }

}
