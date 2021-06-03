classes/%.class : src/%.java
	javac -p $(JAVA_MODULE_PATHS) -d $(JAVA_OUTPUT_DIRECTORY) \
	--module-source-path $(SOURCE_MODULE_PATHS) $(JAVA_COMPILE_FLAGS) $<

JAVA_MODULE_PATHS := /usr/lib/jvm/javafx-sdk-15/lib/
JAVA_OUTPUT_DIRECTORY := classes/
SOURCE_MODULE_PATHS := src/
JAVA_COMPILE_FLAGS := -Xlint


SRCS = $(shell find src/dev.omaremara.editor/ -name '*.java')
CLASSES = $(subst src/, classes/, $(patsubst %.java, %.class, $(SRCS)))

mods/editor.jar: $(CLASSES)
	jar --create --file mods/editor.jar --main-class dev.omaremara.editor.Main -C classes/dev.omaremara.editor/ .

.PHONY: run
run: mods/editor.jar
	java --add-modules javafx.controls -p $(JAVA_MODULE_PATHS) -jar $<
