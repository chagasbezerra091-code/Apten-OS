// Arquivo: bionic/bootable/recovery/ui.cpp

#include "ui.h"
#include <iostream>
#include <stdio.h>

// Etiqueta (TAG) para o modulo de Log
#define RECOVERY_UI_TAG "APT_RECOVERY_UI"

// -------------------------------------------------------------------------
// Implementacao Simples da UI (Exemplo: Baseada em Console/Serial)
// -------------------------------------------------------------------------

class ConsoleRecoveryUI : public RecoveryUI {
public:
    ConsoleRecoveryUI() {
        printf("[%s] UI de Console Inicializada. Esperando Input...\n", RECOVERY_UI_TAG);
    }

    int show_menu(const std::vector<std::string>& options, const std::string& title) override {
        printf("\n====================================\n");
        printf("  %s\n", title.c_str());
        printf("====================================\n");

        for (size_t i = 0; i < options.size(); ++i) {
            printf("  %lu. %s\n", (unsigned long)i + 1, options[i].c_str());
        }
        printf("------------------------------------\n");
        printf("Digite o numero da opcao e pressione ENTER: ");
        
        int choice = -1;
        // Na vida real, a entrada seria lida de um teclado, touch ou porta serial.
        // Aqui, usamos scanf/cin como um placeholder.
        if (scanf("%d", &choice) != 1) {
            // Limpa o buffer de entrada se falhar a leitura
            // (Simulacao de tratamento de erro de entrada)
            int c;
            while ((c = getchar()) != '\n' && c != EOF) {}
            return -1; 
        }

        // Mapeia de 1-base (mostrado) para 0-base (indice)
        if (choice >= 1 && choice <= (int)options.size()) {
            return choice - 1; 
        }
        
        printf("[%s] Erro de Selecao: Opcao %d invalida.\n", RECOVERY_UI_TAG, choice);
        return -1; // Retorna erro ou opcao invalida
    }

    void set_status(const std::string& message) override {
        printf("[%s] STATUS: %s\n", RECOVERY_UI_TAG, message.c_str());
    }
};

// -------------------------------------------------------------------------
// Funcao de Fabrica
// -------------------------------------------------------------------------

RecoveryUI* init_recovery_ui() {
    // Escolhe a implementacao da UI a ser usada (por exemplo, baseada em flags de boot)
    // Por enquanto, retorna a UI de Console simples.
    return new ConsoleRecoveryUI();
}
