import { Table,message,Button } from "antd";
import Column from "antd/lib/table/Column";
import { Component } from "react";
import PessoaDataService from "../services/PessoaDataService";

export default class ListaPessoas extends Component{

    constructor(props){
        super(props)
        this.state ={
            pessoas: [],
            message: null
        }
    }

    //chamado sempre que o componente é(ciclo de vida)
    componentDidMount(){
        this.refreshPessoas();
    }
    
    refreshPessoas(){
        PessoaDataService.retriveAllPessoas()
        .then(
            response =>{
                console.log(response);
                this.setState({pessoas: response.data});
            }
        )
    }

    sucess = (record) => {
        if(record.isVacinada){
            record.isVacinada = false;
        }else{
            record.isVacinada = true;
        }
        PessoaDataService.updatePessoa(record,record.codigo);
        message.success('Status alterado com sucesso!');
    }

    render(){
        return (
            <div className = "container">
                <h2>Pessoas Cadastradas</h2>
                <div className = "container">
                    <Table dataSource = {this.state.pessoas}>
                        <Column title = "NOME" dataIndex ="nome" key= "nome"/>
                        <Column title = "CPF" dataIndex ="cpf" key= "cpf"/>
                        <Column title = "TELEFONE" dataIndex ="telefone" key= "telefone"/>
                        <Column title = "IDADE" dataIndex ="idade" key= "idade"/>
                        <Column title = "EMAIL" dataIndex ="email" key= "email"/>
                        <Column title = "VACINADA" dataIndex ="isVacinada" key= "isVacinada"
                            render ={(text,record)=>(<p>{String(record.isVacinada)}</p>)}/>
                        <Column title = "ATUALIZAR" key= "atualizar"
                            render = {(text,record)=>(<Button type = "primary" onClick = {()=>this.sucess(record)}>Alterar status</Button>)}/>
                    </Table>
                </div>
            </div>
        );
    }
}