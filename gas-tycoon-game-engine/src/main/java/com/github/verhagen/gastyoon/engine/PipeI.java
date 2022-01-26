package com.github.verhagen.gastyoon.engine;

import java.util.HashSet;
import java.util.Set;

public class PipeI extends AbstractPipe {
	private static final Set<Direction> NORTH_SOUTH = new HashSet<>();
	private static final Set<Direction> EAST_WEST = new HashSet<>();

	static {
		NORTH_SOUTH.add(Direction.NORTH);
		NORTH_SOUTH.add(Direction.SOUTH);
		EAST_WEST.add(Direction.EAST);
		EAST_WEST.add(Direction.WEST);
	}


	public PipeI() {
		super(PipeType.PIPE_I);
	}



	@Override
	public Set<Direction> getPipeLocations() {
		return NORTH_SOUTH.contains(pipeLocation) ? NORTH_SOUTH : EAST_WEST;
	}

}
