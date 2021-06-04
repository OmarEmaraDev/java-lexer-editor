package dev.omaremara.editor.view;

import dev.omaremara.editor.Main;
import dev.omaremara.editor.controller.EditorController;
import dev.omaremara.editor.model.AutoCompleteSuggestion;
import dev.omaremara.editor.view.View;
import java.util.List;
import java.util.Optional;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.IndexRange;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

public class EditorView implements View {
  public Parent getRoot() {
    EditorController controller = new EditorController();

    /* Tool Bar */

    Button compileButton = new Button("Compile");
    compileButton.setDefaultButton(true);
    compileButton.setOnAction(e -> controller.compile());

    Button tokensButton = new Button("Tokens");
    tokensButton.setOnAction(e -> controller.tokens());

    ToolBar toolBar = new ToolBar(compileButton, new Separator(), tokensButton);

    /* Editor */

    CodeArea codeArea = Main.codeArea;
    VBox.setVgrow(codeArea, Priority.ALWAYS);
    codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

    /* Comment Operation. */

    codeArea.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.SLASH && event.isControlDown()) {
        IndexRange selectionRange = codeArea.getSelection();
        codeArea.insertText(selectionRange.getStart(), "/# ");
        codeArea.insertText(selectionRange.getEnd() + 3, " #/");
      }
    });

    /* Auto Completeion. */

    ContextMenu autoCompleteMenu = new ContextMenu();
    autoCompleteMenu.setAutoHide(true);

    codeArea.setOnKeyPressed(event -> {
      if (!(event.getCode() == KeyCode.N && event.isControlDown())) {
        return;
      }

      Optional<Bounds> boundsOptional = codeArea.getCaretBounds();
      if (boundsOptional.isEmpty()) {
        return;
      }

      int anchor = codeArea.getAnchor();
      String source = codeArea.getText();
      List<AutoCompleteSuggestion> suggestions =
          controller.autoComplete(anchor, source);

      if (suggestions.isEmpty()) {
        return;
      }

      autoCompleteMenu.getItems().clear();
      for (AutoCompleteSuggestion suggestion : suggestions) {
        MenuItem menuItem = new MenuItem(suggestion.getFullSuggestion());
        menuItem.setOnAction(contextMenuEvent -> {
          codeArea.insertText(anchor, suggestion.getPartialSuggestion());
        });
        autoCompleteMenu.getItems().add(menuItem);
      }

      Bounds bounds = boundsOptional.get();
      autoCompleteMenu.show(Main.primaryStage, bounds.getMaxX(),
                            bounds.getMaxY() - 30);
    });

    VBox vBox = new VBox(toolBar, codeArea);

    return vBox;
  }
}
