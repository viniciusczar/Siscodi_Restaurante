select d.idDelivery, d.idprodutos, p.descricao, d.qtdproduto,d.precoproduto from produtodelivery d, produtos p where d.idProdutos = p.idProdutos and idDelivery LIKE $P{pedido}