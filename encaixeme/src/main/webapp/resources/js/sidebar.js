$("#menu-toggle").click(function(e) {
    e.preventDefault();
	$("#wrapper").toggleClass("toggled");
});

$(".dropbtn").click(function(e) {
	e.preventDefault();
    var dropdowns = document.getElementsByClassName("drop");
	var i = 0;
    for (i < dropdowns.length; i++;) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
});