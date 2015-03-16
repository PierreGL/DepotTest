package org.pgl.mowerauto.business;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.mowerauto.dao.Dao;
import org.pgl.mowerauto.dao.DaoFactory;
import org.pgl.mowerauto.dao.DaoFactoryFile;
import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.entity.Grass;
import org.pgl.mowerauto.entity.Instruction;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.Operation;
import org.pgl.mowerauto.entity.Sequence;
import org.pgl.mowerauto.entity.State;
import org.pgl.mowerauto.ui.UiManager;
import org.pgl.mowerauto.util.Bundle;
import org.pgl.mowerauto.util.UtilsFormat;
import org.pgl.mowerauto.util.exception.UnknownInstructionException;

/**
 * Implementation of business logic.
 * */
public class MowerManagerImpl implements MowerManager {

    private static final Logger LOGGER = LogManager.getLogger(MowerManagerImpl.class);

    private Bundle bundle;

    private UiManager uiManager;

    private Dao dao;

    private Grass grass;

    /**
     * The set of waiting mowers, located on the grass.
     * */
    private Queue<Sequence> waitingSequences;

    public MowerManagerImpl(){
        this.bundle = Bundle.getInstance();
        this.uiManager = UiManager.getInstance();
        DaoFactory factory = new DaoFactoryFile();
        this.dao = factory.getDao();
    }

    @Override
    public void loadOperation(DataSource source) {
        Operation operation = this.dao.getOperation(source);
        this.grass = operation.getGrass();
        List<Sequence> lstSequence = operation.getSequences();
        this.waitingSequences = new ArrayBlockingQueue<>(lstSequence.size());

        for (Sequence sequence : lstSequence) {
            Mower newMower = sequence.getMower();
            State initialState = newMower.getState();

            if(this.grass.isOutsideState(initialState)){//New mower cannot be located outside the grass
                String msg = String.format(bundle.get("MSG_INITIAL_OUT"), newMower.getId());
                uiManager.display(msg);
            }else if(this.grass.isOccupiedState(initialState)){//New mower cannot be located on other mower.
                String msg = String.format(bundle.get("MSG_INITIAL_OCCUPIED"), newMower.getId());
                uiManager.display(msg);
            }else{//The mower can be added on grass.
                this.waitingSequences.add(sequence);
                this.grass.addOccupiedState(initialState);		
            }
        }

        LOGGER.info("Data Source loaded");
    }

    @Override
    public void executeOperation() { 
        LOGGER.info("Execute loaded operation.");

        if(this.grass == null || this.waitingSequences == null){
            throw new IllegalStateException("Probably the execute method has been called before loadOperation.");
        }

        for (Sequence sequence : this.waitingSequences) {
            Mower updatedMower = executeSequence(this.grass, sequence);
            displayFinalState(updatedMower);
        } 
    }

    @Override
    public Mower executeSequence(Grass grass, Sequence sequence){
        Mower mower = sequence.getMower();

        List<Instruction> instructions = sequence.getInstructions();

        for (Instruction instruction : instructions) {
            switch(instruction){
            case FORWARD:
                this.tryForward(mower);//Checks if mower can advance.
                break;
            case RIGHT:
                mower.turnRight();
                break;
            case LEFT:
                mower.turnLeft();
                break;
            default:
                throw new UnknownInstructionException();
            }
        }
        return mower;
    }

    /**
     * Checks if the mower can forward, if yes : move the mower else does not move and displays message to user.
     * 
     * @param mower The mower concerned.
     * */
    private void tryForward(Mower mower){
        State presumedState = mower.presumeForward();
        //Two business test : future state must not be out of grass or on occupied state.
        if(this.grass.isOutsideState(presumedState)){
            String msg = String.format(bundle.get("MSG_MOVE_OUT"), mower.getId());
            uiManager.display(msg);
        }else if(this.grass.isOccupiedState(presumedState)){
            String msg = String.format(bundle.get("MSG_MOVE_OCCUPIED"), mower.getId());
            uiManager.display(msg);
        }else{
            this.grass.removeOccupiedState(mower.getState());//Remove the previous occupied state.
            State newState = mower.forward();
            this.grass.addOccupiedState(newState);//Indicates new state is occupied.
        }
    }

    @Override
    public void displayFinalState(Mower mower) {
        String msg = UtilsFormat.formatMsgFinalLocation(mower);
        uiManager.display(msg);		
    }

    @Override
    public void setDao(Dao dao) {
        this.dao = dao;
    }


}
