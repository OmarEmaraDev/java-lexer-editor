package dev.omaremara.editor.model;

public enum Matchability {
  MATCHED("Matched"),
  NOT_MATCHED("Not Matched");

  private String uiName;

  private Matchability(String uiName) { this.uiName = uiName; }

  @Override
  public String toString() {
    return this.uiName;
  }
}
