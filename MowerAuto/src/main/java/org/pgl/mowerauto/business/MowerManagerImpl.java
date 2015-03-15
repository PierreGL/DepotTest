package org.pgl.mowerauto.business;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.mowerauto.Application;
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
import org.pgl.mowerauto.util.UtilsFormat;
import org.pgl.mowerauto.util.exception.UnknownInstructionException;

/**
 * Implementation of business logic.
 * */
public class MowerManagerImpl implements MowerManager {
	
	private static Logger logger = LogManager.getLogger(MowerManagerImpl.class);

	private Dao dao;

	private Grass grass;

	/**
	 * The set of waiting mowers, located on the grass.
	 * */
	private Queue<Sequence> waitingSequences;

	public MowerManagerImpl(){
		DaoFactory factory = new DaoFactoryFile();
		this.dao = factory.getDao();
	}

	@Override
	public void loadOperation(DataSource source) {
		Operation operation = this.dao.getOperation(source);
		
		this.grass = operation.getGrass();
		List<Sequence> lstSequence = operation.getSequences();
		this.waitingSequences = new ArrayBlockingQueue<>(lstSequence.size());//new ArrayList<>();

		for (Sequence sequence : lstSequence) {
			Mower newMower = sequence.getMower();
			State initialState = newMower.getState();

			if(this.grass.isOutsideState(initialState)){//New mower cannot be located outside the grass
				String msg = String.format(Application.bundle.getString("MSG_INITIAL_OUT"), newMower.getId());
				UiManager.display(msg);
			}else if(this.grass.isOccupiedState(initialState)){//New mower cannot be located on other mower.
				String msg = String.format(Application.bundle.getString("MSG_INITIAL_OCCUPIED"), newMower.getId());
				UiManager.display(msg);
			}else{//The mower can be added on grass.
				this.waitingSequences.add(sequence);
				this.grass.addOccupiedState(initialState);		
			}
		}
		
		logger.info("Data Source loaded");
	}

	@Override
	public void executeOperation() { 
		logger.info("Execute loaded operation.");

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
				State presumedState = mower.presumeForward();
				//Two business test : future state must not be out of grass or on occupied state.
				if(this.grass.isOutsideState(presumedState)){
					String msg = String.format(Application.bundle.getString("MSG_MOVE_OUT"), mower.getId());
					UiManager.display(msg);
				}else if(this.grass.isOccupiedState(presumedState)){
					String msg = String.format(Application.bundle.getString("MSG_MOVE_OCCUPIED"), mower.getId());
					UiManager.display(msg);
				}else{
					this.grass.removeOccupiedState(mower.getState());//Remove the previous occupied state.
					this.grass.addOccupiedState(presumedState);//Indicates new state is occupied.
					mower.setState(presumedState);//Update mower state.
				}
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
	
	@Override
	public void displayFinalState(Mower mower) {
		String msg = UtilsFormat.formatMsgFinalLocation(mower);
		UiManager.display(msg);		
	}

	@Override
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	

}
