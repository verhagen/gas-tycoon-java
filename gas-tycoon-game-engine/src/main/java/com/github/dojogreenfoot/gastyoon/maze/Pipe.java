package com.github.dojogreenfoot.gastyoon.maze;

import java.util.Set;

public interface Pipe {

	PipeType getType();

	void rotateLeft();

	void rotateRight();


	Direction getPipeLocation();

	Set<Direction> getPipeLocations();


	boolean isGasAvailable();

	void setGasAvailable();

	void reset();

}
