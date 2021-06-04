package dev.omaremara.editor.view;

import dev.omaremara.editor.Main;
import dev.omaremara.editor.model.Matchability;
import dev.omaremara.editor.model.Token;
import java.util.function.IntFunction;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ErrorMarkerFactory implements IntFunction<Node> {
  @Override
  public Node apply(int lineNumber) {
    Boolean lineHasError = false;
    int firstErrorWordNumber = 0;
    for (Token token : Main.tokens) {
      if (token.getLineNumber() == lineNumber &&
          token.getMatchability() == Matchability.NOT_MATCHED) {
        lineHasError = true;
        firstErrorWordNumber = token.getWordNumber();
        break;
      }
    }

    if (!lineHasError) {
      return null;
    }

    Circle circle = new Circle(5);
    circle.setFill(Color.RED);
    String error = "Unmatched token at word " + firstErrorWordNumber + ".";
    Tooltip tooltip = new Tooltip(error);
    Tooltip.install(circle, tooltip);
    return circle;
  }
}
