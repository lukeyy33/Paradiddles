package com.lukewaugh.paradiddles.metronome;



/*
An Enum defining the different note values.
Value 4 is a Quarter note
Value 8 is an Eighth Note
Value 16 is an Sixteenth Note
*/
public enum NoteValues {
    four("4"),
    eight("8"),
    sixteen("16");

    private String noteValue;

    NoteValues(String noteValue) {
        this.noteValue = noteValue;
    }

    @Override public String toString() {
        return noteValue;
    }
}
