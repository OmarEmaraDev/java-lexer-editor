package dev.omaremara.editor.model;

public enum TokenType {
  CLASS("Class"),
  INHERITANCE("Inheritance"),
  CONDITION("Condition"),
  COMMENT("Comment"),
  INTEGER("Integer Type"),
  TOKEN_DELIMITER("Token Delimiter"),
  SINTEGER("SInteger"),
  CHARACTER("Character"),
  STRING("String"),
  FLOAT("Float"),
  SFLOAT("SFloat"),
  VOID("Void"),
  BREAK("Break"),
  LOOP("Loop"),
  RETURN("Return"),
  STRUCT("Struct"),
  SWITCH("Switch"),
  START_STATEMENT("Start Statement"),
  END_STATEMENT("End Statement"),
  ARITHMETIC_OPERATION("Arithmetic Operation"),
  LOGIC_OPERATOR("Logic operators"),
  RELATIONAL_OPERATOR("Relational Operator"),
  ASSIGNMENT_OPERATOR("Assignment Operator"),
  ACCESS_OPERATOR("Access Operator"),
  BRACES("Braces"),
  CONSTANT("Constant"),
  QUOTATION_MARK("Quotation Mark"),
  INCLUSION("Inclusion"),
  IDENTIFIER("Identifier"),

  /* Not actual tyoes. Used to unify the implementation. */
  UNKNOWN("Unknown"),
  WHITE_SPACE("White Space");

  private String uiName;

  private TokenType(String uiName) { this.uiName = uiName; }

  @Override
  public String toString() {
    return this.uiName;
  }
}
