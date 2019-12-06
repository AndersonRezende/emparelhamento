package PackagePrincipal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;
import javax.swing.border.*;

/** 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * TRABALHO TEORIA DOS GRAFOS - EMPARELHAMENTO DE GRAFOS			 *
 * 																	 *
 * DESENVOLVIDO POR:	ANDERSON FREIRE REZENDE e CICERO ADSON		 *
 * PROFESSOR: TENORIO FIGUEIREDO									 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
**/

public class ParteGrafica extends JFrame 
{

	private JTextArea txtSaida =new JTextArea(5, 80);;
	private JButton btnEntrada = new JButton("Abrir arquivo");
	private JPanel pPainelLateral = new JPanel();
	private JScrollPane pPainel = new JScrollPane(txtSaida);
	private JFileChooser fileChooser = new JFileChooser();
	private JLabel saida = new JLabel("<html>Total de vertices: 0 <br> Tempo Total: 0 <br> Emparelhamento total: 0 <br> Status: </html>");
  
	
	public ParteGrafica()
	{
		setLayout(new BorderLayout());
		
		//A PARTIR DAQUI AT�........................................
		JMenu arquivo = new JMenu("Arquivo");
		JMenuItem buscarMatriz = new JMenuItem("Abrir Arquivo");
		JMenuItem info = new JMenuItem("Informações");
		JMenuItem desenvolvedores = new JMenuItem("Desenvolvedores");
		JMenuItem sair = new JMenuItem("Sair");
		
		arquivo.setMnemonic('A');
		buscarMatriz.setMnemonic('o');
		info.setMnemonic('I');
		desenvolvedores.setMnemonic('D');
		sair.setMnemonic('S');
		
		buscarMatriz.addActionListener(new BtnEntradaListener());
		info.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						JOptionPane.showMessageDialog(null, "Funcionamento do software:\n"
															+">> O programa busca emparelhar os vértices de acordo com o seu grau. <<\n"
															+">> Os vértices de menor grau são emparelhados primeiro que os vértices que possuem maior grau.<<<\n\n"
															+"#1- Um vértice, v1, com o menor grau é escolhido.\n"
															+"#2- Um vértices, v2, da lista de adjacência de v1 que possua o menor grau é escolhido.\n"
															+"#3- Esses vértices serão emparelhados.\n"
															+"#4- Os vértices Adjacentes a v1 e a v2 são atualizados.\n"
															+"    *4.1- A atualização consiste em remover os vértices, v1 e v2, das listas de adjacência de seus adjacentes.\n"
															+"#5- Esses vértices, v1 e v2, são ignorados e o processo volta ao passo 1 até que todos os vértices restantes sejam de grau 0\n"
															+ "\n\nEssas etapas se repetem de forma geral 'n' vezes para poder gerar um melhor resultado possível.", 
															"Informações", JOptionPane.INFORMATION_MESSAGE, null);
					}
				}
		);
		desenvolvedores.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						JOptionPane.showMessageDialog(null, "Desenvolvido por:\n"
															+"Anderson Rezende\n"
															+"Cícero Adson", 
															"Desenvolvedores", JOptionPane.INFORMATION_MESSAGE, null);
					}
				});
		sair.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						dispose();
					}
				});
		
		arquivo.add(buscarMatriz);
		arquivo.addSeparator();
		arquivo.add(info);
		arquivo.add(desenvolvedores);
		arquivo.addSeparator();
		arquivo.add(sair);
		
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(arquivo);
		//AQUI � S� PRA CRIAR O MENU ..............................................
		
		//DAQUI EM DIANTE TA DA MESMA FORMA QUE TU FEZ......................................
		add(btnEntrada ,BorderLayout.SOUTH);
		add(pPainel ,BorderLayout.CENTER);
		add(pPainelLateral, BorderLayout.EAST);
    
		txtSaida.setLineWrap(true);
    	txtSaida.setWrapStyleWord(true);
    	setResizable(true);
    
    	pPainel.setBorder(new TitledBorder(new EtchedBorder(), "Resultado"));
    	pPainelLateral.setBorder(new TitledBorder(new EtchedBorder(), "Saída"));
    	pPainelLateral.setLayout( new FlowLayout());
    	pPainelLateral.add(saida);

    	pPainel.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
    
    	fileChooser.setFileFilter(new FileNameExtensionFilter("Matriz de adjacencia","txt"));  
    
    	setTitle("Emparelhamento Maximo"); 
    	setSize(300, 300);     
    	setLocationRelativeTo(null);
    
    	btnEntrada.addActionListener(new BtnEntradaListener());
    
    	setVisible(true); 
    	addWindowListener(new WindowAdapter() 
    	{
    		public void windowClosing(WindowEvent e) 
    		{
    			dispose();
    			System.exit(0);
    		}
    	});  
	} 
	
  public static void main(String[] args) 
  {
       java.awt.EventQueue.invokeLater(new Runnable() 
       {
    	   public void run() 
    	   {
    		   new ParteGrafica();
           }
       });
  }
  
  private class BtnEntradaListener implements ActionListener
  {
	  public void actionPerformed(ActionEvent e) 
	  {
		  fileChooser.setCurrentDirectory(fileChooser.getCurrentDirectory());
		  int returnValue = fileChooser.showOpenDialog(null);
      
		  if (returnValue == JFileChooser.APPROVE_OPTION) 
		  {
			  File selectedFile = fileChooser.getSelectedFile();

			  String caminho = selectedFile.getAbsolutePath();
			  Programa programa = new Programa();
			  txtSaida.setText(programa.lerArquivo(caminho, 5));			//ALTERAR ESSE 5 PELA QUANTIDADE DE VEZES QUE TU QUER
			  saida.setText("<html>Total de vertices: "+programa.getQuantidadeVertice()
			  				+"<br>Emparelhamento total: "+programa.getQuantidadeArestas()
			  				+"<br>Vertices emparelhados: "+programa.getQuantidadeArestas()*2
			  				+"</html>");
		  }    
	  }
  } 
}