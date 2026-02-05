public class Seat {
    private boolean taken;
    private Student s;

    public Seat(){
        this. taken = false;
    }

    public boolean isTaken(){
        return taken;
    }

    public boolean isGirl(){
        //if(s == null) return true;      
        return (s.getGender()=='G');
    }

    public void setTaken(Student s){
        this.taken = true;
        this.s = s;
    }

    public Student getStudent(){
        if (s != null) 
            return s;
        else 
            return null;
    }
    
}
