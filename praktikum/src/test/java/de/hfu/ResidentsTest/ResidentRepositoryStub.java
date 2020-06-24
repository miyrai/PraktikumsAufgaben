package de.hfu.ResidentsTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;



import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;

public class ResidentRepositoryStub implements ResidentRepository {
	
	private List<Resident> residents =  Arrays.asList(
			new Resident("Miriam","Aydt","Beispielstraße 5","Kreuzlingen",new Date(1900, 1, 1)),
			new Resident("Max","Aydt","Beispielstraße 5","Furtwangen",new Date(1900, 1, 1))
			);
	
	

	@Override
	public List<Resident> getResidents() {
		return residents;
	}
	

}
