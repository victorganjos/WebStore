//Variável de  indice para não deixar que o lazyload repita a carga da mesma imagem
var teste = 0;
//Array com a lista de produtos
var arr = [
	{
	"idProd": 10,
	"idPrec": "151,00",
	"idPlace": 10,
	"idNome":"Jogo 1"
	 },
	 {
	"idProd": 11,
	"idPrec": "152,00",
	"idPlace": 11,
	"idNome":"Jogo 2"
	},
	 {
	"idProd": 12,
	"idPrec": "153,00",
	"idPlace": 12,
	"idNome":"Jogo 3"
	 },
	 {
	"idProd": 13,
	"idPrec": "154,00",
	"idPlace": 13,
	"idNome":"Jogo 4"
	 },
	 {
	"idProd": 14,
	"idPrec": "155,00",
	"idPlace": 14,
	"idNome":"Jogo 5"
	 },
	 {
	"idProd": 15,
	"idPrec":  "150,00",
	"idPlace": 15,
	"idNome":"Jogo 6"
	 }
	
 ]
//Função que cria os itens na página
function obterItem(id,image,nome, preco) {
	return "<div class=\"col-md-4\">"+
				"<figure class=\"card card-product\" id=\"" + id + "\">"+ 
					"<div class=\"img-wrap\">"+
						"<img src="+image+">"+
					"</div>"+
					"<div class=\"col-md-12\">" +
						"<figcaption>"+nome+"</figcaption>"+
					"</div>"+
					"<div class=\"price-wrap h5\">"+
						"<span class=\"preco\">"+preco+"</span>"+
					"</div>"+	
				"</figure>"+
			"</div>";
}

//Função de manipulação do scroll para criar itens na página
$(document).ready(function() {
	$(document).on("scroll", function(){	
		
				for (var i = 0; i <arr.length; i++) {
					if(teste<arr.length){
						// a chamada da função obterItem
						$("#listadeItens").append(obterItem(arr[i]['idPlace'],"images/"+arr[i]['idProd']+".jpg",arr[i]['idNome'],arr[i]['idPrec']));
						teste = teste+1;
			}};

	});
});

//Não remover
/*$('.btn').on('click', function(){
	var categoria = $(this).attr('data-categoria')
	
	$('.items li').each(function(){
	  if(!$(this).hasClass(categoria)){
		$(this).hide()
	  }else{
		$(this).show()
	  }
	})
  })*/


  function OrdenaJson(lista, chave, ordem) {
	return lista.sort(function(a, b) {
	  var x = a[chave]; var y = b[chave];
	  if (ordem === 'ASC' ) { return ((x < y) ? -1 : ((x > y) ? 1 : 0)); }
	  if (ordem === 'DESC') { return ((x > y) ? -1 : ((x < y) ? 1 : 0)); }
	});
  }
  function OrdenaJson(lista, chave, ordem) {
	return lista.sort(function(a, b) {
		var x = a[chave]; var y = b[chave];
		if (ordem === 'ASC' ) { return ((x < y) ? -1 : ((x > y) ? 1 : 0)); }
		if (ordem === 'DESC') { return ((x > y) ? -1 : ((x < y) ? 1 : 0)); }
	});
  }
  function listaItens(arr) {
	$('#listadeItens').html("");
	for(var ind in arr) {
		var div = "<div class=\"col-md-4\">"+
		"<figure class=\"card card-product\" id=\"" +arr[ind]['idProd'] + "\">"+ 
			"<div class=\"img-wrap\">"+
				"<img src="+arr[ind]['idPlace']+">"+
			"</div>"+
			"<div class=\"col-md-12\">" +
				"<figcaption>"+arr[ind]['idNome']+"</figcaption>"+
			"</div>"+
			"<div class=\"price-wrap h5\">"+
				"<span class=\"preco\">"+arr[ind]['idPrec']+"</span>"+
			"</div>"+	
		"</figure>"+
	"</div>";
		$('#listadeItens').append(div);
	}
  }
  
  
  function OrdenaLista(lista, chave, ordem){
	var listaAux = OrdenaJson(lista, chave, ordem);
	listaItens(listaAux);
  }
  
  