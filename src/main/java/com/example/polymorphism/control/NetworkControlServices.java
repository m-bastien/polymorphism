package com.example.polymorphism.control;

import com.example.polymorphism.model.Network;

public interface NetworkControlServices {
	public int UNKNOWN_NUMBER_OF_PARTICIPANTS = -1;
	public int computeMaxNumberOfParticipants(Network network);
}