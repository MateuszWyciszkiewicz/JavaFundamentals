import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ConsoleTextArea extends Application {

  private final PipedInputStream pipeIn = new PipedInputStream();
  Thread errorThrower;
  private Thread reader;
  boolean quit;
  private TextArea txtArea;

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("ControllerFXML.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

    txtArea = ControllerFXML.staticTxtArea;

    executeReaderThreads();
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent e) {

        closeThread();
        Platform.exit();
        System.exit(0);
      }
    });

  }

  synchronized void closeThread() {
    System.out.println("Message: Stage is closed.");
    this.quit = true;
    notifyAll();
    try {
      this.reader.join(1000L);
      this.pipeIn.close();
    } catch (Exception e) {
      System.out.println("Exception during closing thread");
      System.exit(1);
    }
    System.exit(0);
  }

  public static void main(String[] args) {
    launch(args);
  }

  public void executeReaderThreads() {
    try {
      PipedOutputStream pout = new PipedOutputStream(this.pipeIn);
      System.setOut(new PrintStream(pout, true));
    } catch (IOException io) {
      System.out.println("IOException occured");
    }
    ReaderThread obj = new ReaderThread(pipeIn, errorThrower, reader, quit, txtArea);

  }

}