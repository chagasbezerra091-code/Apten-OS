// Arquivo: bionic/apps/google play/src/com/apten/gplay/store/PlayStoreActivity.java

package com.apten.gplay.store;

import apten.app.Activity;
import apten.ui.WebView;           // Assumindo um componente WebView para exibir o conteúdo
import apten.ui.ProgressBar;
import apten.log.Log;
import apten.net.Uri;

/**
 * Activity principal da Google Play Store. 
 * Esta tela carrega o conteudo da loja usando o WebView apos a autenticacao.
 */
public class PlayStoreActivity extends Activity {
    private static final String TAG = "AptenPlayStoreUI";
    
    private WebView mWebView;
    private ProgressBar mLoadingBar;
    private PlayStoreService mService;
    
    // URL base simulada para o Google Play
    private static final String PLAY_STORE_URL = "https://play.google.com/store/apps"; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_play_store);
        mWebView = findViewById(R.id.play_store_webview);
        mLoadingBar = findViewById(R.id.loading_progress_bar);
        
        mService = PlayStoreService.getInstance(this);

        // 1. Inicia o processo de login/autenticacao
        startLoginAndLoad();
    }
    
    private void startLoginAndLoad() {
        mLoadingBar.setVisibility(View.VISIBLE);
        
        // Simula o processo de autenticacao em background
        mService.authenticateWithGoogle((success, authToken) -> {
            // Este callback roda na thread principal (simulacao)
            mLoadingBar.setVisibility(View.GONE);
            
            if (success) {
                Log.i(TAG, "Autenticacao Google bem-sucedida. Token: " + authToken.substring(0, 10) + "...");
                loadPlayStore(authToken);
            } else {
                Log.e(TAG, "Falha na autenticacao. Nao foi possivel carregar a loja.");
                showError("Falha na conexao com a conta Google.");
            }
        });
    }

    private void loadPlayStore(String authToken) {
        // 1. Configura o WebView para injetar o token de autenticacao (simulacao)
        mWebView.addJavascriptInterface(new PlayStoreBridge(authToken), "AptenGPlay");
        
        // 2. Carrega a URL da Play Store.
        // Na vida real, a URL e os headers seriam especificos para o dispositivo/token.
        mWebView.loadUrl(Uri.parse(PLAY_STORE_URL));
        mWebView.setVisibility(View.VISIBLE);
    }
    
    private void showError(String message) {
        // Placeholder para mostrar a mensagem de erro na tela
        Log.crit(TAG, "ERRO UI: " + message);
    }
    
    // ... Inner Class para interacao JavaScript-Java
    private class PlayStoreBridge {
        private final String token;
        public PlayStoreBridge(String token) {
            this.token = token;
        }
        
        // Metodo que o JS na WebView pode chamar para obter o token (simulacao)
        @JavascriptInterface
        public String getAuthToken() {
            return token;
        }
    }
}
