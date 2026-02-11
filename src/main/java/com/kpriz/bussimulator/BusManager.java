package com.kpriz.bussimulator;

import java.util.ArrayList;

public class BusManager{

    public void GenerateStudents(ArrayList<Student> st, int n){
        
        for(int i = 0; i < n; i++){            
            st.add(new Student(i));
        }
    }
    public Seat[][] runSim() {

        int stNum = 1 + (int)(Math.random() * ((48 - 1) + 1));
        //System.out.println("Generating Students..");
        ArrayList<Student> studentList = new ArrayList<>();

        GenerateStudents(studentList,stNum);
        System.out.println(studentList);

        //bus holds 48 ppl

        //for when the bus leaves the school

        Bus Route1 = new Bus();

        //System.out.println("Deciding Students..");
        
        for(Student s : studentList){

            if(!Route1.allWindowsTaken() && s.getStop() == 3){
                Route1.placeBack(s);
            }else if (!Route1.allWindowsTaken() && s.getStop() < 3) {
                Route1.placeWindow(s);
            }else if (Route1.allWindowsTaken() && s.getGender() != 'G') {
                Route1.placeEarliest(s);                
            }else if (Route1.allWindowsTaken() && s.getGender() == 'G') {
                Route1.placeGirl(s);
            }else{
                Route1.placeEarliest(s);
            }

            //if windows are open, choose the earliest window
            //if all windows taken and student is girl, choose earliest girl (if no girls sit earliest on bus)
            //if all windows taken choose earliest open
            //if windows open and stop 3 place latest (but favor girl?)

            //if stop 3, place latest
            //if girl

        }

        //Route1.displaySeats();

        return Route1.getArray();

    }
}