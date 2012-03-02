/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.smyt.jitgxt.client;

/**
 * Simple color class
 * @author Ildar Musin (c)2012
 */
public class Color {
    private int r;
    private int g;
    private int b;

    /**
     * Simple color data structure. Consists of three color components
     * represented by integer values in range between 0 and 255
     * @param red - red component
     * @param green - green component
     * @param blue - blue component
     */
    public Color(int red, int green, int blue) {
        this.r = red;
        this.g = green;
        this.b = blue;
    }
    
    /**
     * Get blue component
     * @return blue component value in range [0, 255]
     */
    public int b() {
        return b;
    }

    /**
     * Set blue component
     * @param b - blue component value in range [0, 255]
     */
    public void b(int b) {
        this.b = b;
    }

    public int g() {
        return g;
    }

    public void g(int g) {
        this.g = g;
    }

    public int r() {
        return r;
    }

    public void r(int r) {
        this.r = r;
    }
}
