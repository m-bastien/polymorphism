package com.example.polymorphism;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.example.polymorphism.control.NetworkControlServices;
import com.example.polymorphism.model.IpNetwork;
import com.example.polymorphism.model.Network;
import com.example.polymorphism.model.RadioNetwork;
import com.example.polymorphism.model.RestrictedRadioNetwork;

public class NetworkControlServiceDispatch implements NetworkControlServices {

	private final static int RADIO_BROADBAND_PER_PARTICIPANT = 10;
	private final static int IP_BROADBAND_PER_PARTICIPANT = 5;

	public int computeMaxNumberOfParticipants(Network network) {
		int maxNumberOfParticipants = UNKNOWN_NUMBER_OF_PARTICIPANTS;
		try {
			Method method = this.getClass().getMethod("computeMaxNumberOfParticipants", network.getClass());
			maxNumberOfParticipants = (Integer) method.invoke(this, network);
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			maxNumberOfParticipants = UNKNOWN_NUMBER_OF_PARTICIPANTS;
		}
		return maxNumberOfParticipants;
	}

	public int computeMaxNumberOfParticipants(IpNetwork ipNetwork) {
		return (int) (ipNetwork.getDebit() * ipNetwork.getNbRouters() / IP_BROADBAND_PER_PARTICIPANT);
	}

	public int computeMaxNumberOfParticipants(RadioNetwork radioNetwork) {
		return (int) (radioNetwork.getDebit() * radioNetwork.getNbAntennas() / RADIO_BROADBAND_PER_PARTICIPANT);
	}

	public int computeMaxNumberOfParticipants(RestrictedRadioNetwork network) {
		return Math.min(network.getMaxNumberOfParticipants(), computeMaxNumberOfParticipants((RadioNetwork) network));
	}
}
