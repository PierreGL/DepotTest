package org.pgl.mowerauto.util;

import org.pgl.mowerauto.Application;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.State;

public class UtilsFormat {

	/**
	 * Method to provides final location formatted message from a mower.
	 * 
	 * @param mower The defined mower.
	 * @return Formatted message concerning final location.
	 * */
	public static String formatMsgFinalLocation(Mower mower){
		State state = mower.getState();
		String orient = Application.bundle.getString(state.getOrientation().name());
		String msg = String.format(Application.bundle.getString("MSG_FINAL_LOCATION"), 
				mower.getId(), orient, state.getPositionX(), state.getPositionY());
		return msg;
	}
}
