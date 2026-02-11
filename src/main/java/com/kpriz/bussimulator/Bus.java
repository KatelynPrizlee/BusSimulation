package com.kpriz.bussimulator;
public class Bus {

    private Seat[][] seats;

    public Bus(){
        this.seats = new Seat[12][4];

        for(int r = 0; r < 12; r++){
            for(int c = 0; c < 4; c ++){
                this.seats[r][c] = new Seat();
            } 
        }

    }

    public boolean isRowFull(int row){
        return  seats[row][0].isTaken() && 
                seats[row][1].isTaken() && 
                seats[row][2].isTaken() &&
                seats[row][3].isTaken();
    }

    public boolean windowOpen(int row){
        return (! (seats[row][0].isTaken() && seats[row][3].isTaken()) ); //if either are false
    }

    public boolean allWindowsTaken(){
        for(int i = 0; i< 12; i++){
            if(windowOpen(i))
                return false;
        }
        return true;
    }

    public void placeWindow(Student s){ //windows are available
        for(int i = 0; i < 12; i++){
            if(isRowFull(i)) continue;
            if(!seats[i][0].isTaken()){
                seats[i][0].setTaken(s);
                return;
            }else if (!seats[i][3].isTaken()){
                seats[i][3].setTaken(s);
                return;
            }

        }
    }

    public void placeGirl(Student s){ //guarantee that all the windows are taken and s is a girl
        for(int i = 0; i < 12; i++){
            if(isRowFull(i)) continue;
            if(seats[i][0].isGirl() && !seats[i][1].isTaken()){
                seats[i][1].setTaken(s);
                return;
            }
            else if (seats[i][3].isGirl() && !seats[i][2].isTaken()){
                seats[i][2].setTaken(s);
                return;
            }
        }
        placeEarliest(s); //if no girls to sit next to..
    }

    public boolean isGirlLeft(int row){
        return (seats[row][0].isGirl());
    }

    public boolean isGirlRight(int row){
        return (seats[row][3].isGirl());
    }

    public void placeEarliest(Student st){ //place is nearest non taken spot, look at windows first then look left to right
        for(int i = 0; i < 12; i++){
            if(!isRowFull(i)){
                if(!seats[i][0].isTaken()){
                    seats[i][0].setTaken(st);
                }
                else if (!seats[i][3].isTaken()) {
                    seats[i][3].setTaken(st);
                    
                }
                else if (!seats[i][1].isTaken()){
                    seats[i][1].setTaken(st);
                    
                }
                else{
                    seats[i][2].setTaken(st);
                    
                }
                return;
            }

        }
    }

    public void placeBack(Student st){ //want to sit towards the back
        for(int i = 11; i > -1; i--){
            if(!isRowFull(i)){
                if(!seats[i][0].isTaken()){
                    seats[i][0].setTaken(st);
                }
                else if (!seats[i][3].isTaken()) {
                    seats[i][3].setTaken(st);
                }
                else if (!seats[i][1].isTaken()){
                    seats[i][1].setTaken(st);
                }
                else{
                    seats[i][2].setTaken(st);
                }
                return;
            }

        }
    }

    public void displaySeats(){
        System.out.println("------Front------");
        for(int i = 0; i<12; i++){
            System.out.print(seats[i][0].getStudent());
            System.out.print(seats[i][1].getStudent());
            System.out.print("  "); 
            System.out.print(seats[i][2].getStudent());
            System.out.print(seats [i][3].getStudent());
            System.out.println();
        }
        System.out.println("------Back------");
    }

    public Seat[][] getArray(){
        return seats;
    }

}
