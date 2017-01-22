package com.example.polymorphism;

import org.junit.Assert;
import org.junit.Test;

import com.example.polymorphism.control.NetworkControlServices;
import com.example.polymorphism.model.IpNetwork;
import com.example.polymorphism.model.Network;
import com.example.polymorphism.model.RadioNetwork;
import com.example.polymorphism.model.RestrictedRadioNetwork;

public class NetworkControlServiceDispatchTest {

	private NetworkControlServiceDispatch networkControlServiceDispatch = new NetworkControlServiceDispatch();

	@Test
	public void testComputeMaxNumberOfParticipantsRadioNetwork() {
		RadioNetwork radioNetwork = new RadioNetwork();
		radioNetwork.setDebit(15);
		radioNetwork.setNbAntennas(6);
		Network network = radioNetwork;
		int maxNumberOfParticipants = networkControlServiceDispatch.computeMaxNumberOfParticipants(network);
		Assert.assertEquals(9, maxNumberOfParticipants);
	}

	@Test
	public void testComputeMaxNumberOfParticipantsIpNetwork() {
		RestrictedRadioNetwork restrictedRadioNetwork = new RestrictedRadioNetwork();
		restrictedRadioNetwork.setDebit(23.3);
		restrictedRadioNetwork.setNbAntennas(15);
		restrictedRadioNetwork.setMaxNumberOfParticipants(15);
		int maxNumberOfParticipants = networkControlServiceDispatch
				.computeMaxNumberOfParticipants(restrictedRadioNetwork);
		Assert.assertEquals(15, maxNumberOfParticipants);
	}

	@Test
	public void testComputeMaxNumberOfParticipantsRestrcitedRadioNetworkExceedLimit() {
		RestrictedRadioNetwork restrictedRadioNetwork = new RestrictedRadioNetwork();
		restrictedRadioNetwork.setDebit(23.3);
		restrictedRadioNetwork.setNbAntennas(15);
		restrictedRadioNetwork.setMaxNumberOfParticipants(15);
		int maxNumberOfParticipants = networkControlServiceDispatch
				.computeMaxNumberOfParticipants(restrictedRadioNetwork);
		Assert.assertEquals(15, maxNumberOfParticipants);
	}

	@Test
	public void testComputeMaxNumberOfParticipantsUnderLimit() {
		RestrictedRadioNetwork restrictedRadioNetwork = new RestrictedRadioNetwork();
		restrictedRadioNetwork.setDebit(23.3);
		restrictedRadioNetwork.setNbAntennas(4);
		restrictedRadioNetwork.setMaxNumberOfParticipants(15);
		int maxNumberOfParticipants = networkControlServiceDispatch
				.computeMaxNumberOfParticipants(restrictedRadioNetwork);
		Assert.assertEquals(9, maxNumberOfParticipants);
	}

	@Test
	public void testAllBeansHaveAMethod() {
		Network ipNetwork1 = new IpNetwork();
		IpNetwork ipNetwork2 = new IpNetwork();
		Network radioNetwork1 = new RadioNetwork();
		RadioNetwork radioNetwork2 = new RadioNetwork();
		Network restrictedRadioNetwork1 = new RestrictedRadioNetwork();
		RadioNetwork restrictedRadioNetwork2 = new RestrictedRadioNetwork();
		RestrictedRadioNetwork restrictedRadioNetwork3 = new RestrictedRadioNetwork();

		Assert.assertNotEquals(NetworkControlServices.UNKNOWN_NUMBER_OF_PARTICIPANTS,
				networkControlServiceDispatch.computeMaxNumberOfParticipants(ipNetwork1));
		Assert.assertNotEquals(NetworkControlServices.UNKNOWN_NUMBER_OF_PARTICIPANTS,
				networkControlServiceDispatch.computeMaxNumberOfParticipants(ipNetwork2));
		Assert.assertNotEquals(NetworkControlServices.UNKNOWN_NUMBER_OF_PARTICIPANTS,
				networkControlServiceDispatch.computeMaxNumberOfParticipants(radioNetwork1));
		Assert.assertNotEquals(NetworkControlServices.UNKNOWN_NUMBER_OF_PARTICIPANTS,
				networkControlServiceDispatch.computeMaxNumberOfParticipants(radioNetwork2));
		Assert.assertNotEquals(NetworkControlServices.UNKNOWN_NUMBER_OF_PARTICIPANTS,
				networkControlServiceDispatch.computeMaxNumberOfParticipants(restrictedRadioNetwork1));
		Assert.assertNotEquals(NetworkControlServices.UNKNOWN_NUMBER_OF_PARTICIPANTS,
				networkControlServiceDispatch.computeMaxNumberOfParticipants(restrictedRadioNetwork2));
		Assert.assertNotEquals(NetworkControlServices.UNKNOWN_NUMBER_OF_PARTICIPANTS,
				networkControlServiceDispatch.computeMaxNumberOfParticipants(restrictedRadioNetwork3));
	}

	@Test
	public void testUnknownBeanHasNoMethod() {
		Network fakeNetwork = new Network() {};
		Assert.assertEquals(NetworkControlServices.UNKNOWN_NUMBER_OF_PARTICIPANTS,
				networkControlServiceDispatch.computeMaxNumberOfParticipants(fakeNetwork));
	}

}
