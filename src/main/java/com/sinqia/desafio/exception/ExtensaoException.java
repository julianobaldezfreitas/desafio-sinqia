package com.sinqia.desafio.exception;

/**
 * Classe de exception para tratamento de erro
 * quando a extensão do arquivo de entrada não 
 * é válida.
 * @author juliano
 */
public class ExtensaoException extends MonitorException{

	private static final long serialVersionUID = 1L;

	public ExtensaoException() {
		super("Extensão de arquivo de entrada não suportado.");
	}
	
}
