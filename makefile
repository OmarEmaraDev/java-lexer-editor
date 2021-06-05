classes/%.class : src/%.java
	javac -p $(JAVA_MODULE_PATHS) -d $(JAVA_OUTPUT_DIRECTORY) \
	--module-source-path $(SOURCE_MODULE_PATHS) $(JAVA_COMPILE_FLAGS) $<

RICH_TEXT_FX__URL := https://github.com/FXMisc/RichTextFX/releases/download/v0.10.6/richtextfx-fat-0.10.6.jar

libs/richtextfx-fat-0.10.6.jar:
	mkdir libs/
	curl -Lo libs/richtextfx-fat-0.10.6.jar $(RICH_TEXT_FX__URL)
	touch libs/richtextfx-fat-0.10.6.jar

JAVA_MODULE_PATHS := /usr/lib/jvm/javafx-sdk-15/lib/:libs/
JAVA_OUTPUT_DIRECTORY := classes/
SOURCE_MODULE_PATHS := src/
JAVA_COMPILE_FLAGS := -Xlint

SRCS = $(shell find src/dev.omaremara.editor/ -name '*.java')
CLASSES = $(subst src/, classes/, $(patsubst %.java, %.class, $(SRCS)))

mods/editor.jar: $(CLASSES) libs/richtextfx-fat-0.10.6.jar
	jar --create --file mods/editor.jar --main-class dev.omaremara.editor.Main -C classes/dev.omaremara.editor/ .

.PHONY: run
run: mods/editor.jar libs/richtextfx-fat-0.10.6.jar
	java --add-modules ALL-MODULE-PATH -p $(JAVA_MODULE_PATHS) -jar $<
