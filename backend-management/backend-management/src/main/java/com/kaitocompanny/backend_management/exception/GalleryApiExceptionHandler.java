package com.kaitocompanny.backend_management.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GalleryApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    // 1) JSON malformado ou atributo desconhecido
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String msgUsuario = messageSource.getMessage("mensagem.invalida", null, Locale.getDefault());
        String msgDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        Erro erro = new Erro(msgUsuario, msgDev);

        return handleExceptionInternal(ex, erro, headers, HttpStatus.BAD_REQUEST, request);
    }

    // 2) Bean Validation (javax.validation) — campos inválidos
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<Erro> erros = criarListaErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> criarListaErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError f : bindingResult.getFieldErrors()) {
            String msgUsuario = messageSource.getMessage(f, Locale.getDefault());
            String msgDev = f.toString();
            erros.add(new Erro(msgUsuario, msgDev));
        }

        return erros;
    }

    // 3) Recurso não encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Erro> handleResourceNotFound(ResourceNotFoundException ex) {
        String msgUsuario = ex.getMessage();
        String msgDev = ex.toString();
        Erro erro = new Erro(msgUsuario, msgDev);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 4) Erro genérico (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Erro> handleAll(Exception ex) {
        String msgUsuario = messageSource.getMessage("erro.internal", null, Locale.getDefault());
        String msgDev = ex.toString();
        Erro erro = new Erro(msgUsuario, msgDev);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    // Classe interna para payload de erro
    public static class Erro {
        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }
        public String getMensagemUsuario() { return mensagemUsuario; }
        public String getMensagemDesenvolvedor() { return mensagemDesenvolvedor; }
    }
}
