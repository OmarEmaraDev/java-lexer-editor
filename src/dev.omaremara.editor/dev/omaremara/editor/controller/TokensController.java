package dev.omaremara.editor.controller;

import dev.omaremara.editor.Main;
import dev.omaremara.editor.controller.Lexer;
import dev.omaremara.editor.model.Matchability;
import dev.omaremara.editor.model.Token;
import dev.omaremara.editor.model.TokenType;
import dev.omaremara.editor.util.ViewUtil;
import dev.omaremara.editor.view.EditorView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class TokensController {
  public void browse() {
    Stage stage = Main.primaryStage;
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Source File");
    fileChooser.setInitialDirectory(
        new File(System.getProperty("user.home") + "/Documents"));
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Compiler #2 Source Files", "*.c2"));
    File selectedFile = fileChooser.showOpenDialog(stage);
    if (selectedFile == null) {
      return;
    }

    try {
      Lexer lexer = Lexer.fromFile(selectedFile);
      Main.tokens.addAll(lexer.tokenize());
    } catch (FileNotFoundException exception) {
      return;
    }
  }

  public void editor() { ViewUtil.setSceneRoot(new EditorView()); }
}
