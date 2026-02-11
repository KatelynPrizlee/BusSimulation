public class Student {
    private int Stop; //which stop is this person going to get off at? 1,2,3
    private char gender; //B for Boy, G for Girl
    private int IDnum; //to identify

    public Student(int IDnum){
        this.Stop = genNumber(1,3);
        if(genNumber(0, 1) == 0)
            this.gender = 'B';
        else
            this.gender = 'G';
        this.IDnum = IDnum;
    }

    private int genNumber(int min, int max){ //generate number based on min and max
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    @Override
    public String toString(){
        return "[" + this.IDnum + " : " +  this.Stop + " : " + this.gender + "]";
    }

    public int getStop(){
        return Stop;
    }

    public char getGender(){
        return gender;
    }

    public int getID(){
        return IDnum;
    }

}
