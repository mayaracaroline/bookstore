window.onload = function() {

  const selectEnderecos = document.getElementById('endereco-selecionado')
  const selectCartao1= document.getElementById('numero-cartao1')
  const selectCartao2= document.getElementById('numero-cartao2')
  const tituloCuponsPromocionais = document.getElementById('cuponsPromocionais')
  const tituloCuponsTroca = document.getElementById('cuponsTroca')
  
  async function buscarDadosUsuario() {
    let url = '/livraria/Pages/lumino/comprar'
  
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
            cuponsPromocionais, 
            cuponsTroca 
          } = dadosUsuario
  
          createEnderecosEntregaOptions(enderecosEntrega)
          createCartoesOptions1(cartoes) 
          createCartoesOptions2(cartoes) 
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
      const text = document.createTextNode(    											
        `<input type="checkbox" value="${id}" 
          onclick="cupomTrocaSelecionado(this,${valor})" 
          name="cupom-troca"
        >
        <strong>Tipo: </strong>${tipo}<br>
        <strong>Código:</strong>${codigo}<br>
        <strong>Valor:</strong>${valor}<br>
        <label>Validade:</label> ${day}/${month}/${year}<br>`)
  
      ul.className = 'list-group'
      li.className = 'list-group-item'
      li.appendChild(text)
      
      tituloCuponsTroca.insertAdjacentElement('afterbegin', li)
       
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
      const text = document.createTextNode(    											
        `<input type="checkbox" value="${id}" 
          onclick="cupomTrocaSelecionado(this,${valor})" 
          name="cupom-troca"
        >
        <strong>Tipo: </strong>${tipo}<br>
        <strong>Código:</strong>${codigo}<br>
        <strong>Valor:</strong>${valor}<br>
        <label>Validade:</label> ${day}/${month}/${year}<br>`)
  
      ul.className = 'list-group'
      li.className = 'list-group-item'
      li.innerHTML = text;
      
      tituloCuponsTroca.insertAdjacentElement('afterend', li)
       
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
      const text = document.createTextNode(    											
        `<input type="checkbox" value="${id}" 
          onclick="cupomPromocionalSelecionado(this,${valor})" 
          name="cupom-troca"
        >
        <strong>Tipo: </strong>${tipo}<br>
        <strong>Código:</strong>${codigo}<br>
        <strong>Valor:</strong>${valor}<br>
        <label>Validade:</label> ${day}/${month}/${year}<br>`)
  
      ul.className = 'list-group'
      li.className = 'list-group-item'
      li.appendChild(text)
      
      tituloCuponsPromocionais.insertAdjacentElement('afterend', li)
       
    });
  }  

  buildPage()
}