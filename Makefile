#Hopefully all you need for this to work is the path to the
#various java binaries in your PATH
JAVA=java 
JAVAC = javac
JAVADOC=javadoc
SRC=src/murlen/util/fscript
CLASSDIR=build/murlen/util/fscript
JFLAGS=-O -sourcepath src -d build 
BASE=murlen
SRCDIR=src
DOCDIR=docs/doc

warning:
	#OK, if you are feeling brave you can remove the following, but it might not work!
	$(error !!!!!YOU SHOULD BE USING ANT, MAKEFILE IS UNSUPPORTED AND UNTESTED!!!!!!)
	
all: warning
	rm -rf build
	mkdir build
	$(JAVAC) $(JFLAGS) *.java
	 

clean: warning
	rm -f $(SRC)/*.class $(SRC)/*.java~;

jar: warning
	jar -cv0f FScript.jar build/murlen/util/fscript/*.class
jdoc: warning
	$(JAVADOC) -sourcepath src -d $(DOCDIR) murlen.util.fscript

test: warning
	$(JAVAC) -classpath build FSTest.java
	$(JAVA) -classpath build FSTest regtest.script

theworks: warning
	make clean
	make all
	make jar
	make jdoc
	make test
