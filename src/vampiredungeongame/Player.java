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
public class Player {
    private char sign;
    private int x;
    private int y;
    public Player(){
        this.sign='@';
        this.x = 0;
        this.y = 0;
        
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public char getSign(){
        return this.sign;
    }
    
    public void setX(int num){
        this.x=num;
    }
    
    public void setY(int num){
        this.y=num;
    }

    @Override
    public String toString() {
        return this.sign+": "+this.getX()+","+this.getY(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
