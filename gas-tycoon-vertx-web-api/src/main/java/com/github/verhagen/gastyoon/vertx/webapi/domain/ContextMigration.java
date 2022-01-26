package com.github.verhagen.gastyoon.vertx.webapi.domain;

/**
 * This interface is for the injection of a <i>Domain Objects</i> into a <i>Data Transfer Object</i>.
 * as well as for the extraction of a <i><i>Domain Objects</i> from a <i>Data Transfer Object</i>.
 *
 * @param <T> The Type, 
 */
public interface ContextMigration<T> {

	void inject(T t);

	T extract();

}