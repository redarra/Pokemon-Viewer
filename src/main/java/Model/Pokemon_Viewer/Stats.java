package Model.Pokemon_Viewer;

public class Stats {
	public String name;
	public String baseStat;
	public String effort;

//Stats Class Object
	public Stats(String name, String baseStat, String effort) {
		this.name = name;
		this.baseStat = baseStat;
		this.effort = effort;// TODO Auto-generated constructor stub
	}

	public String getStatsStr() {

		return " Name: " + name + "\n" + "Base Stats: " + baseStat + "\n" + "Effort: " + effort;
	}
}
