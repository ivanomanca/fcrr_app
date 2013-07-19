!test.

+!test 
	<- .wait(1000);
	   .my_name(Name);
	   makeArtifact(Name,"ctf.InhabitantBody",[0,0,0,"regular_helmet",0.3,0.3],Body);
	   +my_body_id(Body);
	   walk(1,0,0,0.1).

+garment("redcross_helmet") 
	<- println("hello doctor! Have a nice war!").	  

+garment("bullet_helmet")[artifact_id(Body)]: my_body_id(B) & B \== Body 
	<- println("hello ammo provider, make peace not war!").	  

+shape("medical_pack")[artifact_id(Pack)]
	<-	println("oh, a medical pack!");
		consume(HL) [artifact_id(Pack)];
		println("Got ",HL," new energy").

+shape("ammo_pack")[artifact_id(Pack)]
	<-	println("oh, an ammo pack!");
		consume(Ammo) [artifact_id(Pack)];
		println("Got ",Ammo," new ammo.").

+shape("flag")[artifact_id(F)]
	<-	println("oh, a flag!");
		catchThis [artifact_id(F)];
		println("got!");
		+flag(F).
		
+pos(X,Y,Z)[artifact_id(Me)]: my_body_id(Me) & walking(true)[artifact_id(Me)] & X > 5 
	<- 	println("tired, going to stop");
		stop.
		
+!releaseTheFlag : flag(F)		
	<- 	println("Ok, I release the flag..");
		releaseThis[artifact_id(F)];
		-flag(F).

+!releaseTheFlag : not flag(_)		
	<- 	println("I don't have the flag!").
	