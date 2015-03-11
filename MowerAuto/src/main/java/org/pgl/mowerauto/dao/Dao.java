package org.pgl.mowerauto.dao;

import org.pgl.mowerauto.entity.Operation;

public interface Dao {

    Operation getOperation(DataSource source);
}
