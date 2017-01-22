package com.example.polymorphism.control;

import com.example.polymorphism.model.Network;
import com.example.polymorphism.model.RadioNetwork;

public class RadioNetworkControlServices implements NetworkControlServices {
	private final static int BROADBAND_PER_PARTICIPANT = 10;

	public int computeMaxNumberOfParticipants(Network radioNetwork) {
		return computeMaxNumberOfParticipants((RadioNetwork) radioNetwork);
	}

	private int computeMaxNumberOfParticipants(RadioNetwork radioNetwork) {
		return (int) (radioNetwork.getDebit() * radioNetwork.getNbAntennas() / BROADBAND_PER_PARTICIPANT);
	}
}