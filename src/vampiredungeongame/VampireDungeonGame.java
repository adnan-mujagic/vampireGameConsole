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
public class VampireDungeonGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Dungeon d = new Dungeon(5,10,5,6);
        
        d.start();
    }
    
}
