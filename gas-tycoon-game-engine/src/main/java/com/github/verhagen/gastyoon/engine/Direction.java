package com.github.verhagen.gastyoon.engine;

public enum Direction {
	NORTH
	, EAST
	, SOUTH
	, WEST
	;

	public Direction rotateLeft() {
		Direction newDirection = null;
		switch (this) {
			case NORTH: newDirection = WEST; break;
			case EAST: newDirection = NORTH; break;
			case SOUTH: newDirection = EAST; break;
			case WEST: newDirection = SOUTH; break;
		}
		return newDirection;
	}

	Direction rotateRight() {
		Direction newDirection = null;
		switch (this) {
			case NORTH: newDirection = EAST; break;
			case EAST: newDirection = SOUTH; break;
			case SOUTH: newDirection = WEST; break;
			case WEST: newDirection = NORTH; break;
		}
		return newDirection;
	}

}
