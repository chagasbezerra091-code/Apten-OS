// Arquivo: bionic/apps/system/email/src/com/apten/email/MainActivity.java

package com.apten.email;

import apten.app.Activity;          // Classe base para telas (sua arquitetura de UI)
import apten.ui.ListView;           // Assumindo um componente de lista
import apten.ui.Button;
import apten.view.View;
import apten.content.Intent;
import com.apten.email.data.EmailMessage; // Modelo de dados a ser criado

import java.util.List;

/**
 * Activity principal que exibe a Caixa de Entrada (Inbox) do usuario.
 */
public class MainActivity extends Activity {
    private static final String TAG = "AptenEmailUI";
    private ListView mInboxListView;
    private EmailManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 1. Configuracao da View (simulacao)
        setContentView(R.layout.activity_main_inbox); // R.layout. deve ser um recurso Apten
        mInboxListView = findViewById(R.id.inbox_list);
        Button composeButton = findViewById(R.id.btn_compose);

        // 2. Obtem o Manager
        mManager = EmailApp.getEmailManager();

        // 3. Define a logica da lista e botoes
        mInboxListView.setOnItemClickListener((parent, view, position, id) -> {
            EmailMessage msg = (EmailMessage)parent.getItemAtPosition(position);
            navigateToEmailView(msg.getId());
        });

        composeButton.setOnClickListener(v -> {
            navigateToCompose();
        });

        // Inicia a carga de dados
        loadInboxData();
    }

    private void loadInboxData() {
        // Pede os e-mails para o manager
        List<EmailMessage> messages = mManager.getLatestInbox();
        // Atualiza o adaptador da lista (adapter) com 'messages'
        // mInboxListView.setAdapter(new EmailAdapter(this, messages)); 
        Log.d(TAG, "Caixa de Entrada carregada com " + messages.size() + " e-mails.");
    }
    
    // Placeholder para a navegacao
    private void navigateToEmailView(long messageId) {
        Intent intent = new Intent(this, EmailViewActivity.class);
        intent.putExtra("message_id", messageId);
        startActivity(intent);
    }

    private void navigateToCompose() {
        Intent intent = new Intent(this, ComposeActivity.class);
        startActivity(intent);
    }
    
    // ... outros metodos de ciclo de vida (onResume, onPause)
}
