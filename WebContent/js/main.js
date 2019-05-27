/**
 * 
 */
function toggleDisplayElement(target, className) {
  const element = document.querySelector("."+className);
  if(target.checked) {	  
	  element.style.display= 'none';  
  } else {
	  element.style.display= 'block'; 
  }  
}



function getEnderecoEntrega() {
    let selectTipoResidencia = document.getElementById('tipo-residencia0');
    let inputCepResidencial = document.getElementById('cep0').value;
    let selectTipoLogradouro = document.getElementById('tipo-logradouro0');
    let inputLogradouroResidencial = document.getElementById('logradouro0').value
    let inputBairroResidencial = document.getElementById('bairro0').value
    let inputCidadeResidencial = document.getElementById('cidade0').value
    let inputEstadoResidencial = document.getElementById('estado0').value
    let inputNumeroResidencial = document.getElementById('numero0').value
    let inputObservacoes = document.getElementById('observacoes0').value

    let optionsTipoLogradouro = selectTipoLogradouro.options
    let optionsTipoResidencia = selectTipoResidencia.options
    let tipoLogradouroSelecionado = ''
    let tipoResidenciaSelecionado = ''

    for (let index in optionsTipoLogradouro) {
        if(optionsTipoLogradouro.selectedIndex == index) {
            tipoLogradouroSelecionado  = optionsTipoLogradouro[index].innerText
         }
    }

    for (let index in optionsTipoResidencia) {
        if(optionsTipoResidencia.selectedIndex == index) {
            tipoResidenciaSelecionado  = optionsTipoResidencia[index].innerText
         }
    }
    
    let endereco = {
        tipoResidencia : tipoResidenciaSelecionado,
        cep : inputCepResidencial,
        tipoLogradouro : tipoLogradouroSelecionado,
        logradouro: inputLogradouroResidencial,
        bairro : inputBairroResidencial,
        cidade: inputCidadeResidencial,
        estado : inputEstadoResidencial,
        numero : inputNumeroResidencial,
        observacao : inputObservacoes
    }

    return endereco;
    
}

function preencherEnderecoEntrega() {
    let endereco = getEnderecoEntrega();

    let selectTipoResidencia = document.getElementById('tipo-residencia1')
    let inputCepResidencial = document.getElementById('cep1')
    let selectTipoLogradouro = document.getElementById('tipo-logradouro1')
    let inputLogradouro = document.getElementById('logradouro1')
    let inputBairro = document.getElementById('bairro1')
    let inputCidade = document.getElementById('cidade1')
    let inputEstado = document.getElementById('estado1')
    let inputNumero = document.getElementById('numero1')  
    let inputObservacoes = document.getElementById('observacoes1')
    
    selectTipoLogradouro.selectedOptions[0].innerText = endereco.tipoLogradouro;
    selectTipoResidencia.selectedOptions[0].innerText = endereco.tipoResidencia;
    inputCepResidencial.value = endereco.cep
    inputLogradouro.value = endereco.logradouro
    inputBairro.value = endereco.bairro
    inputCidade.value = endereco.cidade
    inputEstado.value = endereco.estado
    inputNumero.value = endereco.numero
    inputObservacoes.value = endereco.observacao
    
}

function preencherEnderecoCobranca() {
    let endereco = getEnderecoEntrega();

    let selectTipoResidencia = document.getElementById('tipo-residencia2')
    let inputCepResidencial = document.getElementById('cep2')
    let selectTipoLogradouro = document.getElementById('tipo-logradouro2')
    let inputLogradouro = document.getElementById('logradouro2')
    let inputBairro = document.getElementById('bairro2')
    let inputCidade = document.getElementById('cidade2')
    let inputEstado = document.getElementById('estado2')
    let inputNumero = document.getElementById('numero2')  
    let inputObservacoes = document.getElementById('observacoes2')
    
    selectTipoLogradouro.selectedOptions[0].innerText = endereco.tipoLogradouro;
    selectTipoResidencia.selectedOptions[0].innerText = endereco.tipoResidencia;
    inputCepResidencial.value = endereco.cep
    inputLogradouro.value = endereco.logradouro
    inputBairro.value = endereco.bairro
    inputCidade.value = endereco.cidade
    inputEstado.value = endereco.estado
    inputNumero.value = endereco.numero
    inputObservacoes.value = endereco.observacao
    
}

function mascara(t, mask){
 const i = t.value.length;
 const saida = mask.substring(1,0);
 const texto = mask.substring(i);
 
 if (texto.substring(0,1) != saida){
	t.value += texto.substring(0,1);
 }
}

/*price range*/

// if ($.fn.slider) {
//     $('#sl2').slider();
// }

// var RGBChange = function () {
//     $('#RGB').css('background', 'rgb(' + r.getValue() + ',' + g.getValue() + ',' + b.getValue() + ')')
// };

// /*scroll to top*/

// $(document).ready(function () {
//     $(function () {
//         $.scrollUp({
//             scrollName: 'scrollUp', // Element ID
//             scrollDistance: 300, // Distance from top/bottom before showing element (px)
//             scrollFrom: 'top', // 'top' or 'bottom'
//             scrollSpeed: 300, // Speed back to top (ms)
//             easingType: 'linear', // Scroll to top easing (see http://easings.net/)
//             animation: 'fade', // Fade, slide, none
//             animationSpeed: 200, // Animation in speed (ms)
//             scrollTrigger: false, // Set a custom triggering element. Can be an HTML string or jQuery object
//             //scrollTarget: false, // Set a custom target element for scrolling to the top
//             scrollText: '<i class="fa fa-angle-up"></i>', // Text for element, can contain HTML
//             scrollTitle: false, // Set a custom <a> title if required.
//             scrollImg: false, // Set true to use image
//             activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
//             zIndex: 2147483647 // Z-Index for the overlay
//         });
//     });
// });

function showAlert(ids) {
    const alerts = ids.split(",");
    alerts.forEach((item) => {
        document.getElementById(item).style.display="block";
    })
}

function hideElement(ids) {
    const alerts = ids.split(",");
    alerts.forEach((item) => {
        document.getElementById(item).style.display="none";
    })
}

function salvarAlteracoesEndereco() {
    hideElement('enderecoAlterado')
    showAlert('enderecoSalvo');
    setInterval(() => {
        hideElement('enderecoSalvo,salvarAlteracaoEndereco')
    }, 2000);

}

let radioEntregaEconomica = document.getElementById('economica')

let radioEntregaExpress = document.getElementById('express')

function showSelectedValue(element){
	
	let textValorFrete = document.getElementById('valorFrete')
	
	let innerText = document.createTextNode('');
	textValorFrete.appendChild(innerText);
	textValorFrete.innerText = 'R$' + element.value;
	let somaTotal = parseFloat(document.getElementById('soma-total').innerText);
	somaTotal += parseFloat(element.value);
}

function cupomPromocionalSelecionado(element, valor){
	
	let trDescPromocional = document.createElement('tr');
	let trTotal = document.getElementById('total')
	trTotal.insertAdjacentHTML('beforebegin', '<td>Cupom promocional</td><td>R$-'+valor+"</td>");
	let somaTotal = parseFloat(document.getElementById('soma-total').innerText);
	somaTotal += parseFloat(valor);
}

function cupomTrocaSelecionado(element, valor){
	
	let trTotal = document.getElementById('total')
	trTotal.insertAdjacentHTML('beforebegin', '<td>Cupom troca</td><td>R$-'+valor+"</td>");
	let somaTotal = parseFloat(document.getElementById('soma-total').innerText);
	somaTotal += parseFloat(valor);
}

document.getElementById('total');


async function buscarProdutosAtivos() {
	
	const dados = await fetch('/livraria/Pages/lumino/ConsultaProduto?page=product&operacao=CONSULTAR&codigo=0', {method: 'get'})	
	
	return dados;
}

async function alterarQuantidadeItensCarrinho(id, quantidade) {
	
	const dados = await fetch('/livraria/Pages/lumino/carrinho?operacao=CARRINHOALTERAR&codigo='+id+'&quantidade='+quantidade, {method: 'get'});
	return dados;
}

async function excluirItemCarrinho(id) {
	
	const dados = await fetch('/livraria/Pages/lumino/carrinho?operacao=CARRINHOEXCLUIR&codigo='+id, {method: 'get'})
	console.log(dados)
	document.querySelector("[data-cod="+"'"+id+"'"+"]").style.display="none";
	return dados;
}

async function calcularFrete(cep, formName) {
	let dados = await fetch('/livraria/Pages/lumino/calcularFrete?operacao=CALCULARFRETE&cep='+cep+'&formName='+formName, {method: 'post'})
	
	if(formName === 'checkout') {
		location.href="/livraria/Pages/lumino/checkout.jsp";		
	} else {
		location.href="/livraria/Pages/lumino/carrinho.jsp";
	}

	return dados;
}

let checkBoxStatus = document.getElementById('status');

checkBoxStatus.addEventListener('click', () => {
    let inputMotivoAtivacao = document.getElementById('inputMotivoAtivacao');
    let labelAtivacao = document.getElementById('labelMotivoAtivacao');
    let selectCategoriaAtivacao = document.getElementById('categoriaAtivacao');
    let labelCategoriaAtivacao = document.getElementById('labelCategoriaAtivacao')
    let spanCategoriaAtual = document.getElementById('categoriaAtual'); 

    if(checkBoxStatus.checked) {
        inputMotivoAtivacao.setAttribute('name','motivoAtivacao')
        labelAtivacao.innerHTML='Motivo Ativação'
        selectCategoriaAtivacao.setAttribute('name','categoriaAtivacao')
        labelCategoriaAtivacao.innerHTML='Categoria de ativação'
        spanCategoriaAtual.innerHTML = `${livro.categoriaAtivacao}`
        
    } else {
        inputMotivoAtivacao.setAttribute('name','motivoInativacao')
        labelAtivacao.innerHTML='Motivo Inativação'
        selectCategoriaAtivacao.setAttribute('name','categoriaInativacao')
        labelCategoriaAtivacao.innerHTML='Categoria de Inativação'
        spanCategoriaAtual.innerHTML = `${livro.categoriaInativacao}`
    }
})

