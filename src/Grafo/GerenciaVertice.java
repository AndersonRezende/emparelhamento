package Grafo;
import java.util.LinkedList;
import java.util.Random;

public class GerenciaVertice 
{
	private Vertice[] listaVertice;										//Lista com todos os vertices do grafo
	private LinkedList<Vertice>[] listaGrau;							//Lista e vetor que guarda os vertices na posição de seu grau
																		//* Ex: v1 tem grau 1, então v1 ta localizado em listaGrau[1]
	private int quantidadeArestas = 0;
	private String emparelhamento = "";									//String para guardar as arestas
	
	public GerenciaVertice(Vertice[] listaVertice, LinkedList<Vertice>[] listaGrau)				//O construtor recebe as duas listas do programa principal
	{
		this.listaVertice = listaVertice;								//Passa a referencia dessas listas para as listas daqui
		this.listaGrau = listaGrau;			
	}
	
	public void procuraElementos(int n)										//Metodo onde o programa fica preso buscando vertices e emparelhando eles
	{
		Vertice v1,v2;
		while(listaGrau[0].size() != listaVertice.length )				//Enquanto a lista de grau "0" não for igual a quantidade total de elementos														
		{		
			v1 = buscarVertice(n);										//Busco um vertice
			v2 = buscarVertice(v1,n);										//Busco um vertice adjacente
			emparelhar(v1,v2);											//Emparelho esses vertices
		}
	}
	
	public Vertice buscarVertice(int n)										//Esse método busca o primeiro vertice com o menor grau disponivel
	{
		Vertice vertice = new Vertice(-1);
		int cont = 1;													//Começo buscando os de grau 1
		boolean achou = false;										
		
		while(cont < listaGrau.length && achou == false)				//Enquanto nao achar ou percorrer a lista inteira...
		{
			if(listaGrau[cont].size() > 0)								//Se houver algum vertice na posição do grau "cont"(começando por 1)
			{	
				achou = true;
				if(n == 0)
					vertice = listaGrau[cont].getFirst();					//Retorno esse vertice
				else
					if(n == 1)
						vertice = listaGrau[cont].getLast();
					else
						if(n > 1)
						{
							Random num = new Random();
							vertice = listaGrau[cont].get(num.nextInt(listaGrau[cont].size()));
						}
			}
			cont++;
		}
		return(vertice);
	}
	
	public Vertice buscarVertice(Vertice v1, int n)							//Esse método faz a mesma coisa do anterior, porém busca na lista de adjacencia do vertice passado por parametro
	{
		Vertice vertice = new Vertice(-1);
		int cont = 1;													
		boolean achou = false;		
		LinkedList<Vertice>[] listaGrauAux = v1.getListaGrau();				//Pego a referencia da lista de vertices adjacentes do vertice passado como parâmetro

		while(cont < listaGrauAux.length && achou == false)					//Enquanto não buscar em todas as posições ou não achar em um posição específica
		{
			if(listaGrauAux[cont].size() > 0)
			{		
				achou = true;
				if(n == 0)					//ESSA PARTE TESTA AS POSSIBILIDADES DIFERENTES, o 'n' do começo é a quantidade de vezes, se for 0 pega o primeiro, se for 1 o ultimo e se for mais testa randomicamente
					vertice = listaGrauAux[cont].getFirst();					//Retorno o primeiro
				else
					if(n == 1)
						vertice = listaGrauAux[cont].getLast();					//Retorno o ultimo
					else
						if(n > 1)
						{
							Random num = new Random();				
							vertice = listaGrauAux[cont].get(num.nextInt((listaGrauAux[cont].size())));	//Retorno qualquer um
						}
			}
			cont++;
		}
		return(vertice);
	}	
	
	public void emparelhar(Vertice v1, Vertice v2)							//Metodo para emparelhar os dois vertices
	{
		emparelhamento += "A("+v1.getNome()+","+v2.getNome()+")\n";
		quantidadeArestas++;
		atualizar(v1);														//Atualizo os vertices
		atualizar(v2);
	}	
	
	public void atualizar(Vertice vertice)									//Esse metodo remove o vertice emparelhado da lista de adjacencia dos outros vertices
	{
		Vertice v;	
		listaGrau[vertice.getGrau()].remove(vertice);						//Removo o vertice passado por parametro da lista de grau
		int cont = 0;
		
		while(cont < vertice.getlistaVerticesAdjacentes().size())			//Enquanto não percorrer toda a lista de adjacencia do vertice do parametro...
		{
			v = (Vertice) vertice.getlistaVerticesAdjacentes().get(cont);	//Pego a referencia do vertice "cont"(cont vai ser todos os vertices adjacentes)						
			listaGrau[v.getGrau()].remove(v);								//Esse vertice vai ser removido da lista Grau na posição que ele estava			
			v.removerDaLista(vertice);										//Vou remover o vertice passado por parametro da lista de adjacencia desse vertice																			
			listaGrau[v.getGrau()].add(v);									//Vou readicionar o vertice na nova posição da lista de Grau de acordo com o seu novo grau
			cont++;	
			v = new Vertice(-1);		//Instancio um novo só por garantia
		}
		vertice.limparLista();												//Limpo a lista de adjacencia do vertice passado por parametro
		listaGrau[0].add(vertice);											//E adiciono ele na lista de Grau 0
	}
	
	public String getEmparelhamento()										//Retorna a String de emparelhamento
	{	return(this.emparelhamento);	}
	
	public int getTamanho()
	{	return(this.quantidadeArestas);	}
}	