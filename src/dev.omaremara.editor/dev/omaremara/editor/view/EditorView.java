package dev.omaremara.editor.view;

import dev.omaremara.editor.view.View;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class EditorView implements View {
  public Parent getRoot() {
    VBox vBox = new VBox();
    return vBox;
  }
}
