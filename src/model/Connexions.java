package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Connexions {
	/**
	 * connexionsIdt		: identifiant
	 * connexionsUuid		: Uuid de connexions
	 * connexionsDate		: date 
	 * connexionsTime		: heure
	 */
	private int 		connexionsIdt;
	private String 		connexionsUuid;
	private LocalDate	connexionsDate;
	private LocalTime	connexionsTime;
	/**
	 * @param newConnexionsIdt
	 * @param newConnexionsUuid
	 * @param newConnexionsDate
	 * @param newConnexionsTime
	 */
	public Connexions(int newConnexionsIdt, String newConnexionsUuid, LocalDate newConnexionsDate, LocalTime newConnexionsTime) {
		super();
		this.connexionsIdt 	= newConnexionsIdt;
		this.connexionsUuid = newConnexionsUuid;
		this.connexionsDate = newConnexionsDate;
		this.connexionsTime = newConnexionsTime;
	}

}
