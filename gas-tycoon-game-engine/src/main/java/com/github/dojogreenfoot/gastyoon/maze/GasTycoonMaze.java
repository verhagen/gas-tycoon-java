package com.github.dojogreenfoot.gastyoon.maze;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class GasTycoonMaze {
	public enum Show {
		COMPACT,
		FULL;
	}
	private final Maze<Pipe> maze;
	private final static Map<PipeType, Character> TO_CHAR = new HashMap<>();
	private final Set<ActorPosition<Pipe>> producers = new HashSet<>();
	private final Set<ActorPosition<Pipe>> consumers = new HashSet<>();

	static {
		TO_CHAR.put(PipeType.PRODUCER, 'P');
		TO_CHAR.put(PipeType.CONSUMER, 'C');
		TO_CHAR.put(PipeType.PIPE_I, 'I');
		TO_CHAR.put(PipeType.PIPE_L, 'L');
		TO_CHAR.put(PipeType.PIPE_T, 'T');
	}

	public GasTycoonMaze(Builder bldr) {
		maze = new Maze<>(bldr.maxX + 1, bldr.maxY + 1);
		maze.add(bldr.pipes);

		for (ActorPosition<Pipe> pipePos :  maze.getActors(Pipe.class)) {
			switch (pipePos.getActor().getType()) {
				case PRODUCER: producers.add(pipePos); break;
				case CONSUMER: consumers.add(pipePos); break;
				default: break;
			}
		}
	}


	public String show(Show show) {
		checkGasAvailability();
		Visitor<Pipe> visitor = null;
		switch (show) {
			case COMPACT: visitor = new CompactVisitor(); break;
			case FULL: visitor = new FullVisitor(); break;
		}
		maze.accept(visitor);
		return visitor.toString();
	}


	private void checkGasAvailability() {
		// Reset gas availability
		maze.accept(new Visitor<Pipe>() {
			
			@Override
			public void visit(int x, int y, Pipe pipe) {
				pipe.reset();
			}
			
			@Override
			public void nextRow() {
				// Not needed
			}

			@Override
			public boolean skipEmptyLocations() {
				return true;
			}
		});

		// Set is gas available
		for (ActorPosition<Pipe> pipePos : producers) {
			pipePos.getActor().setGasAvailable();
			Set<Direction> pipeLocations = pipePos.getActor().getPipeLocations();
			for (Direction direction : pipeLocations) {
				switch(direction) {
					case NORTH: makeConnection(pipePos.getX(), pipePos.getY() - 1, Direction.SOUTH); break;
					case EAST:  makeConnection(pipePos.getX() + 1, pipePos.getY(), Direction.WEST); break;
					case SOUTH: makeConnection(pipePos.getX(), pipePos.getY() + 1, Direction.NORTH); break;
					case WEST:  makeConnection(pipePos.getX() - 1, pipePos.getY(), Direction.EAST); break;
				}
			}
		}
	}

	private void makeConnection(int x, int y, Direction connctionDirection) {
		Pipe pipe = maze.get(x, y);
		if (pipe == null) {
			return;
		}
		if (pipe.isGasAvailable()) {
			return;
		}
		Set<Direction> pipeLocations = pipe.getPipeLocations();
		if (pipeLocations.contains(connctionDirection)) {
			pipe.setGasAvailable();
			for (Direction direct : pipeLocations) {
				if (direct == connctionDirection) {
					continue;
				}
				switch (direct) {
					case NORTH: makeConnection(x, y - 1, Direction.SOUTH); break;
					case EAST:  makeConnection(x + 1, y, Direction.WEST); break;
					case SOUTH: makeConnection(x, y + 1, Direction.NORTH); break;
					case WEST:  makeConnection(x - 1, y, Direction.EAST); break;
				}
			}
		}
	}


	public static class Builder {
		private int maxX = 0;
		private int maxY = 0;
		private List<ActorPosition<Pipe>> pipes = new LinkedList<>();


		public GasTycoonMaze create() {
			return new GasTycoonMaze(this);
		}

		public Builder addActor(int x, int y, Character actor) {
			if (x < 0) {
				throw new IllegalArgumentException("Argument 'x' should be 0 or larger.");
			}
			if (y < 0) {
				throw new IllegalArgumentException("Argument 'y' should be 0 or larger.");
			}

			if (x > maxX) {
				maxX = x;
			}
			if (y > maxY) {
				maxY = y;
			}

			Pipe pipe = null;
			switch (actor) {
				case 'P': pipe = new Producer(); break;
				case 'C': pipe = new Consumer(); break;
				case 'I': pipe = new PipeI(); break;
				case 'L': pipe = new PipeL(); break;
				case 'T': pipe = new PipeT(); break;
				default:
//					throw new IllegalArgumentException("Argument 'actor' with value '"
//						+ actor + "' is not a known actor type.");
					break;
			}
			if (pipe != null) {
				pipes.add(new ActorPosition<>(x, y, pipe));
			}
			return this;
		}
	}
	
	
	class CompactVisitor implements Visitor<Pipe> {
		private StringBuilder bldr = new StringBuilder();

		@Override
		public void nextRow() {
			if (bldr.length() > 0) {
				bldr.append("\n");
			}
		}

		@Override
		public void visit(int x, int y, Pipe pipe) {
			if (pipe == null) {
				bldr.append(".");
			}
			else {
				bldr.append(TO_CHAR.get(pipe.getType()));
			}
		}

		@Override
		public boolean skipEmptyLocations() {
			return false;
		}

		@Override
		public String toString() {
			return bldr.toString();
		}
		
	}


	class FullVisitor implements Visitor<Pipe> {
		private StringBuilder bldrTop = new StringBuilder();
		private StringBuilder bldrMid = new StringBuilder();
		private StringBuilder bldrBod = new StringBuilder();
		private List<String> mazeAsText = new LinkedList<>();

		
		@Override
		public void nextRow() {
			if (mazeAsText.size() > 0) {
				mazeAsText.add("\n");
			}
			if (bldrTop.length() > 0) {
				mazeAsText.add(bldrTop.append("\n").toString());
				mazeAsText.add(bldrMid.append("\n").toString());
				mazeAsText.add(bldrBod.append("\n").toString());
				bldrTop = new StringBuilder();
				bldrMid = new StringBuilder();
				bldrBod = new StringBuilder();
			}
		}

		@Override
		public void visit(int x, int y, Pipe pipe) {
			if (bldrTop.length() > 0) {
				bldrTop.append(" ");
				bldrMid.append(" ");
				bldrBod.append(" ");
			}
			if (pipe == null) {
				bldrTop.append("...");
				bldrMid.append("...");
				bldrBod.append("...");
			}
			else {
				bldrTop.append(isGasAvailable(x, y) ? "G" : ".");
				bldrTop.append(pipe.getPipeLocations().contains(Direction.NORTH) ? "^" : ".");
				bldrTop.append(".");

				bldrMid.append(pipe.getPipeLocations().contains(Direction.WEST) ? "<" : ".");
				bldrMid.append(TO_CHAR.get(pipe.getType()));
				bldrMid.append(pipe.getPipeLocations().contains(Direction.EAST) ? ">" : ".");

				bldrBod.append(".");
				bldrBod.append(pipe.getPipeLocations().contains(Direction.SOUTH) ? "V" : ".");
				bldrBod.append(".");
			}
		}

		@Override
		public boolean skipEmptyLocations() {
			return false;
		}

		@Override
		public String toString() {
			StringBuilder bldr = new StringBuilder();
			for (String text : mazeAsText) {
				bldr.append(text);
			}
			return bldr.toString();
		}
	}

	public GasTycoonMaze rotateLeft(int x, int y) {
		maze.get(x, y).rotateLeft();
		return this;
	}


	public GasTycoonMaze rotateRight(int x, int y) {
		maze.get(x, y).rotateRight();
		return this;
	}
	
	public boolean isGasAvailable(int x, int y) {
		if (! maze.isLocationInsideMaze(x, y)) {
			return false;
		}
		Pipe pipe = maze.get(x, y);
		if (pipe == null) {
			return false;
		}
		return pipe.isGasAvailable();
	}


	public boolean isCompleted() {
		for (ActorPosition<Pipe> pipePos : consumers) {
			if (! pipePos.getActor().isGasAvailable()) {
				return false;
			}
		}
		return true;
	}

}
