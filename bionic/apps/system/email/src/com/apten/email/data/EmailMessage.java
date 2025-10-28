// Arquivo: bionic/apps/system/email/src/com/apten/email/data/EmailMessage.java

package com.apten.email.data;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Representa um unico item de E-mail (Mensagem) no sistema Apten OS.
 * Esta classe e imutavel (Immutable) apos a criacao para garantir a seguranca dos dados.
 */
public final class EmailMessage {
    private final long mId;
    private final String mSender;
    private final String mSubject;
    private final String mBody;
    private final long mTimestamp; // Tempo em milissegundos
    private final boolean mIsRead;
    private final List<String> mRecipients;
    private final List<Attachment> mAttachments;

    /**
     * Construtor completo para uma nova mensagem de e-mail.
     */
    public EmailMessage(long id, String sender, String subject, String body, 
                        long timestamp, boolean isRead, List<String> recipients, 
                        List<Attachment> attachments) {
        
        this.mId = id;
        this.mSender = sender;
        this.mSubject = subject;
        this.mBody = body;
        this.mTimestamp = timestamp;
        this.mIsRead = isRead;
        // Cria copias defensivas das listas
        this.mRecipients = recipients != null ? new ArrayList<>(recipients) : new ArrayList<>();
        this.mAttachments = attachments != null ? new ArrayList<>(attachments) : new ArrayList<>();
    }
    
    /**
     * Construtor simplificado para caixas de entrada.
     */
    public EmailMessage(long id, String sender, String subject, String body, long timestamp) {
        this(id, sender, subject, body, timestamp, false, null, null);
    }
    
    // --- Getters (acesso aos dados) ---
    
    public long getId() {
        return mId;
    }

    public String getSender() {
        return mSender;
    }

    public String getSubject() {
        return mSubject;
    }

    public String getBody() {
        return mBody;
    }

    public Date getDate() {
        return new Date(mTimestamp);
    }

    public boolean isRead() {
        return mIsRead;
    }

    /**
     * @return Uma copia imutavel da lista de destinatarios.
     */
    public List<String> getRecipients() {
        return mRecipients; // Retorna uma copia defensiva ou a lista imutavel
    }
    
    /**
     * @return Uma copia imutavel da lista de anexos.
     */
    public List<Attachment> getAttachments() {
        return mAttachments;
    }
    
    // --- Inner Class para Anexos ---
    
    /**
     * Define a estrutura de um anexo de e-mail.
     */
    public static class Attachment {
        private final String mFileName;
        private final long mFileSize;
        private final String mContentType;

        public Attachment(String fileName, long fileSize, String contentType) {
            this.mFileName = fileName;
            this.mFileSize = fileSize;
            this.mContentType = contentType;
        }
        
        public String getFileName() { return mFileName; }
        public long getFileSize() { return mFileSize; }
        public String getContentType() { return mContentType; }
    }
}
