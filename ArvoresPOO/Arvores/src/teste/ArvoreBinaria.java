package teste;

public class ArvoreBinaria {

    // Classe interna para representar um nó da árvore
    class No {
        int valor;
        No esquerda, direita;

        public No(int valor) {
            this.valor = valor;
        }
    }

    private No raiz;

    // Método para adicionar um nó na árvore
    public void adicionarNo(int valor) {
        raiz = adicionarRecursivo(raiz, valor);
    }

    private No adicionarRecursivo(No atual, int valor) {
        if (atual == null) {
            return new No(valor);
        }

        if (valor < atual.valor) {
            atual.esquerda = adicionarRecursivo(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = adicionarRecursivo(atual.direita, valor);
        }
        return atual;
    }

    // Método para buscar um valor na árvore
    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(No atual, int valor) {
        if (atual == null) {
            return false;
        }
        if (valor == atual.valor) {
            return true;
        }
        return valor < atual.valor
                ? buscarRecursivo(atual.esquerda, valor)
                : buscarRecursivo(atual.direita, valor);
    }

    // Retornar a raiz da árvore
    public No getRaiz() {
        return raiz;
    }

    // Percurso pré-ordem
    public String percursoPreOrdem() {
        StringBuilder resultado = new StringBuilder();
        percursoPreOrdemRecursivo(raiz, resultado);
        return resultado.toString().trim();
    }

    private void percursoPreOrdemRecursivo(No atual, StringBuilder resultado) {
        if (atual != null) {
            resultado.append(atual.valor).append(" ");
            percursoPreOrdemRecursivo(atual.esquerda, resultado);
            percursoPreOrdemRecursivo(atual.direita, resultado);
        }
    }

    // Percurso in-ordem
    public String percursoInOrdem() {
        StringBuilder resultado = new StringBuilder();
        percursoInOrdemRecursivo(raiz, resultado);
        return resultado.toString().trim();
    }

    private void percursoInOrdemRecursivo(No atual, StringBuilder resultado) {
        if (atual != null) {
            percursoInOrdemRecursivo(atual.esquerda, resultado);
            resultado.append(atual.valor).append(" ");
            percursoInOrdemRecursivo(atual.direita, resultado);
        }
    }

    // Percurso pós-ordem
    public String percursoPosOrdem() {
        StringBuilder resultado = new StringBuilder();
        percursoPosOrdemRecursivo(raiz, resultado);
        return resultado.toString().trim();
    }

    private void percursoPosOrdemRecursivo(No atual, StringBuilder resultado) {
        if (atual != null) {
            percursoPosOrdemRecursivo(atual.esquerda, resultado);
            percursoPosOrdemRecursivo(atual.direita, resultado);
            resultado.append(atual.valor).append(" ");
        }
    }

    
    public String percursoProfundidade() {
        StringBuilder resultado = new StringBuilder();
        percursoProfundidadePreOrdem(raiz, resultado);
        return resultado.toString().trim();
    }

    private void percursoProfundidadePreOrdem(No atual, StringBuilder resultado) {
        if (atual != null) {
            resultado.append(atual.valor).append(" "); // Visita o nó atual
            percursoProfundidadePreOrdem(atual.esquerda, resultado); // Visita o filho à esquerda
            percursoProfundidadePreOrdem(atual.direita, resultado);  // Visita o filho à direita
        }
    }

    // Remover um nó da árvore
    public void removerNo(int valor) {
        raiz = removerRecursivo(raiz, valor);
    }

    private No removerRecursivo(No atual, int valor) {
        if (atual == null) {
            return null;
        }

        if (valor == atual.valor) {
            // Caso 1: nó folha
            if (atual.esquerda == null && atual.direita == null) {
                return null;
            }

            // Caso 2: nó com um filho
            if (atual.esquerda == null) {
                return atual.direita;
            }
            if (atual.direita == null) {
                return atual.esquerda;
            }

            // Caso 3: nó com dois filhos
            int menorValor = encontrarMenorValor(atual.direita);
            atual.valor = menorValor;
            atual.direita = removerRecursivo(atual.direita, menorValor);
            return atual;
        }

        if (valor < atual.valor) {
            atual.esquerda = removerRecursivo(atual.esquerda, valor);
            return atual;
        }

        atual.direita = removerRecursivo(atual.direita, valor);
        return atual;
    }

    private int encontrarMenorValor(No raiz) {
        return raiz.esquerda == null ? raiz.valor : encontrarMenorValor(raiz.esquerda);
    }
}
