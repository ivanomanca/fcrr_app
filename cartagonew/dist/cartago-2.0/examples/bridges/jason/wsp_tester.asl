!test_wsp. 

+!test_wsp 
  <- ?current_wsp(Id0,Name,_);
     println("current workspace ",Name);  
     println("creating new workspaces...");
     createWorkspace("myNewWorkspace1");
     createWorkspace("myNewWorkspace2");
     joinWorkspace("myNewWorkspace1",WspID1);
     ?current_wsp(_,Name1,_);
     println("hello in ",Name1);
     joinWorkspace("myNewWorkspace2",WspID2);
     ?current_wsp(_,Name2,_);
     println("hello in ",Name2);
     cartago.set_current_wsp(WspID1);
     println("hello again in ",WspID1);
     println("quit..");
     quitWorkspace;
     ?current_wsp(_,Name3,_);
     println("back in ",Name3);
     quitWorkspace;
     cartago.set_current_wsp(Id0);
     ?current_wsp(_,Name4,_);     
     println("...and finallly in ",Name4," again.").
          
