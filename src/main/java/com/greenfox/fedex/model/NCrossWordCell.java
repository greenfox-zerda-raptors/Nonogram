package com.greenfox.fedex.model;

/**
 * Created by Viktor on 2017.01.26..
 */
public class NCrossWordCell {

    public static final int OFF = 0;
    public static final int ON = 1;
    public static final int EMPTY = 2;
    public static final int DONT_CARE = 3;

    private int state;

    public NCrosswordCell() {
        state = EMPTY;
    }

    public NCrosswordCell(int state) {
        this.state = state;
    }

    public int getState() { return state; }
    public void setState(int state) { this.state = state; }

    public void setON() { state = ON; }
    public void setOFF() { state = OFF; }
    public void setEMPTY() { state = EMPTY; }
    public void setDONTCARE() { state = DONT_CARE; }

    public boolean isON() { return state == ON; }
    public boolean isOFF() { return state == OFF; }

    public void switchOnOff() {
        switch (state) {
            case OFF: state = ON; break;
            case ON: state = OFF; break;
            default: state = ON;
        }
    }

    public void switchEmptyDontcare() {
        switch (state) {
            case EMPTY: state = DONT_CARE; break;
            case DONT_CARE: state = EMPTY; break;
            default: state = EMPTY;
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof NCrossWordCell) && (((NCrossWordCell)obj).getState() == state);
    }

}

