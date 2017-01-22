package com.example.polymorphism.control;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.polymorphism.NetworkControlServiceFactory;
import com.example.polymorphism.model.IpNetwork;
import com.example.polymorphism.model.Network;
import com.example.polymorphism.model.RadioNetwork;
import com.example.polymorphism.model.RestrictedRadioNetwork;

public class RestrictedRadioNetworkControlServicesTest {
	private RestrictedRadioNetworkControlServices restrictedRadioNetworkControlServices = new RestrictedRadioNetworkControlServices();
	private RestrictedRadioNetwork restrictedRadioNetwork;
	private NetworkControlServiceFactory networkControlServiceFactory = new NetworkControlServiceFactory();
	
	@Before
	public void before() {
		restrictedRadioNetwork = new RestrictedRadioNetwork();
		restrictedRadioNetwork.setDebit(23.3);
		restrictedRadioNetwork.setNbAntennas(15);
		restrictedRadioNetwork.setMaxNumberOfParticipants(15);
	}

	@Test
	public void testComputeMaxNumberOfParticipantsExceedLimit() {		
		int maxNumberOfParticipants = restrictedRadioNetworkControlServices
				.computeMaxNumberOfParticipants(restrictedRadioNetwork);
		Assert.assertEquals(15, maxNumberOfParticipants);
	}

	@Test
	public void testComputeMaxNumberOfParticipantsUnderLimit() {
		restrictedRadioNetwork.setNbAntennas(4);
		int maxNumberOfParticipants = restrictedRadioNetworkControlServices
				.computeMaxNumberOfParticipants(restrictedRadioNetwork);
		Assert.assertEquals(9, maxNumberOfParticipants);
	}

	@Test
	public void testComputeMaxNumberOfParticipantsExceedLimitWithFactory() {
		//Whatever the next reference is, the result will always be the same.
		Network network = restrictedRadioNetwork;
		int maxNumberOfParticipants = networkControlServiceFactory.retrieveControlService(network)
				.computeMaxNumberOfParticipants(restrictedRadioNetwork);
		Assert.assertEquals(15, maxNumberOfParticipants);
	}
}
