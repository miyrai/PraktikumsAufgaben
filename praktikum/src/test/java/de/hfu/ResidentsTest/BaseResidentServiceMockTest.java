package de.hfu.ResidentsTest;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.easymock.EasyMock.*;

import java.awt.List;
import java.util.Date;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class BaseResidentServiceMockTest {

	@Test
	public void getUniqueResidentCorrectTest() throws ResidentServiceException {

		Resident resident1 = new Resident("Miriam", "Alt", "Beispielstraße 5", "Kreuzlingen", new Date(1900, 1, 1));
		Resident resident2 = new Resident("Miriam", "Aydt", "Beispielstraße 5", "Kreuzlingen", new Date(1900, 1, 1));

		LinkedList<Resident> res = new LinkedList<Resident>();
		res.add(resident1);
		res.add(resident2);

		//create mock
		ResidentRepository mock = createMock(ResidentRepository.class);
		
		expect(mock.getResidents()).andReturn(res);
		expect(mock.getResidents()).andReturn(res);
		replay(mock);
		
		//create base sercive
		BaseResidentService base = new BaseResidentService();
		base.setResidentRepository(mock);
		
		Resident tmp_resident = new Resident("","","","",null);
		try {
			tmp_resident = base.getUniqueResident(new Resident("Miriam", "Alt", "Beispielstraße 5", "Kreuzlingen", new Date(1900, 1, 1)));
		} catch (ResidentServiceException e) {
			
			e.printStackTrace();
		} 
		
		
		assertThat(tmp_resident.getFamilyName(), equalTo(resident1.getFamilyName()));
		assertThat(tmp_resident.getGivenName(), equalTo(resident1.getGivenName()));
		assertThat(tmp_resident.getStreet(), equalTo(resident1.getStreet()));
		assertThat(tmp_resident.getCity(), equalTo(resident1.getCity()));
		assertThat(tmp_resident.getDateOfBirth(), equalTo(resident1.getDateOfBirth()));
		
	
	}


	

}
