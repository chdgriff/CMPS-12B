# Chris Griffis <chdgriff>
# 12B 01/26/18
# Makefile - compiles and cleans up files needed to run this as an executable.

JAVASRC         = Search.java
SOURCES         = README Makefile $(JAVASRC)
MAINCLASS       = Search
CLASSES         = Search.class
JARFILE         = Search
SUBMIT          = submit cmps012b-pt.w18 pa2

all: $(JARFILE)
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm -f $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

