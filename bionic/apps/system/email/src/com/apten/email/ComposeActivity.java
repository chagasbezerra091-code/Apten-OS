// Arquivo: bionic/apps/system/email/src/com/apten/email/ComposeActivity.java

package com.apten.email;

import apten.app.Activity;
import apten.ui.EditText;          // Assumindo um componente de entrada de texto
import apten.ui.Button;
import apten.view.View;
import apten.log.Log;
import com.apten.email.data.EmailMessage;

import java.util.Arrays;
import java.util.List;

/**
 * Activity que permite ao usuario compor e enviar um novo e-mail.
 */
public class ComposeActivity extends Activity {
    private static final String TAG = "AptenEmailCompose";
    
    // Componentes de UI
    private EditText mToField;
    private EditText mSubjectField;
    private EditText mBodyField;
    private Button mSendButton;
    
    private EmailManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 1. Configuracao da View (simulacao)
        setContentView(R.layout.activity_compose_email); 
        
        mToField = findViewById(R.id.edit_to);
        mSubjectField = findViewById(R.id.edit_subject);
        mBodyField = findViewById(R.id.edit_body);
        mSendButton = findViewById(R.id.btn_send);

        // 2. Obtem o Gerenciador de E-mail
        mManager = EmailApp.getEmailManager();

        // 3. Define o Listener do Botao Enviar
        mSendButton.setOnClickListener(this::onSendClicked);
        
        Log.d(TAG, "Tela de composicao pronta.");
    }
    
    /**
     * Lida com o clique no botao "Enviar".
     * @param v O botao que foi clicado.
     */
    private void onSendClicked(View v) {
        String recipientStr = mToField.getText().toString().trim();
        String subject = mSubjectField.getText().toString().trim();
        String body = mBodyField.getText().toString();
        
        // 1. Validacao Basica
        if (recipientStr.isEmpty()) {
            showToast("O campo 'Para' e obrigatorio.");
            return;
        }

        // 2. Processa Destinatarios (Exemplo: separa por virgula)
        List<String> recipients = Arrays.asList(recipientStr.split("\\s*,\\s*"));
        
        // 3. Cria o objeto EmailMessage (ID e Timestamp sao mockados, seriam reais no Manager)
        EmailMessage outgoingMessage = new EmailMessage(
            System.currentTimeMillis(), // Mock ID
            mManager.getCurrentUserAddress(), // Assumindo que o manager tem o endereco do remetente
            subject,
            body,
            System.currentTimeMillis(),
            true, // Mensagens enviadas sao consideradas lidas
            recipients,
            null // Sem anexos por enquanto
        );

        // 4. Envia a Mensagem
        boolean success = mManager.sendEmail(outgoingMessage);
        
        if (success) {
            showToast("E-mail enviado com sucesso!");
            finish(); // Fecha a tela de composicao
        } else {
            showToast("Falha no envio do e-mail. Verifique a conexao.");
            // Pode reabilitar o botao e manter os campos abertos
        }
    }
    
    /**
     * Placeholder para exibir uma mensagem Toast na UI do Apten OS.
     */
    private void showToast(String message) {
        Log.i(TAG, "TOAST: " + message);
        // Ex: Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
