package com.example.polymorphism.control;

import com.example.polymorphism.model.Network;
import com.example.polymorphism.model.RestrictedRadioNetwork;

public class RestrictedRadioNetworkControlServices extends RadioNetworkControlServices {

	@Override
	public int computeMaxNumberOfParticipants(Network network) {
		return computeMaxNumberOfParticipants((RestrictedRadioNetwork) network);
	}

	private int computeMaxNumberOfParticipants(RestrictedRadioNetwork network) {
		return Math.min(network.getMaxNumberOfParticipants(), super.computeMaxNumberOfParticipants(network));
	}

}
