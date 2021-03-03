/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vampiredungeongame;

/**
 *
 * @author Paprika
 */
public class Vampire {
    private char sign;
    private int x;
    private int y;
    
    public Vampire(int x, int y){
        this.sign='v';
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSign() {
        return sign;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return this.sign+": "+this.x+","+this.y;
    }
    
    
}
