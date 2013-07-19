// simple agent used for test purposes to insert and remove tuples from the space

!create_and_use.

+!create_and_use : true <- 
	// we here suppose that the artifact has already been created
	!discover("smart-m3");
	
	
	// wait some time in order for other agents to setup their own env
	.wait(3000);
	
	// join the smartspace
	join(Confirmation);
	println("[KP]: join confirmed ",Confirmation);
	
	// insert a given chunk of info
	insert( "ASDFGGHDERE1", "enteredRoom", "date", "URI", "URI",Ack1);
	println("[KP]: tuple has been correctly inserted: ",Ack1);
	
	.wait(2000);
	
	// insert a given chunk of info
	insert( "ASDFGGHDERE2", "enteredRoom", "date", "URI", "URI",Ack2);
	println("[KP]: tuple has been correctly inserted: ",Ack2);
	
	.wait(4000);
	
		// insert a given chunk of info
	insert( "ASDFGGHDERE3", "enteredRoom", "date", "URI", "URI",_);
	
	.wait(5000);
	
	// remove the inserted info
	remove("ASDFGGHDERE1", "enteredRoom", "date", "URI", "URI",Ack3);
	println("[KP]: tuple has been removed correctly: ", Ack3);
	.wait(2000);
	remove("ASDFGGHDERE2", "enteredRoom", "date", "URI", "URI",Ack4);
	println("[KP]: tuple has been removed correctly: ", Ack4);
	.wait(3000);
	remove("ASDFGGHDERE3", "enteredRoom", "date", "URI", "URI",_);
	
	
	// leave the SS
	leave(Ack);
	println("[KP]: leave confirmed ",Ack).

+!discover(ArtName) <- 
	lookupArtifact(ArtName,_).
-!discover(ArtName) <- 
	.wait(10);
	//makeArtifact("smart-m3","welcome.SmartM3",["137.204.70.124","10010","rfid-space"],Id);
	!!discover(ArtName).
