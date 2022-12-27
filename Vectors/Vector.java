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

    public static Vector addAll(ArrayList<Vector> vectors) {
        Vector sum = new Vector(vectors.get(0).getSize());
        for (Vector vector : vectors) {
            for(int i = 0; i < sum.getSize(); i++){
            sum.vector.set(i, sum.vector.get(i) + vector.vector.get(i));
            }
        }
        return sum;
    }

    public static Vector sumVectors(ArrayList<Vector> vectors) throws DifferentVectorsLengthException {
        int size = vectors.get(0).getSize();
        for (Vector vector : vectors) {
            if(vector.getSize() != size){
                int[] lengths = new int[vectors.size()];
				for(int i = 0; i < vectors.size(); i++){
                    lengths[i] = vectors.get(i).getSize();
                }
                throw new DifferentVectorsLengthException(lengths, "Different lengths of vectors");
            }
        }
        Vector sum = addAll(vectors);
        return sum;
    }
}
