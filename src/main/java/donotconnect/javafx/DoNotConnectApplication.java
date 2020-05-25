// CHECKSTYLE:OFF
package donotconnect.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DoNotConnectApplication extends Application {

    private FXMLLoader fxmlLoader = new FXMLLoader();

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("Starting application...");
        fxmlLoader.setLocation(getClass().getResource("/fxml/launch.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Don't Connect Stones");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
