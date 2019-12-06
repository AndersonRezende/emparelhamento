package PackagePrincipal;
import java.io.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import Grafo.*;

public class Programa 
{
	private String resultado = "", resultadoMaior = "";
	private int qntdArestas = 0, qntdArestasMaior = 0, n = 0;
	private int qntdVertices = 0;
	
	public String lerArquivo(String caminho, int quantidadeBusca)
	{	
		int[][] matrizOriginal;

		while(n < quantidadeBusca)				//Realiza o processo tantas vezes
		{
			try
			{			
				File grafo = new File(caminho);
							
				LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(grafo));			//Descobrir a quantidade de vertices
				linhaLeitura.skip(grafo.length());
				int qtdLinha = linhaLeitura.getLineNumber()+1;											
				linhaLeitura.close();																	//FIM QUANTIDADE DE VERTICES
				
				qntdVertices = qtdLinha;
				matrizOriginal = new int[qtdLinha][qtdLinha];											//Criação da matriz e lista de vertices
		
				FileInputStream entrada = new FileInputStream(grafo);									//Leitura da matriz
				InputStreamReader entradaFormatada = new InputStreamReader(entrada);
				int ich = entradaFormatada.read();
		
				String aux = "";
				int l = 0,c = 0;
				while( ich!=-1)																			//Enquanto nao for o ultimo elemento, char ==> -1
				{		
					if(ich >= 48 && ich <= 57)															//Se for do tipo numérico, codigo tabela ascii
					{
						while(ich >= 48 && ich <= 57)
						{
							aux += (char)ich;															//crio uma string aux com os elementos
							ich = entradaFormatada.read();												//caso seja um inteiro com mais de 1 digitos
						}																				//Fico no while se for numerico
						matrizOriginal[l][c] = Integer.parseInt(aux);									//passa o inteiro para a matriz
						aux = "";																		//zera a string para poder usar depois
						c++;																			
					}
					if(c >= qtdLinha)																	//Quando c for maior que a quantidade de vertice, zera c
					{
						c = 0;
						l++;
					}
					if(ich != -1)
						ich = entradaFormatada.read();	
			
				}																						//Matriz já gerada																						
				entrada.close();
				entradaFormatada.close();																//FIM LEITURA da matriz
		
				Vertice[] listaVertices = new Vertice[qtdLinha];
				LinkedList<Vertice>[] listaGrau = new LinkedList[qtdLinha];									
		
				for(l = 0;l < matrizOriginal.length; l++)												//Criação dos vertices
					listaVertices[l] = new Vertice(l);
		
				for(l = 0;l < matrizOriginal.length; l++)												//Criação da lista de adjacencia
				{
					for(c = 0;c < matrizOriginal.length; c++)
					{
						if(matrizOriginal[l][c] != 0 && l != c)
						{
							listaVertices[l].inserirVerticeAdjacente(listaVertices[c]);
						}
					}	
					listaGrau[l] = new LinkedList<Vertice>();
				}
	
				for(l = 0; l < matrizOriginal.length; l++)												//Criação da lista de grau
				{
					listaGrau[listaVertices[l].getGrau()].add(listaVertices[l]);
					listaVertices[l].preencherListaGrau(qtdLinha);
				}
				
				GerenciaVertice gerente = new GerenciaVertice(listaVertices, listaGrau);
				gerente.procuraElementos(quantidadeBusca);
				
				resultado = gerente.getEmparelhamento();
				qntdArestas = gerente.getTamanho();
			}
			catch(FileNotFoundException exception)
			{	
				JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo:\n"+exception.toString(), "Erro na leitura", JOptionPane.ERROR_MESSAGE, null);
			}
			catch (IOException exception) 
			{
				JOptionPane.showMessageDialog(null, "Erro:\n"+exception.toString(), "Erro", JOptionPane.ERROR_MESSAGE, null);
			}
			
			if(qntdArestas > qntdArestasMaior)															//VERIFICA SE O RESULTADO É MAIOR OU NÃO PRA SUBSTITUIR O MELHOR
			{
				qntdArestasMaior = qntdArestas;
				resultadoMaior = resultado;
			}
			n++;
		}																								//FIM WHILE
		return (resultadoMaior);
	}
	
	public int getQuantidadeArestas()
	{	return(this.qntdArestasMaior);	}
	
	public int getQuantidadeVertice()
	{	return(this.qntdVertices);	}
}
