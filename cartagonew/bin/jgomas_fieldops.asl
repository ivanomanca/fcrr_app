!test.

+!test 
	<- .wait(1000);
	   .my_name(Name);
	   makeArtifact(Name,"ctf.InhabitantBody",[1,1,0,"bullet_helmet",1.5,1.5],Body).


+pos(X,Y)[artifact_id(Id)] 
	<- println("I'm able to perceive ",Id," - pos ",X," ",Y).

	