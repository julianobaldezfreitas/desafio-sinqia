package com.sinqia.desafio.exception;

/**
 * Classe de exception para tratamento de erro
 * quando a extens�o do arquivo de entrada n�o 
 * � v�lida.
 * @author juliano
 */
public class ExtensaoException extends MonitorException{

	private static final long serialVersionUID = 1L;

	public ExtensaoException() {
		super("Extens�o de arquivo de entrada n�o suportado.");
	}
	
}