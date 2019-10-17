var arr = [
    {
        "idProd": 1,
        "idPrec": 100,
        "idPlace": 1,
        "nome": "acessorios"
    },
    {
        "idProd": 4,
        "idPrec": 400,
        "idPlace": 4,
        "nome": "Battlefield 4"
    },

    {
        "idProd": 3,
        "idPrec": 300,
        "idPlace": 3,
        "nome": "Call of Duty Black OPS 3"
    },
    {
        "idProd": 2,
        "idPrec": 200,
        "idPlace": 2,
        "nome": "Battlefield 1"
    },


    {
        "idProd": 5,
        "idPrec": 500,
        "idPlace": 5,
        "nome": "far cry 3"
    }

]

var busca = null;

$(document).ready(function(){
  $('#entrada').bind('input',function(){
    busca = $(this).val().toLowerCase();
    
    if(busca !== ''){
      var corresponde = false;
      var saida = Array();
      var quantidade = 0;
      for(var key in arr){
        corresponde = arr[key]['nome'].toLowerCase().indexOf(busca) >= 0;
        if(corresponde){
          saida.push(arr[key]);
          quantidade += 1;
        }
      }
      if(quantidade){
        $('#listadeItens').text('');
        for(var ind in saida){
            var div = '<div class="col-md-4"><figure class="card card-product"><div class="img-wrap"><img src="./images/'+saida[ind]['idPlace']+'.jpg"></div><div class="bottom-wrap"><a href="" class="bt btn-sm btn-primary float-right">Order now</a><div class="price-wrap h5"><span class="price-new">$"'+saida[ind]['idPrec']+'"</span></div></div></figure></div>';
            $('#listadeItens').append(div);
        }
      }
    
    }
    
  });
});