package com.sinqia.desafio.exception;

/**
 * Classe de exception para tratamento de erro
 * quando o c�digo inicial da linha não
 * é válido
 * @author juliano
 */
public class CodigoLinhaException extends MonitorException {

	private static final long serialVersionUID = 1L;

	public CodigoLinhaException() {
		super("C�digo da linha n�o suportado!.");
	}
}
