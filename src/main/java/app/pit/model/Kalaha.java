package app.pit.model;

import app.pit.exception.MethodNotAllowedException;

public class Kalaha extends Pit {

    @Override
    public Integer getOpposite() {
        throw new UnsupportedOperationException("Kalaha is considered as large and has no opposite");
    }
}
