package com.github.verhagen.gastyoon.engine;

import java.util.Collections;
import java.util.Set;

public class AbstractPipe implements Pipe {
	protected Direction pipeLocation = Direction.NORTH;
	protected PipeType pipeType;
	private boolean isGasAvailable = false;


	public AbstractPipe(PipeType pipeType) {
		this.pipeType = pipeType;
	}


	@Override
	public void rotateLeft() {
		pipeLocation = pipeLocation.rotateLeft();
	}

	@Override
	public void rotateRight() {
		pipeLocation = pipeLocation.rotateRight();
	}

	@Override
	public Direction getPipeLocation() {
		return pipeLocation;
	}

	@Override
	public Set<Direction> getPipeLocations() {
		return Collections.singleton(pipeLocation);
	}


	@Override
	public PipeType getType() {
		return pipeType;
	}


	@Override
	public boolean isGasAvailable() {
		if (PipeType.PRODUCER == pipeType) {
			return true;
		}
//		Set<Direction> pipeLocations = getPipeLocations();
//		
//		for (Direction direction : pipeLocations) {
//			switch(direction) {
//				case NORTH:
//					isGasAvailable();
//					
//			}
//		}
		return isGasAvailable;
	}

	@Override
	public void setGasAvailable() {
		isGasAvailable = true;
	}

	@Override
	public void reset() {
		isGasAvailable = false;
	}

}
