import java.util.Map;

public interface Manager {
	//This interface file MUST not be changed in anyway
	
	public void smallTeamRequest(Map<String, Integer> team);
	public void drillerRequest(String teamName, Map<String, Integer> team);
	public String workerLogin(String roll); 
	
}
