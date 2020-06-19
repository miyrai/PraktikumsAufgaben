package de.hfu;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.AssertionFailedError;

public class UtilTest {

	@Test
	public void istErstesHalbjahrTestFirstHalfYear() {
		assertTrue("Liegt im ersten halben Jahr",Util.istErstesHalbjahr(1));
		assertTrue("Liegt im ersten halben Jahr",Util.istErstesHalbjahr(6));
	}
	
	@Test
	public void istErstesHalbjahrTestSecondHalfYear() {
		assertFalse("Liegt nicht im ersten halben Jahr",Util.istErstesHalbjahr(7));
		assertFalse("Liegt nicht im ersten halben Jahr",Util.istErstesHalbjahr(12));
	}
	
	@Test(expected=IllegalArgumentException.class, timeout = 1000)
	public void istErstesHalbjahrException() {
			assertFalse("Liegt nicht im ersten halben Jahr",Util.istErstesHalbjahr(13));
			fail("Expected istErstesHalbesJahr(13) to fail, but it didn't.");
	
	}
	

}
