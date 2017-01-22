package com.example.polymorphism;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Stream;

import com.example.polymorphism.control.IpNetworkControlServices;
import com.example.polymorphism.control.NetworkControlServices;
import com.example.polymorphism.control.RadioNetworkControlServices;
import com.example.polymorphism.control.RestrictedRadioNetworkControlServices;
import com.example.polymorphism.model.IpNetwork;
import com.example.polymorphism.model.Network;
import com.example.polymorphism.model.RadioNetwork;
import com.example.polymorphism.model.RestrictedRadioNetwork;

public class NetworkControlServiceFactory {
	private NetworkControlServices ipControlServices;
	private NetworkControlServices radioControlServices;
	private NetworkControlServices restrictedRadioControlServices;
	private Map<Network, NetworkControlServices> bindingsNetworkController;

	public NetworkControlServiceFactory() {
		radioControlServices = new RadioNetworkControlServices();
		ipControlServices = new IpNetworkControlServices();
		restrictedRadioControlServices = new RestrictedRadioNetworkControlServices();
		bindingsNetworkController = new HashMap<Network, NetworkControlServices>();

		registerControllers();
	}

	/** 
	 * This part could be externalize soon to make it more maintainable.
	 */
	private void registerControllers() {
		bindingsNetworkController.put(new RadioNetwork(), radioControlServices);
		bindingsNetworkController.put(new IpNetwork(), ipControlServices);
		bindingsNetworkController.put(new RestrictedRadioNetwork(), restrictedRadioControlServices);
	}

	public NetworkControlServices retrieveControlService(Network net) {
		NetworkControlServices controlServices = null;
		Stream<Entry<Network, NetworkControlServices>> stream = bindingsNetworkController.entrySet().stream();
		try{
			controlServices = stream.filter(e -> e.getKey().getClass().equals(net.getClass())).map(e -> e.getValue())
					.findFirst().get();
		} catch (NoSuchElementException e){
			controlServices = null;
		}
		return controlServices;
	}
	
	protected Set<Network> getRegisteredNetworks() {
		return bindingsNetworkController.keySet();
	}
	

}