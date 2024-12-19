package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sessions {
	/**
	 * sessionsIdt			: identifiant
	 * sessionsAgentIdt		: identifiant de l'agent
	 * sessionsUuid			: UUID de sessions
	 * sessionsDateDebut	: Date de début de session
	 * sessionsTimeDebut	: Heure de début de session
	 * sessionsDateFin		: Date de fin de session
	 * sessionsTimeFin		: Heure de fin de session
	 * sessionAgent			: agent
	 */
	private int 		sessionsIdt;
	private int 		sessionsAgentIdt;
	private String		sessionsUuid;
	private LocalDate 	sessionsDateDebut;
	private LocalTime	sessionsTimeDebut;
	private LocalDate 	sessionsDateFin;
	private LocalTime	sessionsTimeFin;
	private Agent		sessionAgent;
	/**
	 * @param newSessionsIdt
	 * @param newSessionsAgentIdt
	 * @param newSessionsUuid
	 * @param newSessionsDateDebut
	 * @param newSessionsTimeDebut
	 * @param newSessionsDateFin
	 * @param newSessionsTimeFin
	 * @param newSessionAgent
	 */
	public Sessions(int newSessionsIdt, int newSessionsAgentIdt, String newSessionsUuid, LocalDate newSessionsDateDebut, LocalTime newSessionsTimeDebut,
			          LocalDate newSessionsDateFin, LocalTime newSessionsTimeFin, Agent	newSessionAgent) {
		super();
		this.sessionsIdt 		= newSessionsIdt;
		this.sessionsAgentIdt 	= newSessionsAgentIdt;
		this.sessionsUuid		= newSessionsUuid;
		this.sessionsDateDebut  = newSessionsDateDebut;
		this.sessionsTimeDebut  = newSessionsTimeDebut;
		this.sessionsDateFin	= newSessionsDateFin;
		this.sessionsTimeFin	= newSessionsTimeFin;
		this.sessionAgent		= newSessionAgent;
	}
	@Override
	public String toString() {
		return "Sessions [sessionsIdt=" + sessionsIdt + ", sessionsAgentIdt=" + sessionsAgentIdt + ", sessionsUuid="
				+ sessionsUuid + ", sessionsDateDebut=" + sessionsDateDebut + ", sessionsTimeDebut=" + sessionsTimeDebut
				+ ", sessionsDateFin=" + sessionsDateFin + ", sessionsTimeFin=" + sessionsTimeFin + ", sessionAgent="
				+ sessionAgent + "]";
	}
	
	public void setSessionsDateFin(LocalDate sessionsDateFin) {
		this.sessionsDateFin = sessionsDateFin;
	}
	public void setSessionsTimeFin(LocalTime sessionsTimeFin) {
		this.sessionsTimeFin = sessionsTimeFin;
	}
	public int getSessionsIdt() {
		return sessionsIdt;
	}
	public int getSessionsAgentIdt() {
		return sessionsAgentIdt;
	}
	public String getSessionsUuid() {
		return sessionsUuid;
	}
	public LocalDate getSessionsDateDebut() {
		return sessionsDateDebut;
	}
	public LocalTime getSessionsTimeDebut() {
		return sessionsTimeDebut;
	}
	public LocalDate getSessionsDateFin() {
		return sessionsDateFin;
	}
	public LocalTime getSessionsTimeFin() {
		return sessionsTimeFin;
	}
	public Agent getSessionAgent() {
		return sessionAgent;
	}
	
	

}
