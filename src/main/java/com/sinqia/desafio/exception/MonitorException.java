package com.sinqia.desafio.exception;

/**
 * Classe genérica de exceptions para tratamento de erros
 * na aplicação.
 * @author juliano
 */
public class MonitorException extends Exception {

	private static final long serialVersionUID = 1L;

	public MonitorException(String mensagem) {
		super(mensagem);
	}

}
