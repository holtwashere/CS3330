package skinnerhrelaxatron;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author HS047694
 */
public class IndexController extends ViewController {
    
    @FXML
    Button puppiesButton;
    
    @FXML
    Button rainButton;
    
    @FXML
    Button encouragementButton;
   
    @FXML
    public void goToPuppiesPage() throws Exception {
        try {
           ViewController.switchTo("Puppy");
           PuppyController controller = (PuppyController)getControllerByName("Puppy");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    @FXML
    public void goToRainDropsPage() throws Exception {
        try {
            ViewController.switchTo("RainDrops");
            RainDropsController controller = (RainDropsController)getControllerByName("RainDrops");
            controller.mediaPlayer.play();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    @FXML
    public void goToEncouragementPage() throws Exception {
        try {
            ViewController.switchTo("Encouragement");
            EncouragementController controller = (EncouragementController)getControllerByName("Encouragement");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
