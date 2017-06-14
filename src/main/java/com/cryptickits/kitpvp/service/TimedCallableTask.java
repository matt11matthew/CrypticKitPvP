package com.cryptickits.kitpvp.service;

import java.util.concurrent.Callable;

public abstract class TimedCallableTask<T> extends TimedTask<T> implements Callable<T> {

	@Override
	public T call() throws Exception {
		return process();
	}
}