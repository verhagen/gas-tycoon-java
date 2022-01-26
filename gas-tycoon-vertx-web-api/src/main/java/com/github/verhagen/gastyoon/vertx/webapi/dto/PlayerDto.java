package com.github.verhagen.gastyoon.vertx.webapi.dto;

import java.util.UUID;

import com.github.verhagen.gastyoon.vertx.webapi.domain.ContextMigration;
import com.github.verhagen.gastyoon.vertx.webapi.domain.Player;

public class PlayerDto implements ContextMigration<Player> {
	private UUID id;
	private String name;
	private String emailAddress;
	private String passwordHash;


	@Override
	public void inject(Player player) {
		this.id = player.getId();
		this.name = player.getName();
		this.emailAddress = player.getEmailAddress();
		this.passwordHash = player.getPasswordHash();
	}

	@Override
	public Player extract() {
		return new Player.Builder()
				.id(id)
				.name(name)
				.emailAddress(emailAddress)
				.password(passwordHash)
				.create();
	}

	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	
}
