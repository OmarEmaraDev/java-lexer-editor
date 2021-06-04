module dev.omaremara.editor {
  requires java.base;
  requires javafx.base;
  requires javafx.controls;
  requires javafx.graphics;
  exports dev.omaremara.editor to javafx.graphics;

  requires richtextfx.fat;
}
