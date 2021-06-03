package dev.omaremara.editor.util;

import dev.omaremara.editor.Main;
import dev.omaremara.editor.view.View;
import javafx.stage.Stage;

public class ViewUtil {
  public static void setSceneRoot(View view) {
    Stage stage = Main.primaryStage;
    stage.getScene().setRoot(view.getRoot());
  }
}
