public class DifferentVectorsLengthException extends Exception {
    private int modelLength;
    private int insertedLength;

    DifferentVectorsLengthException(String message, int modelLength, int insertedLength) {
        super(message);
        this.modelLength = modelLength;
        this.insertedLength = insertedLength;
    }

    public int getModelLength() {
        return modelLength;
    }

    public int getInsertedLength() {
        return insertedLength;
    }

    public void printException(){
        this.printStackTrace();
        System.out.println(this.getMessage() + ", addition of vectors with length: " + modelLength + " and " + insertedLength);
    }
}
