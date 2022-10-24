import java.util.ArrayList;

public class MyData {
        private int day;
        private int month;
        private int year;
        private String weekday;
        private String inputString;
        private final static int WEEKDAY_INDEX = 0;
        private final static int DATE_INDEX = 1;
        private final static int DAT_INDEX = 0;
        private  final static int MONTH_INDEX = 1;
        private final static int YEAR_INDEX = 2;
        private final static int DATE_SIZE = 3;
    
        private MyData(String dateString){
            this.inputString = dateString;
        }
       
        private void parseString(String input){
            String[] splitedString = input.split(" ");
            splitedString = arrangeWeekdayAndDate(splitedString);
            this.weekday = splitedString[WEEKDAY_INDEX];
            this.decodeDate(splitedString[DATE_INDEX]);
    
        }
        
        private static String[] arrangeWeekdayAndDate(String[] splitedString){
            String temp = new String();
            if(!splitedString[WEEKDAY_INDEX].matches("[a-zA-Z]+")){
                temp = splitedString[WEEKDAY_INDEX];
                splitedString[WEEKDAY_INDEX] = splitedString[DATE_INDEX];
                splitedString[DATE_INDEX] = temp;
                return splitedString;
            }
            return splitedString;
        }
    
        private void decodeDate(String date)throws Exception{
            String[] data = new String[DATE_SIZE];
            if(date.matches("../(.*)/....")){
                data = date.split("/");
                this.assignDateInformation(data[DAT_INDEX], data[MONTH_INDEX], data[YEAR_INDEX]);
            } else if(date.matches("....-..-..")){
                data = date.split("-");
               this.assignDateInformation(data[YEAR_INDEX], data[MONTH_INDEX], data[DAT_INDEX]);
            } else if(date.matches("..\\...\\.....")){
                data = date.split("\\.");
               this.assignDateInformation(data[DAT_INDEX], data[MONTH_INDEX], data[YEAR_INDEX]);
            } else{
                throw new Exception("Wrong format");
            }
        }
    
        private void assignDateInformation(String day, String month, String year){
            this.day = Integer.parseInt(day);
            this.month = Integer.parseInt(month);
            this.year = Integer.parseInt(year);
        } 
    
        public static int convertDate(String inputFile, String outputFile){
            ArrayList<String> lines = new ArrayList<String>();
    
            MyDataWriter.readLines(lines, inputFile);
    
            ArrayList<String> dates = new ArrayList<String>();
    
            for(int i = 0; i< lines.size() ; i++){
                MyData element = new MyData(lines.get(i));
                try{
                    element.parseString(element.inputString);
                }catch(Exception e){
                    System.out.println("Wrong format");
                    continue;
                }
                if(element.ableToWrite(dates)){
                    dates.add(element.returnDateString());
                }
            }
           int rewritenLines = MyDataWriter.writeLines(dates, outputFile);
           return rewritenLines;
        }
    
        private String returnDateString(){
            String dateString = "day = " + this.day + ", month = " + this.month + ", year = " + this.year + ", weekday = " + this.weekday + "\n";
            return dateString;
        }
    
        private boolean ableToWrite(ArrayList<String> dates){
            if(dates.contains(this.returnDateString())){
                return false;
            }
            return true;
        }
    }
