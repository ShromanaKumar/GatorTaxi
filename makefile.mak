JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Kumar_Shromana/RBNode.java \
		Kumar_Shromana/Node.java \
		Kumar_Shromana/WriteOutput.java \
		Kumar_Shromana/MinHeap.java \
		Kumar_Shromana/RedBlackTree.java \
		Kumar_Shromana/Ride.java \
		Kumar_Shromana/gatorTaxi.java




default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class