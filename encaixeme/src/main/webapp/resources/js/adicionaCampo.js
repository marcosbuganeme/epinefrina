$(function () {
	$(".adicionarCampo").click(function () {
		if(y <= 1){
			novoCampo = $(".campo:first").clone();
		    novoCampo.find("input").val("");
		    novoCampo.insertAfter(".campo:last");
			y++;
		}

		if  (".adicionarCampo" > 2){
			$(".adicionarCampo").hide();
		}
		return false
	});
});
