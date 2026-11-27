package excecoes;



public class OperacaoLavanderiaException extends RuntimeException {

	    public OperacaoLavanderiaException(String mensagem) {
	        super(mensagem);
	    }

	    public OperacaoLavanderiaException(String mensagem, Throwable causa) {
	        super(mensagem, causa);
	    }
}
