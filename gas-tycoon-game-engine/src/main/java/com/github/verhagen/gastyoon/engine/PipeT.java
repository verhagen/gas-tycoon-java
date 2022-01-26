package com.github.verhagen.gastyoon.engine;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PipeT extends AbstractPipe {
	private static final Set<Direction> NORTH_WEST_EAST = new HashSet<>();
	private static final Set<Direction> EAST_NORTH_SOUTH = new HashSet<>();
	private static final Set<Direction> SOUTH_EAST_WEST = new HashSet<>();
	private static final Set<Direction> WEST_SOUTH_NORTH = new HashSet<>();

	static {
		NORTH_WEST_EAST.add(Direction.NORTH);
		NORTH_WEST_EAST.add(Direction.WEST);
		NORTH_WEST_EAST.add(Direction.EAST);

		EAST_NORTH_SOUTH.add(Direction.EAST);
		EAST_NORTH_SOUTH.add(Direction.NORTH);
		EAST_NORTH_SOUTH.add(Direction.SOUTH);

		SOUTH_EAST_WEST.add(Direction.SOUTH);
		SOUTH_EAST_WEST.add(Direction.EAST);
		SOUTH_EAST_WEST.add(Direction.WEST);
		
		WEST_SOUTH_NORTH.add(Direction.WEST);
		WEST_SOUTH_NORTH.add(Direction.SOUTH);
		WEST_SOUTH_NORTH.add(Direction.NORTH);
	}


	public PipeT() {
		super(PipeType.PIPE_T);
	}


	@Override
	public Set<Direction> getPipeLocations() {
		switch (pipeLocation) {
			case NORTH: return NORTH_WEST_EAST;
			case EAST: return EAST_NORTH_SOUTH;
			case SOUTH: return SOUTH_EAST_WEST;
			case WEST: return WEST_SOUTH_NORTH;
		}
		// Should never happen
		return Collections.emptySet();
	}

}
