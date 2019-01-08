# Chris Griffis <chdgriff>
# 12B 
# Makefile - compiles and cleans up files needed to run this as an executable.

JAVASRC         = Recursion.java
SOURCES         = README Makefile $(JAVASRC)
MAINCLASS       = Recursion
CLASSES         = Recursion.class
JARFILE         = Recursion
SUBMIT          = submit cmps012b-pt.w18 pa1

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

