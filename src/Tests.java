
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Tests {
	int  t1 = 100; //Timeout 

	public void exampleTest_A (){
		//See UR1 in specification for description
		System.out.println("\nExampleTest_A");
		AtomicInteger roustaboutReleases = new AtomicInteger(0); //Declare thread-safe counters to accumulate the number of worker threads that return
		DrillLoginManager manager =  new DrillLoginManager();	
		
		//Define and run worker threads:
		class ExampleTestWorkerThread extends Thread {
			public void run(){
				String teamReturned = manager.workerLogin("Roustabout"); //Note that teamName is ignored in this example
				roustaboutReleases.incrementAndGet();
			};	
		};			
		for (int i=0; i < 5; i++) (new ExampleTestWorkerThread()).start();
		System.out.println("4 Roustabout threads started");
		try {Thread.sleep(t1);} catch (InterruptedException e) { e.printStackTrace();} //Give time for worker threads to execute

		//Define and make request to manger:
		Map<String, Integer> teamRequest = new HashMap <String, Integer>();
		teamRequest.put("Roustabout", 2);
		System.out.println("teamRequest = " + teamRequest.toString());		
		manager.smallTeamRequest(teamRequest); //Note 'anonymous' Request (no teamName) and no Driller required
		try {Thread.sleep(t1);} catch (InterruptedException e) { e.printStackTrace();}  //Give time for worker threads to execute

		//Check results:
		System.out.println("Number of Roustabouts released by manager = " + roustaboutReleases.get());
		if (roustaboutReleases.get() == 2) System.out.println("Hence: SUCCESS");
		else System.out.println("Hence: FAIL");
	}
	
	public void exampleTest_B (){
		//This example is relevant to UR5 and UR6
		//Has teamName testing on Worker returns
		//Uses drillerRequest() method so 1 driller is required in each team.
		//3 Roustabouts start THEN 1 anonymousRequest for 2 Roustabouts by the main thread
		System.out.println("\nExampleTest_B");
		DrillLoginManager drillLoginManager =  new DrillLoginManager();
		
		//Declare thread-safe counters to accumulate the number of worker and Driller threads that return
		AtomicInteger drillerReleases = new AtomicInteger(0);
		AtomicInteger roustaboutReleases = new AtomicInteger(0);	
		
		//Define the Team:
		String teamName = "TeamX";		
		Map<String, Integer> teamRequest = new HashMap <String, Integer>();
		teamRequest.put("Driller", 1);
		teamRequest.put("Roustabout", 2);	

		
		//Define worker and driller threads:
		class ExampleDrillerThread extends Thread {
		    public void run(){
		        drillLoginManager.drillerRequest(teamName, teamRequest);
		        drillerReleases.incrementAndGet();
		    };	
		};	
		class ExampleTestWorkerThread extends Thread {
			public void run(){
				String threadName = Thread.currentThread().getName();
				String teamReturned = drillLoginManager.workerLogin("Roustabout"); 
				if (teamReturned == teamName) {
					System.out.println("teamName returned by worker thread " + threadName + "(Roustabout) = " + teamReturned + ", is correct.");
					roustaboutReleases.incrementAndGet();
				}
				else {
					System.out.println("Error: teamName returned by worker thread " + threadName + "(Roustabout) = " + teamReturned + ", is incorrect (it should be " + teamName + ")");
				};	
			}	
		};		
		
		//Start 3 roustabouts:
		for (int i=0; i < 3; i++) (new ExampleTestWorkerThread()).start();
		System.out.println("3 Roustabout threads started");
		//Wait for them to run and suspend:
		try {Thread.sleep(10); } catch (InterruptedException e) {e.printStackTrace();}
		//Start Driller:
		(new ExampleDrillerThread()).start();
		System.out.println("1 Driller thread started by calling: 'drillLoginManager.drillerRequest', teamRequest = " + teamRequest + ", teamName = " + teamName);
		//Wait for Driller to run and release Roustabouts:		
		try {Thread.sleep(10); } catch (InterruptedException e) {e.printStackTrace();}	
		
		//Check results:
		System.out.println("Number of Roustabouts released to run by drillLoginManager for team " + teamName + " = " + roustaboutReleases.get());
		System.out.println("Number of Driller threads released to run by drillLoginManager = " + drillerReleases.get());
		if (drillerReleases.get() == 1 && roustaboutReleases.get() == 2) System.out.println("Hence: SUCCESS");
		else System.out.println("Hence: FAIL");
	}

}


