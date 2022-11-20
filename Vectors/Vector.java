import java.util.ArrayList;

public class Vector {
    private ArrayList<Double> vector;
    
    public Vector(int size){
        ArrayList<Double> newVector = new ArrayList<Double>();
        for(int i = 0; i < size; i++){
            newVector.add(0.0);
        }
        vector = newVector;
    }
    public Vector(String in){
        String[] values = in.split(" ");
        ArrayList<Double> newVector = new ArrayList<Double>();
        for(String value : values){
            try{
                newVector.add(Double.parseDouble(value));
            } catch (NumberFormatException e){}

        }
        vector = newVector;
    }

    public ArrayList<Double> getVector(){
        return vector;
    } 

    public int getSize(){
        return vector.size();
    }

    public void add(Vector v) throws DifferentVectorsLengthException {
        if(getSize() != v.getSize()){
            throw new DifferentVectorsLengthException("Different lengths of vestors", this.getSize(), v.getSize());
        }
        for(int i = 0; i < getSize(); i++){
            vector.set(i, this.vector.get(i) + v.vector.get(i));
        }
    }

    public static Vector sumVectors(ArrayList<Vector> vectors) throws DifferentVectorsLengthException {
        Vector sum = new Vector(vectors.get(0).getSize());
        for (Vector vector : vectors) {
            sum.add(vector);
        }
        return sum;
    }
}
