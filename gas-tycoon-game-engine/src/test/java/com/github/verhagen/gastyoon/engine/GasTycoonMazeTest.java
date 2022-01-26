package com.github.verhagen.gastyoon.engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.verhagen.gastyoon.engine.GasTycoonMaze.Show;

public class GasTycoonMazeTest {
	private Logger logger = LoggerFactory.getLogger(GasTycoonMazeTest.class);

	@Test
	void gameOne() {
//		String filling = 
//				  "PIC\n"
//				+ "...\n"
//				+ "CIP\n"
//				;
		String expected = 
		  "PIC\n"
		+ "...\n"
		+ "CIP\n"
		;
		String expectedFull_1 =
  				  "G^. .^. .^.\n"
				+ ".P. .I. .C.\n"
				+ "... .V. ...\n"
				+ "\n"
				+ "... ... ...\n"
				+ "... ... ...\n"
				+ "... ... ...\n"
				+ "\n"
				+ ".^. .^. G^.\n"
				+ ".C. .I. .P.\n"
				+ "... .V. ...\n"
				;
		String expectedFull_2 =
				  "G.. G.. ...\n"
				+ ".P> <I> .C>\n"
				+ "... ... ...\n"
				+ "\n"
				+ "... ... ...\n"
				+ "... ... ...\n"
				+ "... ... ...\n"
				+ "\n"
				+ "G.. G.. G..\n"
				+ ".C> <I> <P.\n"
				+ "... ... ...\n"
				;
		String expectedFull_3 =
				  "G.. G.. G..\n"
				+ ".P> <I> <C.\n"
				+ "... ... ...\n"
				+ "\n"
				+ "... ... ...\n"
				+ "... ... ...\n"
				+ "... ... ...\n"
				+ "\n"
				+ "G.. G.. G..\n"
				+ ".C> <I> <P.\n"
				+ "... ... ...\n"
				;
		GasTycoonMaze maze = new GasTycoonMaze.Builder()
				.addActor(0, 0, 'P').addActor(1, 0, 'I').addActor(2, 0, 'C')
				.addActor(0, 2, 'C').addActor(1, 2, 'I').addActor(2, 2, 'P')
				.create();
		logger.info("\n" + maze.show(Show.FULL));
		assertEquals(expectedFull_1, maze.show(Show.FULL));
		logger.info("\n" + maze.show(Show.COMPACT));
		assertEquals(expected, maze.show(Show.COMPACT));

		maze.rotateRight(0, 0).rotateRight(1, 0).rotateRight(2, 0);
		maze.rotateRight(0, 2).rotateRight(1, 2).rotateLeft(2, 2);
		logger.info("\n" + maze.show(Show.FULL));
		assertEquals(expectedFull_2, maze.show(Show.FULL));
		assertFalse(maze.isCompleted());

		maze.rotateRight(2, 0).rotateRight(2, 0);
		logger.info("\n" + maze.show(Show.FULL));
		assertEquals(expectedFull_3, maze.show(Show.FULL));
		assertTrue(maze.isCompleted());
	}

	@Test
	void gameTwo() {
//		String filling = 
//				  "PIC\n"
//				+ "...\n"
//				+ "CIP\n"
//				;
		String expected = 
		  "CIL\n"
		+ ".CT\n"
		+ "PIL\n"
		;
		String expectedFull_1 =
  				  ".^. .^. .^.\n"
				+ ".C. .I. .L>\n"
				+ "... .V. ...\n"
				+ "\n"
				+ "... .^. .^.\n"
				+ "... .C. <T>\n"
				+ "... ... ...\n"
				+ "\n"
				+ "G^. .^. .^.\n"
				+ ".P. .I. .L>\n"
				+ "... .V. ...\n"
				;
		String expectedFull_2 =
				  "G.. G.. G..\n"
				+ ".C> <I> <L.\n"
				+ "... ... .V.\n"
				+ "\n"
				+ "... G.. G^.\n"
				+ "... .C> <T.\n"
				+ "... ... .V.\n"
				+ "\n"
				+ "G.. G.. G^.\n"
				+ ".P> <I> <L.\n"
				+ "... ... ...\n"
				;
		String expectedFull_3 =
				  "... ... ...\n"
				+ ".C> <I> <L.\n"
				+ "... ... .V.\n"
				+ "\n"
				+ "... G.. G..\n"
				+ "... .C> <T>\n"
				+ "... ... .V.\n"
				+ "\n"
				+ "G.. G.. G^.\n"
				+ ".P> <I> <L.\n"
				+ "... ... ...\n"
				;
		String expectedFull_4 =
				  "G.. G.. G..\n"
				+ ".C> <I> <L.\n"
				+ "... ... .V.\n"
				+ "\n"
				+ "... ... G^.\n"
				+ "... .C> .T>\n"
				+ "... ... .V.\n"
				+ "\n"
				+ "G.. G.. G^.\n"
				+ ".P> <I> <L.\n"
				+ "... ... ...\n"
				;
		GasTycoonMaze maze = new GasTycoonMaze.Builder()
				.addActor(0, 0, 'C').addActor(1, 0, 'I').addActor(2, 0, 'L')
				.addActor(0, 1, '.').addActor(1, 1, 'C').addActor(2, 1, 'T')
				.addActor(0, 2, 'P').addActor(1, 2, 'I').addActor(2, 2, 'L')
				.create();
		logger.info("\ngameTwo\n" + maze.show(Show.FULL));
		assertEquals(expectedFull_1, maze.show(Show.FULL));
		assertFalse(maze.isCompleted());
		logger.info("\ngameTwo compact\n" + maze.show(Show.COMPACT));
		assertEquals(expected, maze.show(Show.COMPACT));

		maze.rotateRight(0, 0).rotateRight(1, 0).rotateRight(2, 0).rotateRight(2, 0);
		maze.rotateRight(1, 1).rotateLeft(2, 1);
		maze.rotateRight(0, 2).rotateRight(1, 2).rotateLeft(2, 2);
		logger.info("\ngameTwo\n" + maze.show(Show.FULL));
		assertEquals(expectedFull_2, maze.show(Show.FULL));
		assertTrue(maze.isCompleted());
		
		maze.rotateLeft(2, 1);
		logger.info("\ngameTwo\n" + maze.show(Show.FULL));
		assertEquals(expectedFull_3, maze.show(Show.FULL));
		assertFalse(maze.isCompleted());

		maze.rotateLeft(2, 1);
		logger.info("\ngameTwo\n" + maze.show(Show.FULL));
		assertEquals(expectedFull_4, maze.show(Show.FULL));
		assertFalse(maze.isCompleted());
//
//		maze.rotateRight(2, 0).rotateRight(2, 0);
//		logger.info("\n" + maze.show(Show.FULL));
//		assertEquals(expectedFull_3, maze.show(Show.FULL));
//		assertTrue(maze.isCompleted());
	}
}
