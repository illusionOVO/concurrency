
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition; //Note that the 'notifyAll' method or similar polling mechanism MUST not be used

// IMPORTANT:
//Thread safe classes other than those above (e.g. java.util.concurrent) MUST not be used.
//You MUST not use the keyword 'synchronized', or any other `thread safe` classes or mechanisms  
//or any delays or 'busy waiting' (spin lock) methods.
//However, you may import non-tread safe classes e.g.:
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;



//IMPORTANT: DrillLoginManager must handle exceptions locally i.e. it must not  explicitly 'throw' exceptions 
//otherwise the compilation with the Test classes will fail
public class DrillLoginManager implements Manager {

	@Override
	public void smallTeamRequest(Map<String, Integer> team) {
		private final ReentrantLock lock;
	    private final Condition smallTeamCondition;
	    private final Condition drillerCondition;

	    private final ArrayList<SmallTeam> smallTeams;
	    private final ArrayList<DrillerLedTeam> drillerLedTeams;

	    public DrillLoginManager() {
	        lock = new ReentrantLock();
	        smallTeamCondition = lock.newCondition();
	        drillerCondition = lock.newCondition();
	        smallTeams = new ArrayList<>();
	        drillerLedTeams = new ArrayList<>();		
	}

	@Override
	public void drillerRequest(String teamName, Map<String, Integer> team) {
		lock.lock();
        try {
            DrillerLedTeam drillerLedTeam = new DrillerLedTeam(teamName, team);
            drillerLedTeams.add(drillerLedTeam);
            while (!drillerLedTeam.hasEnoughWorkers()) {
                drillerCondition.await();
            }
            drillerLedTeam.notifyWorkers();
            drillerLedTeams.remove(drillerLedTeam);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
	}

	@Override
	public String workerLogin(String role) {
		lock.lock();
        try {
            for (SmallTeam smallTeam : smallTeams) {
                if (smallTeam.canJoin(role)) {
                    smallTeam.join(role);
                    if (smallTeam.hasEnoughWorkers()) {
                        smallTeamCondition.signalAll();
                    }
                    return null;
                }
            }
            for (DrillerLedTeam drillerLedTeam : drillerLedTeams) {
                if (drillerLedTeam.canJoin(role)) {
                    drillerLedTeam.join(role);
                    if (drillerLedTeam.hasEnoughWorkers()) {
                        drillerCondition.signalAll();
                    }
                    return drillerLedTeam.getTeamName();
                }
            }
        } finally {
            lock.unlock();
        }
        return null;
	}
	
	private abstract class Team {
        protected final Map<String, Integer> roles;
        protected final HashMap<String, Integer> joinedRoles;

        public Team(Map<String, Integer> roles) {
            this.roles = roles;
            this.joinedRoles = new HashMap<>();
        }

        public boolean canJoin(String role) {
            Integer requiredCount = roles.get(role);
            Integer joinedCount = joinedRoles.get(role);
            return requiredCount != null && joinedCount != null && joinedCount < requiredCount;
        }

        public boolean hasEnoughWorkers() {
            for (String role : roles.keySet()) {
                Integer requiredCount = roles.get(role);
                Integer joinedCount = joinedRoles.get(role);
                if (requiredCount > joinedCount) {
                    return false;
                }
            }
            return true;
        }

        public void join(String role) {
            Integer count = joinedRoles.getOrDefault(role, 0);
            joinedRoles.put(role, count + 1);
        }
	
	//Note that you may add inner classes and methods to this file as shown below:
	private class PriavateClass1 {
		void myMethod() {};
		// TODO Your code here	
	}
	
	void myMethod() {
		// TODO Your code here	
	}

}
