/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vampiredungeongame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Paprika
 */
public class Dungeon {
    
    private int numOfMoves;
    private int height;
    private int widht;
    private int numOfVamps;
    private Random rand = new Random();
    private char[][] array;
    private ArrayList<Vampire> vampires;
    private Player player;
    
    public Dungeon(int height, int width, int numberOfVampires, int numberOfMoves){
        this.numOfMoves=numberOfMoves;
        this.height=height;
        this.widht=width;
        this.numOfVamps=numberOfVampires;
        this.array= new char[this.height][this.widht];
        this.vampires=new ArrayList<Vampire>();
        this.player=new Player();
        //Here we will initialize the vampires in the dungeon
        for(int i=0;i<this.numOfVamps;i++){
            createNewVampire();
        }
    }
    
    public void generateDungeon(){
        
        for(int i=0;i<this.height;i++){
            for(int j=0;j<this.widht;j++){
                this.array[i][j]='.';
            }
        }
        
        this.array[player.getY()][player.getX()]=player.getSign();
        for(Vampire v: this.vampires){
            int x = v.getX();
            int y = v.getY();
            this.array[y][x]=v.getSign();
        }
    }
    
    public void start(){
        Scanner rd = new Scanner(System.in);
        generateDungeon();
        
        while(true){
            printDungeon();
            System.out.println("What is your move?");
            String userInput=rd.nextLine();
            move(userInput);
            updateDungeon();
            if(vampires.size()==0){
                System.out.println("AHH, that was the last one of them... You are safe now. Congratulations!");
                break;
            }
            else if(this.numOfMoves==0){
                System.out.println("Oh no... Your battery is too low. Vampires will eat you now.");
                break;
            }
            vampireMoves();
            updateDungeon();
            
            
        }
    }
    
    public void updateDungeon(){
        //First we check if any of the vampires are in the same position as the player

        
        //Then reset all the previous positions
        for(int i=0;i<this.height;i++){
            for(int j=0;j<this.widht;j++){
                array[i][j]='.';
            }
        }
        //This just checks if any of the vampires are in the same position as the player and if
        //they are it just removes them from the array.
        //For some reason the for each loop didn't work here so I tried this way and it worked!
        for(int i=0;i<vampires.size();i++){
            if(vampires.get(i).getX()==player.getX() && vampires.get(i).getY()==player.getY()){
                vampires.remove(i);
            }
        }
        
        this.array[player.getY()][player.getX()]=player.getSign();
        for(Vampire v: vampires){
            int x = v.getX();
            int y = v.getY();
            array[y][x]=v.getSign();
        }
        
    }
    
    public void printDungeon(){
        System.out.println("Battery life remaining:"+this.numOfMoves);
        System.out.println(this.player);
        for(Vampire v: this.vampires){
            System.out.println(v);
        }
        for(int i=0;i<this.height;i++){
            for(int j=0;j<this.widht;j++){
                System.out.print(this.array[i][j]);
            }
            System.out.println("");
        }
        
    }
    
    public void move(String move){
        for(int i=0;i<move.length();i++){
            if(move.charAt(i)=='w'){
                if(player.getY()-1>=0){
                    player.setY(player.getY()-1);
                }
            }
            else if(move.charAt(i)=='s'){
                if(player.getY()+1<this.height){
                    player.setY(player.getY()+1);
                }
            }
            else if(move.charAt(i)=='a'){
                if(player.getX()-1>=0){
                    player.setX(player.getX()-1);
                }
            }
            else if(move.charAt(i)=='d'){
                if(player.getX()+1<this.widht){
                    player.setX(player.getX()+1);
                }
            }
        }
        this.numOfMoves--;
    }
    
    public void createNewVampire(){
        int x=this.rand.nextInt(this.widht);
        int y=this.rand.nextInt(this.height);
        if(positionValid(x,y)){
            this.vampires.add(new Vampire(x,y));
        }
        else{
            createNewVampire();
        }
        
    }
    
    public boolean positionValid(int x, int y){
        for(Vampire v : this.vampires){
            if(v.getX()==x && v.getY()==y){
                return false;
            }
        }
        if(this.player.getX()==x && this.player.getY()==y){
            return false;
        }
        else{
            return true;
        }
    }
    
    public void vampireMoves(){
        //Gets a random vampire's id who will make a move
        int vampireID = this.rand.nextInt(vampires.size());
        //List of possible moves
        String[] moves = {"up","down","left","right"};
        //gets one random move from the possible moves
        String move = moves[this.rand.nextInt(moves.length-1)];
        if(move.equals("up")){
            if(vampires.get(vampireID).getY()-1>=0 && positionValid(vampires.get(vampireID).getX(),vampires.get(vampireID).getY()-1)){
                vampires.get(vampireID).setY(vampires.get(vampireID).getY()-1);
            }
        }
        else if(move.equals("down")){
            if(vampires.get(vampireID).getY()+1<this.height && positionValid(vampires.get(vampireID).getX(), vampires.get(vampireID).getY()+1)){
                vampires.get(vampireID).setY(vampires.get(vampireID).getY()+1);
            }
        }
        else if(move.equals("left")){
            if(vampires.get(vampireID).getX()-1>=0 && positionValid(vampires.get(vampireID).getX()-1, vampires.get(vampireID).getY())){
                vampires.get(vampireID).setX(vampires.get(vampireID).getX()-1);
            }
        }
        else if(move.equals("right")){
            if(vampires.get(vampireID).getX()+1<this.widht && positionValid(vampires.get(vampireID).getX()+1, vampires.get(vampireID).getY())){
                vampires.get(vampireID).setX(vampires.get(vampireID).getX()+1);
            }
        }
    }
    
    
}
