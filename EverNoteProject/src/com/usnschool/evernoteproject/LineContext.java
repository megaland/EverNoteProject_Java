package com.usnschool.evernoteproject;

import java.io.Serializable;

public class LineContext implements Serializable{
	
	static final long serialVersionUID = 333333L;
    int x;
    int y;
    boolean state;

    public LineContext(int x, int y, boolean state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }
}