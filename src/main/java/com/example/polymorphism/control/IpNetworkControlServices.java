package com.example.polymorphism.control;

import com.example.polymorphism.model.IpNetwork;
import com.example.polymorphism.model.Network;

public class IpNetworkControlServices implements NetworkControlServices {
	private final static int BROADBAND_PER_PARTICIPANT = 5;

	public int computeMaxNumberOfParticipants(Network network) {
		return computeMaxNumberOfParticipants((IpNetwork) network);
	}

	private int computeMaxNumberOfParticipants(IpNetwork ipNetwork) {
		return (int) (ipNetwork.getDebit() * ipNetwork.getNbRouters() / BROADBAND_PER_PARTICIPANT);
	}
}