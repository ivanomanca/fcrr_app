!test.

+!test 
	<- .wait(1000);
	   makeArtifact("mybody3","test.MyAgentBodyArtifact",[2,0,1.5,1.5],Body).


+pos(X,Y)[artifact_id(Id)] 
	<- println("I'm able to perceive ",Id," - pos ",X," ",Y).

	