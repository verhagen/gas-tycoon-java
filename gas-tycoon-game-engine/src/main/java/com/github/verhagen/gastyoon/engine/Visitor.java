package com.github.verhagen.gastyoon.engine;

public interface Visitor<A> {

	void nextRow();
	void visit(int x, int y, A actor);
	boolean skipEmptyLocations();

}
