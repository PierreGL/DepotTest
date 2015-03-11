package org.pgl.mowerauto.entity;

/**
 * Abstract representation of a grass.
 * */
public class Grass {
    
    /**
     * The width of grass. Matches with abscissa of surface.
     * */
    private int width;
    
    /**
     * The length of grass. Matches with ordinate of surface.
     * */
    private int length;

    public Grass(int width, int length) {
        super();
        this.width = width;
        this.length = length;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getLength() {
        return length;
    }

}
