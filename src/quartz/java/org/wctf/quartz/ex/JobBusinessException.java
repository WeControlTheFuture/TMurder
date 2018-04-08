package org.wctf.quartz.ex;

public class JobBusinessException extends RuntimeException {

	private static final long serialVersionUID = 3098337836584360885L;

	public JobBusinessException() {
		super();
	}

	public JobBusinessException(String message) {
		super(message);
	}

	public JobBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public JobBusinessException(Throwable cause) {
		super(cause);
	}
}
