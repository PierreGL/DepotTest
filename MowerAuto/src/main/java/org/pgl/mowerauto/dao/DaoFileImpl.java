package org.pgl.mowerauto.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.mowerauto.entity.Grass;
import org.pgl.mowerauto.entity.Instruction;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.Operation;
import org.pgl.mowerauto.entity.Orientation;
import org.pgl.mowerauto.entity.Sequence;
import org.pgl.mowerauto.entity.State;
import org.pgl.mowerauto.util.Bundle;
import org.pgl.mowerauto.util.exception.FunctionnalException;
import org.pgl.mowerauto.util.exception.IncorrectDataFileFormatException;
import org.pgl.mowerauto.util.exception.IncorrectDataSourceException;

/**
 * This is the DAO implementation to data source file.
 * */
public class DaoFileImpl implements Dao {

    private static final Logger LOGGER = LogManager
            .getLogger(DaoFileImpl.class);

    private Bundle bundle;

    /**
     * Default scope to protect access. To instanciate Dao impl, use DaoFactory.
     * */
    DaoFileImpl() {
    }

    @Override
    public Operation getOperation(DataSource source) {

        if (!(source instanceof DataSourceFile)) {
            throw new IncorrectDataSourceException(
                    "An incorrect DataSource has been passed to DAO.");
        }

        DataSourceFile sourceFile = (DataSourceFile) source;

        File file = sourceFile.getFile();

        return parseFile(file);
    }

    /**
     * This method parse a complete file to get the matching operation.
     * 
     * @param file
     *            The file object.
     * @return The Operation described by file.
     * @exception IncorrectDataFileFormatException
     *                thrown if the file has an incorrect format.
     * */
    private Operation parseFile(File file) {
        LOGGER.debug("Parsing file starting.");

        Operation result = null;

        try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {

            // Read the first line to define grass dimension.
            String headerLine = bfr.readLine();
            Grass grass = parseHeaderLine(headerLine);
            result = new Operation(grass);

            // Read the rest of line, two by two to define mower position and
            // instructions
            int nbLine = 1;

            String stateLine = bfr.readLine();

            while (stateLine != null) {// If position line is null, the file is
                // terminated.
                nbLine++;
                State state = parseMowerStateLine(stateLine, nbLine);

                String instructionsLine = bfr.readLine();
                if (instructionsLine != null) {
                    nbLine++;
                    List<Instruction> instructions = parseInstructionsLine(
                            instructionsLine, nbLine);

                    Mower newMower = new Mower(state);

                    Sequence sequence = new Sequence(newMower, instructions);
                    result.addSequence(sequence);
                } else {// If there is not an instruction line while there is a
                    // position line, it is a problem
                    String msg = String.format(bundle.get("MSG_FORMAT_MISSING_INSTRUCTION"), nbLine);
                    throw new IncorrectDataFileFormatException(msg);
                }

                stateLine = bfr.readLine();
            }
        } catch (FileNotFoundException fe) {
            throw new FunctionnalException(fe);
        } catch (IOException ie) {
            LOGGER.error(ie);
        }

        return result;
    }

    /**
     * This method parse the header line which contains grass dimension and
     * provide grass object.
     * 
     * @param headerLineThe
     *            first line of data file format.
     * @return Return the grass object including dimensions.
     * @exception IncorrectDataFileFormatException
     *                thrown if header line has incorrect format.
     * */
    private Grass parseHeaderLine(String headerLine) {
        String regex = "[0-9]+ [0-9]+";
        Pattern p = Pattern.compile(regex);
        if (!p.matcher(headerLine).matches()) {
            String msg = String.format(bundle.get("MSG_FORMAT_ERROR_HEADER"));
            throw new IncorrectDataFileFormatException(msg);
        }

        String[] headerLineArray = headerLine.split(" ");

        String sAbs = headerLineArray[0];
        String sOrd = headerLineArray[1];

        int width = Integer.parseInt(sAbs);
        int length = Integer.parseInt(sOrd);

        return new Grass(width, length);
    }

    /**
     * This method parse a mower state line which contains mower state
     * information and provide state object.
     * 
     * @param mowerStateLine A mower state line.
     * @param nbLine Number of line concerned.
     * @return Return a state object matching at the line.
     * @exception IncorrectDataFileFormatException
     *                thrown if state line has incorrect format.
     * */
    private State parseMowerStateLine(String mowerStateLine, int nbLine) {
        String regex = "[0-9]+ [0-9]+ [N,S,E,W]";

        Pattern p = Pattern.compile(regex);
        if (!p.matcher(mowerStateLine).matches()) {
            String msg = String.format(bundle.get("MSG_FORMAT_ERROR_STATE_LINE"), nbLine);
            throw new IncorrectDataFileFormatException(msg);
        }

        String[] mowerStateLineArray = mowerStateLine.split(" ");

        State result = null;

        String sAbs = mowerStateLineArray[0];
        String sOrd = mowerStateLineArray[1];
        String sOrient = mowerStateLineArray[2];

        Orientation orient = null;
        int abs = Integer.parseInt(sAbs);
        int ord = Integer.parseInt(sOrd);

        switch (sOrient) {
        case "N":
            orient = Orientation.NORTH;
            break;
        case "E":
            orient = Orientation.EAST;
            break;
        case "W":
            orient = Orientation.WEST;
            break;
        case "S":
            orient = Orientation.SOUTH;
            break;
        default:
            throw new IncorrectDataFileFormatException();
        }

        result = new State(orient, abs, ord);

        return result;
    }

    /**
     * This method parse an instructions line which contains instructions
     * information and provide list of instructions.
     * 
     * @param instructionsLine
     *            An instructions line.
     * @return Return list of instructions.
     * @exception IncorrectDataFileFormatException
     *                thrown if instructions line has incorrect format.
     * */
    private List<Instruction> parseInstructionsLine(String instructionsLine,
            int nbLine) {
        String regex = "[A,G,D]+";
        Pattern p = Pattern.compile(regex);
        if (!p.matcher(instructionsLine).matches()) {
            String msg = String.format(bundle.get("MSG_FORMAT_ERROR_INSTR_LINE"), nbLine);
            throw new IncorrectDataFileFormatException(msg);
        }

        char[] instructionsArray = instructionsLine.toCharArray();

        List<Instruction> instructionList = new ArrayList<>();
        for (char c : instructionsArray) {
            switch (c) {
            case 'A':
                instructionList.add(Instruction.FORWARD);
                break;
            case 'G':
                instructionList.add(Instruction.LEFT);
                break;
            case 'D':
                instructionList.add(Instruction.RIGHT);
                break;
            default:
                throw new IncorrectDataFileFormatException();
            }
        }

        return instructionList;
    }
}
