window.onload = function() {

  const selectEnderecos = document.getElementById('endereco-selecionado')
  const selectCartao1= document.getElementById('numero-cartao1')
  const selectCartao2= document.getElementById('numero-cartao2')
  const tituloCuponsPromocionais = document.getElementById('cuponsPromocionais')
  const tituloCuponsTroca = document.getElementById('cuponsTroca')
  
  async function buscarDadosUsuario() {
    let url = '/livraria/Pages/lumino/buscarDados'
  
    let dados = await fetch(url, {
          method: 'get',      
          headers: {
              Accept: 'application/json',
              'Content-Type': 'application/json',
            }})

    let JSONDados = await dados.json();
  
    return JSONDados;
  }
  
  async function buildPage() {
  
    const dadosUsuario = await buscarDadosUsuario();
  
    console.log(dadosUsuario)
  
    const { 
            enderecosEntrega, 
            enderecosResidenciais, 
            enderecosCobranca, 
            cartoes, 
            cuponsDiferenca,
            cuponsPromocionais, 
            cuponsTroca 
          } = dadosUsuario
  
          createEnderecosEntregaOptions(enderecosEntrega)
          createCartoesOptions1(cartoes) 
          createCartoesOptions2(cartoes)
          createCuponsDiferenca(cuponsDiferenca)
          createCuponsTroca(cuponsTroca)
          createCuponsPromocionais(cuponsPromocionais)
  
  }
  
  function createEnderecosEntregaOptions(enderecos) {
    enderecos.forEach( endereco => {
      const {
              id,
              logradouro, 
              numero,
              bairro,
              cep,
              estado 
            } = endereco
  
      const option = document.createElement('option')
      option.value = id
      option.innerText = `${logradouro},${numero} - ${bairro} - ${estado} - CEP: ${cep}`
  
      selectEnderecos.appendChild(option);
  
    });
  }
  
  function createCuponsDiferenca(cuponsDiferenca) {
	  cuponsDiferenca.forEach( cupom => {
	      const {
	              id,
	              valor, 
	              codigo,
	              tipo,
	              dataDeValidade
	            } = cupom
	  
	      const {day, month, year } = dataDeValidade
	  
	      const ul = document.createElement('ul')
	      const li = document.createElement('li')
	      const input = document.createElement('input');
	      const divTipo = document.createElement('div')
	      const divCodigo = document.createElement('div')
	      const divValor = document.createElement('div')
	      const divValidade = document.createElement('div')

	      input.addEventListener('click', () => {
	        cupomTrocaSelecionado(this,codigo)
	      })
	      input.setAttribute('type','checkbox')
	      input.setAttribute('name','cupom-troca')
	      input.setAttribute('value',id)
	      divTipo.innerText = `Tipo: ${tipo}` 
	      divCodigo.innerText = `Código: ${codigo}`.toUpperCase() 
	      divValor.innerText = `Valor: ${valor}`
	      divValidade.innerText = `Validade: ${day}/${month}/${year}`
	      
	      li.className = 'list-group-item'
	      ul.className = 'list-group'
	      li.appendChild(input)
	      li.appendChild(divTipo)
	      li.appendChild(divCodigo)
	      li.appendChild(divValor)
	      li.appendChild(divValidade)
	      ul.appendChild(li)
	      
	      tituloCuponsTroca.insertAdjacentElement('afterend', ul)
	       
	  });
	}
  
    function createCartoesOptions1(cartoes) {
    cartoes.forEach( cartao => {
      const {
              id,
              bandeira, 
              nomeTitular,
              numero
            } = cartao
  
      const { nome } = bandeira
  
      const option = document.createElement('option')
      option.value = id
      option.innerText = `Titular:${nomeTitular} - ${numero} - ${nome}`
  
      selectCartao1.appendChild(option);      
    });
  }

  function createCartoesOptions2(cartoes) {
    cartoes.forEach( cartao => {
      const {
              id,
              bandeira, 
              nomeTitular,
              numero
            } = cartao
  
      const { nome } = bandeira
  
      const option = document.createElement('option')
      option.value = id
      option.innerText = `Titular:${nomeTitular} - ${numero} - ${nome}`
  
      selectCartao2.appendChild(option);      
    });
  }
  
  function createCuponsTroca(cuponsTroca) {
    cuponsTroca.forEach( cupom => {
      const {
              id,
              valor, 
              codigo,
              tipo,
              dataDeValidade
            } = cupom
  
      const {day, month, year } = dataDeValidade
  
      const ul = document.createElement('ul')
      const li = document.createElement('li')
      const input = document.createElement('input');
      const divTipo = document.createElement('div')
      const divCodigo = document.createElement('div')
      const divValor = document.createElement('div')
      const divValidade = document.createElement('div')

      input.addEventListener('click', () => {
        cupomTrocaSelecionado(this,codigo)
      })
      input.setAttribute('type','checkbox')
      input.setAttribute('name','cupom-troca')
      input.setAttribute('value',id)
      divTipo.innerText = `Tipo: ${tipo}` 
      divCodigo.innerText = `Código: ${codigo}`.toUpperCase() 
      divValor.innerText = `Valor: ${valor}`
      divValidade.innerText = `Validade: ${day}/${month}/${year}`
      
      li.className = 'list-group-item'
      ul.className = 'list-group'
      li.appendChild(input)
      li.appendChild(divTipo)
      li.appendChild(divCodigo)
      li.appendChild(divValor)
      li.appendChild(divValidade)
      ul.appendChild(li)
      
      tituloCuponsTroca.insertAdjacentElement('afterend', ul)
       
    });
  }
  
  function createCuponsPromocionais(cuponsPromocionais) {
    cuponsPromocionais.forEach( cupom => {
      const {
              id,
              valor, 
              codigo,
              tipo,
              dataDeValidade
            } = cupom
  
      const {day, month, year } = dataDeValidade
  
      const ul = document.createElement('ul')
      const li = document.createElement('li')
      const input = document.createElement('input');
      const divTipo = document.createElement('div')
      const divCodigo = document.createElement('div')
      const divValor = document.createElement('div')
      const divValidade = document.createElement('div')

      input.addEventListener('click', () => {
        cupomTrocaSelecionado(this,codigo)
      })
      input.setAttribute('type','radio')
      input.setAttribute('value',id)
      input.setAttribute('name','cupom-troca')
      divTipo.innerText = `Tipo: ${tipo}` 
      divCodigo.innerText = `Código: ${codigo}`.toUpperCase()
      divValor.innerText = `Valor: ${valor}`
      divValidade.innerText = `Validade: ${day}/${month}/${year}`
      
      li.className = 'list-group-item'
      ul.className = 'list-group'
      li.appendChild(input)
      li.appendChild(divTipo)
      li.appendChild(divCodigo)
      li.appendChild(divValor)
      li.appendChild(divValidade)
      ul.appendChild(li)
      
      tituloCuponsPromocionais.insertAdjacentElement('afterend', ul)
       
    });
  }  

  buildPage()
}