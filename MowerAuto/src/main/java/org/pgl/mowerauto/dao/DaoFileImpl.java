package org.pgl.mowerauto.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.pgl.mowerauto.entity.Grass;
import org.pgl.mowerauto.entity.Instruction;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.Operation;
import org.pgl.mowerauto.entity.Sequence;
import org.pgl.mowerauto.entity.State;
import org.pgl.mowerauto.util.exception.IncorrectDataFileFormatException;
import org.pgl.mowerauto.util.exception.IncorrectDataSourceException;

public class DaoFileImpl implements Dao {

	public Operation getOperation(DataSource source) {
		if(!(source instanceof DataSourceFile)){
			throw new IncorrectDataSourceException("An incorrect DataSource has been passed to DAO.");
		}
		
		Operation result = null;
		
		DataSourceFile sourceFile = (DataSourceFile)source;
		File file = sourceFile.getFile();
		try(BufferedReader bfr = new BufferedReader(new FileReader(file))) {

			//Read the first line to define grass dimension.
			String headerLine = bfr.readLine();
			Grass grass = parseHeaderLine(headerLine);
			result = new Operation(grass);
			
			//Read the rest of line, two by two to define mower position and instructions
			int nbLine = 1;
			
			String stateLine = bfr.readLine();

			while(stateLine != null){//If position line is null, the file is terminated.
				nbLine++;
				String instructionsLine = bfr.readLine();
				if(instructionsLine != null){
					nbLine++;
					State state = parseMowerStateLine(stateLine);
					List<Instruction> instructions = parseInstructionsLine(instructionsLine);
					
					Mower newMower = new Mower(state);
					
					Sequence sequence = new Sequence(newMower, instructions);
					result.addSequence(sequence);
				}else{//If there is not an instruction line while there is a position line, it is a problem
					throw new IncorrectDataFileFormatException("The mower defined at line "+nbLine+" has not instructions associated at next line.");
				}
			}
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();//TODO to log
		} catch (IOException ie) {
			ie.printStackTrace();//TODO to log
		}

		return result;
	}
	
	/**
	 * This method parse the header line which contains grass dimension and provide grass object.
	 * 
	 * @param headerLineThe first line of data file format.
	 * @return Return the grass object including dimensions.
	 * @exception IncorrectDataFileFormatException thrown if header line has incorrect format.
	 * */
	private Grass parseHeaderLine(String headerLine) throws IncorrectDataFileFormatException{
		String[] headerLineArray = headerLine.split(" ");

		if(headerLineArray.length != 2){
			throw new IncorrectDataFileFormatException("The first line of data file has to have 2 elements to define grass dimension.");
		}
		
		Grass result = null;

		String sAbs = headerLineArray[0];
		String sOrd = headerLineArray[1];
		try {
			int abs = Integer.parseInt(sAbs);
			int ord = Integer.parseInt(sOrd);
			result = new Grass(abs, ord);
		} catch (NumberFormatException ne) {
			throw new IncorrectDataFileFormatException("The first line of data file has to be composed by number to define grass dimension.", ne);
		}
		
		return result;
	}
	
	/**
	 * This method parse a mower state line which contains mower state information and provide state object.
	 * 
	 * @param mowerStateLine A mower state line.
	 * @return Return a state object matching at the line.
	 * @exception IncorrectDataFileFormatException thrown if state line has incorrect format.
	 * */
	private State parseMowerStateLine(String mowerStateLine) throws IncorrectDataFileFormatException {
		
		
		return null;
	}
	
	
	/**
	 * This method parse an instructions line which contains instructions information and provide list of instructions.
	 * 
	 * @param instructionsLine An instructions line.
	 * @return Return list of instructions.
	 * @exception IncorrectDataFileFormatException thrown if instructions line has incorrect format.
	 * */
	private List<Instruction> parseInstructionsLine(String instructionsLine) throws IncorrectDataFileFormatException {
		
		
		return null;
	}
}
