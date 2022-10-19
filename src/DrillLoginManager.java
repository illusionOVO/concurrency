import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DrillLoginManager implements Manager {

	@Override
	public void smallTeamRequest(Map<String, Integer> team) {
		// TODO Your code here		
	}

	@Override
	public void drillerRequest(String teamName, Map<String, Integer> team) {
		// TODO Your code here	
		//e.g.
		PriavateClass1 p = new PriavateClass1();
		p.myMethod();
	}

	@Override
	public String workerLogin(String roll) {
		// TODO Your code here	
		//e.g.
		myMethod();
		return null;
	}
	
	private class PriavateClass1 {
		void myMethod() {};
		// TODO Your code here	
	}
	
	void myMethod() {
		// TODO Your code here	
	}

}
