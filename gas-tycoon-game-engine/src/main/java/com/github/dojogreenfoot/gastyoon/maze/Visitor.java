package com.github.dojogreenfoot.gastyoon.maze;

public interface Visitor<A> {

	void nextRow();
	void visit(int x, int y, A actor);
	boolean skipEmptyLocations();

}
