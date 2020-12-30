package com.github.dojogreenfoot.gastyoon.maze;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class PipeTest {

	@Test
	void rotateLeft360() throws Exception {
		Consumer pipe = new Consumer();
		assertEquals(Direction.NORTH, pipe.getPipeLocation());
		pipe.rotateLeft();
		assertEquals(Direction.WEST, pipe.getPipeLocation());
		pipe.rotateLeft();
		assertEquals(Direction.SOUTH, pipe.getPipeLocation());
		pipe.rotateLeft();
		assertEquals(Direction.EAST, pipe.getPipeLocation());
		pipe.rotateLeft();
		assertEquals(Direction.NORTH, pipe.getPipeLocation());
	}

	@Test
	void rotateRight360() throws Exception {
		Consumer pipe = new Consumer();
		assertEquals(Direction.NORTH, pipe.getPipeLocation());
		pipe.rotateRight();
		assertEquals(Direction.EAST, pipe.getPipeLocation());
		pipe.rotateRight();
		assertEquals(Direction.SOUTH, pipe.getPipeLocation());
		pipe.rotateRight();
		assertEquals(Direction.WEST, pipe.getPipeLocation());
		pipe.rotateRight();
		assertEquals(Direction.NORTH, pipe.getPipeLocation());
	}

	@Test
	void validateConsumer() throws Exception {
		Consumer consumer = new Consumer();
		Set<Direction> pipeLocations = consumer.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(1, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.NORTH));
	}

	@Test
	void validateProducer() throws Exception {
		Producer producer = new Producer();
		Set<Direction> pipeLocations = producer.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(1, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.NORTH));
	}

	@Test
	void validatePipeI() throws Exception {
		PipeI pipe = new PipeI();
		Set<Direction> pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(2, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.NORTH));
		assertTrue(pipeLocations.contains(Direction.SOUTH));

		pipe.rotateRight();
		pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(2, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.EAST));
		assertTrue(pipeLocations.contains(Direction.WEST));
	}

	@Test
	void validatePipeL() throws Exception {
		PipeL pipe = new PipeL();
		Set<Direction> pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(2, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.NORTH));
		assertTrue(pipeLocations.contains(Direction.EAST));

		pipe.rotateRight();
		pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(2, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.EAST));
		assertTrue(pipeLocations.contains(Direction.SOUTH));

		pipe.rotateRight();
		pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(2, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.SOUTH));
		assertTrue(pipeLocations.contains(Direction.WEST));

		pipe.rotateRight();
		pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(2, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.WEST));
		assertTrue(pipeLocations.contains(Direction.NORTH));

		pipe.rotateRight();
		pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(2, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.NORTH));
		assertTrue(pipeLocations.contains(Direction.EAST));
	}

	@Test
	void validatePipeT() throws Exception {
		PipeT pipe = new PipeT();
		Set<Direction> pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(3, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.WEST));
		assertTrue(pipeLocations.contains(Direction.NORTH));
		assertTrue(pipeLocations.contains(Direction.EAST));

		pipe.rotateRight();
		pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(3, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.NORTH));
		assertTrue(pipeLocations.contains(Direction.EAST));
		assertTrue(pipeLocations.contains(Direction.SOUTH));

		pipe.rotateRight();
		pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(3, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.EAST));
		assertTrue(pipeLocations.contains(Direction.SOUTH));
		assertTrue(pipeLocations.contains(Direction.WEST));

		pipe.rotateRight();
		pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(3, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.SOUTH));
		assertTrue(pipeLocations.contains(Direction.WEST));
		assertTrue(pipeLocations.contains(Direction.NORTH));

		pipe.rotateRight();
		pipeLocations = pipe.getPipeLocations();
		assertNotNull(pipeLocations);
		assertEquals(3, pipeLocations.size());
		assertTrue(pipeLocations.contains(Direction.WEST));
		assertTrue(pipeLocations.contains(Direction.NORTH));
		assertTrue(pipeLocations.contains(Direction.EAST));
	}

}
