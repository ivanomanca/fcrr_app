!test.

+!test 
	<- createWorkspaceWithTopology("test","test.BasicWorkspaceTopology");
	   test.make_profile(0,0,Profile);
	   joinWorkspaceWithTopology("test",Profile,WspId);
       makeArtifact("a0","test.MyAugmentedArtifact",[50,50]);
       println("configured.").
       
     
	
	