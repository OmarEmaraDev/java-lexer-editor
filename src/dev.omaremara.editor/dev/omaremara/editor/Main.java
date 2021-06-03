package dev.omaremara.editor;

import dev.omaremara.editor.model.Token;
import dev.omaremara.editor.util.ViewUtil;
import dev.omaremara.editor.view.TokensView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Main extends Application {
  public static Stage primaryStage;
  public static ObservableList<Token> tokens =
      FXCollections.observableArrayList();

  @Override
  public void start(Stage stage) {
    Main.primaryStage = stage;
    stage.setScene(new Scene(new Region()));
    ViewUtil.setSceneRoot(new TokensView());
    stage.setMaximized(true);
    stage.show();
  }

  public static void main(String[] args) { launch(); }
}
