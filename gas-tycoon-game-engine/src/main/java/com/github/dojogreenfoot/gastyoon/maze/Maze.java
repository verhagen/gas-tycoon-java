package com.github.dojogreenfoot.gastyoon.maze;

import java.util.LinkedList;
import java.util.List;

public class Maze<A> {
	private Object[][] maze;
	private int width;
	private int height;
	

	public Maze(int width, int height) {
		this.width = width;
		this.height = height;

		maze = new Object[width][];
		for (int w = 0; w < width; ++w) {
			maze[w] = new Object[height]; 
		}
	}

	public void add(List<ActorPosition<A>> actors) {
		for (ActorPosition<A> actor : actors) {
			set(actor.getX(), actor.getY(), actor.getActor());
		}
	}

	private void set(int x, int y, A actor) {
		maze[x][y] = actor;
	}

	@SuppressWarnings("unchecked")
	public A get(int x, int y) {
		if (! isLocationInsideMaze(x, y)) {
			if (x < 0) {
				x = width - 1;
			}
			if (x > width - 1) {
				x = 0;
			}
			if (y < 0) {
				y = height - 1;
			}
			if (y > height - 1) {
				y = 0;
			}
		}
		return (A)maze[x][y];
	}

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	public void accept(Visitor<A> visitor) {
		boolean skipEmptyLocations = visitor.skipEmptyLocations();
		for (int y = 0; y < getHeight(); ++y) {
			visitor.nextRow();
			for (int x = 0; x < getWidth(); ++x) {
				A actor = get(x, y);
				if (skipEmptyLocations && actor == null) {
					continue;
				}
				visitor.visit(x, y, actor);
			}
		}
		visitor.nextRow();
	}

	public boolean isLocationInsideMaze(int x, int y) {
		if (x < 0 || x >= width) {
			return false;
		}
		if (y < 0 || y >= height) {
			return false;
		}
		return true;
	}

	public List<ActorPosition<A>> getActors(Class<A> clazz) {
		ExtractActors visitor = new ExtractActors();
		accept(visitor);
		return visitor.getActors();
	}


	class ExtractActors implements Visitor<A> {
		private List<ActorPosition<A>> actors = new LinkedList<>();

		@Override
		public void nextRow() {
			// No implementation needed
		}

		@Override
		public void visit(int x, int y, A actor) {
			actors.add(new ActorPosition<>(x, y, actor));
		}
		
		public List<ActorPosition<A>> getActors() {
			return actors;
		}

		@Override
		public boolean skipEmptyLocations() {
			return true;
		}
	}
}
