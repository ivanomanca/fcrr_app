!init.

+!init : true
	<- println("[local-agent:]Hello from the agent in test.remote.workspace");
	   makeArtifact("myCalc", "test.remote.Calculator", [], ArtID);
	   println("[local-agent:]Calculator created");
	   println("[local-agent:]Using Calculator Artifact");
	   sum(2,3,X);
	   println("[local-agent:]The sum between 2 and 3 is ", X);
	   sub(1,2,Y);
	   println("[local-agent:]The sub between 1 and 2 is ", Y);
	   division(4,2,Z);
	   println("[local-agent:]The div between 4 and 2 is ", Z).