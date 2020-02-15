package com.sinqia.desafio.exception;

/**
 * Classe gen�rica de exceptions para tratamento de erros
 * na aplica��o.
 * @author juliano
 */
public class MonitorException extends Exception {

	private static final long serialVersionUID = 1L;

	public MonitorException(String mensagem) {
		super(mensagem);
	}

}