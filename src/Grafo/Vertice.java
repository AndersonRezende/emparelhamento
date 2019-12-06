package Grafo;
import java.util.LinkedList;

public class Vertice
{
	private int indice;
	private int grau;
	
	private LinkedList<Vertice> listaVerticesAdjacentes = new LinkedList<Vertice>();	//Lista de vertices adjacentes a o vertice atual
	private LinkedList<Vertice>[] listaGrau;											//Lista de Grau dos vertices adjacentes
	
	public Vertice(int indice)															//Construtor
	{	this.indice = indice;	}
	
	public void inserirVerticeAdjacente(Vertice vertice)								//Recebe um vertice e adiciona ele na lista de adjacencia
	{	
		listaVerticesAdjacentes.add(vertice);
		this.atualizarGrau();															//Toda vez que insiro ou removo vertices, é necessário atualizar o grau
	}
	
	public void preencherListaGrau(int qtdVertices)										//Esse método apenas preenche a lista de grau depois que a lista de adjacencia estiver pronta
	{
		listaGrau = new LinkedList[qtdVertices];										
		for(int aux = 0; aux < qtdVertices; aux++)
			listaGrau[aux] = new LinkedList<Vertice>();
		
		for(int l = 0; l < listaVerticesAdjacentes.size(); l++)
			listaGrau[listaVerticesAdjacentes.get(l).getGrau()].add(listaVerticesAdjacentes.get(l));		//Salva o vertice no indice de seu grau na lista
	}
	
	public void atualizarGrau()															//O grau é a quantidade de vertices adjacentes
	{	this.grau = listaVerticesAdjacentes.size();	}
	
	public void limparLista()															//Limpa todas as listas quando não for usar mais esse vertice
	{	
		listaVerticesAdjacentes.clear();
		for(int aux = 0; aux < listaGrau.length; aux++)
			listaGrau[aux].clear();
		grau = 0;
	}
	
	public void removerDaLista(Vertice vertice)											//Remove o vertice passado por parametro dessas listas
	{
		listaVerticesAdjacentes.remove(vertice);
		//listaGrau[vertice.getGrau()].remove(vertice);
		for(int m=0;m<listaGrau.length;m++)												//Correção para o bug da repetição de vertices hashuahashuahashua
			for(int n=0;n<listaGrau[m].size();n++)
				if(listaGrau[m].get(n).getIndice() == vertice.getIndice())
					listaGrau[m].remove(n);
		atualizarGrau();
	}
	
	public int getIndice()				//GET'S e SET'S
	{	return(this.indice);	}
	
	public int getGrau()
	{	return(this.grau);	}
	
	public String getNome()																//O nome do vertice é o indice + 1 pra poder começar do vertice 1
	{	return(""+(this.indice+1));	}
	
	public LinkedList[] getListaGrau()
	{	return(this.listaGrau);	}
	
	public LinkedList<Vertice> getlistaVerticesAdjacentes()								//Retorna essa lista de adjacencia
	{	return(listaVerticesAdjacentes);	}
}