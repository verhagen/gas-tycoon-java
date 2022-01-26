package com.github.verhagen.gastyoon.engine;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PipeL extends AbstractPipe {
	private static final Set<Direction> NORTH_EAST = new HashSet<>();
	private static final Set<Direction> EAST_SOUTH = new HashSet<>();
	private static final Set<Direction> SOUTH_WEST = new HashSet<>();
	private static final Set<Direction> WEST_NORTH = new HashSet<>();

	static {
		NORTH_EAST.add(Direction.NORTH);
		NORTH_EAST.add(Direction.EAST);
		EAST_SOUTH.add(Direction.EAST);
		EAST_SOUTH.add(Direction.SOUTH);
		SOUTH_WEST.add(Direction.SOUTH);
		SOUTH_WEST.add(Direction.WEST);
		WEST_NORTH.add(Direction.WEST);
		WEST_NORTH.add(Direction.NORTH);
	}


	public PipeL() {
		super(PipeType.PIPE_L);
	}


	@Override
	public Set<Direction> getPipeLocations() {
		switch (pipeLocation) {
			case NORTH: return NORTH_EAST;
			case EAST: return EAST_SOUTH;
			case SOUTH: return SOUTH_WEST;
			case WEST: return WEST_NORTH;
		}
		// Should never happen
		return Collections.emptySet();
	}

	
}
