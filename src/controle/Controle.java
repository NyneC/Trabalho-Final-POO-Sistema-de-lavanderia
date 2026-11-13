package controle;

import java.security.cert.TrustAnchor;
import java.util.*;
import modelo.*;

public class Controle {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Servico> servicos = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();
    private List<Cobranca> cobrancas = new ArrayList<>();

    //Funções cliente
    public void CadastrarCliente(Cliente c){

        clientes.add(c);

    }
    public List<Cliente> ListarClientes(){

        return clientes;

    }
    //Funções serviço
    public void CadastrarServico(Servico s){

        servicos.add(s);

    }
    public List<Servico> ListarServicos(){

        return servicos;

    }
    //Funções pedido
    public void CriarPedido(Pedido p){

        pedidos.add(p);

    }
    public void AdicionarServicoAoPedido(Pedido p,Servico s,int qtd){

        if(qtd > 0){
           p.addItem(new ItemPedido(s,qtd));
        }

    }
    public List<Pedido> ListarPedidos(){

        return pedidos;

    }
    //Funções cobrança
    public void GerarCobranca(Cobranca c){

        cobrancas.add(c);

    }
    public List<Cobranca> ListaCobranca(){

        return cobrancas;

    }
    public void PagarCobranca(Cobranca c){

        c.setPago(true);

    }

}