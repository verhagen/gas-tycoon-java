package com.github.dojogreenfoot.gastyoon.vertx.webapi.domain;

import java.util.UUID;

public class Player {
	private final UUID id;
	private final String name;
	private final String emailAddress;
	private final String passwordHash;

	public Player(Builder bldr) {
		this.id = bldr.getId() != null ? bldr.getId() : UUID.randomUUID();
		this.name = bldr.getName();
		this.emailAddress = bldr.getEmailAddress();
		this.passwordHash = bldr.getPasswordHash();
	}


	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPasswordHash() {
		return passwordHash;
	}



	public static class Builder {
		private UUID id;
		private String name;
		private String emailAddress;
		private String passwordHash;


		public Player create() {
			return new Player(this);
		}


		public UUID getId() {
			return id;
		}
		public Builder id(UUID id) {
			this.id = id;
			return this;
		}
		public String getName() {
			return name;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public String getEmailAddress() {
			return emailAddress;
		}
		public Builder emailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
			return this;
		}
		public String getPasswordHash() {
			return passwordHash;
		}
		public Builder password(String passwordHash) {
			this.passwordHash = passwordHash;
			return this;
		}
	}
}
