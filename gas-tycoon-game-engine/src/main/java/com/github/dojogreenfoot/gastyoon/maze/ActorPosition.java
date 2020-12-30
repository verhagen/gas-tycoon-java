package com.github.dojogreenfoot.gastyoon.maze;

public class ActorPosition<A> {
	private final int x;
	private final int y;
	private final A actor; 


	public ActorPosition(int x, int y, A actor) {
		this.x = x;
		this.y = y;
		this.actor = actor;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public A getActor() {
		return actor;
	}

}
