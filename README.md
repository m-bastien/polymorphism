# polymorphism
How to bring polymorphism back to anemic models ? (limiting the use of instanceof instruction and switch statements)

Let say we have two objects dealing with radio networks (`RadioNetwork`,`RestrictedRadioNetwork`) inheriting from `Network. Both have a max number of participant (radios in fact) depending on their features : number of antennas, throughput ... This size has to be computed in a quite common way for those two objects. However, the RestrictedRadioNetwork must not exceed a given size. 

So, the first thing is to define the method signature in an interface through which all other software modules will communicate with. This is the goal of the NetworkControlService contract : `public int computeMaxNumberOfParticipants(Network network);`. This interface has to be as simple as possible. In this way, specializing signatures for each kind of objects must be forbidden.

If we create a unique controller for those objects, we will be quite annoyed ... In fact, implementing NetworkControlService, would have led to the wrong way since it is not possible to associate a method according to the type of instance. Thus the following code compiles but will not get what you expect with an object referenced by a Network class (`Network net = new RadioNetwork()`).

```java
Controller implements NetworkControlServices {
	public int computeMaxNumberOfParticipants(Network network){
		return this.computeMaxNumberOfParticipants(network);
	}
	public int computeMaxNumberOfParticipants(RadioNetwork network){...}
	public int computeMaxNumberOfParticipants(RestrictedRadioNetwork network){...}
}
```

This is because Java does not implement dispatch (https://en.wikipedia.org/wiki/Multiple_dispatch). Please have a look at   `NetworkControlServiceDispatch` to solve this situation. This is not a really sleek solution ...

So we have to implement multiple controllers (as many as network types). Some situations may be quite easy to bind an object to its controller. What about method context like this : `void foo(Network net)` ? Having a switch statement based on object type is on the one hand an absolutely horrible design and on the other hand may bring to misleading answers ...  In fact if B inherits A, B is an instance of B and of A ! Have a glance at method `retrieveControlService`in `NetworkControlServiceFactory` class to see how to retrieve the correct controller. This is shown in a production context in `RestrictedRadioNetworkControlServicesTest # testComputeMaxNumberOfParticipantsExceedLimitWithFactory()`.





