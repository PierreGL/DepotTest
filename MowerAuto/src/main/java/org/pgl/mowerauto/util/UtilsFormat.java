package org.pgl.mowerauto.util;

import org.pgl.mowerauto.entity.Mower;
import org.pgl.mowerauto.entity.State;

public class UtilsFormat {
    
    private UtilsFormat() {
    }

    /**
     * Method to provides final location formatted message from a mower.
     * 
     * @param mower The defined mower.
     * @return Formatted message concerning final location.
     * */
    public static String formatMsgFinalLocation(Mower mower) {
        Bundle bundle = Bundle.getInstance();
        
        State state = mower.getState();
        String orient = bundle.get(state.getOrientation().name());

        return String.format(
                bundle.get("MSG_FINAL_LOCATION"),
                mower.getId(), orient, state.getPositionX(),
                state.getPositionY());
    }
}
