package com.github.verhagen.gastyoon.engine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MazeTest {
	private Logger logger = LoggerFactory.getLogger(MazeTest.class);

	@Test
	void gameOne() {
		String expected =
				  "... ...\n"
				+ ".G. ...\n"
				+ "... ...\n"
				+ "\n"
				+ "... ...\n"
				+ ".P. .D.\n"
				+ "... ...\n";
		Maze<Actor> maze = new Maze<>(2, 2);
		maze.add(createActors());
		
		assertEquals(2, maze.getWidth());
		assertEquals(2, maze.getHeight());
		Actor actor = maze.get(0, 0);
		assertEquals("Goofy", actor.getName());
		actor = maze.get(1, 1);
		assertEquals("Donald Duck", actor.getName());
		actor = maze.get(0, 1);
		assertEquals("Pluto", actor.getName());
		actor = maze.get(1, 0);
		assertNull(actor);
		
		FullTextVisitor visitor = new FullTextVisitor();
		maze.accept(visitor);
		logger.info("\ngameOne\n" + visitor.toString());
		assertEquals(expected, visitor.toString());
	}

	
	private List<ActorPosition<Actor>> createActors() {
		LinkedList<ActorPosition<Actor>> actors = new LinkedList<>();
		actors.add(new ActorPosition<MazeTest.Actor>(0, 0, new Actor("Goofy")));
		actors.add(new ActorPosition<MazeTest.Actor>(1, 1, new Actor("Donald Duck")));
		actors.add(new ActorPosition<MazeTest.Actor>(0, 1, new Actor("Pluto")));
		return actors;
	}


	class Actor {
		private final String name;

		public Actor(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public class FullTextVisitor implements Visitor<Actor> {
		StringBuilder bldrTop = new StringBuilder();
		StringBuilder bldrMid = new StringBuilder();
		StringBuilder bldrBod = new StringBuilder();
		List<String> mazeAsText = new LinkedList<>();

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
		public void visit(int x, int y, Actor actor) {
			
			if (bldrTop.length() > 0) {
				bldrTop.append(" ");
			}
			bldrTop.append("...");

			if (bldrMid.length() > 0) {
				bldrMid.append(" ");
			}
			if (actor == null) {
				bldrMid.append("...");
			}
			else {
				bldrMid.append("." + actor.getName().charAt(0) + ".");
			}

			if (bldrBod.length() > 0) {
				bldrBod.append(" ");
			}
			bldrBod.append("...");
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

}
