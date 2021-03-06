package skinnerhstopwatch;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import static javafx.animation.Animation.Status.RUNNING;
import static javafx.animation.Animation.Status.STOPPED;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
/**
 *
 * @author Holt Skinner
 */
public class AnalogStopWatch {
    
    private double tickTimeInSeconds = 0.01; 
    private double angleDeltaPerSeconds = 6.0;
    
    private double secondsElapsed = 0;
    
    private Timeline timeline;
    private KeyFrame keyFrame;
    
    private StackPane rootContainer;
    private ImageView dialImageView;
    private ImageView handImageView;
    private Image dialImage;
    private Image handImage;
    private String dialImageName = "clockface.png";
    private String handImageName = "hand.png";
    
    private Label digitalClock;
    private Duration timeElapsed; //Time object constructed from milliseconds
       
    
    public AnalogStopWatch() {
        setupUI();
        setupTimer();
    }
    
    private void setupUI() {
        rootContainer = new StackPane();
        
        dialImageView = new ImageView();
        handImageView = new ImageView();    
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        handImage = new Image(getClass().getResourceAsStream(handImageName));
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);
        
        digitalClock = new Label("0:00.00");
        digitalClock.setFont(new Font("Sans-Serif", 40));
        
        rootContainer.getChildren().addAll(dialImageView, handImageView, digitalClock);
    }
    
    
    private void setupTimer() {
        boolean running = false;
        if (timeline != null) {
            if (timeline.getStatus() == Status.RUNNING) {
                running = true;
                timeline.stop();
            }
        }

        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            update();
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE); 
        
        if (running) {
            timeline.play();
        }
    }
    
    private void update() {
        secondsElapsed += tickTimeInSeconds;
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        handImageView.setRotate(rotation);
        
        digitalClock.setText(String.format("%02d:%02.2f",  
                            (int)(secondsElapsed/60),
                            secondsElapsed % 60));
         
    }
    
    public Parent getRootContainer() {
        return rootContainer;
    }
    
    public Double getWidth() {
        if (dialImage != null) return dialImage.getWidth();
        else return 0.0;
    }
    
    public Double getHeight() {
        if (dialImage != null) return dialImage.getHeight();
        else return 0.0;       
    }
    
    public void start() {
        if(timeline.getStatus() != RUNNING){
            timeline.play();
        }
    }
    
    public void stop() {
        if(timeline.getStatus()!= STOPPED){
            timeline.stop();
        }
    }
    
    public void reset() {
        stop();
        secondsElapsed = 0;
        handImageView.setRotate(0);
        digitalClock.setText("0:00.00");
    }
    
    public void setTickTimeInSeconds(Double tickTimeInSeconds) {
        this.tickTimeInSeconds = tickTimeInSeconds;
        setupTimer();
    }
    
    public boolean isRunning() {
        if (timeline != null) {
            if (timeline.getStatus() == Status.RUNNING) {
                return true;
            }
        } 
        return false;
    }
    
}

