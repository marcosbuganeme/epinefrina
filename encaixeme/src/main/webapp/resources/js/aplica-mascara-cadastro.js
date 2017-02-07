$(document).ready(function() {
	$('#nascimento').mask("99/99/9999");

	$('#cep').mask("99.999-999");

	$('#cpf').mask("999.999.999-99");

	$('#ddd').mask("(99)");

	$("#telefone").mask("9999-9999");

	$('#cnpj').mask("99.999.999/9999-99");

	$('[data-toggle="tooltip"]').tooltip();
});