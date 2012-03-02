/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.smyt.jitgxt.client.Treemap;

/**
 * Visitor pattern implementation for tree map model
 * @author Ildar Musin
 */
public interface ITreemapVisitor {
    /**
     * Visit node
     * @param node - node to visit
     */
    void visit(TreemapModel node);
}
