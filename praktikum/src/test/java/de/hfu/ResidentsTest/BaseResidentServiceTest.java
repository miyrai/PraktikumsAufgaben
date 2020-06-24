package de.hfu.ResidentsTest;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class BaseResidentServiceTest {

	private Resident resident, residentWildcard;
	private BaseResidentService baseResidentService;

	@SuppressWarnings("deprecation")
	@Before
	public void init() {

		baseResidentService = new BaseResidentService();
		baseResidentService.setResidentRepository(new ResidentRepositoryStub());

		resident = new Resident("Miriam", "Aydt", "Beispielstraße 5", "Kreuzlingen", new Date(1900, 1, 1));
		residentWildcard = new Resident("*Miriam", "Aydt", "Beispielstraße 5", "Kreuzlingen", new Date(1900, 1, 1));
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	// -----------------getUniqueResident Test ------------------------

	@Test
	public void getUniqueResidentWildcardTest() throws ResidentServiceException {

		Resident tmp_resident;
		expectedEx.expect(ResidentServiceException.class);
		expectedEx.expectMessage("Wildcards (*) sind nicht erlaubt!");

		tmp_resident = baseResidentService.getUniqueResident(residentWildcard);

	}

	@Test
	public void getUniqueResidentCorrectTest() throws ResidentServiceException {

		Resident tmp_resident;

		tmp_resident = baseResidentService.getUniqueResident(resident);

		assertEquals(resident.getGivenName(), tmp_resident.getGivenName());
		assertEquals(resident.getFamilyName(), tmp_resident.getFamilyName());
		assertEquals(resident.getStreet(), tmp_resident.getStreet());
		assertEquals(resident.getCity(), tmp_resident.getCity());
		assertEquals(resident.getDateOfBirth(), tmp_resident.getDateOfBirth());

	}

	@Test
	public void getUniqueResidentNotCorrectTest() throws ResidentServiceException {

		expectedEx.expect(ResidentServiceException.class);
		expectedEx.expectMessage("Suchanfrage lieferte kein eindeutiges Ergebnis!");

		baseResidentService.getUniqueResident(new Resident("", "", " ", "", new Date(1900, 2, 1)));

		
	}
	
	// -----------------getFilteredResidentsList Test ------------------------

	@Test
	public void getFilteredResidentsListWithoutDateOfBirth() {

		java.util.List<Resident> residentList = baseResidentService
				.getFilteredResidentsList(new Resident("Miriam Aydt", "", " ", "", null));

		for (Resident tmp_resident : residentList) {
			assertEquals(resident.getGivenName(), tmp_resident.getGivenName());
			assertEquals(resident.getFamilyName(), tmp_resident.getFamilyName());
			assertEquals(resident.getStreet(), tmp_resident.getStreet());
			assertEquals(resident.getCity(), tmp_resident.getCity());
			assertEquals(resident.getDateOfBirth(), tmp_resident.getDateOfBirth());
		}

	}

	@Test(expected = NullPointerException.class, timeout = 1000)
	public void getFilteredResidentsListNullResident() {

		java.util.List<Resident> residentList = baseResidentService.getFilteredResidentsList(null);

	}

	@Test
	public void getFilteredResidentsListWithDateOfBirth() {

		java.util.List<Resident> residentList = baseResidentService
				.getFilteredResidentsList(new Resident("Miriam *", "", " ", "", new Date(1900, 1, 1)));

		for (Resident tmp_resident : residentList) {
			assertEquals(resident.getGivenName(), tmp_resident.getGivenName());
			assertEquals(resident.getFamilyName(), tmp_resident.getFamilyName());
			assertEquals(resident.getStreet(), tmp_resident.getStreet());
			assertEquals(resident.getCity(), tmp_resident.getCity());
			assertEquals(resident.getDateOfBirth(), tmp_resident.getDateOfBirth());
		}

	}

}
