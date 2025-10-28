// Arquivo: bionic/html_view/html_view.cpp

#include "html_view.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Etiqueta (TAG) para o modulo de Log
#define HTML_VIEW_TAG "APT_HTML_VIEW"

// Funcao de log simplificada (adaptada da sua estrutura anterior)
static void log_html(const char* level, const char* message) {
    printf("[%s] [%s] %s\n", HTML_VIEW_TAG, level, message);
}


// Implementacao do Parser (Muito Simples e Limitada a busca por <body>)
HtmlStatus apten_html_parse(const char* html_buffer, size_t buffer_len, AptenHtmlDocument* out_doc) {
    
    // 1. Validacao Basica
    if (!html_buffer || !out_doc || buffer_len == 0) {
        log_html("ERROR", "Buffer de entrada nulo ou vazio.");
        return HTML_ERROR_NULL_INPUT;
    }

    log_html("INFO", "Iniciando o parsing do documento HTML...");

    // Garante que o documento de saida esteja limpo
    memset(out_doc, 0, sizeof(AptenHtmlDocument));
    out_doc->status_code = 200; // Default para OK

    // --- Simples logica de busca por tags ---
    
    // Busca por <body>
    const char* body_start = strstr(html_buffer, "<body>");
    const char* body_end = strstr(html_buffer, "</body>");
    
    if (body_start && body_end && body_end > body_start) {
        
        // Ajusta o ponteiro para o inicio do conteudo (apos "<body>")
        body_start += strlen("<body>");
        size_t content_size = body_end - body_start;

        // 2. Alocacao de Memoria para o Conteudo
        out_doc->body_content = (char*)malloc(content_size + 1);
        if (!out_doc->body_content) {
            log_html("ERROR", "Falha na alocacao de memoria para o body_content.");
            return HTML_ERROR_MEMORY;
        }

        // Copia o conteudo
        memcpy(out_doc->body_content, body_start, content_size);
        out_doc->body_content[content_size] = '\0'; // Null-terminator
        out_doc->content_size = content_size;
        
        log_html("INFO", "Conteudo do Body extraido com sucesso.");

    } else {
        log_html("WARNING", "Tags <body> ou </body> nao encontradas. Tratando como texto puro.");
        // Se as tags nao forem encontradas, voce pode optar por copiar o buffer inteiro
        // Logica de erro aqui, dependendo da politica do Apten OS
        out_doc->status_code = 404; // Nao encontrado, ou outra logica
    }

    // Nota: O parsing de tags aninhadas (DOM) e mais complexo e exigiria 
    // um parser de estado ou uma biblioteca dedicada. Este é apenas o esqueleto.

    return HTML_OK;
}


// Implementacao da Liberacao de Memoria
void apten_html_free_document(AptenHtmlDocument* doc) {
    if (doc) {
        if (doc->title) {
            free(doc->title);
            doc->title = NULL;
        }
        if (doc->body_content) {
            free(doc->body_content);
            doc->body_content = NULL;
        }
        log_html("INFO", "Memoria do documento HTML liberada.");
        // Não libera o 'doc' em si, apenas seus membros internos.
    }
}
