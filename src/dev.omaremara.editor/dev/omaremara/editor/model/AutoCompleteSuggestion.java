package dev.omaremara.editor.model;

public class AutoCompleteSuggestion {
  private final String fullSuggestion;
  private final String partialSuggestion;

  public AutoCompleteSuggestion(String fullSuggestion,
                                String partialSuggestion) {
    this.fullSuggestion = fullSuggestion;
    this.partialSuggestion = partialSuggestion;
  }

  public String getFullSuggestion() { return fullSuggestion; }
  public String getPartialSuggestion() { return partialSuggestion; }
}
