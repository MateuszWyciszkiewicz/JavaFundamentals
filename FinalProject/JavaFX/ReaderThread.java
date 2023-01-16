import java.io.IOException;
import java.io.PipedInputStream;
import javafx.scene.control.TextArea;

public class ReaderThread implements Runnable{
    
    private final PipedInputStream pipeIn ;
    private final PipedInputStream pipeIn2 ;
    Thread errorThrower;
    private Thread reader;
    private Thread reader2;
    private boolean quit; 
    
    private TextArea txtArea;
    
    ReaderThread(PipedInputStream pinInput1, PipedInputStream pinInput2, Thread errorThrower1, Thread reader11, Thread reader22, boolean newflag, TextArea txtArea1)
    {
       pipeIn = pinInput1;
       pipeIn2 = pinInput2;
       errorThrower = errorThrower1;
       reader = reader11;
       reader2 = reader22;
       quit =  newflag;
       txtArea = txtArea1;   
       
    this.quit = false;
    this.reader = new Thread(this);
    this.reader.setDaemon(true);
    this.reader.start();

    this.reader2 = new Thread(this);
    this.reader2.setDaemon(true);
    this.reader2.start();

    this.errorThrower = new Thread(this);
    this.errorThrower.setDaemon(true);
    this.errorThrower.start();
    }        
    

  public synchronized void run()
  {
    try
    {
      while (Thread.currentThread() == this.reader) {
           
        try {
             wait(100L); 
        } 
        catch (InterruptedException ie) 
        {
            System.out.println("Interrupted thread 1");
        }
        
        if (this.pipeIn.available() != 0)
        {
          String input = readLine(this.pipeIn); 
          this.txtArea.appendText(input);
        }
        if (this.quit) return;
      }

      while (Thread.currentThread() == this.reader2) {
        try {
          wait(100L); 
        } 
        catch (InterruptedException ie) 
        {
        }
       
        if (this.pipeIn2.available() != 0)
        {
          String input = readLine(this.pipeIn2);
           this.txtArea.appendText(input);
        }

        if (this.quit) return; 

      }

    }
    catch (Exception e)
    {
    }

    if (Thread.currentThread() == this.errorThrower) {
      try {
        wait(200L); 
      } 
      catch (InterruptedException ie) {
      }      
    }
  }

  public synchronized String readLine(PipedInputStream in)
    throws IOException
  {
    String input = "";
    do
    {
      int available = in.available();
      if (available == 0) break;
      byte[] b = new byte[available];
      in.read(b);
      input = input + new String(b, 0, b.length);
    }while ((!input.endsWith("\n")) && (!input.endsWith("\r\n")) && (!this.quit));
    return input;
  }

}