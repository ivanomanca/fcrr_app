!test.	   
	
+!test 
	<- println("setting the topology..");
	   cartago.new_obj("test.Space2D",[],Top);
	   println("object created");
	   setWorkspaceTopology(Top);
	   println("creating augmented artifacts..");
       makeArtifact("a0","test.MyAugmentedArtifact",[50,50,5],Id);
       makeArtifact("a1","test.MyAugmentedArtifact",[100,100,5],Id2);
	   inc [artifact_id(Id2)];
       .wait(5000);
	   makeArtifact("mybody","test.MyAgentBodyArtifact",[1,0,1.5,1.5],Body).
	   
	   /* .wait(1000);
	   moveTo(95,95)[artifact_id(Body)];
	   .wait(1000);
	   moveTo(99,99)[artifact_id(Body)].*/
	   
+count(X)[artifact_id(Id)] 
	<- println("detected ",X," in ",Id);
	   println("moving away the artifact ",Id);
	   moveTo(70,70)[artifact_id(Id)].

-count(X)[artifact_id(Id)] 
	<- println("no more observable: ",Id).

+pos(X,Y)[artifact_id(Id)] 
	<- println("I'm able to perceive ",Id," - pos ",X," ",Y).

	