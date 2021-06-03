package dev.omaremara.editor.view;

import dev.omaremara.editor.Main;
import dev.omaremara.editor.controller.TokensController;
import dev.omaremara.editor.model.Matchability;
import dev.omaremara.editor.model.Token;
import dev.omaremara.editor.model.TokenType;
import dev.omaremara.editor.view.View;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TokensView implements View {
  public Parent getRoot() {
    TokensController controller = new TokensController();

    /* Tool Bar */

    Button browseButton = new Button("Browse");
    browseButton.setDefaultButton(true);
    browseButton.setOnAction(e -> controller.browse());

    Button editorButton = new Button("Editor");
    editorButton.setOnAction(e -> controller.editor());

    ToolBar toolBar = new ToolBar(browseButton, new Separator(), editorButton);

    /* Table */

    TableView<Token> table = new TableView<>();

    VBox.setVgrow(table, Priority.ALWAYS);

    TableColumn<Token, Integer> lineNumberColumn =
        new TableColumn<>("Line Number");
    lineNumberColumn.setPrefWidth(150);
    TableColumn<Token, String> lexemeColumn = new TableColumn<>("Lexeme");
    lexemeColumn.setPrefWidth(150);
    TableColumn<Token, TokenType> tokenTypeColumn =
        new TableColumn<>("Token Type");
    tokenTypeColumn.setPrefWidth(150);
    TableColumn<Token, Integer> wordNumberColumn =
        new TableColumn<>("Word Number");
    wordNumberColumn.setPrefWidth(150);
    TableColumn<Token, Matchability> matchabilityColumn =
        new TableColumn<>("Matchability");
    matchabilityColumn.setPrefWidth(150);

    lineNumberColumn.setCellValueFactory(
        new PropertyValueFactory<>("lineNumber"));
    lexemeColumn.setCellValueFactory(new PropertyValueFactory<>("lexeme"));
    tokenTypeColumn.setCellValueFactory(
        new PropertyValueFactory<>("tokenType"));
    wordNumberColumn.setCellValueFactory(
        new PropertyValueFactory<>("wordNumber"));
    matchabilityColumn.setCellValueFactory(
        new PropertyValueFactory<>("matchability"));

    table.getColumns().add(lineNumberColumn);
    table.getColumns().add(lexemeColumn);
    table.getColumns().add(tokenTypeColumn);
    table.getColumns().add(wordNumberColumn);
    table.getColumns().add(matchabilityColumn);

    table.setItems(Main.tokens);

    VBox vBox = new VBox(toolBar, table);

    return vBox;
  }
}
