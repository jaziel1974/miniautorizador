package com.vr.jaziel.miniautorizador.service.transaction.approvalservice;

public class ApprovalException extends Throwable {
    public enum ERROR_MESSAGE { 
        CARTAO_INEXISTENTE, SENHA_INVALIDA, SALDO_INSUFICIENTE
    }

    public ApprovalException(ERROR_MESSAGE message) {
        super(message.toString());
    }
}
