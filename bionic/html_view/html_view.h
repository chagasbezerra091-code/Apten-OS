// Arquivo: bionic/html_view/html_view.h

#ifndef APTEN_OS_HTML_VIEW_H
#define APTEN_OS_HTML_VIEW_H

#include <stddef.h> // Para size_t

// Estrutura que representa o documento HTML parseado.
// Voce pode expandir isso para uma arvore DOM (Document Object Model) no futuro.
typedef struct {
    char* title;         // Titulo da pagina (Conteudo de <title>)
    char* body_content;  // Conteudo de texto plano extraido do <body>
    size_t content_size; // Tamanho do conteudo
    int status_code;     // Codigo de status (ex: 200 OK)
} AptenHtmlDocument;


// Enumeração de Erros
typedef enum {
    HTML_OK = 0,
    HTML_ERROR_NULL_INPUT,
    HTML_ERROR_MEMORY,
    HTML_ERROR_PARSING
} HtmlStatus;


/**
 * @brief Inicializa e processa o buffer HTML.
 * @param html_buffer O buffer de entrada contendo o codigo HTML bruto.
 * @param buffer_len O comprimento do buffer de entrada.
 * @param out_doc Ponteiro para a estrutura onde o resultado sera armazenado.
 * @return HtmlStatus indicando sucesso ou o tipo de erro.
 */
HtmlStatus apten_html_parse(const char* html_buffer, size_t buffer_len, AptenHtmlDocument* out_doc);


/**
 * @brief Libera a memoria alocada pela estrutura AptenHtmlDocument.
 * @param doc A estrutura a ser liberada.
 */
void apten_html_free_document(AptenHtmlDocument* doc);

#endif // APTEN_OS_HTML_VIEW_H
