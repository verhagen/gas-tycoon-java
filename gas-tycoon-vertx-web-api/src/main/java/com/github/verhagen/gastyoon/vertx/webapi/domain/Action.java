package com.github.verhagen.gastyoon.vertx.webapi.domain;

import java.time.LocalDateTime;

public class Action {
	enum ActionType {
		// Game actions
		START
		, STOP
		, FINISH
		// Tile / Pipe actions
		, ROTATE_LEFT
		, ROTATE_RIGHT
	}

	private final LocalDateTime timestamp;
	private final int x;
	private final int y;
	private final ActionType actionType;


	public Action(Builder bldr) {
		timestamp = LocalDateTime.now();
		x = bldr.getX();
		y = bldr.getY();
		actionType = bldr.getActionType();
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ActionType getActionType() {
		return actionType;
	}


	public static class Builder {
		private int x;
		private int y;
		private ActionType actionType;

		public Action creeate() {
			return new Action(this);
		}
		
		public int getX() {
			return x;
		}
		public Builder x(int x) {
			this.x = x;
			return this;
		}
		public int getY() {
			return y;
		}
		public Builder y(int y) {
			this.y = y;
			return this;
		}
		public ActionType getActionType() {
			return actionType;
		}
		public Builder actionType(ActionType actionType) {
			this.actionType = actionType;
			return this;
		}
	}

}
