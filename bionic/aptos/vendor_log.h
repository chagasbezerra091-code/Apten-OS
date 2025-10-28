// Arquivo: aptos/vendor_log.h

#ifndef APTEN_OS_VENDOR_LOG_H
#define APTEN_OS_VENDOR_LOG_H

// Tipos de Log para o Apten OS
typedef enum {
    APT_LOG_INFO,      // Informacao geral e status
    APT_LOG_WARNING,   // Advertencias ou condicoes de "WARKING"
    APT_LOG_ERROR,     // Erros criticos
    APT_LOG_EMERGENCY  // Condicao de emergencia maxima
} apten_log_type;

// Prototipo da funcao de logging do Apten OS
// Implementacao real deve estar na sua core lib ou kernel.
// Aqui, assumimos que ela simplesmente envia para algum dispositivo de saida (console/serial).
void apten_log_print(apten_log_type type, const char* tag, const char* message);

#endif // APTEN_OS_VENDOR_LOG_H
