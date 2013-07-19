// an agent using the agenda
!test_agenda.

+!test_agenda : true
  <- make_artifact("my_agenda","c4jexamples.Agenda",Id);
     focus(Id);
     schedule("task1", 4000);
     schedule("task2", 6000).

+todo("task1"): true
  <- println("going to do task1..").

+todo("task2"): true
  <- println("going to do task2..").

