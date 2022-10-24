class Main{
    private static String outputFile = "MyData.txt";
    private static String inputFile = "InputData.txt";
    public static void main(String[] args){
       int rewritenLines =  MyData.convertDate(inputFile, outputFile);
       System.out.println("Number of rewriten dates: " + rewritenLines);
    }
}