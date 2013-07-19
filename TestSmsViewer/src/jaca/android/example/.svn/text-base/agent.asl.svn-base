!start.

+!start
  <-  !init;
       !do_job.

/*
 * Initialisation plan for joining the MiddlewareServices workspace
 */
+!init
  <-   lookupArtifact("console", LocalConsoleID);
       focusWhenAvailable("viewer");
       lookupArtifact("viewer", ViewerID);
       +art_id(viewer, ViewerID);
       +art_id(console,LocalConsoleID);
       +join_attempts(0);
       !try_join.

+!try_join : art_id(console,LocalConsoleID) & join_attempts(N) & N < 3
  <-  -+join_attempts(N+1);
       joinRemoteWorkspace("MiddlewareServices", "jaca.android.jacaservice", WspID);
       +wsp_id(jaca_services,WspID);
       println("Join to MiddlewareServices wsp done.")[artifact_id(LocalConsoleID)].

-!try_join : join_attempts(N) & N >= 3
  <-  println("Failure, cannot join MiddlewareServices wsp.")[artifact_id(LocalConsoleID)].

-!try_join
  <-  println("Failure, trying join MiddlewareServices again in one second.")[artifact_id(LocalConsoleID)];
       .wait(1000);
       !try_join.

/******************** Main agent plan ********************/		

+!do_job: true
  <-  focusWhenAvailable("sms-manager");
       .println("init done").

+sms_received(A, B) : art_id(viewer, ViewerID)
  <-  addSMSToList(A, B)[artifact_id(ViewerID)].