package dev.omaremara.editor.model;

public enum TokenType {
  CLASS("Class"),
  INHERITANCE("Inheritance"),
  CONDITION("Condition"),
  COMMENT("Comment"),
  INTEGER_TYPE("Integer Type"),
  TOKEN_DELIMITER("Token Delimiter"),
  IDENTIFIER("Identifier");

  private String uiName;

  private TokenType(String uiName) { this.uiName = uiName; }

  @Override
  public String toString() {
    return this.uiName;
  }
}
