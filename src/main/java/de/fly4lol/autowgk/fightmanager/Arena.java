package de.fly4lol.autowgk.fightmanager;

import org.bukkit.Location;

public class Arena {
	private Location team1Loc;
	private Location team2Loc;
	private boolean isEnabled;
	private String team1Location;
	private String team2Location;
	
	public Arena(Location team1Loc, Location team2Loc, boolean isEnabled, String team1Location, String team2Location){
		this.team1Loc = team1Loc;
		this.team2Loc = team2Loc;
		this.isEnabled = isEnabled;
		this.team1Location = team1Location;
		this.team2Location = team2Location;
		
	}

	public Location getTeam1Loc() {
		return team1Loc;
	}

	public void setTeam1Loc(Location team1Loc) {
		this.team1Loc = team1Loc;
	}

	public Location getTeam2Loc() {
		return team2Loc;
	}

	public void setTeam2Loc(Location team2Loc) {
		this.team2Loc = team2Loc;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getTeam1Location() {
		return team1Location;
	}

	public void setTeam1Location(String team1Location) {
		this.team1Location = team1Location;
	}

	public String getTeam2Location() {
		return team2Location;
	}

	public void setTeam2Location(String team2Location) {
		this.team2Location = team2Location;
	}
	
}