public class DifferentVectorsLengthException extends Exception {
    private int[] lengths;
	
    public int[] getLengths(){
        return lengths;
    }

	DifferentVectorsLengthException(int[] lengths, String message){
		super(message);
		this.lengths = lengths;
	}
}
