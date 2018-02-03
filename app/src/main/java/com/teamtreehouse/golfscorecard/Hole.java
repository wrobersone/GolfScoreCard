package com.teamtreehouse.golfscorecard;

public class Hole {
    private String mLabel;
    private int mStroke;

    public Hole(String label, int stroke) {
        mLabel = label;
        mStroke = stroke;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public int getStroke() {
        return mStroke;
    }

    public void setStroke(int stroke) {
        mStroke = stroke;
    }
}
