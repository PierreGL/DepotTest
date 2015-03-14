package org.pgl.mowerauto.util.exception;

import org.pgl.mowerauto.Application;
import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.State;

public class UtilsFormat {

	public static String formatMsgFinalLocation(Mower mower){
		State state = mower.getState();
		String orient = Application.bundle.getString(state.getOrientation().name());
		String msg = String.format(Application.bundle.getString("MSG_FINAL_LOCATION"), 
				mower.getId(), orient, state.getPositionX(), state.getPositionY());
		return msg;
	}
}
