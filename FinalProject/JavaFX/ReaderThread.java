import java.io.IOException;
import java.io.PipedInputStream;
import javafx.scene.control.TextArea;

public class ReaderThread implements Runnable {

  private final PipedInputStream pipeIn;
  private Thread reader;
  private boolean quit;

  private TextArea txtArea;

  ReaderThread(PipedInputStream pinInput, Thread reader1, boolean newflag, TextArea txtArea1) {
    pipeIn = pinInput;
    reader = reader1;
    quit = newflag;
    txtArea = txtArea1;

    this.quit = false;
    this.reader = new Thread(this);
    this.reader.setDaemon(true);
    this.reader.start();
  }

  public synchronized void run() {
    try {
      while (Thread.currentThread() == this.reader) {
        try {
          wait(100L);
        } catch (InterruptedException ie) {
          System.out.println("Interrupted thread");
        }

        if (this.pipeIn.available() != 0) {
          String input = readLine(this.pipeIn);
          this.txtArea.appendText(input);
        }
        if (this.quit)
          return;
      }
    } catch (Exception e) {
    }
  }

  public synchronized String readLine(PipedInputStream in) throws IOException {
    String input = "";
    do {
      int available = in.available();
      if (available == 0){
        break;
      }
      byte[] b = new byte[available];
      in.read(b);
      input = input + new String(b, 0, b.length);
    } while ((!input.endsWith("\n")) && (!input.endsWith("\r\n")) && (!this.quit));
    return input;
  }

}