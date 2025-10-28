// Arquivo: bionic/bootable/recovery/ui.h
#ifndef APTEN_OS_RECOVERY_UI_H
#define APTEN_OS_RECOVERY_UI_H

#include <vector>
#include <string>

// Classe Abstrata/Base para a Interface de Usuario do Recovery
class RecoveryUI {
public:
    // Exibe o menu e espera pelo input do usuario.
    // Retorna o indice da opcao selecionada (0-base) ou -1 em caso de erro/timeout.
    virtual int show_menu(const std::vector<std::string>& options, const std::string& title) = 0;

    // Funcao para mostrar mensagens de status ou progresso
    virtual void set_status(const std::string& message) = 0;

    // Destrutor virtual
    virtual ~RecoveryUI() {}
};

// Funcao de fabrica para criar e inicializar a UI (Ex: UI Serial/Console/Grafica)
RecoveryUI* init_recovery_ui();

#endif // APTEN_OS_RECOVERY_UI_H
